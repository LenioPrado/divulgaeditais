package services.divulga.editais.ifsuldeminas.edu.br;

import java.util.Date;
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
import beans.divulga.editais.ifsuldeminas.edu.br.User;

@Path("/notice")
public class NoticeService extends BaseService {

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Notice> listAll() {
		TypedQuery<Notice> query = getEM().createQuery("select t from Notice t", Notice.class);
        List<Notice> list = query.getResultList();
        return list;
    }
		
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Notice create(Notice notice) {
    	notice.setPublishingDate(new Date());
    	notice.setFileName("arquivo.txt");
    	//TODO: Pegar usuário logado!!!
    	User user = new User();
    	user.setUserId(1);
    	notice.setUser(user);
    	EntityManager em = getEM();   
    	
    	try {
    		em.getTransaction().begin();    		
			em.persist(notice);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw e;
		}
		return notice;
    }
}