package project4_hulbert_1181;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Brandon Hulbert CS 1181 Dr.Cheathem Project 4
 */
public class Project4_Hulbert_1181 extends Application {

    private final VBox mainPane = new VBox();
    private final GridPane sortPane = new GridPane();
    private final GridPane inputPane = new GridPane();
    private final GridPane TextFieldPane = new GridPane();
    private final GridPane ButtonPane = new GridPane();
    private final ToggleGroup group = new ToggleGroup();
    private final ToggleGroup group2 = new ToggleGroup();
    private final RadioButton rbSelection = new RadioButton("Selection");
    private final RadioButton rbBubble = new RadioButton("Bubble");
    private final RadioButton rbInsertion = new RadioButton("Insertion");
    private final RadioButton rbQuick = new RadioButton("Quick");
    private final RadioButton rbAlreadySorted = new RadioButton("Already sorted");
    private final RadioButton rbReverseOrder = new RadioButton("Reverse order");
    private final RadioButton rbRandom = new RadioButton("Random");
    private final Label sortLabel = new Label("Sorting Algorithm");
    private final Label InputLabel = new Label("Input Type");
    private final Label InputSizeLabel = new Label("Input Size");
    private final Label BlockSizeLabel = new Label("Block Size");
    private TextField txInput = new TextField("");
    private TextField txBlock = new TextField("");
    private final Button btGo = new Button("Go");
    private int nNum = -1;
    private int nChoice = -1;

    public static void main(String[] args) {
        Application.launch(args);
    }

    public int SortChoice() {
        rbSelection.setOnAction(event -> {
            nNum = 0;
        });
        rbInsertion.setOnAction(event -> {
            nNum = 1;
        });
        rbBubble.setOnAction(event -> {
            nNum = 2;
        });
        rbQuick.setOnAction(event -> {
            nNum = 3;
        });
        return nNum;
    }

    public int SortTypeChoice() {
        rbAlreadySorted.setOnAction(event -> {
            nChoice = 0;
        });
        rbReverseOrder.setOnAction(event -> {
            nChoice = 1;
        });
        rbRandom.setOnAction(event -> {
            nChoice = 2;
        });
        return nChoice;
    }

