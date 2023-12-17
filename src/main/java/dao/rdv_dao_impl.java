package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.rdv;

public class rdv_dao_impl implements rdv_dao{
	
	private dao_factory dao_factory;

    public rdv_dao_impl(dao_factory dao_factory) {
        this.dao_factory = dao_factory;
    }

	@Override
	public void ajouter(rdv rdv) {
		// TODO Auto-generated method stub
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    String query_rdv = "INSERT INTO rendez_vous (id_patient, id_med, date_debut, date_fin, heure_debut, heure_fin) VALUES (?, ?, ?, ?, ?, ?)";
	    try {
	    connexion = dao_factory.getConnection();
		preparedStatement = connexion.prepareStatement(query_rdv, PreparedStatement.RETURN_GENERATED_KEYS);
		
        preparedStatement.setInt(1, rdv.getId_patient());
        preparedStatement.setInt(2, rdv.getId_med());
        preparedStatement.setDate(3, rdv.getDate_debut());
        preparedStatement.setDate(4, rdv.getDate_fin());
        preparedStatement.setString(5, rdv.getHeure_debut());
        preparedStatement.setString(6, rdv.getHeure_fin());

        // Exécution de la requête
        preparedStatement.executeUpdate();
	    }
	    catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception de manière appropriée dans votre application
        }
	}

}
