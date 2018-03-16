package services.divulga.editais.ifsuldeminas.edu.br;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import beans.divulga.editais.ifsuldeminas.edu.br.User;
import utils.divulga.editais.ifsuldeminas.edu.br.UserUtils;

@Provider
@PreMatching
public class Authentication implements ContainerRequestFilter {
	
	@Context
    private HttpServletRequest servletRequest;
	
	private boolean isPathOrMethodAllowed(String path, String method) {
		boolean allowed = false;
		
		if(path.equals("pages/index.html") || 
				path.contains("user/login") || 
				path.contains("user/create")) {
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
		
        String method = containerRequest.getMethod();
        String path = containerRequest.getUriInfo().getPath(true);
        
        System.out.println(String.format("Path: %s -- Method: %s ", path, method));
        
        if(isPathOrMethodAllowed(path, method)){
        	System.out.println("Caminho ou método permitido");
            return;
        } 
		
		HttpSession session = servletRequest.getSession(false);
		 
        if (session == null) {
        	System.out.println("Sessão nula");
        	throw new WebApplicationException(Status.UNAUTHORIZED);
        } else {
        	User user = UserUtils.getUserInSession(session);
        	if(user == null) {
        		System.out.println("Usuário nulo");
        		throw new WebApplicationException(Status.UNAUTHORIZED);
        	}
        }
	}
}