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
import utils.divulga.editais.ifsuldeminas.edu.br.ProjectLogger;

@Path("/user")
public class UserService extends BaseService {
	
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public User login(User user) {
		ProjectLogger.log.info("login ");
		String pageNav = "";
//		User user = null;
//		try {
//			user = _userDelegate.login(_email, _pwd);
//		} catch (UserException e) {
//			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOGIN, "msg",FacesMessage.SEVERITY_ERROR);
//			e.printStackTrace();
//		}
//		if (user != null) {
//			UserAccessUtils.getInstance().removeUserRolePagesFromSession();
//			BeanUtil.invalidateSession();
//			UserAccessUtils.getInstance().putUserInSession(user);
//			pageNav = MsgConstants.SELECT_ROLE_PAGE;
//		} else {
//			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOGIN, "msg",FacesMessage.SEVERITY_ERROR);
//		}
		ProjectLogger.log.info("validateLogin " + pageNav);
		//return pageNav;    	
    	
    	String sql = "SELECT t FROM User t WHERE t.email = " + user.getEmail();
    	TypedQuery<User> query = getEM().createQuery(sql, User.class);
    	List<User> list = query.getResultList();
        return list.get(0);
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create")
    public User create(User user) {
    	try {
    		EntityManager em = getEM();
    		beginTransaction();    		
			em.persist(user);
			commitTransaction();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
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
