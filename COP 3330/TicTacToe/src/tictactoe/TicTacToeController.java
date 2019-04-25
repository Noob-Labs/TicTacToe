package tictactoe;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class TicTacToeController {
    // setNames is called from the LoginController to set the player names.
    public void setNames(String p1name, String p2name) {
        this.player1name.setText(p1name);
        this.player2name.setText(p2name);
        this.p1Score = 0;
        this.p2Score = 0;
    }

    boolean playerTurn = true;// It's player 1's turn if this is true, and player 2's turn if this is false.
    boolean gameOn = false;// The game board tiles cannot be clicked if this is false, but the start button can be clicked.
    boolean isTie = false;// As long as the board isn't full, this is false. The value is rechecked at the games conclusion.

    static int count = 0;// A tile counter that keeps track of the board (max value of 9, since there are 9 tiles).

    /*
        Array "board" represents the 9 tiles (ignore index 0). Bottom row is 1,2,3, Middle row is 4,5,6, Top row is
        7,8,9 (all from left to right). Player 1 moves will add a value of 1 to a certain index of the array, and player
        2 moves will add a value of 2.
     */
    static int[] board = new int[10];
    static int i = 0;

    // These integers track how many times a player has won.
    static int p1Score;
    static int p2Score;

    // FXML Button declarations.
    @FXML
    public Button button_7, button_8, button_9, button_4, button_5, button_6, button_1, button_2, button_3;
    @FXML
    public Text button_7_Text, button_8_Text, button_9_Text, button_4_Text, button_5_Text, button_6_Text, button_1_Text, button_2_Text, button_3_Text;
    @FXML
    public Text player1name, player1go;
    @FXML
    public Text player2name, player2go;
    @FXML
    public Text player1Score, player2Score;
    @FXML
    private Button buttonStartGame;

    ///////////////////////////////////////////////////////////////////////////
    ///////////////////// TIC TAC TOE CUSTOM METHODS //////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public void buttonStartGameClicked(javafx.scene.input.MouseEvent mouseEvent) {
        /*
            This method is called when the Start button is clicked, and it starts by disabling the Start button. The game
            board is also cleared by calling "clearBoard." Then a local variable named "turn" will choose a random number
            between 0 and 1, which decides who goes first. A players name is highlighted in red when it's their turn to
            move. At the end of this method, "gameOn" is set to true.
         */

        buttonStartGame.setDisable(true);
        clearBoard();
        double turn = 0;// turn must be set to 0 every time, which resets the score if the player closes the game window and chooses new names.
        turn = Math.random();
        if (turn < 0.5) {
            playerTurn = true;
            player1go.setText("X, your turn!");
            player1name.setFill(Color.RED);
            player2name.setFill(Color.BLACK);
        } else {
            playerTurn = false;
            player2go.setText("O, your turn!");
            player2name.setFill(Color.RED);
            player1name.setFill(Color.BLACK);
        }
        gameOn = true;
    }
    private void clicked(Button button, Text button_text, int i) {
        /*
            Players can only click buttons if "gameOn" is true. The clicked button is then disabled and count is incremented by 1.
            The following If statements determine who's turn it is, and then prints an X or an O above the button. Then
            the opposing player will be told "your turn!," and their name colors will be swapped. Then "playerTurn" will have
            its boolean value swapped. "board" then receives a value of 1(player 1), or 2(player 2), at the array index
            that is passed when this method is called. Finally, "isWinner" is called to check for any winning conditions.
         */

        if (gameOn) {
            button.setDisable(true);
            count++;
            if (playerTurn) {
                button_text.setText("X");
                player1go.setText("");
                player2go.setText("O, your turn!");
                player2name.setFill(Color.RED);
                player1name.setFill(Color.BLACK);
                playerTurn = false;
                board[i] = 1;
                isWinner(board, 1);
            } else {
                button_text.setText("O");
                player2go.setText("");
                player1go.setText("X, your turn!");
                player1name.setFill(Color.RED);
                player2name.setFill(Color.BLACK);
                playerTurn = true;
                board[i] = 2;
                isWinner(board, 2);
            }
        }
    }
    private void disableButtons() {
        // Disables all of the buttons, except for the Start button.
        button_7.setDisable(true);
        button_8.setDisable(true);
        button_9.setDisable(true);
        button_4.setDisable(true);
        button_5.setDisable(true);
        button_6.setDisable(true);
        button_1.setDisable(true);
        button_2.setDisable(true);
        button_3.setDisable(true);
        buttonStartGame.setDisable(false);
    }
    private void isBoardFull() {
        /*
            This method checks if "count" is equal to 9, indicating a full board and that no more moves can be made.
            "gameOn" is set to false, limiting the players ability to click buttons. Then the "start" button is enabled,
            allowing a reset of the game. Also, the text below each players name is reset, since it is neither players
            turn anymore.
         */

        if (count == 9) {
            gameOn = false;
            buttonStartGame.setDisable(false);
            player1go.setText("");
            player2go.setText("");
        }
    }
    private void isWinner(int[] board, int x) {
        /*
            When this method is called, it checks the If statements for any winning conditions. When an If statement is true,
            "announceWinner" is called. The final Else statement will check if the board is full, and if nobody wins the
            game then a Tie is declared to the players.
         */

        if (board[7] == x && board[8] == x && board[9] == x) {// Across the top.
            announceWinner();
        } else if (board[4] == x && board[5] == x && board[6] == x) {// Across the middle.
            announceWinner();
        } else if (board[1] == x && board[2] == x && board[3] == x) {// Across the bottom.
            announceWinner();
        } else if (board[7] == x && board[4] == x && board[1] == x) {// Down the left side.
            announceWinner();
        } else if (board[8] == x && board[5] == x && board[2] == x) {// Down the middle.
            announceWinner();
        } else if (board[9] == x && board[6] == x && board[3] == x) {// Down the right side.
            announceWinner();
        } else if (board[7] == x && board[5] == x && board[3] == x) {// Diagonal.
            announceWinner();
        } else if (board[9] == x && board[5] == x && board[1] == x) {// Diagonal.
            announceWinner();
        } else {
            if (count == 9) {
                isTie = true;
                player1go.setText("");
                player2go.setText("");
                Alert a = new Alert(Alert.AlertType.INFORMATION, "NOBODY! It's a tie! Will you try again?");
                a.setTitle("aaaaand the winner is...");
                a.showAndWait();
            }
        }
    }
    private void announceWinner() {
        /*
            If this method is called that means someone won the game. "gameOn" is set to false and "isTie" is set to false,
            since a Tie would be impossible at this point. Then the "Start" button and text below player names are reset.
            If it is player 1's turn, then it must be player 2 that won the game, and vice versa. "disableButtons" is called
            and an Alert window will announce the winner.
         */

        gameOn = false;
        isTie = false;
        buttonStartGame.setDisable(false);
        player1go.setText("");
        player2go.setText("");
        if (playerTurn) {
            displayWinner(player2name.getText());
            p2Score++;
            player2Score.setText("" + p2Score);
        } else {
            displayWinner(player1name.getText());
            p1Score++;
            player1Score.setText("" + p1Score);
        }
    }
    private void displayWinner(String playerName) {
        disableButtons();
        Alert a = new Alert(Alert.AlertType.INFORMATION, "The winner is " + playerName + "! Will you try again?");
        a.setTitle("WE HAVE A WINNER!");
        a.showAndWait();
    }
    private void clearBoard() {
        // The name says it all. Reset all buttons, count, and the board array.
        button_7.setDisable(false);
        button_8.setDisable(false);
        button_9.setDisable(false);
        button_4.setDisable(false);
        button_5.setDisable(false);
        button_6.setDisable(false);
        button_1.setDisable(false);
        button_2.setDisable(false);
        button_3.setDisable(false);
        count = 0;
        button_7_Text.setText("");
        button_8_Text.setText("");
        button_9_Text.setText("");
        button_4_Text.setText("");
        button_5_Text.setText("");
        button_6_Text.setText("");
        button_1_Text.setText("");
        button_2_Text.setText("");
        button_3_Text.setText("");
        player1go.setText("");
        player2go.setText("");
        for (int x = 0; x < 10; x++) {
            board[x] = 0;
        }
    }

    //////////////////////////////////////////////////////////////////////////
    /////////////////////// TIC TAC TOE BUTTONS //////////////////////////////
    //////////////////////////////////////////////////////////////////////////

    /*  Each of the buttons use the same methods.
        They each pass a unique value, "i", depending on the buttons location.
        Lastly, the "isBoardFull" method is called, to check if any more
        moves are possible.
        Here is a visual representation of the board...

                    7 8 9
                    4 5 6
                    1 2 3
     */

    //////////////////////////////////////////////////////////////////////////
    //////////////////////////// TOP ROW /////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

    public void button_7_clicked(javafx.scene.input.MouseEvent mouseEvent) {
        i = 7;
        clicked(button_7, button_7_Text, i);
        isBoardFull();
    }
    public void button_8_clicked(javafx.scene.input.MouseEvent mouseEvent) {
        i = 8;
        clicked(button_8, button_8_Text, i);
        isBoardFull();
    }
    public void button_9_clicked(javafx.scene.input.MouseEvent mouseEvent) {
        i = 9;
        clicked(button_9, button_9_Text, i);
        isBoardFull();
    }

    //////////////////////////////////////////////////////////////////////////
    /////////////////////////// MIDDLE ROW ///////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

    public void button_4_clicked(javafx.scene.input.MouseEvent mouseEvent) {
        i = 4;
        clicked(button_4, button_4_Text, i);
        isBoardFull();
    }
    public void button_5_clicked(javafx.scene.input.MouseEvent mouseEvent) {
        i = 5;
        clicked(button_5, button_5_Text, i);
        isBoardFull();
    }
    public void button_6_clicked(javafx.scene.input.MouseEvent mouseEvent) {
        i = 6;
        clicked(button_6, button_6_Text, i);
        isBoardFull();
    }

    //////////////////////////////////////////////////////////////////////////
    ////////////////////////// BOTTOM ROW ////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

    public void button_1_clicked(javafx.scene.input.MouseEvent mouseEvent) {
        i = 1;
        clicked(button_1, button_1_Text, i);
        isBoardFull();
    }
    public void button_2_clicked(javafx.scene.input.MouseEvent mouseEvent) {
        i = 2;
        clicked(button_2, button_2_Text, i);
        isBoardFull();
    }
    public void button_3_clicked(javafx.scene.input.MouseEvent mouseEvent) {
        i = 3;
        clicked(button_3, button_3_Text, i);
        isBoardFull();
    }

    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
}
