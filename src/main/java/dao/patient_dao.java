package dao;

import beans.patient;

public interface patient_dao {
	public boolean ajouter(patient patient);
	public void modifierPatient(patient patient);
	public void supprimerPatient(int id_patient);

}
