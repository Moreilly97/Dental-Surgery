package dentalSurgery;
import java.io.Serializable;
import java.util.ArrayList;

public class Patient extends Person implements Serializable{

	private int patientId;
	private ArrayList<Invoice> patient_invoiceList = new ArrayList<Invoice>();

	private int maxPatient = 1;

	public Patient(String name, String address, String phoneNumber) {
		super(name, address, phoneNumber);
		maxPatient++;
		setPatientId(maxPatient);
	
	}
	
	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public ArrayList<Invoice> getPatient_invoiceList() {
		return patient_invoiceList;
	}

	public void setP_invoiceList(ArrayList<Invoice> patient_invoiceList) {
		this.patient_invoiceList = patient_invoiceList;
	}



	public int getMaxPatient() {
		return maxPatient;
	}

	public void setMaxPatient(int maxPatient) {
		this.maxPatient = maxPatient;
	}
	
	 public void addInvoice(Invoice y) {
		 patient_invoiceList.add(y);
	 }
	 
	 public void removeInvoice(Invoice y) {
		 patient_invoiceList.remove(y);
	 }
	
	
	
	public String toString() {
		return (" Patient name : " + this.getName() + "Patients Address : " +this.getAddress()+"Patient Phone Number : " + this.getPhoneNumber());
	}



		
	

	
}
