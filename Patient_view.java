package dentalSurgery;
import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Patient_view {

	ArrayList<Dentist> dentistList;
	ArrayList<Procedure> procedureList;
	Scene scene;
	Stage window;
	
	@SuppressWarnings("unchecked")
	public Patient_view(BaseController controller,ArrayList<Patient> patientList,ArrayList<Dentist> dentistList,Stage stage){
	
		window = stage;
		Label label = new Label("Patient List");
		VBox vbox = new VBox();
		TableView<Patient> table;
		TextField nameInput,addressInput,numberInput;
		
		 
		Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Adding patient failed");
    	alert.setHeaderText(null);
    	alert.setContentText("Creation of the new Patient has failed.\nPlease fill in all fields."); 
    	
    	Alert saveQuit = new Alert(AlertType.INFORMATION);
		saveQuit.setTitle("Quiting without saving!");
		saveQuit.setHeaderText(null);
		saveQuit.setContentText("You are quitting the application without saving.\nWould you like to save now?");
		ButtonType saveExit = new ButtonType("Save and exit.");
		ButtonType exit = new ButtonType("Exit without saving.");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);    	
		saveQuit.getButtonTypes().setAll(saveExit,exit,buttonTypeCancel);
    	
		
		
		TableColumn<Patient, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(150);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<Patient, String> addressColumn = new TableColumn<>("address");
		addressColumn.setMinWidth(200);
		addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
		TableColumn<Patient, String> numberColumn = new TableColumn<>("Phone Number");
		numberColumn.setMinWidth(200);
		numberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		
		table = new TableView<>();
		table.setItems(controller.displayPatient(dentistList));
		table.getColumns().addAll(nameColumn, addressColumn, numberColumn);
		table.setPadding(new Insets(10,0,0,10));
		
		nameInput = new TextField();
		nameInput.setPromptText("Name");
		nameInput.setMinWidth(100);
		
		addressInput = new TextField();
		addressInput.setPromptText("Address");
		//addressInput.setMinWidth(200);
		
		numberInput = new TextField();
		numberInput.setPromptText("Phone Number");
		//numberInput.setMinWidth(200);
		
		Button addNew = new Button("Add Patient");
		Button delete = new Button("Delete Patient");
		
		
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10,10,10,10));
		hbox.setSpacing(10);
		hbox.getChildren().addAll(nameInput,addressInput,numberInput,addNew,delete);	
		
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
	    vbox.getChildren().addAll(tab,label, table, hbox);
	
	
		addNew.setOnAction(e ->	{		
			if(nameInput.getText().isEmpty() || addressInput.getText().isEmpty() || numberInput.getText().isEmpty()){
        		alert.showAndWait();
        	}
        	else{
				controller.createPatient(controller,dentistList, nameInput.getText(), addressInput.getText(),numberInput.getText());
				nameInput.clear();
				addressInput.clear();
				numberInput.clear();
        	}
			});
		delete.setOnAction(e -> {
			ObservableList<Patient> patientSelected, allPatients;
			allPatients = table.getItems();			
			Patient patient = table.getSelectionModel().getSelectedItem();
			patientSelected = table.getSelectionModel().getSelectedItems();
			controller.deletePatient(dentistList,patient.getName(),patient.getAddress(),patient.getPhoneNumber());
			patientSelected.forEach(allPatients::remove);
		});	
				welcomeScreen.setOnSelectionChanged(e -> controller.application(scene,tab));
			//	addPatient.setOnSelectionChanged(e -> controller.patient(dentistList, scene,  tab));
				procedureView.setOnSelectionChanged(e -> controller.procedure(dentistList,procedureList, scene,  tab));
				invoiceList.setOnSelectionChanged(e -> controller.invoice(dentistList, scene, tab));
				addPayment.setOnSelectionChanged(e -> controller.payment(dentistList, scene,  tab));
				reportsView.setOnSelectionChanged(e -> controller.reports(patientList, dentistList, procedureList, tab));
				
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
