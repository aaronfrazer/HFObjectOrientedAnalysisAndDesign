package ch1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application
{
    private Stage window;
    private GridPane gridPane = new GridPane();

    private Label labelBuilder = new Label("Builder: ");
    private Label labelModel = new Label("Model: ");
    private Label labelType = new Label("Type: ");
    private Label labelNumStrings = new Label("Num Strings: ");
    private Label labelBackWood = new Label("Back Wood: ");
    private Label labelTopWood = new Label("Top Wood: ");

    private ChoiceBox<String> choiceBoxBuilder = new ChoiceBox<>();
    private TextField textFieldModel = new TextField("Stratocastor");
    private ArrayList<RadioButton> radioButtonsType = new ArrayList<>();
    private ToggleGroup tg = new ToggleGroup();
    private Spinner<Integer> spinnerNumStrings = new Spinner<>();
    private ChoiceBox<String> choiceBoxBackWood = new ChoiceBox<>();
    private ChoiceBox<String> choiceBoxTopWood = new ChoiceBox<>();

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        for (Builder b : Builder.values())
            choiceBoxBuilder.getItems().add(b.toString());

        for (Type t : Type.values())
            radioButtonsType.add(new RadioButton(t.toString()));

        spinnerNumStrings.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24, 6));

        for (Wood w : Wood.values())
        {
            choiceBoxBackWood.getItems().add(w.toString());
            choiceBoxTopWood.getItems().add(w.toString());
        }

        gridPane.add(labelBuilder, 0, 0, 1, 1);
        gridPane.add(choiceBoxBuilder, 1, 0, 1, 1);
        gridPane.add(labelModel, 0, 1, 1, 1);
        gridPane.add(textFieldModel, 1, 1, 1, 1);
        gridPane.add(labelType, 0, 2, 1, 1);
        int x = 2;
        for (RadioButton rb : radioButtonsType)
        {
            rb.setToggleGroup(tg);
            gridPane.add(rb, 1, x, 1, 1);
            x++;
        }
        gridPane.add(labelNumStrings, 0, x, 1, 1);
        x++;
        gridPane.add(spinnerNumStrings, 1, x, 1, 1);
        gridPane.add(labelBackWood, 0, x, 1, 1);
        x++;
        gridPane.add(choiceBoxBackWood, 1, x, 1, 1);
        gridPane.add(labelTopWood, 0, x, 1, 1);
        x++;
        gridPane.add(choiceBoxTopWood, 1, x, 1, 1);

        Scene scene = new Scene(gridPane, 1000, 550);
        window = primaryStage;
        window.setTitle("Tutorial");
        window.setScene(scene);
        window.setOnCloseRequest(e -> closeProgram());
        window.show();
    }

    /**
     * Closes the window of the program.
     */
    private void closeProgram()
    {
        window.close();
    }
}