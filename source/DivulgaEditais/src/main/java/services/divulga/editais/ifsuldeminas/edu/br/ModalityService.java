package services.divulga.editais.ifsuldeminas.edu.br;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.divulga.editais.ifsuldeminas.edu.br.Modality;
import beans.divulga.editais.ifsuldeminas.edu.br.Notice;

@Path("/modality")
public class ModalityService extends BaseService {

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Modality> findAll() {
        Query q = getEM().createQuery("select t from Modality t");
        List<Modality> todoList = q.getResultList();
        return todoList;
    }
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Modality create(Modality modality) {
    	System.out.println(modality);    	
    	EntityManager em = getEM();   
    	
    	try {
    		em.getTransaction().begin();
			em.persist(modality);
			em.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return modality;
    }
		
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Modality login(Modality modality) {
    	System.out.println("Output from Server .... \n");
    	System.out.println(modality);    	
    	Query q = getEM().createQuery("select t from Modality t");
    	List<Modality> todoList = q.getResultList();
        return todoList.get(0);
    }
}