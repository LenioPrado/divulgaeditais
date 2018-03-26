package services.divulga.editais.ifsuldeminas.edu.br;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.divulga.editais.ifsuldeminas.edu.br.Notice;
import beans.divulga.editais.ifsuldeminas.edu.br.NoticesCategory;
import beans.divulga.editais.ifsuldeminas.edu.br.User;
import utils.divulga.editais.ifsuldeminas.edu.br.UserUtils;

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
    	User user = UserUtils.getUserInSession(getSession());
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
		return listNotices("NOT IN");
    }
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/listSubscribed/{userId}")
    public List<Notice> listNoticesRegisteredByUser(int userId) {
		return listNotices("IN");
    }

    private List<Notice> listNotices(String queryPart) {
		User user = UserUtils.getUserInSession(getSession());
		String query = "select t from Notice t WHERE t.noticeId " + queryPart +
				" (select un.notice.noticeId from UsersNotice un where un.user.userId = "+ user.getUserId() +")"; 
		return listFiltering(query);
    }	
}