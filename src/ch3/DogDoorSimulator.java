package ch3;

public class DogDoorSimulator
{

    public static void main(String[] args)
    {
        Dog dog = new Dog();
        DogDoor door = new DogDoor(dog);
        BarkRecognizer recognizer = new BarkRecognizer(door);
        Remote remote = new Remote(door);

        System.out.println("Fido starts barking.");
        recognizer.recognize("Woof");

        System.out.println("\nFido has gone outside...");

        System.out.println("\nFido's all done...");

        try
        {
            Thread.sleep(10000);
        } catch (InterruptedException e)
        {
            System.err.println("Error: Sleep interrupted");
        }

        System.out.println("...but he's stuck outside!");

        System.out.println("\nFido starts barking.");
        recognizer.recognize("Woof");

        System.out.println("\nFido's back inside...");
    }
}
