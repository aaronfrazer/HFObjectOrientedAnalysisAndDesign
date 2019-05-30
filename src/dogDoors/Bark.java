package dogDoors;

public class Bark
{
    private String sound;

    Bark(String sound)
    {
        this.sound = sound;
    }

    public boolean equals(Object bark)
    {
        if (bark instanceof Bark)
        {
            Bark otherBark = (Bark) bark;
            return this.sound.equalsIgnoreCase(otherBark.sound);
        }
        return false;
    }

    String getSound()
    {
        return sound;
    }
}
