package gameFramework;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Unit
{
    private String type;
    private int id;
    private String name;
    private List weapons;
    private Map properties;

    Unit(int id)
    {
        this.id = id;
    }

    String getType()
    {
        return type;
    }

    void setType(String type)
    {
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List getWeapons()
    {
        return weapons;
    }

    int getId()
    {
        return id;
    }

    public void addWeapon(Weapon weapon)
    {
        if (weapon == null)
        {
            weapons = new LinkedList();
        }
        weapons.add(weapon);
    }

    void setProperty(String property, Object value)
    {
        if (properties == null)
        {
            properties = new HashMap();
        }
        properties.put(property, value);
    }

    Object getProperty(String property)
    {
        if (properties == null)
        {
            return null;
        }
        return properties.get(property);
    }
}
