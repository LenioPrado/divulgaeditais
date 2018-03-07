package services.divulga.editais.ifsuldeminas.edu.br;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import beans.divulga.editais.ifsuldeminas.edu.br.User;
import utils.divulga.editais.ifsuldeminas.edu.br.PasswordUtils;

@Provider
@PreMatching
public class Authentication implements ContainerRequestFilter {
	
	private boolean isPathOrMethodAllowed(String path, String method) {
		boolean allowed = false;
		
		if(path.equals("pages/index.html") || 
				path.contains("user/login")) {
			allowed = true;
		}
		
		if(method.equals("GET") && (path.equals("application.wadl") || path.equals("application.wadl/xsd0.xsd"))) {
			allowed = true;
		}
		
		return allowed;
	}

	@Override
	public void filter(ContainerRequestContext containerRequest) throws WebApplicationException {
		//
		System.out.println("ENTROU NO MÉTODO FILTER");
		
		//GET, POST, PUT, DELETE, ...
        String method = containerRequest.getMethod();
        // myresource/get/56bCA for example
        String path = containerRequest.getUriInfo().getPath(true);
 
        System.out.println(String.format("Path: %s -- Method: %s ", path, method));
        
        //We do allow wadl to be retrieve
        if(isPathOrMethodAllowed(path, method)){
            return;
        }
 
        //Get the authentification passed in HTTP headers parameters
        String auth = containerRequest.getHeaderString("authorization");
 
        //If the user does not have the right (does not provide any HTTP Basic Auth)
        if(auth == null){
            throw new WebApplicationException(Status.UNAUTHORIZED);
        }
 
        //lap : loginAndPassword
        String[] lap = PasswordUtils.decode(auth);
 
        //If login or password fail
        if(lap == null || lap.length != 2){
            throw new WebApplicationException(Status.UNAUTHORIZED);
        }
 
        //DO YOUR DATABASE CHECK HERE (replace that line behind)...
        User authentificationResult =  new UserService().getUserByEmailAndPassword(lap[0], lap[1]);
 
        //Our system refuse login and password
        if(authentificationResult == null){
            throw new WebApplicationException(Status.UNAUTHORIZED);
        }
 
        //TODO : HERE YOU SHOULD ADD PARAMETER TO REQUEST, TO REMEMBER USER ON YOUR REST SERVICE...
 
//        return;		
	}
}