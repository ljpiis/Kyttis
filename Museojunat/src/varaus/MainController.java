package varaus;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {
	
	public void sayHi() {
		System.out.println("Hi!");
	}

	/**
	 * When this method is called,  it will change the scene
	 * @throws IOException 
	 */
	
	public void changeScene(ActionEvent event) throws IOException {
		Parent tableViewParent = FXMLLoader.load(getClass().getResource("view/UserView00.fxml"));
		Scene tableViewScene = new Scene(tableViewParent);
		
		//This line gets the Stage information
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(tableViewScene);
		window.show();
	}
	
	public void goToLogin(ActionEvent event) throws IOException {
		Parent logInView = FXMLLoader.load(getClass().getResource("view/Ruutu00.fxml"));
		Scene logInScene = new Scene(logInView);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(logInScene);
		window.show();
	}
	
}
