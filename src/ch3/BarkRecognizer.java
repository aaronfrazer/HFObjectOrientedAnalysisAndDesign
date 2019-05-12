package ch3;

class BarkRecognizer
{
    private DogDoor door;

    BarkRecognizer(DogDoor door)
    {
        this.door = door;
    }

    void recognize(String bark)
    {
        System.out.println("BarkRecognizer: Heard a '" + bark + "'");
        door.open();
    }
}
