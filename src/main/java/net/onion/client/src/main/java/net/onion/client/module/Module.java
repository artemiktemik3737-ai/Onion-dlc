package net.onion.client.module;

import net.onion.client.module.settings.Setting;
import java.util.ArrayList;
import java.util.List;

public class Module {
    private final String name;
    private final Category category;
    private boolean enabled;
    private final List<Setting> settings = new ArrayList<>();

    public Module(String name, Category category) {
        this.name = name;
        this.category = category;
        this.enabled = false;
    }

    public String getName() { return name; }
    public Category getCategory() { return category; }
    public boolean isEnabled() { return enabled; }
    public void toggle() { this.enabled = !this.enabled; }
    public List<Setting> getSettings() { return settings; }
    
    protected void addSetting(Setting setting) {
        this.settings.add(setting);
    }
}
