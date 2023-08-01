package fr.mathilde;

import dev.jcsoftware.minecraft.gui.GUIAPI;
import fr.mathilde.commands.FastItemEditorCommand.FieCommand;
import fr.mathilde.events.FieGuiListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class FastItemEditor extends JavaPlugin {

    public static GUIAPI<FastItemEditor> guiAPI;


    @Override
    public void onEnable() {
        // Plugin startup logic
        guiAPI = new GUIAPI<>(this);

        // Commands
        FieCommand fieCommand = new FieCommand(this);
        getCommand("fastitemeditor").setExecutor(fieCommand);
        getCommand("fastitemeditor").setTabCompleter(fieCommand);

        // Events
        getServer().getPluginManager().registerEvents(new FieGuiListener(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
