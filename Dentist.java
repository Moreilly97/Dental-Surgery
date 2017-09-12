package dentalSurgery;
import java.io.Serializable;
import java.util.ArrayList;

import dentalSurgery.Patient;

public class Dentist extends Person implements Serializable{
	
	/**
	 * 
	 */
	private String password;
	public static ArrayList<Patient> patientList;
	public ArrayList<Procedure> procedureList;
	
	public Dentist(){
	}

	
	public Dentist(String name, String address, String phoneNumber, String password) {
		super(name, address, phoneNumber);
		this.password = password;
		patientList = new ArrayList<Patient>();
		procedureList = new ArrayList<Procedure>();
		
	}
	
	public void addPat(Patient i) {
		patientList.add(i);
	}
	
	public void addPro(Procedure i){
		procedureList.add(i);
	}
	
	public void removePatient(String name, String address, String number) {
		for(Patient y : patientList) {
			if(name.equals(y.getName()) && address.equals(y.getAddress()) && number.equals(y.getPhoneNumber())) {
				patientList.remove(y);
				return;
				
			}
		}
	}
	
	public void removeProcedure(String procName, double procCost){
		for(Procedure y : procedureList){
			if(procName.equals(y.getProcName()) && procCost == y.getProcCost()){
				procedureList.remove(y);
				return;
			}
		}
		
	}
	
	public void addInvoice(Patient i,Invoice v) {
		for(Patient y : patientList) {
			if(i.getName().equals(y.getName())) {
				i.addInvoice(v);
			}
		}
	}

	public static Patient getPatient() {
		if(patientList.get(0) != null) 
		{
			for(int i =0; i <= patientList.size();i++) {
				Patient x = patientList.get(i);
				return x;
				
			}
		}
		return null;
	} 
	public static Patient findPatient(int index) {
		int o = index;
		Patient x = patientList.get(0);
		if(patientList.get(o) != null) 
		{
				x = patientList.get(o);
				return x;
				
			
		}
		return x;
	} 
	public Procedure getProcedure(){
		if(procedureList.get(0) != null) 
		{
			for(int i =0; i < procedureList.size();) {
				return procedureList.get(i);
			}
		}
		return null;
	} 
	public String toString() {
		return "Dentist [password=" + password + ", patientList=" + patientList + "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Patient> getPatientList() {
		return patientList;
	}

	public void setPatientList(ArrayList<Patient> patientList) {
		this.patientList = patientList;
	}

}