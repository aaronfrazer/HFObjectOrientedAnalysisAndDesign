package ch1;

import java.util.LinkedList;
import java.util.List;

public class Inventory
{
    private List guitars;

    Inventory()
    {
        guitars = new LinkedList();
    }

    void addGuitar(String serialNumber, double price, GuitarSpec spec)
    {
        Guitar guitar = new Guitar(serialNumber, price, spec);
        guitars.add(guitar);
    }

    public Guitar getGuitar(String serialNumber)
    {
        for (Object o : guitars)
        {
            Guitar guitar = (Guitar) o;
            if (guitar.getSerialNumber().equals(serialNumber))
            {
                return guitar;
            }
        }
        return null;
    }

    List search(GuitarSpec searchSpec)
    {
        List matchingGuitars = new LinkedList();
        for (Object o : guitars)
        {
            Guitar guitar = (Guitar) o;
            if (guitar.getSpec().matches(searchSpec))
                matchingGuitars.add(guitar);
        }
        return matchingGuitars;
    }
}
