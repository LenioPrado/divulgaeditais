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

import beans.divulga.editais.ifsuldeminas.edu.br.CompanyType;

@Path("/companyTypes")
public class CompanyTypesService extends BaseService {

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CompanyType> listAll() {
		List<CompanyType> companyTypeList = null;
		try {
			TypedQuery<CompanyType> q = getEM().createQuery("select t from CompanyType t", CompanyType.class);
			companyTypeList = q.getResultList();
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		}
		return companyTypeList;
    }
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create")
    public CompanyType create(CompanyType companyType) {
    	EntityManager em = getEM();     	
    	try {
    		beginTransaction();    		
			em.persist(companyType);
			commitTransaction();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		return companyType;
    }
}