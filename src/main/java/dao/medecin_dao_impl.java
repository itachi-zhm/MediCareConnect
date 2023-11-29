package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.medecin;

public class medecin_dao_impl implements medecin_dao{
	
	private dao_factory dao_factory;

    public medecin_dao_impl(dao_factory dao_factory) {
        this.dao_factory = dao_factory;
    }
	@Override
	public boolean ajouter(medecin medecin) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		String query_medecin = "INSERT INTO medecins (id_utilisateur, specialite, adresse) VALUES (?, ?, ?)";
		try {
            connexion = dao_factory.getConnection();
            preparedStatement = connexion.prepareStatement(query_medecin);

            preparedStatement.setInt(1, medecin.getId_utilisateur());
            preparedStatement.setString(2, medecin.getSpecialite());
            preparedStatement.setString(3, medecin.getAdresse());

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
