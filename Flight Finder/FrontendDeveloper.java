// --== CS400 File Header Information ==--
// Name: James Cui
// Email: jcui57@wisc.edu
// Group and Team: BCred
// Group TA: Naman Gupta
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.RowConstraints;
import javafx.application.Platform;
import javafx.scene.text.FontWeight;
import javafx.geometry.Pos;

import java.io.FileNotFoundException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Random;
import javafx.scene.control.TextField;

public class FrontendDeveloper extends Application implements FrontendInterface {
    /*
    public FrontendDeveloper(BackendInterface backend) {

    } */
    AirportNetworkReaderInterface anr = new AirportNetworkReaderDW();
    public BackendDeveloper backend = new BackendDeveloper(anr); // input backend for FD
    public Text bottomText; 
    
    /**
     * The start method in the Frontend Developer starts the JavaFX code
     * which allows the application to begin running.
     */
    @Override
    public void start(final Stage stage) {
        // Step 3 & 4
        stage.setTitle("Flight Finder 1.0");

        TextField firstValue = new TextField();
        // creates the welcome button
        Button buttonWel = new Button("Click Me to Begin!");
        buttonWel.setFont(new Font("Arial", 21));
        buttonWel.setId("buttonWel");
        buttonWel.setMaxWidth(660);
        buttonWel.textFillProperty();
        buttonWel.setFocusTraversable(false);
        // sets the welcome message and ask for file message
        Label welcomeMsg = new Label("\n \n Welcome to the Flight Finder App!");
        Label askFile = new Label("\n \n \n \n \nPlease enter the data file you would like to load below \n");
    askFile.setId("askFile");
    welcomeMsg.setId("welcomeMsg");
        welcomeMsg.setFont(new Font("Arial", 32));
        // formats size and format of welcome menu
        buttonWel.setPrefHeight(150);
        buttonWel.setPrefWidth(265);
        VBox vbox = new VBox(askFile, firstValue);
        BorderPane bpane = new BorderPane();
        bpane.setTop(welcomeMsg);
        bpane.setBottom(buttonWel);
        bpane.setCenter(vbox);
        BorderPane.setAlignment(askFile, Pos.TOP_CENTER);
        BorderPane.setAlignment(welcomeMsg, Pos.CENTER);
        buttonWel.setOnAction(event -> {

            String fileData = firstValue.getText();
            
            try {
                backend.loadFile(fileData);
                stage.setScene(scene2());

            }
            catch(FileNotFoundException fnfe) {
                Label error = new Label("\nERROR: File Not Found");
                vbox.getChildren().add(error);
            }
            
        });

        Scene sceneOne = new Scene(bpane, 660, 560);

        stage.setScene(sceneOne);
        stage.show();
    }
    /*
    private Scene scene1() {
        Button button1 = new Button("Click Me to Begin!");
        Label welcomeMsg = new Label("Welcome to the Flight Finder Application!");
        button1.setPrefHeight(150);
        button1.setPrefWidth(265);
        button1.setOnAction(event -> {
            stage.setScene(scene1());
        });
        VBox vbox = new VBox(welcomeMsg, button1);
        Scene sceneOne = new Scene(vbox, 500, 300);
    }
    */
    private Scene scene2() {
        // creates a Pane for scene for Application menu
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 660, 560);
        
        // sets up the exit button
        Button exitButton = new Button("Exit");
        exitButton.requestFocus();
        exitButton.setOnAction(event -> {
            Platform.exit();
        });
        exitButton.setPrefWidth(105);
        exitButton.setPrefHeight(60);
        exitButton.setStyle("-fx-background-color: TOMATO");
        bottomText = new Text("");
        bottomText.setId("bottomText");
        HBox botHBox = new HBox(50, exitButton, bottomText);
        borderPane.setBottom(botHBox);
        
        exitButton.setId("exitButton");
 
