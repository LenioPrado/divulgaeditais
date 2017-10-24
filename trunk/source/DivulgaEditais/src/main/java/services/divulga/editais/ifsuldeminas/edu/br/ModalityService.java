package services.divulga.editais.ifsuldeminas.edu.br;

import java.util.List;

import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;

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