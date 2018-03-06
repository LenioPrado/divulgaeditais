package authentication.utils.divulga.editais.ifsuldeminas.edu.br;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

import beans.divulga.editais.ifsuldeminas.edu.br.User;
import services.divulga.editais.ifsuldeminas.edu.br.UserService;
import utils.divulga.editais.ifsuldeminas.edu.br.PasswordUtils;
/**
 * Jersey HTTP Basic Auth filter
 * @author Deisss (LGPLv3)
 */
public class Authentication implements ContainerRequestFilter {
    /**
     * Apply the filter : check input request, validate or not with user auth
     * @param containerRequest The request from Tomcat server
     */
    @Override
    public ContainerRequest filter(ContainerRequest containerRequest) throws WebApplicationException {
        //GET, POST, PUT, DELETE, ...
        String method = containerRequest.getMethod();
        // myresource/get/56bCA for example
        String path = containerRequest.getPath(true);
 
        //We do allow wadl to be retrieve
        if(method.equals("GET") && (path.equals("application.wadl") || path.equals("application.wadl/xsd0.xsd"))){
            return containerRequest;
        }
 
        //Get the authentification passed in HTTP headers parameters
        String auth = containerRequest.getHeaderValue("authorization");
 
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
 
        return containerRequest;
    }
}