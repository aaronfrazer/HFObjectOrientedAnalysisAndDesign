package ch4;

import java.util.ArrayList;

class BarkRecognizer
{
    private DogDoor door;

    BarkRecognizer(DogDoor door)
    {
        this.door = door;
    }

    void recognize(Bark bark)
    {
        System.out.println("BarkRecognizer: Heard a '" + bark.getSound() + "'");

        ArrayList<Bark> allowedBarks = door.getAllowedBarks();
        for (Bark allowedBark : allowedBarks)
        {
            if (allowedBark.equals(bark))
            {
                door.open();
                return;
            }
        }
        System.out.println("This dog is not allowed");
    }
}
