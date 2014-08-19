import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Evento;
import models.Participacao;
import models.Tema;
import models.exceptions.EventoInvalidoException;
import models.exceptions.PessoaInvalidaException;

import org.junit.Before;
import org.junit.Test;

public class ParticipacoesTest {

	private Evento evento;
	private List<Tema> temas;
	
	@Before
	public void setUp(){
		temas = new ArrayList<Tema>();
		temas.add(Tema.ARDUINO);
		Local = new Local("Centro de Convenções", "Abaixo do  hotel garden...", 40);
		try {
			evento = new Evento("Python na cabeça", "Vamos programar em Python!", new Date(), temas);
		} catch (EventoInvalidoException e) {
			fail();
		}
	}
	
	@Test
	public void deveCriarUmParticipante() {
		try {
			new Participacao("João José da Silva", "joao_jose@mail.com", evento);
		} catch (PessoaInvalidaException e) {
			fail();
		}
	}

	@Test
	public void deveOcorrerException() {
		try {
			new Participacao("João José da Silva Maria da Penha do Ultimo Socorro Pereira Lima Roberto", "joao_jose@mail.com", evento);
			fail();
		} catch (PessoaInvalidaException e) {
			assertEquals("Nome longo", e.getMessage());
		}
		try {
			new Participacao("João José da Silva", "joao_jose_da_silva_maria_da_penha_do_ultimo_socorro_pereira_lima@mail.com", evento);
			fail();
		} catch (PessoaInvalidaException e) {
			assertEquals("Email longo", e.getMessage());
		}
		try {
			new Participacao(null, "joao_jose@mail.com", evento);
			fail();
		} catch (PessoaInvalidaException e) {
			assertEquals("Parametro nulo", e.getMessage());
		}
		try {
			new Participacao("João José da Silva", null, evento);
			fail();
		} catch (PessoaInvalidaException e) {
			assertEquals("Parametro nulo", e.getMessage());
		}
		try {
			new Participacao("João José da Silva", null, null);
			fail();
		} catch (PessoaInvalidaException e) {
			assertEquals("Parametro nulo", e.getMessage());
		}
		try {
			new Participacao("João José da Silva", "joao_jose_mail.com", evento);
			fail();
		} catch (PessoaInvalidaException e) {
			assertEquals("Email inválido", e.getMessage());
		}
	}
}
