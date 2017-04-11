package com.accenture.pota.sdk.http.rest;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/echoservice")
@Stateless
public class EchoServiceRest {
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
		System.out.println("Hello------------------------------");
		String output = "Jersey say : " + msg;
		return Response.status(200).entity(output).build();
	}
}
