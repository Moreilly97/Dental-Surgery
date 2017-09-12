package dentalSurgery;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class newDentist_view {
    Scene scene;
    ArrayList<Dentist> dentistList;
    public newDentist_view(BaseController controller,ArrayList<Dentist> dentistList) {
    	this.dentistList = dentistList;
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Creation failed");
    	alert.setHeaderText(null);
    	alert.setContentText("Creation of the new dentist has failed.\nPlease fill in all fields.");    	
    	
    	
		Button createDentist = new Button("Create Dentist");
		Button cancel = new Button("Cancel");
		GridPane pane = new GridPane();
    	Label name = new Label("Enter your username:");
		GridPane.setConstraints(name, 0, 0);
		
		TextField nameInput = new TextField("");
		nameInput.setPromptText("username");
		GridPane.setConstraints(nameInput, 1, 0);
		
        
        Label address = new Label("Enter your address:");
		GridPane.setConstraints(address, 0, 1);
		
		TextField addressInput = new TextField();
		addressInput.setPromptText("address");
		GridPane.setConstraints(addressInput, 1, 1);
		
        
        Label pNo = new Label("Enter your phone number:");
		GridPane.setConstraints(pNo, 0, 2);
		
		TextField pNoInput = new TextField();
		pNoInput.setPromptText("password");
		GridPane.setConstraints(pNoInput, 1, 2);
                
        Label pass = new Label("Enter your password:");
		GridPane.setConstraints(pass, 0, 3);
		
		TextField passwordInput = new TextField();
		passwordInput.setPromptText("password");
		GridPane.setConstraints(passwordInput, 1, 3);
     
		
		GridPane.setConstraints(createDentist, 0, 4);
		GridPane.setConstraints(cancel, 1, 4);
		pane.setAlignment(Pos.CENTER);
		
		 scene = new Scene(pane, 400, 350);
	    
	    pane.getChildren().addAll(name, nameInput,address, addressInput, pNo, pNoInput, pass, passwordInput,cancel, createDentist);
        cancel.setOnAction(e -> controller.cancelDentist(scene));
           	
        createDentist.setOnAction(e -> {
        	if(nameInput.getText().isEmpty() || addressInput.getText().isEmpty() || pNo.getText().isEmpty() || passwordInput.getText().isEmpty()){
        		alert.showAndWait();
        	}
        	else{
        		controller.createDentist(nameInput.getText(),addressInput.getText(), pNoInput.getText(), passwordInput.getText());
                }
        });
		
    }
    
    public Scene getScene() {
        return scene;
    }
    
}