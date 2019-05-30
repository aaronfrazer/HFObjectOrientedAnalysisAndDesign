package dogDoors;

class Remote
{
    private DogDoor door;

    Remote(DogDoor door)
    {
        this.door = door;
    }

    void pressButton()
    {
        System.out.println("Pressing remote control button...");
        if (DogDoor.isOpen())
            door.close();
        else
            door.open();
    }
}