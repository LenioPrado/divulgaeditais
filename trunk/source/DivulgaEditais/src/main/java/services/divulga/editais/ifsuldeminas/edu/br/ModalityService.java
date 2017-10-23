package services.divulga.editais.ifsuldeminas.edu.br;

import java.util.List;

import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.divulga.editais.ifsuldeminas.edu.br.Modality;

@Path("/modality")
public class ModalityService extends BaseService {

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Modality> findAll() {
        Query q = getEM().createQuery("select t from Modality t");
        List<Modality> todoList = q.getResultList();
        return todoList;
    }
		
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Modality read(@PathParam("id") long modalityId) {
    	Query q = getEM().createQuery("select t from Modality t where t.modalityId = " + modalityId);
    	List<Modality> todoList = q.getResultList();
        return todoList.get(0);
    }
}