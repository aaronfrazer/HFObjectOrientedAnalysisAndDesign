package ch3;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

class Dog
{
    private static boolean needsToPee;
    private static boolean isOutside;

    // Time it takes for dog to pee/bark
    private static Timeline timelinePee = new Timeline(new KeyFrame(Duration.seconds(5), event -> pee()), new KeyFrame(Duration.seconds(6), event -> needsInside()));
    static Timeline timelineBark = new Timeline(new KeyFrame(Duration.seconds(5), event -> bark("Woof")));

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
        System.out.println("Fido's all done...");
        bark("Woof");
    }

    static void goOutside()
    {
        System.out.println("Fido has gone outside");
        Main.dogInsideImageView.setVisible(false);
        Main.dogOutsideImageView.setVisible(true);
        isOutside = true;
        timelineBark.stop();
        timelinePee.play();
    }

    static void goInside()
    {
        System.out.println("Fido's back inside");
        Main.dogOutsideImageView.setVisible(false);
        Main.dogInsideImageView.setVisible(true);
        isOutside = false;
        timelineBark.play();
    }

    private static void pee()
    {
        System.out.println("Fido is peeing");
        needsToPee = false;
    }

    private static void bark(String bark)
    {
        Main.bark();

        if (!isOutside())
        {
            System.out.println("Fido starts barking (wants outside)");
            needsToPee = true;
            if (DogDoor.isOpen())
                goOutside();
        } else
        {
            System.out.println("Fido starts barking (wants inside)");
        }

        Main.recognizer.recognize(bark); // Remove this line of code if you want to manually push the button
    }
}