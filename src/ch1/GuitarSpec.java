package ch1;

public class GuitarSpec
{

    private Builder builder;
    private String model;
    private Type type;
    private int numStrings;
    private Wood backWood;
    private Wood topWood;

    GuitarSpec(Builder builder, String model, Type type, int numStrings, Wood backWood, Wood topWood)
    {
        this.builder = builder;
        this.model = model;
        this.type = type;
        this.numStrings = numStrings;
        this.backWood = backWood;
        this.topWood = topWood;
    }

    Builder getBuilder()
    {
        return builder;
    }

    String getModel()
    {
        return model;
    }

    Type getType()
    {
        return type;
    }

    public int getNumStrings()
    {
        return numStrings;
    }

    Wood getBackWood()
    {
        return backWood;
    }

    Wood getTopWood()
    {
        return topWood;
    }

    boolean matches(GuitarSpec otherSpec)
    {
        if (builder != otherSpec.builder)
            return false;
        if ((model != null) && (!model.equals("")) &&
                (!model.toLowerCase().equals(otherSpec.model.toLowerCase())))
            return false;
        if (type != otherSpec.type)
            return false;
        if (numStrings != otherSpec.numStrings)
            return false;
        if (backWood != otherSpec.backWood)
            return false;
        return topWood == otherSpec.topWood;
    }
}
