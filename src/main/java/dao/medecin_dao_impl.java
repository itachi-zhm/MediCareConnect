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
    
    //--------------------la fonction qui permet d'ajouter un nouveau medecin à notre base de données
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

	@Override
	public void modifierMedecin(medecin medecin) {
	    Connection connexion = null;
	    PreparedStatement preparedStatementMedecins = null;
	    PreparedStatement preparedStatementUtilisateurs = null;

	    try {
	        connexion = dao_factory.getConnection();
	        connexion.setAutoCommit(false);

	        // Mise à jour de la table medecins
	        preparedStatementMedecins = connexion.prepareStatement("UPDATE medecins SET specialite = ?, adresse = ? WHERE id_utilisateur = ?");
	        preparedStatementMedecins.setString(1, medecin.getSpecialite());
	        preparedStatementMedecins.setString(2, medecin.getAdresse());
	        preparedStatementMedecins.setInt(3, medecin.getId_utilisateur());
	        preparedStatementMedecins.executeUpdate();
	        
	        // Mise à jour de la table utilisateurs
	        preparedStatementUtilisateurs = connexion.prepareStatement("UPDATE utilisateurs SET nom = ?, prenom = ?, email = ?, num_tel = ? WHERE id_utilisateur = ?");
	        preparedStatementUtilisateurs.setString(1, medecin.getNom());
	        preparedStatementUtilisateurs.setString(2, medecin.getPrenom());
	        preparedStatementUtilisateurs.setString(3, medecin.getEmail());
	        preparedStatementUtilisateurs.setString(4, medecin.getNum_tel());
	        preparedStatementUtilisateurs.setInt(5, medecin.getId_utilisateur());
	        preparedStatementUtilisateurs.executeUpdate();

	        // Commit de la transaction
	        connexion.commit();
	    } catch (SQLException e) {
	        // En cas d'erreur, rollback la transaction
	        if (connexion != null) {
	            try {
	                connexion.rollback();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
	        e.printStackTrace(); // Gérer l'exception de manière appropriée
	    } finally {
	        // Fermeture des ressources
	        try {
	            if (preparedStatementMedecins != null) {
	                preparedStatementMedecins.close();
	            }
	            if (preparedStatementUtilisateurs != null) {
	                preparedStatementUtilisateurs.close();
	            }
	            if (connexion != null) {
	                connexion.setAutoCommit(true);
	                connexion.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


	@Override
	public void supprimerMedecin(int id_medecin) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connexion = dao_factory.getConnection();
	        preparedStatement = connexion.prepareStatement("DELETE FROM utilisateurs WHERE id_utilisateur = ?");
	        preparedStatement.setInt(1, id_medecin);
	        preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace(); // Gérer l'exception de manière appropriée
	        }
	 }
		

}
