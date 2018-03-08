package services.divulga.editais.ifsuldeminas.edu.br;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.divulga.editais.ifsuldeminas.edu.br.Category;
import beans.divulga.editais.ifsuldeminas.edu.br.User;
import utils.divulga.editais.ifsuldeminas.edu.br.UserUtils;

@Path("/category")
public class CategoryService extends BaseService<Category> {

	public CategoryService() {
		super(Category.class);
	}
	
	protected String getDeleteErrorMessage(Exception exception) {
		if (exception.getMessage().toLowerCase().contains("notices")) {
			return "Não é possível excluir pois existem editais cadastrados com esta categoria!";
		}
		return exception.getMessage();
	}
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/listNotSubscribed")
    public List<Category> listCategoriesNotSubscribed() {
		return listCategories("NOT IN");
    }
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/listSubscribed")
    public List<Category> listCategoriesRegisteredByUser() {
		return listCategories("IN");
    }

    private List<Category> listCategories(String queryPart) {
		User user = UserUtils.getUserInSession(getSession());
		String query = "select t from Category t WHERE t.categoryId " + queryPart +
				" (select uc.category.categoryId from UsersCategory uc WHERE uc.user.userId = "+ user.getUserId() +")"; 
		return listFiltering(query);
    }
}