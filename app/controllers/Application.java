package controllers;


import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;

import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {
	
	private static GenericDAO dao = new GenericDAOImpl();

	@Transactional
    public static Result index(){
        return ok(index.render());
    }

	public static GenericDAO getDao(){
		return dao;
	}



}

