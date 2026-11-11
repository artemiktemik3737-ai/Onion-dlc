package net.onion.client.manager;

import net.onion.client.module.Category;
import net.onion.client.module.Module;
import net.onion.client.module.settings.Setting;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    private static final List<Module> modules = new ArrayList<>();

    public static void init() {
        // --- COMBAT ---
        Module aimAssist = new Module("AimAssist", Category.COMBAT);
        aimAssist.getSettings().add(new Setting("Humanization", true));
        aimAssist.getSettings().add(new Setting("FOV", 35.0, 5.0, 180.0));
        aimAssist.getSettings().add(new Setting("Distance", 4.0, 1.0, 6.0));
        aimAssist.getSettings().add(new Setting("ReactionDelay", 70.0, 0.0, 300.0));
        aimAssist.getSettings().add(new Setting("PacketSmoothing", true));
        aimAssist.getSettings().add(new Setting("StrictBypass", true));
        modules.add(aimAssist);

        Module triggerBot = new Module("TriggerBot", Category.COMBAT);
        triggerBot.getSettings().add(new Setting("OnlyCrit", false));
        triggerBot.getSettings().add(new Setting("WeaponOnly", true));
        triggerBot.getSettings().add(new Setting("MinDelay", 45.0, 0.0, 500.0));
        triggerBot.getSettings().add(new Setting("MaxDelay", 110.0, 0.0, 500.0));
        triggerBot.getSettings().add(new Setting("Randomize", true));
        triggerBot.getSettings().add(new Setting("ShieldBypass", true));
        modules.add(triggerBot);

        modules.add(new Module("AutoTotem", Category.COMBAT));
        modules.add(new Module("CrystalTap", Category.COMBAT));
        modules.add(new Module("ShiftTap", Category.COMBAT));
        modules.add(new Module("WTap", Category.COMBAT));
        modules.add(new Module("MaceSpear", Category.COMBAT));
        modules.add(new Module("BreachSwap", Category.COMBAT));
        modules.add(new Module("Backtrack", Category.COMBAT));
        modules.add(new Module("HitBoxExpand", Category.COMBAT));
        modules.add(new Module("VelocityBypass", Category.COMBAT));

        // --- MOVEMENT ---
        modules.add(new Module("NoSlow", Category.MOVEMENT));
        modules.add(new Module("InvMove", Category.MOVEMENT));
        modules.add(new Module("AirStuck", Category.MOVEMENT));
        modules.add(new Module("NoWeb", Category.MOVEMENT));
        modules.add(new Module("PearlCatch", Category.MOVEMENT));
        modules.add(new Module("SpeedBypass", Category.MOVEMENT));
        modules.add(new Module("Sprint", Category.MOVEMENT));
        modules.add(new Module("Step", Category.MOVEMENT));
        modules.add(new Module("SafeWalk", Category.MOVEMENT));
        modules.add(new Module("Blink", Category.MOVEMENT));

        // --- PLAYER ---
        modules.add(new Module("AutoSwap", Category.PLAYER));
        modules.add(new Module("ItemScroller", Category.PLAYER));
        modules.add(new Module("ChestStealer", Category.PLAYER));
        modules.add(new Module("NoServerRotate", Category.PLAYER));
        modules.add(new Module("SafeLeave", Category.PLAYER));
        modules.add(new Module("AutoArmor", Category.PLAYER));
        modules.add(new Module("AutoTool", Category.PLAYER));
        modules.add(new Module("FastEat", Category.PLAYER));
        modules.add(new Module("NoFall", Category.PLAYER));
        modules.add(new Module("Freecam", Category.PLAYER));

        // --- RENDER ---
        modules.add(new Module("BaseFinder", Category.RENDER));
        modules.add(new Module("NameTags", Category.RENDER));
        modules.add(new Module("PlayerESP", Category.RENDER));
        modules.add(new Module("StorageESP", Category.RENDER));
        modules.add(new Module("Tracers", Category.RENDER));
        modules.add(new Module("Trajectories", Category.RENDER));
        modules.add(new Module("ShulkerPreview", Category.RENDER));
        modules.add(new Module("FullBright", Category.RENDER));
        modules.add(new Module("TargetHUD", Category.RENDER));
        modules.add(new Module("ArmorStatus", Category.RENDER));

        // --- VISUAL ---
        Module ambience = new Module("Ambience", Category.VISUAL);
        ambience.getSettings().add(new Setting("CustomTime", true));
        ambience.getSettings().add(new Setting("TimeValue", 12000.0, 0.0, 24000.0));
        ambience.getSettings().add(new Setting("WetWorld", true));
        ambience.getSettings().add(new Setting("Reflection", 50.0, 0.0, 100.0));
        ambience.getSettings().add(new Setting("FogDensity", 40.0, 0.0, 100.0));
        modules.add(ambience);

        modules.add(new Module("NoRender", Category.VISUAL));
        modules.add(new Module("Saturation", Category.VISUAL));
        modules.add(new Module("AspectRatio", Category.VISUAL));
        modules.add(new Module("Bloom", Category.VISUAL));
        modules.add(new Module("Atmosphere", Category.VISUAL));
        modules.add(new Module("JumpCircles", Category.VISUAL));
        modules.add(new Module("Zoom", Category.VISUAL));
        modules.add(new Module("HandGlow", Category.VISUAL));
        modules.add(new Module("MotionBlur", Category.VISUAL));

        // --- CLIENT ---
        modules.add(new Module("ClickGUI", Category.CLIENT));
        modules.add(new Module("Configs", Category.CLIENT));
        modules.add(new Module("StreamMode", Category.CLIENT));
        modules.add(new Module("AntiCheatBypass", Category.CLIENT));
        modules.add(new Module("PacketFixer", Category.CLIENT));
        modules.add(new Module("FPSBooster", Category.CLIENT));
    }

    public static List<Module> getModules() { return modules; }
    
    public static List<Module> getModulesByCategory(Category category) {
        return modules.stream().filter(m -> m.getCategory() == category).collect(Collectors.toList());
    }
}
