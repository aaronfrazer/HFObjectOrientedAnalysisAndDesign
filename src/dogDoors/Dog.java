package dogDoors;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

class Dog
{
    private static boolean needsToPee;
    private static boolean isOutside;
    private Bark bark;
    private String name;

    // Time it takes for dog to pee/bark
    private Timeline timelinePee;
    Timeline timelineBark;

    Dog(String name, Bark bark, int timeToPee)
    {
        needsToPee = false;
        isOutside = false;
        this.name = name;
        this.bark = bark;
        timelinePee = new Timeline(new KeyFrame(Duration.seconds(timeToPee), event -> pee()), new KeyFrame(Duration.seconds(timeToPee + 1), event -> needsInside()));
        timelineBark = new Timeline(new KeyFrame(Duration.seconds(timeToPee), event -> bark()));
    }

    boolean needsToPee()
    {
        return needsToPee;
    }

    static boolean isOutside()
    {
        return isOutside;
    }

    private void needsInside()
    {
        System.out.println(this.name + "'s all done...");
        bark();
    }

    void goOutside()
    {
        System.out.println(name + " has gone outside");
        Main.dogInsideImageView.setVisible(false);
        Main.dogOutsideImageView.setVisible(true);
        isOutside = true;
        timelineBark.stop();
        timelinePee.play();
    }

    void goInside()
    {
        System.out.println(name + "'s back inside");
        Main.dogOutsideImageView.setVisible(false);
        Main.dogInsideImageView.setVisible(true);
        isOutside = false;
        timelineBark.play();
    }

    private void pee()
    {
        System.out.println(this.name + " is peeing");
        needsToPee = false;
    }

    private void bark()
    {
        Main.bark(bark.getSound());

        if (!isOutside())
        {
            System.out.println(name + " starts barking (wants outside)");
            needsToPee = true;
            if (DogDoor.isOpen())
                goOutside();
        } else
        {
            System.out.println(name + " starts barking (wants inside)");
        }

        Main.recognizer.recognize(bark); // Remove this line of code if you want to manually push the button
    }

    String getName()
    {
        return name;
    }
}