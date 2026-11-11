package net.onion.client.module.settings;

public class Setting {
    private final String name;
    private boolean booleanValue;
    private double doubleValue;
    private double min, max;

    public Setting(String name, boolean defaultValue) {
        this.name = name;
        this.booleanValue = defaultValue;
    }

    public Setting(String name, double defaultValue, double min, double max) {
        this.name = name;
        this.doubleValue = defaultValue;
        this.min = min;
        this.max = max;
    }

    public String getName() { return name; }
    public boolean getBooleanValue() { return booleanValue; }
    public void setBooleanValue(boolean value) { this.booleanValue = value; }
    public double getDoubleValue() { return doubleValue; }
    public void setDoubleValue(double value) { this.doubleValue = value; }
    public double getMin() { return min; }
    public double getMax() { return max; }
}
