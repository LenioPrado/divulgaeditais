package services.divulga.editais.ifsuldeminas.edu.br;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.divulga.editais.ifsuldeminas.edu.br.Notice;
import beans.divulga.editais.ifsuldeminas.edu.br.User;
import beans.divulga.editais.ifsuldeminas.edu.br.UsersNotice;
import utils.divulga.editais.ifsuldeminas.edu.br.UserUtils;

@Path("/userNotice")
public class UserNoticeService extends BaseService<UsersNotice> {

	public UserNoticeService() {
		super(UsersNotice.class);
	}
	
	@POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/subscribe")
	public Response subscribe(Notice notice) {
		UsersNotice userNotice = new UsersNotice();
		userNotice.setNotice(notice);
		userNotice.setUser(UserUtils.getUserInSession(getSession()));

		return super.edit(userNotice);
	}

	protected String getEditSuccessMessage() {
		return "Inscri��o Realizada com Sucesso!";
	}
	
	@POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/unsubscribe")
	public Response deleteSubscribed(Notice notice) {
		User user = UserUtils.getUserInSession(getSession());
		String query = "DELETE FROM UsersNotice un WHERE un.user.userId = " + user.getUserId() + 
				" AND un.notice.noticeId = " + notice.getNoticeId();

		return super.delete(query);
	}	
	
	protected String getDeleteSuccessMessage() {
		return "Inscri��o Removida com Sucesso!";
	}	
}