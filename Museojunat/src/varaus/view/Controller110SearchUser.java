package varaus.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
        	changeScene(event, "view/100AdminView.fxml");
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
	    	// Called right after the fxml file has been loaded. 
	    	// At this time all the fxml fields should have been initialized already.
	        // Initialize the user table with the two columns.
	    	// Using a Java 8 feature called Lambdas
	        userIdColumn.setCellValueFactory(cellData -> cellData.getValue().userIdProperty());
	        nameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
	        
	        // Clear person details.
	        showUserDetails(null);

	        // Listen for selection changes and show the person details when changed.
	        // Gets the selecterItemProperty of the person table and adds a listener to it.
	        // When a user is selected from the table, a lambda expression is executed and
	        // the user infor selected is passed to the showUserDetails() method
	        userTable.getSelectionModel().selectedItemProperty().addListener(
	                (observable, oldValue, newValue) -> showUserDetails(newValue));
	    }

	    /**
	     * Is called by the main application to give a reference back to itself.
	     * 
	     * @param mainApp
	     */
	    public void setMainApp(MainApp mainAppi) {
	        this.mainApp = mainAppi;
	        // Add observable list data to the table
	        userTable.setItems(mainApp.getUserData());
	    }
	    
	    /**
	     * Fills all text fields to show details about the user.
	     * If the specified user is null, all text fields are cleared.
	     * 
	     * @param user the user or null
	     */
	    private void showUserDetails(User user) {
	        if (user != null) {
	            // Fill the labels with info from the user object.
	            userIdLabel.setText(user.getUserId());
	            nameLabel.setText(user.getLastName());
	            adminLabel.setText(user.getSAdmin());
	            passwordLabel.setText(user.getPassword());

	        } else {
	            // user is null, remove all the text.
	            userIdLabel.setText("");
	            nameLabel.setText("");
	            adminLabel.setText("");
	            passwordLabel.setText("");
	        }
	    }
	    
	    /**
	     * Called when the user clicks on the delete button.
	     * atm does not have a dialog to inform the user whenever they
	     * click on a line that does not refer to a user
	     */
	    @FXML
	    private void handleDeleteUser() {
	        int selectedIndex = userTable.getSelectionModel().getSelectedIndex();
	        if (selectedIndex >= 0) {
	        	userTable.getItems().remove(selectedIndex);
	        } else {
	        	//Nothing selected
	        	Alert alert = new Alert(AlertType.WARNING);
	            alert.initOwner(mainApp.getPrimaryStage());
	            alert.setTitle("No Selection");
	            alert.setHeaderText("No Person Selected");
	            alert.setContentText("Please select a person in the table.");

	            alert.showAndWait();
	        }
	    }
	    
	    
	    /**
	     * Called when the user clicks the new button. Opens a dialog to edit
	     * details for a new person.
	     */
	    @FXML
	    private void handleNewUser() {
	        User tempUser = new User();
	        boolean okClicked = mainApp.showUserEditDialog(tempUser);
	        if (okClicked) {
	            mainApp.getUserData().add(tempUser);
	        }
	    }//handleNewUser

	    /**
	     * Called when the user clicks the edit button. Opens a dialog to edit
	     * details for the selected person.
	     */
	    @FXML
	    private void handleEditUser() {
	        User selectedUser = userTable.getSelectionModel().getSelectedItem();
	        if (selectedUser != null) {
	            boolean okClicked = mainApp.showUserEditDialog(selectedUser);
	            if (okClicked) {
	                showUserDetails(selectedUser);
	            }

	        } else {
	            // Nothing selected.
	            Alert alert = new Alert(AlertType.WARNING);
	            alert.initOwner(mainApp.getPrimaryStage());
	            alert.setTitle("No Selection");
	            alert.setHeaderText("No User Selected");
	            alert.setContentText("Please select a user in the table.");

	            alert.showAndWait();
	        }
	    }//handleEditUser
}
