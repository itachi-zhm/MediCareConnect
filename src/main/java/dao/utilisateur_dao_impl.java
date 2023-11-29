package dao;

import beans.utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class utilisateur_dao_impl implements utilisateur_dao{
	
	private dao_factory dao_factory;
	private medecin_dao medecin_dao;

    public utilisateur_dao_impl(dao_factory dao_factory) {
        this.dao_factory = dao_factory;
        this.medecin_dao = new medecin_dao_impl(dao_factory);
    }
	
	// Fonction pour hacher un mot de passe
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] passwordBytes = password.getBytes();
            byte[] hashBytes = md.digest(passwordBytes);

            StringBuilder hexHash = new StringBuilder();
            for (byte b : hashBytes) {
                hexHash.append(String.format("%02x", b));
            }

            return hexHash.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

	@Override
	public int inscription(utilisateur utilisateur, String typeUtilisateur) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		String query_utilisateur = "INSERT INTO utilisateurs (nom, prenom, email, password, type, num_tel, sexe) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			connexion = dao_factory.getConnection();
			preparedStatement = connexion.prepareStatement(query_utilisateur, PreparedStatement.RETURN_GENERATED_KEYS);
            // Hacher le mot de passe avant de l'insérer
            String hashedPassword = hashPassword(utilisateur.getPassword());

            preparedStatement.setString(1, utilisateur.getNom());
            preparedStatement.setString(2, utilisateur.getPrenom());
            preparedStatement.setString(3, utilisateur.getEmail());
            preparedStatement.setString(4, hashedPassword); // Utiliser le mot de passe haché
            preparedStatement.setString(5, utilisateur.getType());
            preparedStatement.setString(6, utilisateur.getNum_tel());
            preparedStatement.setString(7, utilisateur.getSexe());
          
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int id_utilisateur = generatedKeys.getInt(1);
                    return id_utilisateur;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
	}
	
	
	//-------------la fonction de connexion
	public utilisateur connexion(String email, String password) {
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;

	    String query = "SELECT * FROM utilisateurs WHERE email = ? AND password = ?";

	    try {
	        connexion = dao_factory.getConnection();
	        preparedStatement = connexion.prepareStatement(query);

	        preparedStatement.setString(1, email);
	        preparedStatement.setString(2, hashPassword(password)); // Hacher le mot de passe pour la comparaison

	        resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            // Si le résultat contient une ligne, construire un objet utilisateur
	            utilisateur user = new utilisateur();
	            user.setId_utilistaeur(resultSet.getInt("id_utilisateur"));
	            user.setNom(resultSet.getString("nom"));
	            user.setPrenom(resultSet.getString("prenom"));
	            user.setEmail(resultSet.getString("email"));
	            user.setPassword(resultSet.getString("password"));
	            user.setType(resultSet.getString("type")); // Assurez-vous que le type est correct
	            user.setNum_tel(resultSet.getString("num_tel"));

	            return user;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Fermer les ressources dans le bloc finally si nécessaire
	        // (try-with-resources est une meilleure option si vous utilisez Java 7+)
	        // ...
	    }

	    return null;
	}


}
