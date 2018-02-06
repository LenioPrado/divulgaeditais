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

import beans.divulga.editais.ifsuldeminas.edu.br.User;

@Path("/user")
public class UserService extends BaseService {
	
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public User login(User user) {
    	TypedQuery<User> query = getEM().createQuery("SELECT t FROM User t", User.class);
    	List<User> list = query.getResultList();
        return list.get(0);
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create")
    public User create(User user) {
    	EntityManager em = getEM();     	
    	try {
    		beginTransaction();    		
			em.persist(user);
			commitTransaction();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		return user;
    }    
    
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> listAll() {
		TypedQuery<User> query = getEM().createQuery("select t from User t", User.class);
        List<User> list = query.getResultList();
        return list;
    }
}
