package dentalSurgery;

import java.util.ArrayList;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class GUI extends Application {
	static ArrayList<Dentist> dentistList = new ArrayList<Dentist>();
	static ArrayList<Procedure> procedureList = new ArrayList<Procedure>();
	static ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
	static ArrayList<Patient> patientList = new ArrayList<Patient>();
    ObservableList<Patient> patients = FXCollections.observableArrayList();
	ObservableList<Procedure> procedures = FXCollections.observableArrayList();
	//Dentist login = null;
	public static void main(String[] args) {
		 
		launch(args);
	}

	public void start(Stage stage) throws Exception {
	    
		BaseController controller = new BaseController(stage,dentistList);
	    login_view logView = new login_view(controller,dentistList);
	    application_view appView = new application_view(controller,patientList,dentistList,procedureList,stage);
	    newDentist_view newDV = new newDentist_view(controller, dentistList);
	    Procedure_view newProV = new Procedure_view(controller,patientList,dentistList,procedureList,stage);
	    Patient_view newPV = new Patient_view(controller,patientList,dentistList,stage);
	    Invoice_view invView = new Invoice_view(controller,dentistList,procedureList,patientList,stage);
	    Payment_view payV = new Payment_view(controller,patientList,dentistList, invoiceList,procedureList, stage);
	    Reports_View repV = new Reports_View(controller,patientList, dentistList, procedureList, stage);
	    controller.setViews(logView,appView,newPV,newDV,newProV,invView,payV,repV);
	    stage.setTitle("Login");
	    stage.setScene(logView.scene);
        stage.show();
		
	}
	
	

}
