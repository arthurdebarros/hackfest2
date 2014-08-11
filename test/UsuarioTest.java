import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import models.Usuario;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import base.AbstractTest;


public class UsuarioTest extends AbstractTest{

	GenericDAO dao = new GenericDAOImpl();
	
	@Test
	public void deveSalvarUsuarioNoBD() {
		Usuario user1 = new Usuario("admin@gmail.com","1234","Admin");
		dao.persist(user1);
		assertThat(dao.findAllByClassName("Usuario").size()).isEqualTo(1);
		Usuario user2 = new Usuario("t_mcmilliam@siriusmail.com","123456","Trillian");
		dao.persist(user2);
		assertThat(dao.findAllByClassName("Usuario").size()).isEqualTo(2);
		
	}
	
}
