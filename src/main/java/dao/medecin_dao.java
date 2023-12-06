package dao;

import beans.medecin;

public interface medecin_dao {
	public boolean ajouter(medecin medecin);
	public void modifierMedecin(medecin medecin);
	public void supprimerMedecin(int id_medecin);

}
