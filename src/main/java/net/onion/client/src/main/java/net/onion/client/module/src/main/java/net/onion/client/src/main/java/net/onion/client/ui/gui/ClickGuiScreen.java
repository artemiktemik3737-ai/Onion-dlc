package net.onion.client.ui.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Screen;
import net.minecraft.text.Text;
import net.onion.client.manager.ModuleManager;
import net.onion.client.module.Category;
import net.onion.client.module.Module;
import net.onion.client.module.settings.Setting;

import java.awt.Color;
import java.util.List;

public class ClickGuiScreen extends Screen {
    private Category selectedCategory = Category.COMBAT;
    private Module selectedModule = null;
    
    private final float posX = 70;
    private final float posY = 50;
    private final float width = 580;
    private final float height = 380;

    public ClickGuiScreen() {
        super(Text.literal("Onion DLC Glass ClickGUI"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int glassBg = new Color(14, 14, 18, 220).getRGB();
        int glassBorder = new Color(45, 45, 60, 180).getRGB();
        
        context.fill((int)posX, (int)posY, (int)(posX + width), (int)(posY + height), glassBg);
        context.drawBorder((int)posX, (int)posY, (int)width, (int)height, glassBorder);

        renderSidebar(context, mouseX, mouseY);
        renderModulesList(context, mouseX, mouseY);
        renderSettingsPanel(context, mouseX, mouseY);

        super.render(context, mouseX, mouseY, delta);
    }

    private void renderSidebar(DrawContext context, int mouseX, int mouseY) {
        int sidebarWidth = 140;
        int sidebarBg = new Color(10, 10, 13, 140).getRGB();
        
        context.fill((int)posX, (int)posY, (int)(posX + sidebarWidth), (int)(posY + height), sidebarBg);

        context.drawText(this.textRenderer, "✦ Onion DLC", (int)posX + 16, (int)posY + 16, 0xFFFFFF, true);
        context.drawText(this.textRenderer, "Client categories", (int)posX + 16, (int)posY + 34, 0x666666, false);

        int startY = (int)posY + 60;
        for (Category category : Category.values()) {
            boolean isSelected = (category == selectedCategory);
            
            if (isSelected) {
                context.fill((int)posX + 10, startY - 4, (int)(posX + sidebarWidth - 10), startY + 18, new Color(55, 55, 75, 130).getRGB());
            }

            int textColor = isSelected ? 0xFFFFFF : 0x777777;
            context.drawText(this.textRenderer, category.name(), (int)posX + 20, startY, textColor, false);
            startY += 26;
        }
    }

    private void renderModulesList(DrawContext context, int mouseX, int mouseY) {
        int listX = (int)posX + 150;
        int listY = (int)posY + 16;

        context.drawText(this.textRenderer, selectedCategory.name(), listX, listY, 0xFFFFFF, true);

        int moduleY = listY + 26;
        List<Module> modules = ModuleManager.getModulesByCategory(selectedCategory);

        for (Module module : modules) {
            int cardBg = new Color(22, 22, 28, 180).getRGB();
            if (module == selectedModule) {
                cardBg = new Color(45, 45, 60, 200).getRGB();
            }
            
            context.fill(listX, moduleY, listX + 190, moduleY + 28, cardBg);
            context.drawText(this.textRenderer, module.getName(), listX + 10, moduleY + 10, 0xEEEEEE, false);
            
            String status = module.isEnabled() ? "On" : "Off";
            int statusColor = module.isEnabled() ? 0x55FF55 : 0x666666;
            context.drawText(this.textRenderer, status, listX + 155, moduleY + 10, statusColor, false);

            moduleY += 34;
        }
    }

    private void renderSettingsPanel(DrawContext context, int mouseX, int mouseY) {
        if (selectedModule == null) return;

        int settingsX = (int)posX + 350;
        int settingsY = (int)posY + 16;
        int settingsBg = new Color(18, 18, 24, 150).getRGB();

        context.fill(settingsX, settingsY, (int)(posX + width - 15), (int)(posY + height - 16), settingsBg);
        context.drawText(this.textRenderer, selectedModule.getName() + " Settings", settingsX + 12, settingsY + 10, 0xFFFFFF, true);

        int settingY = settingsY + 35;
        for (Setting setting : selectedModule.getSettings()) {
            context.drawText(this.textRenderer, setting.getName(), settingsX + 12, settingY, 0xCCCCCC, false);
            
            if (setting.getMax() == 0 && setting.getMin() == 0) {
                String val = setting.getBooleanValue() ? "True" : "False";
                context.drawText(this.textRenderer, val, settingsX + 150, settingY, setting.getBooleanValue() ? 0x55FF55 : 0x888888, false);
            } else {
                context.drawText(this.textRenderer, String.format("%.1f", setting.getDoubleValue()), settingsX + 140, settingY, 0xAAAAAA, false);
            }
            
            settingY += 28;
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int sidebarWidth = 140;
        if (mouseX >= posX && mouseX <= posX + sidebarWidth && mouseY >= posY + 60 && mouseY <= posY + height) {
            int index = (int)((mouseY - (posY + 60)) / 26);
            Category[] categories = Category.values();
            if (index >= 0 && index < categories.length) {
                selectedCategory = categories[index];
                selectedModule = null;
            }
        }
        
        int listX = (int)posX + 150;
        int listY = (int)posY + 42;
        List<Module> modules = ModuleManager.getModulesByCategory(selectedCategory);
        for (int i = 0; i < modules.size(); i++) {
            int moduleY = listY + (i * 34);
            if (mouseX >= listX && mouseX <= listX + 190 && mouseY >= moduleY && mouseY <= moduleY + 28) {
                if (button == 0) {
                    modules.get(i).toggle();
                } else if (button == 1) {
                    selectedModule = modules.get(i);
                }
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
