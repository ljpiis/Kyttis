package varaus;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {

	
	// --------- Methods for changing the scene, when a button is clicked ---------
	
	//naming convention: changeTo[wanted scene name], for example: changeToLogin
	
	public void changeToUserView(ActionEvent event) throws IOException {
		changeScene(event, "view/UserView00.fxml");
	}
	
	public void changeToLogin(ActionEvent event) throws IOException {
		changeScene(event, "view/Ruutu00.fxml");
	}
	
	
	// A general code for changing the scene in the window. Parameters are the ActionEvent and the
	//name of the scene that you want to display.
	public void changeScene(ActionEvent event, String sceneName) throws IOException {
		
		Parent wantedScene = FXMLLoader.load(getClass().getResource(sceneName));
		Scene newScene = new Scene (wantedScene);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(newScene);
		window.show();
		
	}
	
}
