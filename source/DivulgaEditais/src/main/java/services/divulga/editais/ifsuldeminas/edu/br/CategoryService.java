package services.divulga.editais.ifsuldeminas.edu.br;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.divulga.editais.ifsuldeminas.edu.br.Category;

@Path("/category")
public class CategoryService extends BaseService {

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> listAll() {
		List<Category> categoryList = null;
		try {
			TypedQuery<Category> q = getEM().createQuery("select t from Category t", Category.class);
			categoryList = q.getResultList();
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		}
		return categoryList;
    }
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Category create(Category category) {
    	EntityManager em = getEM();     	
    	try {
    		em.getTransaction().begin();    		
			em.persist(category);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		} 
		return category;
    }
}