package tictactoe;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {

    @FXML
    public TextField player1NameTextField;
    @FXML
    public TextField player2NameTextField;
    @FXML
    private Button cancelButton;
    @FXML
    private Button startButton;

    // The Start button won't work unless player names have been typed in.
    public void startButtonClicked(javafx.scene.input.MouseEvent mouseEvent) throws IOException {

        // If either of the NameTextField's are empty then this error will display, prompting players to enter a name.
        if (player1NameTextField.getText().length() == 0 || player2NameTextField.getText().length() == 0) {
            Alert a = new Alert(Alert.AlertType.ERROR, "You must enter a username for each player.");
            a.setTitle("Missing Player Name");
            a.showAndWait();
        } else { // If both players enter their names then the game begins.

            // Get the text from each NameTextField and place them into their own string variables, which get passed to the game board.
            String player1Name = player1NameTextField.getText();
            String player2Name = player2NameTextField.getText();

            // Start an FXML loader. I don't know why a try/catch statement is needed, but without that the program would crash.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("GameBoardScene.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

            /*
                Create a controller object that calls the setNames method from the TicTacToeController.
                This is how the names are setup.
             */
            TicTacToeController controller = loader.getController();
            controller.setNames(player1Name, player2Name);

            // Finish setting up the scene and start the game.
            Parent p = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(p));
            stage.showAndWait();
        }
    }

    // Exits the program.
    public void cancelButtonClicked(javafx.scene.input.MouseEvent mouseEvent) { System.exit(0);}
}
