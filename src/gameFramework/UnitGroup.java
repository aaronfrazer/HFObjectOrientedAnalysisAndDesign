package gameFramework;

import java.util.*;

public class UnitGroup
{
    private Map units;

    private UnitGroup(List unitList)
    {
        units = new HashMap();
        for (Object o : unitList)
        {
            Unit unit = (Unit) o;
            units.put(unit.getId(), unit);
        }
    }

    public UnitGroup()
    {
        this(new LinkedList());
    }

    public void addUnit(Unit unit)
    {
        units.put(unit.getId(), unit);
    }

    private void removeUnit(int id)
    {
        units.remove(id);
    }

    public void removeUnit(Unit unit)
    {
        removeUnit(unit.getId());
    }

    public Unit getUnit(int id)
    {
        return (Unit) units.get(id);
    }

    public List getUnits()
    {
        List unitList = new LinkedList();
        for (Object o : units.entrySet())
        {
            Unit unit = (Unit) o;
            unitList.add(unit);
        }
        return unitList;
    }
}
