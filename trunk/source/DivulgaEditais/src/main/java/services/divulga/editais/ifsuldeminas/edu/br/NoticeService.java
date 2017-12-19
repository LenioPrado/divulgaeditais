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

import beans.divulga.editais.ifsuldeminas.edu.br.Notice;

@Path("/notice")
public class NoticeService extends BaseService {

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Notice> findAll() {
		TypedQuery<Notice> query = getEM().createQuery("select t from Notice t", Notice.class);
        List<Notice> todoList = query.getResultList();
        return todoList;
    }
		
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Notice create(Notice notice) {
    	EntityManager em = getEM();   
    	
    	try {
    		beginTransaction();
			em.persist(notice);
			commitTransaction();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return notice;
    }
}