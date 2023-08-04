package fr.mathilde;

import dev.jcsoftware.minecraft.gui.GUIAPI;
import fr.mathilde.commands.FastItemEditorCommand.FieCommand;
import fr.mathilde.events.ChatListener;
import fr.mathilde.events.FieGuiListener;
import fr.mathilde.lang.Commands;
import fr.mathilde.lang.Inventories;
import fr.mathilde.lang.Languages;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class FastItemEditor extends JavaPlugin {

    public static GUIAPI<FastItemEditor> guiAPI;

    private static Languages acutalLang;

    private FileConfiguration langConfig;

    public FileConfiguration getLangConfig() {
        return langConfig;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        createLangConfig();

        // Plugin startup logic
        guiAPI = new GUIAPI<>(this);

        // Commands

        getCommand("fastitemeditor").setExecutor(new FieCommand(this));
        getCommand("fastitemeditor").setTabCompleter(new FieCommand());


        // Events
        getServer().getPluginManager().registerEvents(new FieGuiListener(this), this);
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);

    }

    public void createLangConfig() {
        acutalLang = Languages.valueOf(getConfig().getString("lang").toUpperCase());
        File langConfigFile = acutalLang.getFile();
        if (!langConfigFile.exists()) {
            langConfigFile.getParentFile().mkdirs();
            saveResource(acutalLang.getFileName(), false);
        }


        langConfig = YamlConfiguration.loadConfiguration(acutalLang.getFile());
        new Commands(this);
        new Inventories.RenameGUI(this);
        new Inventories.ItemFlagGUI(this);
        new Inventories.MainGUI(this);
        new Inventories.EnchantGUI(this);

    }


}
