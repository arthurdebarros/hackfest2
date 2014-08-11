import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.callAction;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.status;

import org.junit.Test;

import play.mvc.Http;
import play.mvc.Result;
import base.AbstractTest;


import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import models.Usuario;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.api.mvc.RequestHeader;

import org.h2.engine.Session;
import org.junit.Before;
import org.junit.Test;

import play.mvc.Http.Request;
import play.mvc.Controller;
import play.mvc.Http;
import controllers.Registro;

public class RegistroTest extends AbstractTest{
	
	GenericDAO dao = new GenericDAOImpl();
	
	Result result;
	
	@Test
	public void testeChamarRegistro() {
		result = callAction(controllers.routes.ref.Registro.show(),
				fakeRequest());
		assertThat(status(result)).isEqualTo(Http.Status.OK);
		
	} 
	
	@Test
	public void testeDeCadastrarUsuario() {
		Controller.session().clear();
		String email="president@siriusmail.com";
		Usuario novouser = new Usuario(email, "123456", "Zaphod Beeblebrox");
		Registro.registrar(novouser);
		assertTrue(dao.findByAttributeName("Usuario", "email", email).size() == 1);
		//assertEquals("president@siriusmail.com", dao.findByAttributeName("Usuario", "email", "president@siriusmail.com").get(0));
					
				
		
	}

}
