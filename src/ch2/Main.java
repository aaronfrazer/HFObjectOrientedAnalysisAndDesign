package ch2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application
{
    private DogDoor door = new DogDoor();
    private Remote remote = new Remote(door);

    private static String imageDir = "res/images/ch2/";

    private Stage window;
    private Scene scene;
    private GridPane gridPane = new GridPane();

    static Image dogDoorOpenImage = new Image(new File(imageDir + "DogDoorOpen.jpg").toURI().toString());
    static Image dogDoorCloseImage = new Image(new File(imageDir + "DogDoorClose.jpg").toURI().toString());
    private static Image dogInsideImage = new Image(new File(imageDir + "DogInside.png").toURI().toString());
    private static Image dogOutsideImage = new Image(new File(imageDir + "DogOutside.jpg").toURI().toString());

    static ImageView dogDoorImageView = new ImageView(dogDoorCloseImage);
    static ImageView dogInsideImageView = new ImageView(dogInsideImage);
    static ImageView dogOutsideImageView = new ImageView(dogOutsideImage);

    static Button button = new Button("Open");

    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        window = primaryStage;
        window.setTitle("Dog Door Simulator");
        window.setOnCloseRequest(e -> closeProgram());

        dogOutsideImageView.setVisible(false);

        dogInsideImageView.setPreserveRatio(true);
        dogDoorImageView.setPreserveRatio(true);
        dogOutsideImageView.setPreserveRatio(true);

        dogInsideImageView.setFitWidth(350);
        dogDoorImageView.setFitWidth(400);
        dogOutsideImageView.setFitWidth(400);

        button.setOnAction(e -> remote.pressButton());

        gridPane.add(dogInsideImageView, 0, 0);
        gridPane.add(dogDoorImageView, 1, 0);
        gridPane.add(dogOutsideImageView, 2, 0);
        gridPane.add(button, 0, 1);

        scene = new Scene(gridPane, 1200, 600);
        window.setScene(scene);
        window.show();
    }

    /**
     * Terminates thread and closes program window.
     */
    private void closeProgram()
    {
        window.close();
    }
}