/*
    Here is a program that plays the venerable game of tic-tac-toe. When launched, the program starts with a login
    screen that asks for user names. The user names are displayed on the game board, which also has a score counter
    to show how many times a player has won. You will see a start button that allows a player to click tiles on the
    game board: player 1 marks the tiles with X, and player 2 marks the tiles with O. But the start button is disabled
    when a game is in session. When a game is over, a prompt will either announce a winner or a tie.
 */

package tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    // Setup the scene and then start the login screen (LoginScene.fxml).
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
        primaryStage.setTitle("Tic-Tac-Toe Login Screen");
        primaryStage.setScene(new Scene(root, 500, 150));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