        // Creates the 4 buttons for each filter
        Button button1 = new Button("Minimum Distance");
        button1.setPrefHeight(50);
        button1.setPrefWidth(165);
        button1.setId("button1");
        Button button2 = new Button("Minimum Distance +");
        button2.setPrefHeight(50);
        button2.setPrefWidth(165);
        button2.setId("button2");
        Button button3 = new Button("Minimum Stops");
        button3.setPrefHeight(50);
        button3.setPrefWidth(165);
        button3.setId("button3");
        Button button4 = new Button("Minimum Stops +");
        button4.setPrefHeight(50);
        button4.setPrefWidth(165);
        button4.setId("button4");
        
        HBox hbox = new HBox(button1, button2, button3, button4);
        borderPane.setTop(hbox);

        Text howToUse = new Text("Click on the Option You Would Like to Travel");
        howToUse.setY(300);
        howToUse.setX(200);
        
        // Action for first button
        button1.setOnAction(event -> {
            
            bottomText.setText("");
            TextField start = new TextField();
	    start.setId("start");
            Text textStart = new Text("\n \n \n Enter the IATA of the Airport you will like to Travel From");
            VBox combine = new VBox(textStart, start);
            TextField dest = new TextField();
	    dest.setId("dest");
            Text textDest = new Text("\n \n Enter the IATA of the Airport you will like to Travel To");
            VBox combine2 = new VBox(textDest, dest);
            Button goBut = new Button("Search!");
            goBut.setId("goBut1");
            goBut.setPrefWidth(100);
            goBut.setPrefHeight(30);
            goBut.setStyle("-fx-background-color: LawnGreen");
            Text result1 = new Text("\n \n \n \n \n \n \n \n \n \nFastest Path by Distance");
            result1.setId("result1");
            result1.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            VBox temp = new VBox(result1);
            temp.setAlignment(Pos.CENTER);
            VBox vbox2 = new VBox(combine, combine2, goBut, temp);
            borderPane.setCenter(vbox2);

            goBut.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        bottomText.setText("");
                        bottomText.setText(backend.findQuickestPath(start.getText(), dest.getText()).toString());
                        bottomText.setFont(Font.font("OpenSymbol", FontWeight.BOLD, 16));
                        bottomText.setX(300);
                        
                        //GridPane grid = new GridPane();
                        //botHBox.getChildren().add(bottomText);
                        //borderPane.setBottom(bottom);
                    }
                    catch(Exception e) {
                        bottomText.setText("\nERROR: Invalid Airport(s)");
                    }
                }   
            });
        });

        // Action for second button
        button2.setOnAction(event -> {

            bottomText.setText("");
            TextField start = new TextField();
	        start.setId("start");
            Text textStart = new Text("\n \n \n Enter the IATA of the Airport you will like to Travel From");
        textStart.setId("textStart");
            VBox combine = new VBox(textStart, start);
            TextField dest = new TextField();
	        dest.setId("dest");
            Text textDest = new Text("\n \n Enter the IATA of the Airport you will like to Travel To");
        textDest.setId("textDest");
            VBox combine2 = new VBox(textDest, dest);
            TextField middle = new TextField();
	        middle.setId("middle");
            Text textMiddle = new Text("\n \n Enter the IATA of the Airport you will like to Pass Through");
        textMiddle.setId("textMiddle");
            VBox combine3 = new VBox(textMiddle, middle);
            Button goBut = new Button("Search!");
            goBut.setId("goBut2");
            goBut.setPrefWidth(100);
            goBut.setPrefHeight(30);
            goBut.setStyle("-fx-background-color: LawnGreen");
            Text result2 = new Text("\n \n \n \n \n \n \n Fastest Path by Distance Adv.");
            result2.setId("result2");
            result2.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            VBox temp = new VBox(result2);
            temp.setAlignment(Pos.CENTER);
            VBox vbox2 = new VBox(combine, combine2, combine3, goBut, temp);
            borderPane.setCenter(vbox2);

            goBut.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        bottomText.setText("");
                        bottomText.setText((backend.findQuickestPath(start.getText(), middle.getText(), dest.getText())).toString());
                        bottomText.setFont(Font.font("OpenSymbol", FontWeight.BOLD, 16));
                        bottomText.setX(300);
                        
                        //GridPane grid = new GridPane();
                        //botHBox.getChildren().add(bottomText);
                        //borderPane.setBottom(bottom);
                    }
                    catch(Exception e) {
                        bottomText.setText("\nERROR: Invalid Airport(s)");
                    }
                }    
            });
        });

        // Action for third button
        button3.setOnAction(event -> {
            bottomText.setText("");
            TextField start = new TextField();
	        start.setId("start");
            Text textStart = new Text("\n \n \n Enter the IATA of the Airport you will like to Travel From");
        textStart.setId("textStart");
            VBox combine = new VBox(textStart, start);
            TextField dest = new TextField();
	        dest.setId("dest");
            Text textDest = new Text("\n \n Enter the IATA of the Airport you will like to Travel To");
        textDest.setId("textDest");
            VBox combine2 = new VBox(textDest, dest);
            Button goBut = new Button("Search!");
            goBut.setId("goBut3");
            goBut.setPrefWidth(100);
            goBut.setPrefHeight(30);
            goBut.setStyle("-fx-background-color: LawnGreen");
            Text result3 = new Text("\n \n \n \n \n \n \n \n \n \nFastest Path by Stops");
            result3.setId("result3");
            result3.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            VBox temp = new VBox(result3);
            temp.setAlignment(Pos.CENTER);
            VBox vbox2 = new VBox(combine, combine2, goBut, temp);
            borderPane.setCenter(vbox2);

            goBut.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        bottomText.setText("");
                        //System.out.println(textStart.getText());
                        //System.out.println(textDest.getText());

                        bottomText.setText(backend.findFastestPath(start.getText(), dest.getText()).toString() + "\nMinimum Stops: " + (backend.findFastestPath(start.getText(), dest.getText()).size()-2));
                        bottomText.setFont(Font.font("OpenSymbol", FontWeight.BOLD, 16));
                        
                        //GridPane grid = new GridPane();
                        //botHBox.getChildren().add(bottomText);
                        //borderPane.setBottom(bottom);
                    }
                    catch(Exception e) {
                        bottomText.setText("\nERROR: Invalid Airport(s)");
                    }
                }
            });
        });
        
        // Action for fourth button
        button4.setOnAction(event -> {
            bottomText.setText("");
            TextField start = new TextField();
            start.setId("start");
            Text textStart = new Text("\n \n \n Enter the IATA of the Airport you will like to Travel From");
            textStart.setId("textStart");
            VBox combine = new VBox(textStart, start);
            TextField dest = new TextField();
            dest.setId("dest");
            Text textDest = new Text("\n \n Enter the IATA of the Airport you will like to Travel To");
            textDest.setId("textDest");
            VBox combine2 = new VBox(textDest, dest);
            TextField middle = new TextField();
            middle.setId("middle");
            Text textMiddle = new Text("\n \n Enter the IATA of the Airport you will like to Pass Through");
            textMiddle.setId("textMiddle");
            VBox combine3 = new VBox(textMiddle, middle);
            Button goBut = new Button("Search!");
            goBut.setId("goBut4");
            goBut.setPrefWidth(100);
            goBut.setPrefHeight(30);
            goBut.setStyle("-fx-background-color: LawnGreen");
            Text result4 = new Text("\n \n \n \n \n \n \n Fastest Path by Stops Adv.");
            result4.setId("result4");
            result4.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            VBox temp = new VBox(result4);
            temp.setAlignment(Pos.CENTER);
            VBox vbox2 = new VBox(combine, combine2, combine3, goBut, temp);
            borderPane.setCenter(vbox2);

            goBut.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        bottomText.setText("");
                        bottomText.setText(backend.findFastestPath(start.getText(), middle.getText(), dest.getText()).toString());
                        bottomText.setFont(Font.font("OpenSymbol", FontWeight.BOLD, 16));
                        //borderPane.setBottom(bottom);
                    }
                    catch(Exception e) {
                        bottomText.setText("\nERROR: Invalid Airport(s)");
                    }
                }
                
            });
        });
        
        return scene;
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
