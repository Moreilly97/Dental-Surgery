package dentalSurgery;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class login_view {
    Scene scene;
    ArrayList<Dentist> dentistList;
    
    public login_view(BaseController controller, ArrayList<Dentist> dentistList) {
      
   
        this.dentistList = dentistList;
        
        Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Login Failed");
    	alert.setHeaderText(null);
    	alert.setContentText("Could not login.\nPlease fill in all fields.");    	
    	
        
		Button login = new Button("Login");
		Button newDentist = new Button("Add a New Dentist");
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
		GridPane.setConstraints(pNo, 0, 1);
		
		TextField pNoInput = new TextField();
		pNoInput.setPromptText("phone number");
		GridPane.setConstraints(pNoInput, 1, 1);
                
        Label pass = new Label("Enter your password:");
		GridPane.setConstraints(pass, 0, 1);
		
		TextField passwordInput = new TextField();
		passwordInput.setPromptText("password");
		GridPane.setConstraints(passwordInput, 1, 1);
                
        GridPane.setConstraints(login, 1, 2);
		GridPane.setConstraints(newDentist, 1, 3);
                
         scene = new Scene(pane, 400, 350);
          pane.setAlignment(Pos.CENTER);
          pane.getChildren().addAll(name, nameInput, pass, passwordInput, login, newDentist);
                
        newDentist.setOnAction(e -> controller.addDentist(nameInput.getText(), addressInput.getText(), pNoInput.getText(), passwordInput.getText()));
		login.setOnAction(e -> {
        	if(nameInput.getText().isEmpty() || passwordInput.getText().isEmpty()){
        		alert.showAndWait();
        	}
        	else{
        		controller.loginDentist(nameInput.getText(), passwordInput.getText());
                }
        });
                
       
    }
    
    public Scene getScene() {
        return scene;
    }
}
