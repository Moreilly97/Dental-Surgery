package dentalSurgery;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;



public class BaseController {
	
	String loggedDentist;
	String loggedPassword;
	login_view logView;
	application_view appView;
    newDentist_view newDV;
    Patient_view newPV;
    Procedure_view newProV;
    Invoice_view invView;
    Payment_view payV;
    Reports_View repV;
    Stage stage;
    ArrayList<String> procNames = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> invoices = new ArrayList<>();
    ArrayList<Dentist> dentistList; 
    ArrayList<Procedure> procedureList;
    ObservableList<Patient> patients = FXCollections.observableArrayList();
    ObservableList<Payment> payments = FXCollections.observableArrayList();
    ObservableList<Procedure> procedures = FXCollections.observableArrayList();
	private Procedure b;

    
    
	 public BaseController(Stage stage,ArrayList<Dentist> dentistList){
	    	this.stage = stage;
	    	this.dentistList = dentistList;
	    //	loadSession();
	    	
	    }	 
	
	 public void setViews(login_view logView,application_view app_view,Patient_view newPV, newDentist_view newDV, Procedure_view newProV,Invoice_view invView,Payment_view payV,Reports_View repV){
		 		this.logView =logView ;
		 		this.appView = app_view;
	  	    	this.newPV = newPV;
	  	    	this.newDV = newDV;
	  	    	this.newProV = newProV;
	  	    	this.invView = invView;
	  	    	this.payV = payV;
	  	    	this.repV = repV;
	    }

		public void application(Scene scene, TabPane tab){
			stage.setTitle("Main Page");
			stage.setResizable(false);
			stage.setScene(appView.scene);
		}
		public void patient( ArrayList<Dentist> dentistList, Scene scene, TabPane tab) {
			stage.setTitle("Add Patient");
			stage.setResizable(false);
			stage.setScene(newPV.scene);
		}
		public void procedure(ArrayList<Dentist> dentistList, ArrayList<Procedure> procedureList, Scene scene, TabPane tab){
			stage.setTitle("Procedure List");
			stage.setResizable(false);
			stage.setScene(newProV.scene);
		}
		public void invoice(ArrayList<Dentist> dentistList,Scene scene, TabPane tab){
			stage.setTitle("Invoice List");
			stage.setResizable(false);
			stage.setScene(invView.scene);
		}
		public void payment(ArrayList<Dentist> dentistList, Scene scene,TabPane tab){
			stage.setTitle("Payments");
			stage.setResizable(false);
			stage.setScene(payV.scene);
		}
		public void reports(ArrayList<Patient> patientList,ArrayList<Dentist> dentistList, ArrayList<Procedure> procedureList,TabPane tab){			
			stage.setTitle("Reports");
			stage.setResizable(false);
			stage.setScene(repV.scene);
		}
		
		
	public void addDentist(String name, String address, String phoneNumber, String pass) {
	   Dentist newDentist = new Dentist(name, address, phoneNumber, pass);
		dentistList.add(newDentist);
		stage.setTitle("Create Dentist");
		stage.setResizable(false);
		stage.setScene(newDV.scene);
	}


	public ArrayList<Dentist> createDentist(String name,String address, String pNo, String pass) {
		Dentist newDent = new Dentist(name,address,pNo,pass);
		dentistList.add(newDent);
	
		stage.setScene(logView.scene);
		
		return dentistList;
	}
	public void loginDentist(String name, String password) {
		for(Dentist logView : dentistList) {
			if(logView.getName().equals(name) && logView.getPassword().equals(password)) {
			    stage.setTitle("Dentist Surgery Application");
				stage.setResizable(false);
				stage.setScene(appView.scene);
				this.loggedDentist = name;
				this.loggedPassword = password;
			}
		}
	}

	public void cancelDentist(Scene scene) {
		stage.setResizable(false);
		stage.setScene(logView.scene);
	}

	public void createPatient(BaseController controller,ArrayList<Dentist> dentistList, String name, String addr, String phoneNo) {

		 for(Dentist x : dentistList) {
				if(x.getName().equals(loggedDentist) && x.getPassword().equals(loggedPassword)) {
					Patient y = new Patient(name, addr,phoneNo);
					x.addPat(y);			
					patients.add(new Patient(y.getName(),y.getAddress(),y.getPhoneNumber()));
					names.add(name);
					invView.populateNames(controller);
									
					 
				}
				
			}		 
	}
	public void deletePatient(ArrayList<Dentist> dentistList,String patientName,String patientAddress,String patientNumber){
		
		for(Dentist x: dentistList){
			if(x.getName().equals(loggedDentist) && x.getPassword().equals(loggedPassword)){				
					x.removePatient(patientName, patientAddress, patientNumber);
					
				}				
			}
		}				
	
	public ObservableList<Patient> displayPatient(ArrayList<Dentist> dentistList){
		 for(Dentist x : dentistList) {
				if(x.getName().equals(loggedDentist) && x.getPassword().equals(loggedPassword)) {
					patients.add(new Patient(x.getName(),x.getAddress(),x.getPhoneNumber()));
				}
				
			}
		return patients;
		
	}
	
