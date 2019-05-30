package ch1235;

import java.util.HashMap;
import java.util.Map;

class InstrumentSpec
{
    private Map properties;

    InstrumentSpec(Map properties)
    {
        if (properties == null)
        {
            this.properties = new HashMap();
        } else
        {
            this.properties = new HashMap(properties);
        }
    }

    Object getProperty(String propertyName)
    {
        return properties.get(propertyName);
    }

    Map getProperties()
    {
        return properties;
    }

    boolean matches(InstrumentSpec otherSpec)
    {
        for (Object o : otherSpec.getProperties().keySet())
        {
            String propertyName = (String) o;
            if (!properties.get(propertyName).equals(otherSpec.getProperty(propertyName)))
                return false;
        }
        return true;
    }
}