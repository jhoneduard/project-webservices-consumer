/**
 * 
 */
package com.eduard.projectwebservices.client;

import java.time.LocalDateTime;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.eduard.projectwebservices.dto.EmpleadoDTO;
import org.glassfish.jersey.client.ClientConfig;

/**
 * @author EduardTriana Clase cliente que permtie consumir el webservice de
 *         empleados
 */
public class EmpleadoWSClient {
	public static void main(String[] args) {
		// ::::::::::: GET ::::::::
		/*
		 * //Client es la clase principal de jersey para indicar que vamos a conectarnos
		 * a una ubicacion remota o url... //Proviene de la clase javax.ws.rs //Ok con
		 * esto vamos a indicar que vamos a crear un cliente que nos permita consumir un
		 * servicio rest Client client = ClientBuilder.newClient(); //WebTarget permite
		 * indicar cual es el endpoint o url a la cual vamos a comunicarnos //con el
		 * client estamos indicando a la url de origen que vamos a indicarnos, solamente
		 * el contexto //luego indicamos el nombre del webservices (path) WebTarget
		 * webTarget =
		 * client.target("http://localhost:8090/proyect-webservices/apis/empleadosWS").
		 * path("consultarEmpleadoPorNumeroEmpleado").path("987654321");
		 * //InvocationBuilder va permitir el tipo de peticion que voy hacer, en ese
		 * caso es con JSON Invocation.Builder invocationBuilder =
		 * webTarget.request(MediaType.APPLICATION_JSON); //Response es la respuesta
		 * obtenida en el webservices, en ese caso sera con el get(); Response response
		 * = invocationBuilder.get();
		 * 
		 * if(response.getStatus() == 204) {
		 * System.out.println("No se encontro el empleado con el numero de cliente."); }
		 * 
		 * if(response.getStatus() == 200) {
		 * 
		 * //Lllamo el dto de empleado, aqui vamos a leer o buscando el entity
		 * EmpleadoDTO EmpleadoDTO employeeDTO = response.readEntity(EmpleadoDTO.class);
		 * 
		 * System.out.println("Nombre Empleado : "+employeeDTO.getNombre());
		 * System.out.println("Fecha de creacion : "+employeeDTO.getFechaCreacion()); }
		 */

		// ::::::::::: POST ::::::::

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8090/proyect-webservices/apis/empleadosWS")
				.path("guardarEmpleado");
		EmpleadoDTO emp = new EmpleadoDTO();
		emp.setNumeroEmpleado("6789");
		emp.setNombre("Luis JEsus");
		emp.setPrimerApellido("Lopez");
		emp.setSegundoApellido("Romero");
		emp.setEdad(50);
		emp.setFechaCreacion(LocalDateTime.now());
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.post(Entity.entity(emp, MediaType.APPLICATION_JSON));

		if (response.getStatus() == 400) {
		String error = 	response.readEntity(String.class);
		System.out.println("error : "+error);
		}

		if (response.getStatus() == 200) {
			// Realizamos el parseo con response.readEntity para poder mapear cada propiedad
			// JSON con su respectiva propiedad de la clase java
			EmpleadoDTO empleadoDTO = response.readEntity(EmpleadoDTO.class);
			System.out.println("Respuesta : " + response.getStatus());
			System.out.println("Nombre : " + empleadoDTO.getNombre());
			System.out.println("Fecha de creacion : " + empleadoDTO.getFechaCreacion());
		}

	}
}
