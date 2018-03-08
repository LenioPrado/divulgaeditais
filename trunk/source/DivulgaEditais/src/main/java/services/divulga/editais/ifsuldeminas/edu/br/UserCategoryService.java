package services.divulga.editais.ifsuldeminas.edu.br;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.divulga.editais.ifsuldeminas.edu.br.Category;
import beans.divulga.editais.ifsuldeminas.edu.br.User;
import beans.divulga.editais.ifsuldeminas.edu.br.UsersCategory;
import utils.divulga.editais.ifsuldeminas.edu.br.UserUtils;

@Path("/userCategory")
public class UserCategoryService extends BaseService<UsersCategory> {

	public UserCategoryService() {
		super(UsersCategory.class);
	}

	@POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/subscribe")
	public Response subscribe(Category category) {
		UsersCategory userCategory = new UsersCategory();
		userCategory.setCategory(category);
		userCategory.setUser(UserUtils.getUserInSession(getSession()));

		return super.edit(userCategory);
	}

	protected String getEditSuccessMessage() {
		return "Inscrição Realizada com Sucesso!";
	}	
	
	@POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/unsubscribe")
	public Response deleteSubscribed(Category category) {
		User user = UserUtils.getUserInSession(getSession());
		String query = "DELETE FROM UsersCategory uc WHERE uc.user.userId = " + user.getUserId() + 
				" AND uc.category.categoryId = " + category.getCategoryId();

		return super.delete(query);
	}

	protected String getDeleteSuccessMessage() {
		return "Inscrição Removida com Sucesso!";
	}	
}
