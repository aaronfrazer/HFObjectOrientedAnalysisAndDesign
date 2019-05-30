package ch1235;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application
{
    private Stage window;
    private Scene scene;
    private GridPane gridPane = new GridPane();
    private Inventory inventory = new Inventory();
    private Map properties = new HashMap();

    private Label labelInstrumentType = new Label("Instrument Type: ");
    private Label labelBuilder = new Label("Builder: ");
    private Label labelModel = new Label("Model: ");
    private Label labelType = new Label("Type: ");
    private Label labelNumStrings = new Label("Num Strings: ");
    private Label labelBackWood = new Label("Back Wood: ");
    private Label labelTopWood = new Label("Top Wood: ");

    private ChoiceBox<String> choiceboxInstrumentType = new ChoiceBox<>();
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
        gridPane.setHgap(50);
        gridPane.setVgap(20);

        initializeInventory(inventory);

        for (InstrumentType it : InstrumentType.values())
            choiceboxInstrumentType.getItems().add(it.toString());
        choiceboxInstrumentType.setValue("Guitar");

        choiceboxInstrumentType.setOnAction(e -> updateInstrumentFields());

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

        searchButton.setOnAction(e -> searchInstruments());

        gridPane.add(labelInstrumentType, 0, 0, 1, 1);
        gridPane.add(choiceboxInstrumentType, 1, 0, 1, 1);
        gridPane.add(labelBuilder, 0, 1, 1, 1);
        gridPane.add(choiceBoxBuilder, 1, 1, 1, 1);
        gridPane.add(labelModel, 0, 2, 1, 1);
        gridPane.add(textFieldModel, 1, 2, 1, 1);
        gridPane.add(labelType, 0, 3, 1, 1);
        int x = 3;
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
        window.setTitle("Rick's Guitar Inventory: Search for Instruments");
        window.setScene(scene);
        window.setOnCloseRequest(e -> closeProgram());
        window.show();
    }

    private static void initializeInventory(Inventory inventory)
    {
        Map properties = new HashMap();
        properties.put("instrumentType", InstrumentType.GUITAR);
        properties.put("builder", Builder.COLLINGS);
        properties.put("model", "CJ");
        properties.put("type", Type.ACOUSTIC);
        properties.put("numStrings", 6);
        properties.put("topWood", Wood.INDIAN_ROSEWOOD);
        properties.put("backWood", Wood.SITKA);
        inventory.addInstrument("11277", 3999.95, new InstrumentSpec(properties));

        properties.put("instrumentType", InstrumentType.GUITAR);
        properties.put("builder", Builder.MARTIN);
        properties.put("model", "D-18");
        properties.put("type", Type.ACOUSTIC);
        properties.put("numStrings", 6);
        properties.put("topWood", Wood.MAHOGANY);
        properties.put("backWood", Wood.ADIRONDACK);
        inventory.addInstrument("122784", 5495.95, new InstrumentSpec(properties));

        properties.put("instrumentType", InstrumentType.GUITAR);
        properties.put("builder", Builder.FENDER);
        properties.put("model", "Stratocastor");
        properties.put("type", Type.ELECTRIC);
        properties.put("numStrings", 6);
        properties.put("topWood", Wood.ALDER);
        properties.put("backWood", Wood.ALDER);
        inventory.addInstrument("V95693", 1499.95, new InstrumentSpec(properties));
        inventory.addInstrument("V9512", 1549.95, new InstrumentSpec(properties));

        properties.put("instrumentType", InstrumentType.GUITAR);
        properties.put("builder", Builder.GIBSON);
        properties.put("model", "Les Paul");
        properties.put("type", Type.ELECTRIC);
        properties.put("numStrings", 6);
        properties.put("topWood", Wood.MAPLE);
        properties.put("backWood", Wood.MAPLE);
        inventory.addInstrument("70108276", 2295.95, new InstrumentSpec(properties));

        properties.put("instrumentType", InstrumentType.GUITAR);
        properties.put("model", "SG '61 Reissue");
        properties.put("type", Type.ELECTRIC);
        properties.put("numStrings", 6);
        properties.put("topWood", Wood.MAHOGANY);
        properties.put("backWood", Wood.MAHOGANY);
        inventory.addInstrument("82765501", 1890.95, new InstrumentSpec(properties));

        properties.put("instrumentType", InstrumentType.MANDOLIN);
        properties.put("type", Type.ACOUSTIC);
        properties.put("model", "F-5G");
        properties.remove("numStrings");
        properties.put("backWood", Wood.MAPLE);
        properties.put("topWood", Wood.MAPLE);
        properties.remove("numStrings");
        inventory.addInstrument("9019920", 5495.99, new InstrumentSpec(properties));

        properties.put("instrumentType", InstrumentType.BANJO);
        properties.put("model", "RB-3 Wreath");
        properties.remove("topWood");
        properties.put("numStrings", 5);
        inventory.addInstrument("8900231", 2945.95, new InstrumentSpec(properties));
    }

    private void searchInstruments()
    {
        // gather all inputs and store them in variables
        String instrumentTypeString = choiceboxInstrumentType.getValue();
        String builderString = choiceBoxBuilder.getValue();
        String model = textFieldModel.getText();
        String typeString = ((RadioButton) tg.getSelectedToggle()).getText();
        int numStrings = spinnerNumStrings.getValue();
        String backWoodString = choiceBoxBackWood.getValue();
        String topWoodString = choiceBoxTopWood.getValue();

        InstrumentType instrumentType = null;
        Builder builder = null;
        Type type = null;
        Wood backWood = null;
        Wood topWood = null;

        switch (instrumentTypeString)
        {
            case "Guitar":
                instrumentType = InstrumentType.GUITAR;
                break;
            case "Banjo":
                instrumentType = InstrumentType.BANJO;
                break;
            case "Dobro":
                instrumentType = InstrumentType.DOBRO;
                break;
            case "Fiddle":
                instrumentType = InstrumentType.FIDDLE;
                break;
            case "Bass":
                instrumentType = InstrumentType.BASS;
                break;
            case "Mandolin":
                instrumentType = InstrumentType.MANDOLIN;
        }

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

        properties.put("instrumentType", instrumentType);
        if (instrumentType == InstrumentType.GUITAR)
            properties.put("builder", builder);
        properties.put("model", model);
        properties.put("type", type);
        if (instrumentType != InstrumentType.MANDOLIN)
            properties.put("numStrings", numStrings);
        properties.put("backWood", backWood);
        if (instrumentType != InstrumentType.BANJO)
            properties.put("topWood", topWood);


        InstrumentSpec whatUserLikes = new InstrumentSpec(properties);

        List matchingInstruments = inventory.search(whatUserLikes);

        int numResults = 0;

        if (!matchingInstruments.isEmpty())
        {
            System.out.println("You might like these instruments:");
            for (Object matchingInstrument : matchingInstruments)
            {
                numResults++;
                Instrument instrument = (Instrument) matchingInstrument;
                InstrumentSpec spec = instrument.getSpec();

                System.out.println(numResults + ".  We have a " +
                        spec.getProperty("instrumentType") + " with the following properties:");

                for (Object o : spec.getProperties().keySet())
                {
                    String propertyName = (String) o;
                    if (propertyName.equals("instrumentType"))
                        continue;
                    System.out.println("    " + propertyName + ": " +
                            spec.getProperty(propertyName));
                }
                System.out.println("  You can have this " +
                        spec.getProperty("instrumentType") + " for $" +
                        instrument.getPrice() + "\n---");
            }
        } else
        {
            System.out.println("Sorry, Aaron, we have nothing for you.");
        }
    }

    /**
     * Updates the fields according to which instrument type is selected.
     * Guitars have everything.
     * Banjos do not have a top wood
     * Mandolins do not have numStrings
     */
    private void updateInstrumentFields()
    {
        choiceBoxTopWood.setDisable(false);
        spinnerNumStrings.setDisable(false);

        switch (choiceboxInstrumentType.getValue())
        {
            case "Guitar":

                break;
            case "Banjo":
                choiceBoxBuilder.setDisable(true);
                choiceBoxTopWood.setDisable(true);
                break;
            case "Dobro":

                break;
            case "Fiddle":

                break;
            case "Bass":

                break;
            case "Mandolin":
                choiceBoxBuilder.setDisable(true);
                spinnerNumStrings.setDisable(true);
                break;
        }
    }

    /**
     * Closes the window of the program.
     */
    private void closeProgram()
    {
        window.close();
    }
}