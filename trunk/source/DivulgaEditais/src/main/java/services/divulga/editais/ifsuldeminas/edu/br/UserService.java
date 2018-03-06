package services.divulga.editais.ifsuldeminas.edu.br;

import java.io.IOException;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.divulga.editais.ifsuldeminas.edu.br.User;
import utils.divulga.editais.ifsuldeminas.edu.br.PasswordUtils;
import utils.divulga.editais.ifsuldeminas.edu.br.ProjectLogger;

@WebService
@Path("/user")
public class UserService extends BaseService<User> {
	
	@Context
    private HttpServletRequest request;	
	
	private final String userKey = "user";
	
	public UserService() {
		super(User.class);
	}
	
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login/{userEmail}/{userPassword}")
    public Response login(@PathParam("userEmail") String userEmail, @PathParam("userPassword") String userPassword) {
		ProjectLogger.log.info("login ");
		User user = get("SELECT t FROM User t WHERE t.email = '" + userEmail + "'");
		
		if (user != null) {
			user = PasswordUtils.authenticateUser(user, userPassword);
			
			if(user != null) {				
				ProjectLogger.log.info("user logged ");
				request.setAttribute(userKey, user);
				String json = "";
				try {
					json = getJsonFormattedObject(user);
				} catch (IOException e) {
					e.printStackTrace();
					return Response.serverError().entity(e.getMessage()).build();	
				}
				return Response.ok().entity(json).build();
			} else {
				// Senha inválida
				ProjectLogger.log.info("invalid password ");
				request.removeAttribute(userKey);
				String json = getJsonFormattedMessage("Os dados de acesso informados estão incorretos!");
				return Response.ok().entity(json).build();
			}
		} else {
			// Conta inexistente
			ProjectLogger.log.info("account not found ");
			request.removeAttribute(userKey);
			String json = getJsonFormattedMessage("Não há uma conta associada ao e-mail informado!");
			return Response.ok().entity(json).build();
		}
    }
    
    public User getUserByEmailAndPassword(String userEmail, String password ) {
    	User user = get("SELECT t FROM User t WHERE t.email = '" + userEmail + "'");
    	return PasswordUtils.authenticateUser(user, password);
    }
}
