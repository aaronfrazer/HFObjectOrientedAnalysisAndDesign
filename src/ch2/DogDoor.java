package ch2;

class DogDoor
{
    private boolean open;

    DogDoor()
    {
        this.open = false;
    }

    void open()
    {
        Main.dogDoorImageView.setImage(Main.dogDoorOpenImage);
        Main.dogInsideImageView.setVisible(false);
        Main.dogOutsideImageView.setVisible(true);
        Main.button.setText("Close");
        System.out.println("The dog door opens.");
        open = true;
    }

    void close()
    {
        Main.dogDoorImageView.setImage(Main.dogDoorCloseImage);
        Main.dogOutsideImageView.setVisible(false);
        Main.dogInsideImageView.setVisible(true);
        Main.button.setText("Open");
        System.out.println("The dog door closes.");
        open = false;
    }

    boolean isOpen()
    {
        return open;
    }
}