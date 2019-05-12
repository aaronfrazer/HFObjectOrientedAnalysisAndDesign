package ch2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

class Dog
{
    private static boolean needsToPee;
    private static boolean isOutside;

    // Time it takes for dog to pee/bark
    private static Timeline timelinePee = new Timeline(new KeyFrame(Duration.seconds(5), event -> pee()), new KeyFrame(Duration.seconds(6), event -> needsInside()));
    static Timeline timelineBark = new Timeline(new KeyFrame(Duration.seconds(5), event -> bark()));

    Dog()
    {
        needsToPee = false;
        isOutside = false;
    }

    boolean needsToPee()
    {
        return needsToPee;
    }

    static boolean isOutside()
    {
        return isOutside;
    }

    private static void needsInside()
    {
        System.out.println("Dog needs back inside (should bark)");
    }

    static void goOutside()
    {
        System.out.println("The dog went outside");
        Main.dogInsideImageView.setVisible(false);
        Main.dogOutsideImageView.setVisible(true);
        isOutside = true;
        timelineBark.stop();
        timelinePee.play();
    }

    static void goInside()
    {
        System.out.println("The dog went inside");
        Main.dogOutsideImageView.setVisible(false);
        Main.dogInsideImageView.setVisible(true);
        isOutside = false;
        timelineBark.play();
    }

    private static void pee()
    {
        System.out.println("Dog is peeing");
        needsToPee = false;
    }

    private static void bark()
    {
        Main.bark();

        if (isOutside())
        {
            System.out.println("Dog is barking needs to go inside...");
        } else
        {
            System.out.println("Dog is barking needs to go outside...");
            needsToPee = true;
            if (DogDoor.isOpen())
                goOutside();
        }
    }
}