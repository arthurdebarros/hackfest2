

import static org.junit.Assert.*;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


import models.Local;
import models.exceptions.LocalInvalidoException;

import org.junit.Before;
import org.junit.Test;

public class LocalTest {

	
	@Before
	public void setUp(){
	}
	
	@Test
	public void deveCriarUmLocal() {

		try {
			new Local("Centro de Convenções", "Abaixo do  hotel garden...", 40);
		} catch (LocalInvalidoException e) {
			fail();
		}
	}
	
	@Test
	public void deveDarExceptionPorParametroNuloNome() {
		
		try {
			new Local(null,
					"Abaixo do  hotel garden...",
					40);
			fail();
		} catch (LocalInvalidoException e) {
			assertEquals("Parametro nulo", e.getMessage());
		}
	}
	
	@Test
	public void deveDarExceptionPorParametroNuloReferencia() {
		try {
			new Local("Centro de Convenções",
					null,
					40);
			fail();
		} catch (LocalInvalidoException e) {
			assertEquals("Parametro nulo", e.getMessage());
		}
	}
	
	@Test
	public void deveDarExceptionPorParametroNuloCapacidade() {
		try {
			new Local("Centro de Convenções",
					"Abaixo do  hotel garden...",
					null);
			fail();
			
		} catch (LocalInvalidoException e) {
			System.out.println(e.getMessage());
			assertEquals("Parametro nulo", e.getMessage());
		}

	}
}
