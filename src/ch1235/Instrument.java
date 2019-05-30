package ch1235;

public class Instrument
{

    private String serialNumber;
    private double price;
    private InstrumentSpec spec;

    Instrument(String serialNumber, double price, InstrumentSpec spec)
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

    InstrumentSpec getSpec()
    {
        return spec;
    }
}
