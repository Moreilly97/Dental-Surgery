package dentalSurgery;
import java.util.ArrayList;
import java.util.Optional;

import dentalSurgery.Dentist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class application_view {
	Scene scene;
	 ObservableList<Patient> patients = FXCollections.observableArrayList();
	 ObservableList<Procedure> procedures = FXCollections.observableArrayList();
	 
	public application_view(BaseController controller,ArrayList<Patient> patientList,ArrayList<Dentist> dentistList,ArrayList<Procedure> procedureList,Stage stage){
		VBox mainVbox = new VBox();
		mainVbox.setPadding(new Insets(10, 0, 0, 10));
		HBox top = new HBox();
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10,10,10,10));
		hbox.setSpacing(10);
		

		Alert saveQuit = new Alert(AlertType.INFORMATION);
		saveQuit.setTitle("Quiting without saving!");
		saveQuit.setHeaderText(null);
		saveQuit.setContentText("You are quitting the application without saving.\nWould you like to save now?");
		ButtonType saveExit = new ButtonType("Save and exit.");
		ButtonType exit = new ButtonType("Exit without saving.");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);    	
		saveQuit.getButtonTypes().setAll(saveExit,exit,buttonTypeCancel);
		
		Text t = new Text (25, 20, "Welcome to your control panel!");
		t.setFont(Font.font(null, FontWeight.BOLD, 30));
		Text description = new Text(20,20, "From here you can access your patient list, procedures and payments.");
		
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

		        
		
		scene = new Scene(mainVbox, 700,550);
		
		hbox.getChildren().addAll(description);
		mainVbox.getChildren().addAll(top,tab,t,hbox);
		
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
		
		//welcomeScreen.setOnSelectionChanged(e -> controller.application(scene,tab));
		addPatient.setOnSelectionChanged(e -> controller.patient(dentistList, scene,  tab));
		procedureView.setOnSelectionChanged(e -> controller.procedure(dentistList,procedureList, scene,  tab));
		invoiceList.setOnSelectionChanged(e -> controller.invoice(dentistList, scene, tab));
		addPayment.setOnSelectionChanged(e -> controller.payment(dentistList, scene,  tab));
		procedureView.setOnSelectionChanged(e -> controller.procedure(dentistList,procedureList, scene,  tab));
		reportsView.setOnSelectionChanged(e -> controller.reports(patientList, dentistList, procedureList, tab));
		
		
		
		}

}
