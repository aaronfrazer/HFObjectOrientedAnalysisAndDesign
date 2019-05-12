package ch2;

public class DogDoorSimulator
{
    public static void main(String[] args)
    {
        Dog dog = new Dog();
        DogDoor door = new DogDoor(dog);
        Remote remote = new Remote(door);

        System.out.println("Fido barks to go outside...");
        remote.pressButton();

        System.out.println("\nFido has gone outside...");
        System.out.println("\nFido's all done...");

        try
        {
            Thread.sleep(10000);
        } catch (InterruptedException e)
        {
            System.err.println("Thread did not execute");
        }

        System.out.println("...but he's stuck outside!");

        System.out.println("\nFido starts barking...");
        System.out.println("...so Gina grabs the remote control.");
        remote.pressButton();

        System.out.println("\nFido's back inside...");
    }
}