package dentalSurgery;

import java.util.ArrayList;
import java.util.Optional;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Reports_View {

	Scene scene;
	public Reports_View(BaseController controller,ArrayList<Patient> patientList,ArrayList<Dentist> dentistList,ArrayList<Procedure> procedureList,Stage stage){
		
		Label label = new Label("Reports Section");
		VBox vbox = new VBox();
		Label text = new Label("Generate report on all patients and details");
		GridPane.setConstraints(text, 0, 1);
		Button generateReport = new Button("Generate Report");
		GridPane.setConstraints(generateReport, 1, 1);
		Label text2 = new Label("Generate report on all patients who owe money but have not paid");
		GridPane.setConstraints(text2, 0, 2);
		Button generateReport2 = new Button("Generate Report");
		GridPane.setConstraints(generateReport2, 2, 1);
		
		Alert saveQuit = new Alert(AlertType.INFORMATION);
		saveQuit.setTitle("Quiting without saving!");
		saveQuit.setHeaderText(null);
		saveQuit.setContentText("You are quitting the application without saving.\nWould you like to save now?");
		ButtonType saveExit = new ButtonType("Save and exit.");
		ButtonType exit = new ButtonType("Exit without saving.");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);    	
		saveQuit.getButtonTypes().setAll(saveExit,exit,buttonTypeCancel);
		
		
		TabPane tab = new TabPane();
		Tab welcomeScreen = new Tab();
		welcomeScreen.setText("Welcome Screen");	
		Tab addPatient = new Tab();
		addPatient.setText("Patients");	
		Tab procedureView = new Tab();
		procedureView.setText("Procedures");
		Tab invoiceList = new Tab();
		invoiceList.setText("Invoice List");		
		Tab addPayment = new Tab();
		addPayment.setText("Add Payment");		
		Tab reportsView = new Tab();
		reportsView.setText("Reports Section");
		tab.getTabs().add(welcomeScreen);
		tab.getTabs().add(addPatient);
		tab.getTabs().add(procedureView);
		tab.getTabs().add(invoiceList);
		tab.getTabs().add(addPayment);
		tab.getTabs().add(reportsView);
		tab.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		  vbox.setSpacing(5);
		  vbox.setPadding(new Insets(10, 0, 0, 10));
		  vbox.getChildren().addAll(tab,label, text,generateReport,text2,generateReport2);
		  
		  generateReport.setOnAction(e->controller.generateReport1());
		  generateReport2.setOnAction(e -> controller.generateReport2());
		  
		    welcomeScreen.setOnSelectionChanged(e -> controller.application(scene,tab));
			addPatient.setOnSelectionChanged(e -> controller.patient(dentistList, scene,  tab));
		    procedureView.setOnSelectionChanged(e -> controller.procedure(dentistList,procedureList, scene,  tab));
			invoiceList.setOnSelectionChanged(e -> controller.invoice(dentistList, scene, tab));
			addPayment.setOnSelectionChanged(e -> controller.payment(dentistList, scene,  tab));
			//reportsView.setOnSelectionChanged(e -> controller.reports(patientList, dentistList, procedureList, tab));
			
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent event) {
			    	Optional<ButtonType> result = saveQuit.showAndWait();
			    	
			    	if(result.get() == 	saveExit){		    		
			    		controller.saveSession();
			    		stage.close();
			    	}
			    	else if(result.get() == exit){
			    		stage.close();
			    	}
			    	else{
			    		return;
			    	}
			    }
			});
		
		  scene = new Scene(vbox, 700,550);
		
		
	}
	
	
}
