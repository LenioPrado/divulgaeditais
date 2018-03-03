package services.divulga.editais.ifsuldeminas.edu.br;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.divulga.editais.ifsuldeminas.edu.br.Notice;
import beans.divulga.editais.ifsuldeminas.edu.br.NoticesCategory;
import beans.divulga.editais.ifsuldeminas.edu.br.User;

@Path("/notice")
public class NoticeService extends BaseService<Notice> {

	public NoticeService() {
		super(Notice.class);
	}

	protected Notice beforeCreate(Notice notice) {
	    for (NoticesCategory noticeCategory : notice.getNoticesCategories()) {
	    	noticeCategory.setNotice(notice);
		}
    	notice.setPublishingDate(new Date());
    	//TODO: Pegar usuário logado!!!
    	User user = new User();
    	user.setUserId(1);
    	notice.setUser(user);
    	
		return notice;
	}
	
	protected Notice beforeEdit(Notice notice) {
	    for (NoticesCategory noticeCategory : notice.getNoticesCategories()) {
	    	noticeCategory.setNotice(notice);
		}
    	
		return notice;
	}
	
	protected String getDeleteErrorMessage(Exception exception) {
		if (exception.getMessage().toLowerCase().contains("users_notices")) {
			return "Não é possível excluir o edital pois existem usuários inscritos!";
		}
		return exception.getMessage();
	}
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/listNotSubscribed")
    public List<Notice> listNoticesNotSubscribed() {
		//TODO: Criar tabela para relacionar os editais ao usuário!
		// Pegar o usuário logado.
		String userId = "1";
		String query = "select t from Notice t LEFT JOIN t.usersNotices un WHERE un.user != " + userId; 
		return listFiltering(query);
    }
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/userRegisteredNotices/{userId}")
    public List<Notice> listUserRegisteredNotices(@PathParam("userId") int userId) {
		//TODO: Criar tabela para relacionar os editais ao usuário!
		String query = "select t from Notice t where t.user = " + userId; 
		return listFiltering(query);
    }
	
	@POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/subscribe")
	public Response subscribe(Notice notice) {
		String query = "delete from UsersNotice t WHERE t.notice == " + notice.getNoticeId();
		TypedQuery<Notice> q = getEM().createQuery(query, classType);
		int result = q.executeUpdate();
		
		return Response.ok("{\"message\": \"Inscrição Excluída com Sucesso!\"}", MediaType.TEXT_PLAIN).build();
	}	
	
	@POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/deleteSubscribed")
	public Response deleteSubscribed(Notice notice) {
		String query = "delete from UsersNotice t WHERE t.notice == " + notice.getNoticeId();
		TypedQuery<Notice> q = getEM().createQuery(query, classType);
		int result = q.executeUpdate();
		
		return Response.ok("{\"message\": \"Inscrição Excluída com Sucesso!\"}", MediaType.TEXT_PLAIN).build();
	}
}