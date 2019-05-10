package ch1;

import java.util.LinkedList;
import java.util.List;

class Inventory
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
