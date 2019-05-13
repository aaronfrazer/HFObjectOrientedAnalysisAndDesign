package ch4;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;

class DogDoor
{
    private Dog dog;
    private static boolean open;
    private ArrayList<Bark> allowedBarks;

    private static Timeline timelineCloseDoor;

    DogDoor(Dog dog)
    {
        this.dog = dog;
        open = false;
        this.allowedBarks = new ArrayList<>();
        timelineCloseDoor = new Timeline(new KeyFrame(Duration.seconds(2), event -> this.close()));

    }

    void addAllowedBark(Bark bark)
    {
        allowedBarks.add(bark);
    }

    ArrayList<Bark> getAllowedBarks()
    {
        return allowedBarks;
    }

    void open()
    {
        Main.dogDoorImageView.setImage(Main.dogDoorOpenImage);
        Main.button.setText("Close");

        open = true;
        System.out.println("The dog door is open");

        if (!dog.isOutside() && dog.needsToPee())
            dog.goOutside();
        else if (dog.isOutside() && !dog.needsToPee())
            dog.goInside();
        else if (dog.isOutside() && dog.needsToPee())
            System.out.println(dog.getName() + " is still peeing so he stayed outside");
        else if (!dog.isOutside() && !dog.needsToPee())
            System.out.println(dog.getName() + " doesn't need to pee so he stayed inside");

        timelineCloseDoor.play(); // close automatically
    }

    void close()
    {
        Main.dogDoorImageView.setImage(Main.dogDoorCloseImage);
        Main.button.setText("Open");

        open = false;
        System.out.println("The dog door is closed");

        timelineCloseDoor.stop();
    }

    static boolean isOpen()
    {
        return open;
    }
}