package ch1235;

import java.util.LinkedList;
import java.util.List;

class Inventory
{
    private List inventory;

    Inventory()
    {
        inventory = new LinkedList();
    }

    void addInstrument(String serialNumber, double price, InstrumentSpec spec)
    {
        Instrument instrument = new Instrument(serialNumber, price, spec);
        inventory.add(instrument);
    }

    Instrument get(String serialNumber)
    {
        for (Object o : inventory)
        {
            Instrument instrument = (Instrument) o;
            if (instrument.getSerialNumber().equals(serialNumber))
                return instrument;
        }
        return null;
    }

    List search(InstrumentSpec searchSpec)
    {
        List matchingInstruments = new LinkedList();
        for (Object o : inventory)
        {
            Instrument instrument = (Instrument) o;
            if (instrument.getSpec().matches(searchSpec))
                matchingInstruments.add(instrument);
        }
        return matchingInstruments;
    }
}
