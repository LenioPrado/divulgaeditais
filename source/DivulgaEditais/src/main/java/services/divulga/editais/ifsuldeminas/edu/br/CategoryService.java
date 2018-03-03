package services.divulga.editais.ifsuldeminas.edu.br;

import javax.ws.rs.Path;

import beans.divulga.editais.ifsuldeminas.edu.br.Category;

@Path("/category")
public class CategoryService extends BaseService<Category> {

	public CategoryService() {
		super(Category.class);
	}
	
	protected String getDeleteErrorMessage(Exception exception) {
		if (exception.getMessage().toLowerCase().contains("notices")) {
			return "N�o � poss�vel excluir pois existem editais cadastrados com esta categoria!";
		}
		return exception.getMessage();
	}
}