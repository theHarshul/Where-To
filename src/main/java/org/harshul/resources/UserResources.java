package org.harshul.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.harshul.model.User;
import org.harshul.services.UserService;

@Path("/user")

public class UserResources {
	
	
	
	UserService userService = new UserService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/authenticate")
	public Response getUser(@QueryParam("email") String email,@QueryParam("password") String password,@Context UriInfo uriInfo){
		boolean exists = userService.getUser(email, password);
		String ex = String.valueOf(exists);
		
		URI uri = uriInfo.getAbsolutePathBuilder().path(ex).build();
		
		if(exists){
		
			return Response.ok(uri)
					.entity(ex)
					.build();
		}
		
		return Response.status(Response.Status.BAD_REQUEST).entity(ex).build();
	}
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User addUser(User user){
		return userService.addUser(user);
	}

}
