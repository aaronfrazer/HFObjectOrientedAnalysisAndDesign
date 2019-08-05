package subwaySystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main extends Application
{
	private static String fileDir = "res/files/";

	private SubwayLoader loader = new SubwayLoader();
	private Subway objectville;

	private Stage window;
	private Scene scene;
	private GridPane gridPane = new GridPane();

	private Label labelSubway = new Label("Subway Route: ");
	private Label labelTo = new Label(" to ");
	private ChoiceBox<String> choiceBoxStation1 = new ChoiceBox<>();
	private ChoiceBox<String> choiceBoxStation2 = new ChoiceBox<>();
	private Button buttonSearch = new Button("Search Route");

	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException
	{
		objectville = loader.loadFromFile(new File(fileDir + "ObjectvilleSubway.txt"));

		// Load from txt file into choiceboxes
		try (BufferedReader br = new BufferedReader(new FileReader(new File(fileDir + "ObjectvilleSubway.txt"))))
		{
			ArrayList<String> stations = new ArrayList<>();
			String line;
			while ((line = br.readLine()) != null)
			{
				if (!stations.contains(line) && !stations.contains(line) && !line.equalsIgnoreCase(""))
					stations.add(line);
			}

			Collections.sort(stations);
			choiceBoxStation1.getItems().addAll(stations);
			choiceBoxStation2.getItems().addAll(stations);
		}

		buttonSearch.setOnAction(e -> searchRoutes(choiceBoxStation1.getValue(), choiceBoxStation2.getValue()));

		gridPane.add(labelSubway, 0, 0, 1, 1);
		gridPane.add(choiceBoxStation1, 1, 0, 1, 1);
		gridPane.add(labelTo, 2, 0, 1, 1);
		gridPane.add(choiceBoxStation2, 3, 0, 1, 1);
		gridPane.add(buttonSearch, 0, 1, 2, 1);

		scene = new Scene(gridPane, 1000, 550);
		window = primaryStage;
		window.setTitle("Objectville Subway System");
		window.setScene(scene);
		window.setOnCloseRequest(e -> closeProgram());
		window.show();
	}

	private void searchRoutes(String station1, String station2)
	{
		System.out.println("--- Searching " + station1 + " to " + station2 + " ---");
		List route = objectville.getDirections(station1, station2);

		if (!route.isEmpty())
        {
            SubwayPrinter printer = new SubwayPrinter(System.out);
            printer.printDirections(route);
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