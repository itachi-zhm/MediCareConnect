package servlets;

import java.io.IOException;

import beans.medecin;
import beans.utilisateur;
import beans.patient;

import dao.dao_factory;
import dao.utilisateur_dao;
import dao.medecin_dao;
import dao.patient_dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class inscription
 */

public class inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private utilisateur_dao utilisateur_dao;

    public void init() throws ServletException {
    	dao_factory dao_Factory = dao_factory.getInstance();
        this.utilisateur_dao = dao_Factory.get_utilisateur_dao();
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public inscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Afficher le formulaire d'inscription
        request.getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer les données du formulaire
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String numTel = request.getParameter("numTel");
        String type = request.getParameter("type"); // patient ou medecin
        String sexe = request.getParameter("sexe");
       

        // Créer un objet utilisateur avec les données du formulaire
        utilisateur user = new utilisateur();
        user.setNom(nom);
        user.setPrenom(prenom);
        user.setEmail(email);
        user.setPassword(password);
        user.setNum_tel(numTel);
        user.setType(type);
        user.setSexe(sexe);
        


        // Enregistrer l'utilisateur dans la base de données
        dao_factory daoFactory = dao_factory.getInstance();
        utilisateur_dao userDao = daoFactory.get_utilisateur_dao();
        int id_utilisateur = userDao.inscription(user, type);
        
        
        
        //enregistrement des données des medecins et des utilisateurs selon le type d'utilisateur (patinet, medecin)
        if ("patient".equalsIgnoreCase(type) && id_utilisateur!=-1) {
        	String contact_urgence = request.getParameter("contact_urgence");
        	
        	patient patient = new patient();
        	patient.setContact_urgence(contact_urgence);
        	patient.setId_utilisateur(id_utilisateur);
        	
        	
        	patient_dao patient_dao = daoFactory.get_patient_dao();
        	patient_dao.ajouter(patient);
  
        }
        else if("medecin".equalsIgnoreCase(type)&& id_utilisateur!=-1) {
        	String specialite = request.getParameter("specialite");
        	String adresse = request.getParameter("adresse");
        	
        	medecin medecin = new medecin();
        	medecin.setSpecialite(specialite);
        	medecin.setAdresse(adresse);
        	medecin.setId_utilisateur(id_utilisateur);
        	
        	medecin_dao medecin_dao = daoFactory.get_medecin_dao();
            medecin_dao.ajouter(medecin);
        	
        }
        
        if (id_utilisateur!=-1) {
        	request.getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
        } 

       
    }

}
