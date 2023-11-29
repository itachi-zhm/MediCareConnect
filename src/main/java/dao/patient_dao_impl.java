package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.patient;

public class patient_dao_impl implements patient_dao {
	
	private dao_factory dao_factory;

    public patient_dao_impl(dao_factory dao_factory) {
        this.dao_factory = dao_factory;
    }
	@Override
	public boolean ajouter(patient patient) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		String query_patient = "INSERT INTO patients (id_utilisateur, contact_urgence) VALUES (?, ?)";
		try {
            connexion = dao_factory.getConnection();
            preparedStatement = connexion.prepareStatement(query_patient);

            preparedStatement.setInt(1, patient.getId_utilisateur());
            preparedStatement.setString(2, patient.getContact_urgence());
            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connexion != null) {
                try {
                    connexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
		
	    
		return false;
	}

}
