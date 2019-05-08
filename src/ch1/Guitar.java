package ch1;

public class Guitar
{
    private String serialNumber;
    private double price;
    private GuitarSpec spec;

    Guitar(String serialNumber, double price, GuitarSpec spec)
    {
        this.serialNumber = serialNumber;
        this.price = price;
        this.spec = spec;
    }

    String getSerialNumber()
    {
        return serialNumber;
    }

    double getPrice()
    {
        return price;
    }

    public void setPrice(float newPrice)
    {
        this.price = newPrice;
    }

    GuitarSpec getSpec()
    {
        return spec;
    }
}
