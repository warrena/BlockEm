package pong;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.util.Timer;
import java.util.TimerTask;

public class Controller implements EventHandler<KeyEvent> {
    final private double FRAMES_PER_SECOND = 20.0;

    @FXML private Button pauseButton;
    @FXML private GridPane grid;
    @FXML private Label scoreLabel;
    @FXML private AnchorPane gameBoard;
    @FXML private Rectangle paddle;
    @FXML private Ball ball;

    private int score;
    private boolean paused;
    private Timer timer;

    public Controller() {
        this.paused = false;
        this.score = 0;

        //grid.add(new Button(), 1, 0);
       // grid.add(new Label(), 3, 1);  // column=3 row=1

        Button button = new Button();
        grid.setRowIndex(button, 0);
        grid.setColumnIndex(button, 0);
        button.setText("Jerimiah");

    }

    public void initialize() {
        grid.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent arg0) {
                System.out.println("Hello"+ arg0.getX() +"Y Value" + arg0.getY());
            }

        });

        final Label l1 = new Label("  ");
        final Label l2 = new Label("  ");
        final Label l3 = new Label("  ");


        l1.setOnMouseClicked(new EventHandler<MouseEvent>()
        {

            @Override
            public void handle(MouseEvent arg0)
            {
                l1.setStyle("-fx-background-color:yellow;");

            }
        });

        l2.setOnMouseClicked(new EventHandler<MouseEvent>()
        {

            @Override
            public void handle(MouseEvent arg0)
            {
                l2.setStyle("-fx-background-color:yellow;");

            }
        });


        l3.setOnMouseClicked(new EventHandler<MouseEvent>()
        {

            @Override
            public void handle(MouseEvent arg0)
            {
                l3.setStyle("-fx-background-color:yellow;");

            }
        });


        grid.addRow(0, l1, l2, l3);
    }

    @FXML
    private void mouseEntered(MouseEvent e) {
        Node source = (Node)e.getSource() ;
        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);
        System.out.printf("Mouse entered cell [%d, %d]%n", colIndex.intValue(), rowIndex.intValue());
    }


    @Override
    public void handle(KeyEvent keyEvent) {

    }

    @FXML
    private void testClicked(MouseEvent e){
        Node src = (Node)e.getSource();
        System.out.println("Row: "+ grid.getRowIndex(src));
        System.out.println("Column: "+ grid.getColumnIndex(src));
    }

    public void onPauseButton(ActionEvent actionEvent) {

    }

    public void onNewBallButton(ActionEvent actionEvent) {

    }
}
