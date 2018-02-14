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

import beans.divulga.editais.ifsuldeminas.edu.br.Modality;

@Path("/modality")
public class ModalityService extends BaseService {

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Modality> listAll() {
		List<Modality> modalityList = null;
		try {
			TypedQuery<Modality> q = getEM().createQuery("select t from Modality t", Modality.class);
			modalityList = q.getResultList();
			return modalityList;
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return modalityList;
    }
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Modality create(Modality modality) {
    	EntityManager em = getEM();     	
    	try {
    		beginTransaction();    		
			em.persist(modality);
			commitTransaction();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		return modality;
    }
}