    public void ButtonAction() {
        nNum = SortChoice();
        nChoice = SortTypeChoice();
        //this wil work as a choice menue for the gui.  This will allow
        //the program to determine what was selected and wasnt.
        btGo.setOnAction((ActionEvent event)
                -> {

            int ntext = 0;
            int nBlock = 0;
            int[] nArray = null;
            //this is selection sort button
            if (nNum == 0) {
                boolean DidntthrewException = false;
                //this will test if the two blocks have input in them
                try {
                    ntext = textFieldInput(txInput);
                    nBlock = textFieldBlock(txBlock);
                    if (ntext < nBlock) {
                        throw new InputMismatchException();
                    }
                    if (ntext <= 0 || nBlock <= 0) {
                        throw new NegativeInputException();
                    }
                }
                catch (InputMismatchException e) {
                    DidntthrewException = true;
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Block size cannot be greater than the input size");
                    alert.show();
                }
                catch (NegativeInputException ex) {
                    DidntthrewException = true;
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Block size and input size both cannot be negative");
                    alert.show();
                }
                if (!DidntthrewException) {
                    SortingAlgorithms sort = new SortingAlgorithms(ntext, nBlock);
                    //this is an already sorted array
                    if (nChoice == 0) {
                        long start = System.nanoTime();
                        nArray = sort.AlreadySorted();
                        sort.CreateSubArray(nArray);
                        try {
                            sort.SortChunks(nNum);
                            sort.MergeThreadQueue(nNum);

                        }
                        catch (InterruptedException ex) {
                            Logger.getLogger(Project4_Hulbert_1181.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        long end = System.nanoTime();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Sort completed in " + (end - start) / 1000000 + " ms");
                        Optional<ButtonType> newResult = alert.showAndWait();
                        //System.exit(0);

                    }
                    //this is a reverse ordered array
                    if (nChoice == 1) {
                        long start = System.nanoTime();
                        nArray = sort.ReverseOrder();
                        sort.CreateSubArray(nArray);

                        try {
                            sort.SortChunks(nNum);
                            sort.MergeThreadQueue(nNum);

                        }
                        catch (InterruptedException ex) {
                            Logger.getLogger(Project4_Hulbert_1181.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        long end = System.nanoTime();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Sort completed in " + (end - start) / 1000000 + " ms");
                        Optional<ButtonType> newResult = alert.showAndWait();
                        //System.exit(0);
                       
                    }
                    //this is a random array
                    if (nChoice == 2) {
                        long start = System.nanoTime();
                        nArray = sort.RandomArray();
                        sort.CreateSubArray(nArray);
                        try {
                            sort.SortChunks(nNum);
                            sort.MergeThreadQueue(nNum);

                        }
                        catch (InterruptedException ex) {
                            Logger.getLogger(Project4_Hulbert_1181.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        long end = System.nanoTime();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Sort completed in " + (end - start) / 1000000 + " ms");
                        Optional<ButtonType> newResult = alert.showAndWait();
                       // System.exit(0);
                    }
                }
            }
            if (nNum == 1) {
                boolean DidntthrewException = false;
                //this will test if the two blocks have input in them
                try {
                    ntext = textFieldInput(txInput);
                    nBlock = textFieldBlock(txBlock);
                    if (ntext < nBlock) {
                        throw new InputMismatchException();
                    }
                    if (ntext <= 0 || nBlock <= 0) {
                        throw new NegativeInputException();
                    }
                }

                catch (InputMismatchException e) {
                    DidntthrewException = true;
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Block size cannot be greater than the input size");
                    alert.show();

                    //Optional<ButtonType> newResult = alert.showAndWait();
                }
                catch (NegativeInputException ex) {
                    DidntthrewException = true;
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Block size and input size both cannot be negative");
                    alert.show();
                }

                SortingAlgorithms sort = new SortingAlgorithms(ntext, nBlock);

                if (nChoice == 0) {
                    long start = System.nanoTime();
                    nArray = sort.AlreadySorted();
                    sort.CreateSubArray(nArray);
                    try {
                        sort.SortChunks(nNum);
                        sort.MergeThreadQueue(nNum);

                    }
                    catch (InterruptedException ex) {
                        Logger.getLogger(Project4_Hulbert_1181.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    long end = System.nanoTime();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Sort completed in " + (end - start) / 1000000 + " ms");
                    Optional<ButtonType> newResult = alert.showAndWait();
                    //System.exit(0);
                }
                if (nChoice == 1) {
                    long start = System.nanoTime();
                    nArray = sort.ReverseOrder();
                    sort.CreateSubArray(nArray);
                    try {
                        sort.SortChunks(nNum);
                        sort.MergeThreadQueue(nNum);

                    }
                    catch (InterruptedException ex) {
                        Logger.getLogger(Project4_Hulbert_1181.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    long end = System.nanoTime();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Sort completed in " + (end - start) / 1000000 + " ms");
                    Optional<ButtonType> newResult = alert.showAndWait();
                    //System.exit(0);
                }
                if (nChoice == 2) {
                    long start = System.nanoTime();
                    nArray = sort.RandomArray();
                    sort.CreateSubArray(nArray);
                    try {
                        sort.SortChunks(nNum);
                        sort.MergeThreadQueue(nNum);

                    }
                    catch (InterruptedException ex) {
                        Logger.getLogger(Project4_Hulbert_1181.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    long end = System.nanoTime();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Sort completed in " + (end - start) / 1000000 + " ms");
                    Optional<ButtonType> newResult = alert.showAndWait();
                    //System.exit(0);
                }
            }
            if (nNum == 2) {
                boolean DidntthrewException = false;
                //this will test if the two blocks have input in them
                try {
                    ntext = textFieldInput(txInput);
                    nBlock = textFieldBlock(txBlock);
                    if (ntext < nBlock) {
                        throw new InputMismatchException();
                    }
                    if (ntext <= 0 || nBlock <= 0) {
                        throw new NegativeInputException();
                    }
                }
                catch (InputMismatchException e) {
                    DidntthrewException = true;
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Block size cannot be greater than the input size");
                    alert.show();

                    //Optional<ButtonType> newResult = alert.showAndWait();
                }
                catch (NegativeInputException ex) {
                    DidntthrewException = true;
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Block size and input size both cannot be negative");
                    alert.show();
                }
                SortingAlgorithms sort = new SortingAlgorithms(ntext, nBlock);

                if (nChoice == 0) {
                    long start = System.nanoTime();
                    nArray = sort.AlreadySorted();
                    sort.CreateSubArray(nArray);
                    try {
                        sort.SortChunks(nNum);
                        sort.MergeThreadQueue(nNum);

                    }
                    catch (InterruptedException ex) {
                        Logger.getLogger(Project4_Hulbert_1181.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    long end = System.nanoTime();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Sort completed in " + (end - start) / 1000000 + " ms");
                    Optional<ButtonType> newResult = alert.showAndWait();
                    //System.exit(0);
                }
                if (nChoice == 1) {
                    long start = System.nanoTime();
                    nArray = sort.ReverseOrder();
                    sort.CreateSubArray(nArray);
                    try {
                        sort.SortChunks(nNum);
                        sort.MergeThreadQueue(nNum);

                    }
                    catch (InterruptedException ex) {
                        Logger.getLogger(Project4_Hulbert_1181.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    long end = System.nanoTime();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Sort completed in " + (end - start) / 1000000 + " ms");
                    Optional<ButtonType> newResult = alert.showAndWait();
                   // System.exit(0);
                }
                if (nChoice == 2) {
                    long start = System.nanoTime();
                    nArray = sort.RandomArray();
                    sort.CreateSubArray(nArray);
                    try {
                        sort.SortChunks(nNum);
                        sort.MergeThreadQueue(nNum);

                    }
                    catch (InterruptedException ex) {
                        Logger.getLogger(Project4_Hulbert_1181.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    long end = System.nanoTime();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Sort completed in " + (end - start) / 1000000 + " ms");
                    Optional<ButtonType> newResult = alert.showAndWait();
                    //System.exit(0);
                }
            }
            if (nNum == 3) {
                boolean DidntthrewException = false;
                //this will test if the two blocks have input in them
                try {
                    ntext = textFieldInput(txInput);
                    nBlock = textFieldBlock(txBlock);
                    if (ntext < nBlock) {
                        throw new InputMismatchException();
                    }
                    if (ntext <= 0 || nBlock <= 0) {
                        throw new NegativeInputException();
                    }
                }
                catch (InputMismatchException e) {
                    DidntthrewException = true;
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Block size cannot be greater than the input size");
                    alert.show();

                    //Optional<ButtonType> newResult = alert.showAndWait();
                }
                catch (NegativeInputException ex) {
                    DidntthrewException = true;
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Block size and input size both cannot be negative");
                    alert.show();
                }

                SortingAlgorithms sort = new SortingAlgorithms(ntext, nBlock);

                if (nChoice == 0) {
                    long start = System.nanoTime();
                    nArray = sort.AlreadySorted();
                    sort.CreateSubArray(nArray);
                    try {
                        sort.SortChunks(nNum);
                        sort.MergeThreadQueue(nNum);

                    }
                    catch (InterruptedException ex) {
                        Logger.getLogger(Project4_Hulbert_1181.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    long end = System.nanoTime();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Sort completed in " + (end - start) / 1000000 + " ms");
                    Optional<ButtonType> newResult = alert.showAndWait();
                   // System.exit(0);
                }
                if (nChoice == 1) {
                    long start = System.nanoTime();
                    nArray = sort.ReverseOrder();
                    sort.CreateSubArray(nArray);
                    try {
                        sort.SortChunks(nNum);
                        sort.MergeThreadQueue(nNum);

                    }
                    catch (InterruptedException ex) {
                        Logger.getLogger(Project4_Hulbert_1181.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    long end = System.nanoTime();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Sort completed in " + (end - start) / 1000000 + " ms");
                    Optional<ButtonType> newResult = alert.showAndWait();
                    //System.exit(0);
                }
                if (nChoice == 2) {
                    long start = System.nanoTime();
                    nArray = sort.RandomArray();
                    sort.CreateSubArray(nArray);
                    try {
                        sort.SortChunks(nNum);
                        sort.MergeThreadQueue(nNum);

                    }
                    catch (InterruptedException ex) {
                        Logger.getLogger(Project4_Hulbert_1181.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    long end = System.nanoTime();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Sort completed in " + (end - start) / 1000000 + " ms");
                    Optional<ButtonType> newResult = alert.showAndWait();
                   // System.exit(0);
                }
            }
            if (nNum == -1) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Failed to select all the buttons");
                Optional<ButtonType> newResult = alert.showAndWait();

            }
            if (nChoice == -1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Failed to select all the buttons");
                Optional<ButtonType> newResult = alert.showAndWait();
            }

        });

    }

    public int textFieldInput(TextField Input) {
        int nInput = 0;
        try{
        String input = txInput.getText();
        nInput = Integer.parseInt(input);
        }catch(NumberFormatException e){
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setContentText("NonInteger entered");
            alert.show();
        }
        return nInput;
    }

    public int textFieldBlock(TextField Block) {
        int nBlock = 0;
        String block = txBlock.getText();
        try{
        nBlock = Integer.parseInt(block);
         }catch(NumberFormatException e){
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setContentText("NonInteger entered");
            alert.show();
        }
        return nBlock;
    }

    @Override
    public void start(Stage stage) throws Exception {

        rbBubble.setToggleGroup(group);
        rbInsertion.setToggleGroup(group);
        rbQuick.setToggleGroup(group);
        rbAlreadySorted.setToggleGroup(group2);
        rbReverseOrder.setToggleGroup(group2);
        rbRandom.setToggleGroup(group2);
        rbSelection.setToggleGroup(group);

        btGo.setPrefSize(500, 50);

        sortPane.add(sortLabel, 0, 0);
        sortPane.add(rbSelection, 0, 1);
        sortPane.add(rbBubble, 0, 2);
        sortPane.add(rbInsertion, 0, 3);
        sortPane.add(rbQuick, 0, 4);
        sortPane.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, new CornerRadii(15), BorderWidths.DEFAULT)));
        sortPane.setPadding(new Insets(10, 10, 10, 10));

        inputPane.add(InputLabel, 0, 0);
        inputPane.add(rbAlreadySorted, 0, 1);
        inputPane.add(rbReverseOrder, 0, 2);
        inputPane.add(rbRandom, 0, 3);
        inputPane.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, new CornerRadii(15), BorderWidths.DEFAULT)));
        inputPane.setPadding(new Insets(10, 10, 10, 10));

        TextFieldPane.add(InputSizeLabel, 0, 0);
        TextFieldPane.add(txInput, 1, 0);
        TextFieldPane.add(BlockSizeLabel, 0, 1);
        TextFieldPane.add(txBlock, 1, 1);
        TextFieldPane.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, new CornerRadii(15), BorderWidths.DEFAULT)));
        TextFieldPane.setPadding(new Insets(10, 10, 10, 10));

        ButtonPane.add(btGo, 0, 0);
        ButtonPane.setPadding(new Insets(10, 10, 10, 10));

        ButtonAction();

        mainPane.getChildren().addAll(sortPane);
        mainPane.getChildren().addAll(inputPane);
        mainPane.getChildren().addAll(TextFieldPane);
        mainPane.getChildren().addAll(ButtonPane);

        Scene scene = new Scene(mainPane, 400, 500);
        stage.setScene(scene);
        stage.show();

    }

}
