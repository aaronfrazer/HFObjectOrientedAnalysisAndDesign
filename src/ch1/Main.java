package ch1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application
{
    private Stage window;
    private Scene scene;
    private GridPane gridPane = new GridPane();

    private Inventory inventory = new Inventory();

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

    private Button searchButton = new Button("Search Inventory");

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        initializeInventory(inventory);

        for (Builder b : Builder.values())
            choiceBoxBuilder.getItems().add(b.toString());
        choiceBoxBuilder.setValue("Fender");

        for (Type t : Type.values())
            radioButtonsType.add(new RadioButton(t.toString()));
        radioButtonsType.get(1).setSelected(true);

        spinnerNumStrings.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24, 6));

        for (Wood w : Wood.values())
        {
            choiceBoxBackWood.getItems().add(w.toString());
            choiceBoxTopWood.getItems().add(w.toString());
        }
        choiceBoxBackWood.setValue("Alder");
        choiceBoxTopWood.setValue("Alder");

        searchButton.setOnAction(e -> searchGuitars());

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
        gridPane.add(spinnerNumStrings, 1, x, 1, 1);
        x++;
        gridPane.add(labelBackWood, 0, x, 1, 1);
        gridPane.add(choiceBoxBackWood, 1, x, 1, 1);
        x++;
        gridPane.add(labelTopWood, 0, x, 1, 1);
        gridPane.add(choiceBoxTopWood, 1, x, 1, 1);
        x++;
        gridPane.add(searchButton, 0, x, 2, 1);

        scene = new Scene(gridPane, 1000, 550);
        window = primaryStage;
        window.setTitle("Rick's Guitar Inventory: Search for Guitar");
        window.setScene(scene);
        window.setOnCloseRequest(e -> closeProgram());
        window.show();
    }

    private static void initializeInventory(Inventory inventory)
    {
        inventory.addGuitar("11277", 3999.95,
                new GuitarSpec(Builder.COLLINGS, "CJ", Type.ACOUSTIC, 6,
                        Wood.INDIAN_ROSEWOOD, Wood.SITKA));
        inventory.addGuitar("V95693", 1499.95,
                new GuitarSpec(Builder.FENDER, "Stratocastor", Type.ELECTRIC, 6,
                        Wood.ALDER, Wood.ALDER));
        inventory.addGuitar("V9512", 1549.95,
                new GuitarSpec(Builder.FENDER, "Stratocastor", Type.ELECTRIC, 6,
                        Wood.ALDER, Wood.ALDER));
        inventory.addGuitar("122784", 5495.95,
                new GuitarSpec(Builder.MARTIN, "D-18", Type.ACOUSTIC, 6,
                        Wood.MAHOGANY, Wood.ADIRONDACK));
        inventory.addGuitar("76531", 6295.95,
                new GuitarSpec(Builder.MARTIN, "OM-28", Type.ACOUSTIC, 6,
                        Wood.BRAZILIAN_ROSEWOOD, Wood.ADIRONDACK));
        inventory.addGuitar("70108276", 2295.95,
                new GuitarSpec(Builder.GIBSON, "Les Paul", Type.ELECTRIC, 6,
                        Wood.MAHOGANY, Wood.MAHOGANY));
        inventory.addGuitar("82765501", 1890.95,
                new GuitarSpec(Builder.GIBSON, "SG '61 Reissue", Type.ELECTRIC, 6,
                        Wood.MAHOGANY, Wood.MAHOGANY));
        inventory.addGuitar("77023", 6275.95,
                new GuitarSpec(Builder.MARTIN, "D-28", Type.ACOUSTIC, 6,
                        Wood.BRAZILIAN_ROSEWOOD, Wood.ADIRONDACK));
        inventory.addGuitar("1092", 12995.95,
                new GuitarSpec(Builder.OLSON, "SJ", Type.ACOUSTIC, 12,
                        Wood.INDIAN_ROSEWOOD, Wood.CEDAR));
        inventory.addGuitar("566-62", 8999.95,
                new GuitarSpec(Builder.RYAN, "Cathedral", Type.ACOUSTIC, 12,
                        Wood.COCOBOLO, Wood.CEDAR));
        inventory.addGuitar("6 29584", 2100.95,
                new GuitarSpec(Builder.PRS, "Dave Navarro Signature", Type.ELECTRIC,
                        6, Wood.MAHOGANY, Wood.MAPLE));
    }

    private void searchGuitars()
    {
        // gather all inputs and store them in variables
        String builderString = choiceBoxBuilder.getValue();
        String model = textFieldModel.getText();
        String typeString = ((RadioButton) tg.getSelectedToggle()).getText();
        int numStrings = spinnerNumStrings.getValue();
        String backWoodString = choiceBoxBackWood.getValue();
        String topWoodString = choiceBoxTopWood.getValue();

        Builder builder = null;
        Type type = null;
        Wood backWood = null;
        Wood topWood = null;

        switch (builderString)
        {
            case "Fender":
                builder = Builder.FENDER;
                break;
            case "Martin":
                builder = Builder.MARTIN;
                break;
            case "Gibson":
                builder = Builder.GIBSON;
                break;
            case "Collings":
                builder = Builder.COLLINGS;
                break;
            case "Olson":
                builder = Builder.OLSON;
                break;
            case "Ryan":
                builder = Builder.RYAN;
                break;
            case "PRS":
                builder = Builder.PRS;
        }

        switch (typeString)
        {
            case "acoustic":
                type = Type.ACOUSTIC;
                break;
            case "electric":
                type = Type.ELECTRIC;
                break;
        }

        switch (backWoodString)
        {
            case "Indian Rosewood":
                backWood = Wood.INDIAN_ROSEWOOD;
                break;
            case "Brazilian Rosewood":
                backWood = Wood.BRAZILIAN_ROSEWOOD;
                break;
            case "Mahogany":
                backWood = Wood.MAHOGANY;
                break;
            case "Maple":
                backWood = Wood.MAPLE;
                break;
            case "Cocobolo":
                backWood = Wood.COCOBOLO;
                break;
            case "Cedar":
                backWood = Wood.CEDAR;
                break;
            case "Adirondack":
                backWood = Wood.ADIRONDACK;
                break;
            case "Alder":
                backWood = Wood.ALDER;
                break;
            case "Sitka":
                backWood = Wood.SITKA;
        }

        switch (topWoodString)
        {
            case "Indian Rosewood":
                topWood = Wood.INDIAN_ROSEWOOD;
                break;
            case "Brazilian Rosewood":
                topWood = Wood.BRAZILIAN_ROSEWOOD;
                break;
            case "Mahogany":
                topWood = Wood.MAHOGANY;
                break;
            case "Maple":
                topWood = Wood.MAPLE;
                break;
            case "Cocobolo":
                topWood = Wood.COCOBOLO;
                break;
            case "Cedar":
                topWood = Wood.CEDAR;
                break;
            case "Adirondack":
                topWood = Wood.ADIRONDACK;
                break;
            case "Alder":
                topWood = Wood.ALDER;
                break;
            case "Sitka":
                topWood = Wood.SITKA;
        }

        // TODO: What if one of these fields are null?
        GuitarSpec whatUserLikes = new GuitarSpec(builder, model, type, numStrings, backWood, topWood);
        GuitarSpec whatErinLikes = new GuitarSpec(Builder.FENDER, "Stratocastor", Type.ELECTRIC, 6, Wood.ALDER, Wood.ALDER);

        List matchingGuitars = inventory.search(whatUserLikes);

        if (!matchingGuitars.isEmpty())
        {
            System.out.println("Aaron, you might like these guitars:");
            for (Object matchingGuitar : matchingGuitars)
            {
                Guitar guitar = (Guitar) matchingGuitar;
                GuitarSpec spec = guitar.getSpec();
                System.out.println("  We have a " +
                        spec.getBuilder() + " " + spec.getModel() + " " +
                        spec.getType() + " guitar:\n     " +
                        spec.getBackWood() + " back and sides,\n     " +
                        spec.getTopWood() + " top.\n  You can have it for only $" +
                        guitar.getPrice() + "!\n  ----");
            }
        } else
        {
            System.out.println("Sorry, Aaron, we have nothing for you.");
        }

        System.out.println(builder + ": " + Builder.FENDER);
        System.out.println(model + ": Stratocastor");
        System.out.println(type + ": " + Type.ELECTRIC);
        System.out.println(numStrings + ": " + 6);
        System.out.println(backWood + ": " + Wood.ALDER);
        System.out.println(topWood + ": " + Wood.ALDER);

    }

    /**
     * Closes the window of the program.
     */
    private void closeProgram()
    {
        window.close();
    }
}