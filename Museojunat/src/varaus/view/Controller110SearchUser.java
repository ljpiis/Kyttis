package varaus.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import varaus.MainApp;
import varaus.model.User;

public class Controller110SearchUser {

	
	 @FXML
	    private TableView<User> userTable;
	    @FXML
	    private TableColumn<User, String> userIdColumn;
	    @FXML
	    private TableColumn<User, String> nameColumn;

	    @FXML
	    private Label userIdLabel;
	    @FXML
	    private Label nameLabel;
	    @FXML
	    private Label adminLabel;
	    @FXML
	    private Label passwordLabel;
    	@FXML
        private Button to100AdminView;
        @FXML
        public void toAdminView(ActionEvent event) throws IOException {
        	changeScene(event, "100AdminView.fxml");
        }
    	// A general code for changing the scene in the window. Parameters are the ActionEvent (button click) and the
    	//name of the scene that you want to display.
    	public void changeScene(ActionEvent event, String sceneName) throws IOException {
    		
    		Parent wantedScene = FXMLLoader.load(getClass().getResource(sceneName));
    		Scene newScene = new Scene (wantedScene);
    		
    		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
    		
    		window.setScene(newScene);
    		window.show();
    		
    	}
    	
	    // Reference to the main application.
	    private MainApp mainApp;

	    /**
	     * The constructor.
	     * The constructor is called before the initialize() method.
	     */
	    public Controller110SearchUser() {
	    }

	    /**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	     */
	    @FXML
	    private void initialize() {
	        // Initialize the person table with the two columns.
	        userIdColumn.setCellValueFactory(cellData -> cellData.getValue().userIdProperty());
	        nameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
	    }

	    /**
	     * Is called by the main application to give a reference back to itself.
	     * 
	     * @param mainApp
	     */
	    public void setMainApp(MainApp mainApp) {
	        this.mainApp = mainApp;
	        // Add observable list data to the table
	        userTable.setItems(mainApp.getUserData());
	    }
	
	
}
