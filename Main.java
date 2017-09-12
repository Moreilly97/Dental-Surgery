package dentalSurgery;
/*package dentalSurgery;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
public class Main {
	public static void main(String args[]) {
		
		Scanner keyboard = new Scanner(System.in);
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		ArrayList<Dentist> dentistList = new ArrayList<Dentist>();
		ArrayList<Procedure> procedureList = new ArrayList<Procedure>();
		boolean valid = true;
		int choice =0;
		displayMenu();
		System.out.print("Please enter your choice: ");
		boolean pMenu = true;
		choice = keyboard.nextInt();
				
		while(valid == true) {
			hoice = -1;
			while(! keyboard.hasNextInt())
			{
				keyboard.next();
				System.out.print("Please enter your choice: ");      
			}
			choice = keyboard.nextInt();

			while (choice < 1 )
			{
				System.out.print("Please enter your choice: "); 
				keyboard.next();
				while(! keyboard.hasNextInt() )
				{
					keyboard.next();
					System.out.print("Please enter your choice: ");
				}
				choice = keyboard.nextInt();
			}
			while (choice > 5 )
			{
				System.out.print("Please enter your choice: ");
				keyboard.next();
				while(! keyboard.hasNextInt() )
				{
					keyboard.next();
					System.out.print("Please enter your choice: ");
				}
				choice = keyboard.nextInt();
			}
			
		}
		
		switch(choice) {
		
		case 1:
			System.out.println("Welcome to the Patient Management Section");
			PMenu(keyboard, patientList, dentistList);			
			displayMenu();
			System.out.println("Please enter your choice: ");
			
		break;
		
		case 2:
			System.out.println("Welcome to the Invoice / Procedure Management Section");
			IPMenu(keyboard, patientList, procedureList, dentistList);
			displayMenu();
			System.out.println("Please enter your choice: ");
		break;
		
		case 3:
			System.out.println("Welcome to the Payment Management Section");
			paymentMenu(keyboard, patientList);
			displayMenu();
			System.out.println("Please enter your choice: ");
		break;
		
		case 4:
			valid = false;
		break;
			
		}
	}	
		
		}
		
				public static void displayMenu() {
					System.out.println("1. Patient Management Section");
					System.out.println("2. Invoice / Procedure Management Section");
					System.out.println("3. Payment Management Section");
					System.out.println("4. Exit");
					
				}
				
				public static void displayPMMenu(){
					System.out.println("1. Add Patient");
					System.out.println("2. Remove Patient");
					System.out.println("3. Display Patients");
					System.out.println("4. Save and Quit");
					System.out.println("5. Add a dentist");
					System.out.println("6. Quit");
					
				}
				
				public static void displayIPMenu(){
					System.out.println("1. Add Procedure");
					System.out.println("2. Remove Procedure");
					System.out.println("3. Quit");
				}
				
				public static void displayPaymentMenu(){
					System.out.println("1. List Invoices");
					System.out.println("2. List Payments");
					System.out.println("3. Quit");
				}
				
				public static void PMenu(Scanner keyboard, ArrayList<Patient> patientList, ArrayList<Dentist> dentistList){
					boolean valid = true;
					
					displayPMMenu();
					System.out.print("Please enter your choice: ");
					while(valid == true){
						int choice = keyboard.nextInt();
						switch(choice){
						
						
							case 1:
								addPatient(keyboard, patientList, dentistList);
								displayPMMenu();
								System.out.println("Please enter your choice: ");
								
							break;
								
								
							case 2:
								removePatient(keyboard, patientList, dentistList);
								displayPMMenu();
								System.out.println("Please enter your choice: ");
								choice = keyboard.nextInt();
								
							break; 	
							
							case 3:
								displayPatients(keyboard, patientList, dentistList);
								displayPMMenu();
								System.out.println("Please enter your choice: ");
							break;
							
							case 4:
								outputPatients(patientList);
							break;
							
							case 5:
								addDentist(keyboard,dentistList);
								displayPMMenu();
								System.out.println("Please enter your choice: ");
							break;
							case 6:
								valid = false;
							break;
						
					}
					}
				}
				
				public static void IPMenu(Scanner keyboard, ArrayList<Patient> patientList, ArrayList<Procedure> procedureList, ArrayList<Dentist> dentistList){
					boolean valid = true;
					
					displayIPMenu();
					System.out.print("Please enter your choice: ");
					while(valid == true){
						int choice = keyboard.nextInt();
						switch(choice){
							
						case 1:
							addProcedure(keyboard, procedureList, dentistList);
							displayIPMenu();
							System.out.println("Please enter your choice: ");
						break;
						
						case 2:
							removeProcedure(keyboard, procedureList);
							displayIPMenu();
							System.out.println("Please enter your choice: ");
						break;
						
						case 3:
							valid = false;
						break;
						
					}
				}
					
				}
				
				public static void paymentMenu(Scanner keyboard, ArrayList<Patient> patientList){
					boolean valid = true;
					
					displayPaymentMenu();
					System.out.print("Please enter your choice: ");
					while(valid == true){
					int choice = keyboard.nextInt();
					switch(choice){
						
					case 1:
						displayInvoices();
						displayIPMenu();
						System.out.println("Please enter your choice: ");
					break;
					
					case 2:
						displayPayments();
						displayIPMenu();
						System.out.println("Please enter your choice: ");
					break;
					
					case 3:
						valid = false;
					break;						

					}
				}
					
				}

				public static void addPatient(Scanner keyboard,ArrayList<Patient> patientList,ArrayList<Dentist> dentistList ){
					String name;
					String address;
					String phoneNumber;
					String dentistName;
					String pass;
					
					System.out.println("Please enter the patients name: ");
					name = keyboard.next();
					System.out.println("Please enter the patients address: ");
					address = keyboard.next();
					System.out.println("Please enter the patients phone number: ");
					phoneNumber = keyboard.next();
									
					System.out.println("Enter Dentist's name: ");
					dentistName = keyboard.next();
					
					System.out.println("Enter password for " + dentistName+ " :");
					pass = keyboard.next();
					
					Patient added = new Patient(name, address, phoneNumber);
					
					for(Dentist x : dentistList) {
						if(x.getName().equals(dentistName) && x.getPassword().equals(pass)) {
							x.addPat(added);
							
							System.out.println("Patient added! ");								
						}
						else{
							System.out.println("No such dentist can be found in the records.");
						}
					}
					
					
					
			
					
				}
				
				public static void addDentist(Scanner keyboard, ArrayList<Dentist> dentistList) {
					String name;
					String pass;
					String address;
					String phoneNumber;
					
					System.out.println("Enter the Dentist's name: ");
					name = keyboard.next();
					
					System.out.println("Enter the Dentist's address: ");
					address = keyboard.next();
					
					System.out.println("Enter the Dentist's phone number: ");
					phoneNumber = keyboard.next();
					
					
					System.out.println("Enter the Dentist's password :");
					pass = keyboard.next();
					
					Dentist x = new Dentist(name,address,phoneNumber,pass);
					dentistList.add(x);
					System.out.println("Dentist added! ");
				}
				
				public static void addProcedure(Scanner keyboard,ArrayList<Procedure> procedureList,ArrayList<Dentist> dentistList) {
					String procName;
					double procCost;
					
					System.out.println("Please enter the name of the procedure: ");
					procName = keyboard.next();
					System.out.println("Please enter procedures cost: ");
					procCost = keyboard.nextInt();
					Procedure proc = new Procedure(procName,procCost);
					
					System.out.println("Enter the invoice amount: ");
					double amount = keyboard.nextDouble();
					
					
					System.out.println("Please enter procedures date: ");				

					System.out.println("Day: ");
					int day = keyboard.nextInt();
					System.out.println("Month: ");
					int month = keyboard.nextInt();
					System.out.println("Year: ");
					int year = keyboard.nextInt();
					Date date = new Date(day,month,year);					
					
					System.out.println("Has it been paid? (Y/N)");
					char p = keyboard.next().charAt(0);
					boolean paid = false;
					
					if(p == 'y' || p == 'Y') {
						paid = true;
					}
					else if(p == 'n' || p == 'N') {
						paid = false;
					}
					
					Payment payment = new Payment(amount, date);
					
					Invoice x = new Invoice(date,paid,amount,payment,proc);
					
					System.out.println("Enter patients name: ");
					String patientName = keyboard.next();
					System.out.println("Enter patients address: ");
					String address = keyboard.next();
					System.out.println("Enter patients phone number: ");
					String phoneNumber = keyboard.next();
				
					Patient patient = new Patient(patientName,address,phoneNumber);
					System.out.println("Enter Dentist's name: ");
					String dentistName = keyboard.next();
					
					System.out.println("Enter password for " + dentistName+ " :");
					String pass = keyboard.next();
								
					Procedure added = new Procedure(procName, procCost);
					procedureList.add(added);
					System.out.println("Procedure added! ");
					
					for(Dentist k : dentistList) {
						if(k.getName().equals(patientName) && k.getPassword().equals(pass)) {
							k.addInvoice(patient,x);
							System.out.println("Invoice Added");
						}
						else{
							System.out.println("No such dentist can be found in the records.");
						}
						
				}
					
					
				}
				
				public static void removePatient(Scanner keyboard,ArrayList<Patient> patientList, ArrayList<Dentist> dentistList ){
					String address;
					String dentistName;
					String phoneNumber;
					String pass;
					String name;
					
					System.out.println("Enter patient name: ");
				    name = keyboard.next();
				    
				    System.out.println("Enter patient address: ");
				    address = keyboard.next();
				    
				    System.out.println("Enter patient phone number: ");
				    phoneNumber = keyboard.next();
				    
				    Patient c = new Patient(name,address,phoneNumber);
									
					System.out.println("Enter Dentist's name: ");
					dentistName = keyboard.next();
					
					System.out.println("Enter password for " + dentistName+ " :");
					pass = keyboard.next();
					
					for(Dentist x : dentistList) {
						if(x.getName().equals(name) && x.getPassword().equals(pass)) {
							x.removePatient(c);
							System.out.println("Patient removed.");
						}
						else{
							System.out.println("No such dentist can be found in the records.");
						}
					}		
					
			  
				    
					
				}
				
				public static void removeProcedure(Scanner keyboard,ArrayList<Procedure> procedureList ){
					int procToRemove = 0;
					
					System.out.print("Enter the patients id: ");
					procToRemove = keyboard.nextInt();
					
					procedureList.remove(procToRemove - 1);
					
					
					System.out.println("Procedure removed.");       
				    
					
				} 
				
				public static void displayPatients(Scanner keyboard,ArrayList<Patient> patientList,ArrayList<Dentist> dentistList ){
					
					ArrayList<Patient> temp = new ArrayList<Patient>();
					String name;
					String pass;
					
					System.out.println("Enter Dentist's name: ");
					name = keyboard.next();
					
					System.out.println("Enter password for " + name+ " :");
					pass = keyboard.next();
					
					for(Dentist x : dentistList) {
						if(x.getName().equals(name) && x.getPassword().equals(pass)) {
							temp = x.getPatientList();
						}
						else{
							System.out.println("No such dentist can be found in the records.");
						}						
					}
					System.out.println(temp);
				}
				
				
				
				
				public static void addPayment(Scanner keyboard,ArrayList<Patient> patientList,ArrayList<Dentist> dentistList ){
				
					for(Dentist b : dentistList){
						
					}
				}
			
				
				public static void displayPayments() {
			
					
				}

				public static void displayInvoices() {
				
					
				}
				
				public static void outputPatients(ArrayList<Patient> patientList) {
					String output = "";
					
					for(Patient p : patientList)
				    {
						output += p.toString() + "\n";
				    }
					
					
					 try {
						FileWriter filewrite = new FileWriter("patientlist.txt");
						BufferedWriter bufferedwriter = new BufferedWriter(filewrite);
						bufferedwriter.write("List of patients");
						bufferedwriter.newLine();
						bufferedwriter.write(output + "\n ");
						bufferedwriter.newLine();
						bufferedwriter.close();
					 }
					 
					 catch (FileNotFoundException filenotfound){
						 System.out.println("patientlist.txt" + " not found ");
						 System.exit(0);
					 }

					 catch (IOException ioe){
					    ioe.printStackTrace();
					    System.exit(1);
					 }
				}
				
}*/
