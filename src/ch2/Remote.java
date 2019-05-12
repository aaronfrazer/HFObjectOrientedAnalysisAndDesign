package ch2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

class Remote
{
    private static DogDoor door;

    // Time it takes to close the door automatically
    static Timeline timelineCloseDoor = new Timeline(new KeyFrame(Duration.seconds(5), event -> door.close()));

    Remote(DogDoor door)
    {
        Remote.door = door;
    }

    void pressButton()
    {
        System.out.println("Pressing the remote control button...");
        if (DogDoor.isOpen())
        {
            door.close();
        } else
        {
            door.open();

            timelineCloseDoor.play();
        }
    }
}