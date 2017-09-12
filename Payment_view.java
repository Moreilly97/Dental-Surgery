package dentalSurgery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Payment_view {
	Scene scene;
	
	public Payment_view(BaseController controller,ArrayList<Patient> patientList,ArrayList<Dentist> dentistList,ArrayList<Invoice> invoiceList1,ArrayList<Procedure> procedureList,Stage stage){
		
		Label label = new Label("Payments List");
		VBox vbox = new VBox();
		TableView<Payment> table;
		TextField amountPayment;
		
		Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Adding procedure failed");
    	alert.setHeaderText(null);
    	alert.setContentText("Creation of the new Procedure has failed.\nPlease fill in all fields.");  
    	
    	Alert saveQuit = new Alert(AlertType.INFORMATION);
		saveQuit.setTitle("Quiting without saving!");
		saveQuit.setHeaderText(null);
		saveQuit.setContentText("You are quitting the application without saving.\nWould you like to save now?");
		ButtonType saveExit = new ButtonType("Save and exit.");
		ButtonType exit = new ButtonType("Exit without saving.");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);    	
		saveQuit.getButtonTypes().setAll(saveExit,exit,buttonTypeCancel);
    	
    	TableColumn<Payment,Integer> noColumn = new TableColumn<>("Payment No");    	
    	noColumn.setMinWidth(150);
		noColumn.setCellValueFactory(new PropertyValueFactory<>("paymentNo"));
    	TableColumn<Payment, LocalDate> dateColumn = new TableColumn<>("Payment Date");
    	dateColumn.setMinWidth(150);
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));  
		TableColumn<Payment,Double> costColumn = new TableColumn<>("Payment Amount");    	
    	costColumn.setMinWidth(150);
		costColumn.setCellValueFactory(new PropertyValueFactory<>("paymentAmt"));
		TableColumn<Payment, Boolean> isPaidColumn = new TableColumn<>("Fully Paid");    	
		isPaidColumn.setMinWidth(150);
		isPaidColumn.setCellValueFactory(new PropertyValueFactory<>("isPaid"));
		
		table = new TableView<>();
		table.setItems(controller.displayPayments(dentistList,invoiceList1));
		table.getColumns().addAll(noColumn, dateColumn, costColumn,isPaidColumn);
		table.setPadding(new Insets(10,0,0,10));
		
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
		 vbox.getChildren().addAll(tab,label, table);
		  
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
		 
		 	welcomeScreen.setOnSelectionChanged(e -> controller.application(scene,tab));
			addPatient.setOnSelectionChanged(e -> controller.patient(dentistList, scene,  tab));
			procedureView.setOnSelectionChanged(e -> controller.procedure(dentistList,procedureList, scene,  tab));
			invoiceList.setOnSelectionChanged(e -> controller.invoice(dentistList, scene, tab));
			//addPayment.setOnSelectionChanged(e -> controller.payment(dentistList, scene,  tab));
			reportsView.setOnSelectionChanged(e -> controller.reports(patientList, dentistList, procedureList, tab));
			
		 scene = new Scene(vbox, 700,550);
		
	}

}


