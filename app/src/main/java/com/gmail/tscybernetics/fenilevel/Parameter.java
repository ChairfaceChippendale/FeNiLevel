package com.gmail.tscybernetics.fenilevel;


public class Parameter {

    String name;
    double value;
    String unit;

    double maxValue;
    double minValue;

    public Parameter(String name, double value, String unit, double maxValue, double minValue) {
        this.name = name;
        this.unit = unit;
        this.maxValue = maxValue;
        this.minValue = minValue;
        setValue(value);
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        if (value > maxValue) {
            this.value = maxValue;
        } else if (value < minValue) {
            this.value = minValue;
        } else {
            this.value = value;
        }
    }

    public String getUnit() {
        return unit;
    }
}
