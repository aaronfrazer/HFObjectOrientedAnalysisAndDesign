package gameFramework;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class TestUnit
{
    @Test
    public void testType()
    {
        Unit unit = new Unit(1000);
        String type = "infantry";
        unit.setType(type);

        assertEquals(unit.getType(), type);
    }

    @Test
    public void testUnitSpecificProperty()
    {
        Unit unit = new Unit(1000);
        String propertyName = "hitPoints";
        Object expectedOutputType = 25;
        unit.setProperty(propertyName, expectedOutputType);

        assertEquals(unit.getProperty(propertyName), expectedOutputType);
    }

    @Test
    public void testNonExistentProperty()
    {
        Unit unit = new Unit(1000);
        String propertyName = "strength";
        Object outputValue = unit.getProperty(propertyName);

        assertNull(outputValue);
    }
}
