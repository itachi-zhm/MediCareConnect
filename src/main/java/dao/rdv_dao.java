package dao;

import java.util.List;

import beans.rdv;

public interface rdv_dao {
	public void ajouter(rdv rdv);
	public List<rdv> getRdvMedecin(int idMedecin);

}
