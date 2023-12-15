package beans;

import java.util.Date;

public class rdv {
	private int id_rdv;     
	private Date date_rdv;       
	private Date heure;      
	private String remarque;   
	private int id_patient; 
	private int id_med;
	
	public int getId_rdv() {
		return id_rdv;
	}
	public void setId_rdv(int id_rdv) {
		this.id_rdv = id_rdv;
	}
	public Date getDate_rdv() {
		return date_rdv;
	}
	public void setDate_rdv(Date date_rdv) {
		this.date_rdv = date_rdv;
	}
	public Date getHeure() {
		return heure;
	}
	public void setHeure(Date heure) {
		this.heure = heure;
	}
	public String getRemarque() {
		return remarque;
	}
	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}
	public int getId_patient() {
		return id_patient;
	}
	public void setId_patient(int id_patient) {
		this.id_patient = id_patient;
	}
	public int getId_med() {
		return id_med;
	}
	public void setId_med(int id_med) {
		this.id_med = id_med;
	}      

}
