package dogDoors;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application
{
    private static Dog bruce = new Dog("Bruce", new Bark("rowlf"), 5);
    private static Dog fido = new Dog("Fido", new Bark("yip"), 2);
    private static DogDoor door = new DogDoor(bruce);
    static BarkRecognizer recognizer = new BarkRecognizer(door);
    private Remote remote = new Remote(door);

    private static String imageDir = "res/images/";
    private static String soundDir = "res/sounds/";

    private Stage window;
    private Scene scene;
    private GridPane gridPane = new GridPane();

    static Image dogDoorOpenImage = new Image(new File(imageDir + "DogDoorOpen.png").toURI().toString());
    static Image dogDoorCloseImage = new Image(new File(imageDir + "DogDoorClose.png").toURI().toString());
    private static Image dogInsideImage = new Image(new File(imageDir + "DogInside.png").toURI().toString());
    private static Image dogOutsideImage = new Image(new File(imageDir + "DogOutside.png").toURI().toString());

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

        door.addAllowedBark(new Bark("rowlf"));
        door.addAllowedBark(new Bark("rooowlf"));
        door.addAllowedBark(new Bark("rawlf"));
        door.addAllowedBark(new Bark("woof"));

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

        // Dog barks after 5 seconds
        bruce.timelineBark.play();
        fido.timelineBark.play();
    }

    /**
     * Makes the sound of a bruce barking.
     */
    static void bark(String soundFile)
    {
        Media sound = new Media(new File(soundDir + soundFile + ".wav").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    /**
     * Terminates thread and closes program window.
     */
    private void closeProgram()
    {
        window.close();
    }
}