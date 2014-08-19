package controllers;

import static play.data.Form.form;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Evento;
import models.EventoComparator;
import models.Local;
import models.Participacao;
import models.Tema;
import models.Usuario;
import models.dao.GenericDAOImpl;
import models.exceptions.EventoInvalidoException;
import models.exceptions.LocalInvalidoException;
import models.exceptions.PessoaInvalidaException;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;
import views.html.modalCriacaoDeEvento;
import views.html.modalCadastroDeLocal;

import com.fasterxml.jackson.databind.ObjectMapper;

import controllers.Login;

public class EventoController extends Controller {

	private final static Form<Evento> EVENTO_FORM = form(Evento.class);
	private final static Form<Participacao> participanteForm = form(Participacao.class);
	private final static Form<Local> LOCAL_FORM = form(Local.class);

	@Transactional
	public static Result eventosPorTema(int id) throws PessoaInvalidaException, EventoInvalidoException{
	
		List<Evento> todosEventos = Application.getDao().findAllByClassName("Evento");
		
		List<Evento> eventosRequeridos = new ArrayList<Evento>();
		
		for (Evento ev : todosEventos) {
			if (ev.getTemas().contains(Tema.values()[(int) id])){
				eventosRequeridos.add(ev);
			}
		}

		Collections.sort(eventosRequeridos, new EventoComparator());
		
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try {
			json = mapper.writeValueAsString(eventosRequeridos);
		} catch (Exception e) {
			return badRequest();
		}
		
		return ok(json);
	}
	
	@Transactional
	public static Result novo() throws PessoaInvalidaException, EventoInvalidoException{
		Form<Evento> eventoFormRequest = EVENTO_FORM.bindFromRequest();

		if (EVENTO_FORM.hasErrors()) {
			return badRequest();
		} else {
			Evento novoEvento = eventoFormRequest.get();
			Application.getDao().persist(novoEvento);
			Application.getDao().merge(novoEvento);
			Application.getDao().flush();
			return redirect(controllers.routes.Application.index());
		}
	}
	
	@Transactional
	public static Result participar(long idEvento) throws PessoaInvalidaException, EventoInvalidoException{
		//deprecated... 
		//Form<Participante> participanteFormRequest = participanteForm.bindFromRequest();
		
		//if (participanteForm.hasErrors()) {
		//	return badRequest();
		//} else {
				Long idDoUserAtual = Long.valueOf(session().get("userid")).longValue();
				Usuario usuarioAtualmenteLogado = Application.getDao().findByEntityId(Usuario.class, idDoUserAtual);
				Evento evento = Application.getDao().findByEntityId(Evento.class, idEvento);
				
				Participacao novoParticipante = new Participacao(usuarioAtualmenteLogado,evento);	
				
				Application.getDao().persist(novoParticipante);
				Application.getDao().merge(novoParticipante);
				Application.getDao().flush();
				return redirect(controllers.routes.Application.index());
			
	}
	
	@Transactional
	public static Result getCadastradorDeLocal() {
		
		return ok(modalCadastroDeLocal.render(LOCAL_FORM));
	}

	
//	@Transactional
//	public static Result cadastrarLocal() throws LocalInvalidoException {
//
//		Form<Local> localFormRequest = LOCAL_FORM.bindFromRequest();
//		
//		if (LOCAL_FORM.hasErrors()) {
//			return badRequest();
//		} else {
//			Local novoLocal = localFormRequest.get();
//			Application.getDao().persist(novoLocal);
//			Application.getDao().merge(novoLocal);
//			Application.getDao().flush();
//			return redirect(controllers.routes.Application.index());
//		}
//		
//	}
	
	@Transactional
	public static Result cadastrarLocal() {

		Form<Local> localFormRequest = LOCAL_FORM.bindFromRequest();

		if (localFormRequest.hasErrors()) {
			return badRequest(modalCadastroDeLocal.render(localFormRequest));
		} else {
			Local local = localFormRequest.get();
			
			if (Application.getDao().findAllByClassName("Local").contains(local)) {
				flash("success", "Local ja cadastrado");
				return badRequest(modalCadastroDeLocal.render(localFormRequest));
			} else {
				Application.getDao().persist(local);
				Application.getDao().merge(local);
				Application.getDao().flush();
			}
			return redirect(routes.Application.index());
		}
	}
	
	@Transactional
	public static List<Local> getLocais() {
		return Application.getDao().findAllByClassName("Local");
	}

}