	public void addProcedure(BaseController controller,ArrayList<Dentist> dentistList,String procName, double procCost){
		for(Dentist x : dentistList){
			if(x.getName().equals(loggedDentist) && x.getPassword().equals(loggedPassword)){				
				Procedure y = new Procedure(procName, procCost);
				x.addPro(y);
				procedures.add(new Procedure(procName,procCost));
				procNames.add(procName);
				invView.populateProcNames(controller);

				
			}
		}
		
	}
	public void deleteProcedure(ArrayList<Dentist> dentistList , String procName, double procCost){
		for(Dentist x : dentistList){
			if(x.getName().equals(loggedDentist) && x.getPassword().equals(loggedPassword)){		
				x.removeProcedure(procName, procCost);
			}
		}
		
	}
	public ObservableList<Procedure> displayProcedure(ArrayList<Dentist> dentistList, ArrayList<Procedure> procedureList){
		for(Dentist x : dentistList){
			if(x.getName().equals(loggedDentist) && x.getPassword().equals(loggedPassword)) {
					Procedure y = x.getProcedure();
					procedures.add(new Procedure(y.getProcName() , y.getProcCost()));
				
				}			
			}

		return procedures;
	}
	
	public void addInvoice(BaseController controller, ArrayList<Dentist> dentistList,ArrayList<Patient> patientList,String name,String procName,LocalDate date,
							double a){		
		double cost = 0;
		double amtOwed = 0.0;
		double change = 0.0;
		Payment k = new Payment(a,date);
		boolean paid = false;
		for(Dentist x : dentistList){
			if(x.getName().equals(loggedDentist) && x.getPassword().equals(loggedPassword)) {
					 for(Procedure p : x.procedureList){
						if(p.getProcName() == procName){
							cost = p.getProcCost();			
							amtOwed = cost - a;
							if(amtOwed == 0){
								paid = true;
							}
							if(amtOwed < 0){
								change = amtOwed;
							}
							this.b = p;
							new Invoice(date, paid, cost, k, b);
							
						}	
						
					}
					 
					 for(int i =0;i<x.getPatientList().size();i++){
						Patient l = Dentist.findPatient(i);
						 if(l.getName().equals(name)){
							 Invoice y = new Invoice(date, paid, cost, k, b);
							 x.addInvoice(l,y);
							 if(paid == true){
								 invoices.add("\nPatient " +name + " has received the procedure : " + procName + " on the date " +date + "\nWas it paid for fully: " +paid +"\n-------");
								 int p = payments.size()+1;
								 Payment m = new Payment(p,date);
								 payments.add(m);
							 }
							 else if(amtOwed < 0)
							 {
								 x.addInvoice(l,y);
								 change = Math.abs(change);
								 invoices.add("\nPatient " +name + " has received the procedure : " + procName + " on the date " +date + "\nWas it paid for fully: " +paid +"   Change due: "
								 		+ change +"\n-------");

								 		 int p = payments.size()+1;
										 Payment m = new Payment(p,date);
										 payments.add(m);
									 
								 
							 }
							 else
							 {
								 x.addInvoice(l,y);
								 invoices.add("\nPatient " +name + " has received the procedure : " + procName + " on the date " +date + "\nWas it paid for fully: " +paid +"   Amount still owed: "
								 		+ amtOwed +"\n-------");
								 int p = payments.size()+1;
								 Payment m = new Payment(p,date);
								 payments.add(m);
							 }
							 
						 }						 
					 }			
				}			
			}		
	}
	
	public String displayInvoices(){
		
		for(int x = 0;x <= invoices.size();){			
			return invoices.toString();			
		}
		
		return null;
		
	}
	
	public ObservableList<Payment> displayPayments(ArrayList<Dentist> dentistList,ArrayList<Invoice> invoiceList){
		
		return payments;
		
		
	}

	public void generateReport1(){
		
	
		for(Dentist x: dentistList){
			if(x.getName().equals(loggedDentist) && x.getPassword().equals(loggedPassword)){			
				try{

			    PrintWriter writer = new PrintWriter("Patients_Report.txt");
			    writer.println("Patients Report for Dentist : " + loggedDentist);
			    writer.println("-----------------------------------------------");
			    	for(Patient p : x.patientList){
			    			writer.println(p.toString());
					    	for(Invoice h : p.getPatient_invoiceList()){
					    		writer.println(h.toString());
					    	}
			    		
			    	}
			    	
			   // }
			    writer.close();
			} catch (IOException n) {
				n.printStackTrace();
			}
			}
			}
	}
	
	public void generateReport2(){
		 LocalDate now = LocalDate.now();

		for(Dentist x: dentistList){
			if(x.getName().equals(loggedDentist) && x.getPassword().equals(loggedPassword)){			
				try{
					PrintWriter writer = new PrintWriter("Patients_Report_MoneyDue.txt");
					 writer.println("Patients who owe money report for Dentist : " + loggedDentist);
					 writer.println("-----------------------------------------------");
					 for(Patient p : x.patientList){
						for(Invoice h : p.getPatient_invoiceList()){
							long j = ChronoUnit.DAYS.between(h.getInvoiceDate(),now);
							if(j >= 182.5){
								
			    			writer.println(p.toString());
					    	
					    		writer.println(h.toString());
					    	}
						}
			    		
			    	}
					 
					 
				}catch(IOException n){
				
				
			}
			}
		}
			
	}
	public void saveSession(){
		try {
	         FileOutputStream fileOut = new FileOutputStream("dentist.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(dentistList);
	         out.close();
	         fileOut.close();
	         System.out.println("Saving successful");
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
	} 


	public void loadSession(){
		try {
			FileInputStream fileIn = new FileInputStream("dentist.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
		while(fileIn.available() > 0){
			
			dentistList.add((Dentist) in.readObject());
			{
				in.close();
				fileIn.close();
			}
		}
		}
		catch (IOException i) {
			i.printStackTrace();
		}
		catch(ClassNotFoundException c){
			c.printStackTrace();
		}	
		
	

      }
}

