package ch2;

import java.util.Timer;
import java.util.TimerTask;

class Remote
{
    private DogDoor door;

    Remote(DogDoor door)
    {
        this.door = door;
    }

    void pressButton()
    {
        System.out.println("Pressing the remote control button...");
        if (door.isOpen())
        {
            door.close();
        } else
        {
            door.open();

            final Timer timer = new Timer();
            timer.schedule(new TimerTask()
            {
                public void run()
                {
                    door.close();
                    timer.cancel();
                }
            }, 5000);
        }
    }
}