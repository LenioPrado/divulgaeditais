package services.divulga.editais.ifsuldeminas.edu.br;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.hibernate.exception.ConstraintViolationException;

import utils.divulga.editais.ifsuldeminas.edu.br.EMFactory;

public class BaseService<T> {
	
	final Class<T> classType;
	
	public BaseService(Class<T> classType){
		this.classType = classType;
	}
	
	protected EntityManager getEM() {
		return EMFactory.getInstance().getEntityManager();
	}
	
	private ObjectWriter getObjectWriter() {
		return new ObjectMapper().writer().withDefaultPrettyPrinter();
	}
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<T> listAll() {
		return listFiltering(getBaseQuery());
    }

    protected List<T> listFiltering(String query) {
		List<T> entityList = null;
		try {
			TypedQuery<T> q = getEM().createQuery(query, classType);
			entityList = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			throw e;
		}
		return entityList;
    }
	
	private String getBaseQuery() {
		return "select t from "+ getClassName() +" t";
	}
	
	private String getClassName() {
		return this.classType.getSimpleName();
	}
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response create(T entity) {
		String json="";
		EntityManager em = getEM(); 
		
		beforeCreate(entity);
		
    	try {
    		em.getTransaction().begin();    		
			em.persist(entity);
			em.getTransaction().commit();			
			json = getObjectWriter().writeValueAsString(entity);
		} catch (ConstraintViolationException | PersistenceException e) {
			e.printStackTrace();
			String message = getCreateErrorMessage(getCauseError(e));
			return Response.serverError().entity(message).build();
		} catch (JsonGenerationException | JsonMappingException e) {
			e.printStackTrace();
			String message = getCreateErrorMessage(getCauseError(e));
			return Response.serverError().entity(message).build();
		} catch (IOException e) {
			e.printStackTrace();
			String message = getCreateErrorMessage(getCauseError(e));
			return Response.serverError().entity(message).build();
		} catch (Exception e) {
			e.printStackTrace();
			String message = getCreateErrorMessage(getCauseError(e));
			return Response.serverError().entity(message).build();
		} 
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }	
	
	@POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete")
    public Response delete(T entity) {
		EntityManager em = getEM(); 

		beforeDelete(entity);
		
    	try {
    		em.getTransaction().begin();
			em.remove(em.contains(entity) ? entity : em.merge(entity));
			em.getTransaction().commit();			
		} catch (ConstraintViolationException | PersistenceException e) {
			e.printStackTrace();
			String message = getDeleteErrorMessage(getCauseError(e));
			return Response.serverError().entity(message).build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
		return Response.ok("{\"message\": \"Registro Excluído com Sucesso!\"}", MediaType.TEXT_PLAIN).build();
    }

	@POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/edit")
    public Response edit(T entity) {
		EntityManager em = getEM(); 
		
		beforeEdit(entity);
		
    	try {
    		em.getTransaction().begin();
    		em.merge(entity);
			em.getTransaction().commit();
		} catch (ConstraintViolationException | PersistenceException e) {
			e.printStackTrace();
			String message = getDeleteErrorMessage(getCauseError(e));
			return Response.serverError().entity(message).build();
		} catch (Exception e) {
			e.printStackTrace();
			String message = getCreateErrorMessage(getCauseError(e));
			return Response.serverError().entity(message).build();
		} 
		return Response.ok("{\"message\": \"Registro Editado com Sucesso!\"}", MediaType.TEXT_PLAIN).build();
    }	
	
	protected T beforeCreate(T entity) {
		return entity;
	}
	
	protected T beforeEdit(T entity) {
		return entity;
	}
	
	protected T beforeDelete(T entity) {
		return entity;
	}
	
	private Exception getCauseError(Exception e) {
		if(e.getCause() instanceof ConstraintViolationException) {
			ConstraintViolationException cve = (ConstraintViolationException)e.getCause();
			return cve.getSQLException();
		} else if(e.getCause() instanceof PersistenceException) {
			PersistenceException pe = (PersistenceException)e.getCause();
			return getCauseError(pe);
		}
		return e;
	}
	
	protected String getCreateErrorMessage(Exception e) {
		return e.getMessage();
	}
	
	protected String getDeleteErrorMessage(Exception e) {
		return e.getMessage();
	}
}
