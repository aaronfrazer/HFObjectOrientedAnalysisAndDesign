package ch3;

public class DogDoor
{
    private Dog dog;
    private static boolean open;

    DogDoor(Dog dog)
    {
        this.dog = dog;
        open = false;
    }

    void open()
    {
        Main.dogDoorImageView.setImage(Main.dogDoorOpenImage);
        Main.button.setText("Close");

        open = true;
        System.out.println("The dog door is open");

        if (!Dog.isOutside() && dog.needsToPee())
            Dog.goOutside();
        else if (Dog.isOutside() && !dog.needsToPee())
            Dog.goInside();
        else if (Dog.isOutside() && dog.needsToPee())
            System.out.println("Dog is still peeing so he stayed outside");
        else if (!Dog.isOutside() && !dog.needsToPee())
            System.out.println("Dog doesn't need to pee so he stayed inside");
    }

    void close()
    {
        Main.dogDoorImageView.setImage(Main.dogDoorCloseImage);
        Main.button.setText("Open");

        open = false;
        System.out.println("The dog door is closed");

        Remote.timelineCloseDoor.stop();
    }

    static boolean isOpen()
    {
        return open;
    }
}