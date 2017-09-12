package dentalSurgery;

import java.awt.Event;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Invoice_view {
	
	Scene scene;
	ChoiceBox<String> cb = new ChoiceBox<>();
	ChoiceBox<String> cb2 = new ChoiceBox<>();
	TextArea invoices = new TextArea();
	
	public Invoice_view(BaseController controller,ArrayList<Dentist> dentistList,ArrayList<Procedure> procedureList,ArrayList<Patient> patientList,Stage stage){

			Label label = new Label("Invoice List");
			VBox vbox = new VBox();
			
			TextField initialPay = new TextField();
			initialPay.setPromptText("Amount paid");
	
			DatePicker datePicker = new DatePicker();
			Button addButton = new Button("Add");
			
			 
			Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Adding procedure failed");
	    	alert.setHeaderText(null);
	    	alert.setContentText("Creation of the new Invoice has failed.\nPlease fill in all fields.");  
	    	
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
			
			HBox hbox = new HBox();
			hbox.setPadding(new Insets(10,10,10,10));
			hbox.setSpacing(10);
			HBox text = new HBox();
			text.getChildren().add(invoices);
			hbox.getChildren().addAll(cb,cb2,initialPay,datePicker,addButton);	

			
			vbox.setSpacing(5);
		    vbox.setPadding(new Insets(10, 0, 0, 10));
		    vbox.getChildren().addAll(tab,text,label,hbox);
		    scene = new Scene(vbox, 700,550);
    	
		    welcomeScreen.setOnSelectionChanged(e -> controller.application(scene,tab));
			addPatient.setOnSelectionChanged(e -> controller.patient(dentistList, scene,  tab));
			procedureView.setOnSelectionChanged(e -> controller.procedure(dentistList,procedureList, scene,  tab));
			//invoiceList.setOnSelectionChanged(e -> controller.invoice(dentistList, scene, tab));
			addPayment.setOnSelectionChanged(e -> controller.payment(dentistList, scene,  tab));
			reportsView.setOnSelectionChanged(e -> controller.reports(patientList, dentistList, procedureList, tab));
			
		  addButton.setOnAction(e -> {
			  controller.addInvoice(controller,dentistList,patientList,cb.getValue(),cb2.getValue(),datePicker.getValue(),Double.parseDouble(initialPay.getText()));
			  populateInvoices(controller);
			

		  });
		
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
		  
    	 datePicker.setOnAction(new EventHandler() {
		     public void handle(Event t) {
		         LocalDate date = datePicker.getValue();
		         System.err.println("Selected date: " + date);
		     }

			@Override
			public void handle(javafx.event.Event arg0) {
				
			}
		 });
		
		
	}

	public void populateNames(BaseController controller)
	{
		cb.getItems().clear();
		cb.getItems().addAll(controller.names);
	}
	public void populateProcNames(BaseController controller)
	{
		cb2.getItems().clear();
		cb2.getItems().addAll(controller.procNames);
	}
	public void populateInvoices(BaseController controller){
		invoices.clear();
		invoices.setText(controller.displayInvoices());
	}
	
}
