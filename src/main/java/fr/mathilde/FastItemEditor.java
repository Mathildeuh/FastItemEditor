package fr.mathilde;

import dev.jcsoftware.minecraft.gui.GUIAPI;
import fr.mathilde.commands.FastItemEditorCommand.FieCommand;
import fr.mathilde.events.ChatListener;
import fr.mathilde.events.FieGuiListener;
import fr.mathilde.events.PlayerConnectListener;
import fr.mathilde.lang.Commands;
import fr.mathilde.lang.Inventories;
import fr.mathilde.lang.Languages;
import fr.mathilde.utilities.UpdateChecker;
import org.bstats.bukkit.Metrics;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class FastItemEditor extends JavaPlugin {

    public static GUIAPI<FastItemEditor> guiAPI;

    private static Languages acutalLang;
    private static boolean updateAvailable = false;
    private static String newVersion;
    private FileConfiguration langConfig;

    public static boolean hasUpdate() {
        return updateAvailable;
    }

    public static String getLatestVersion() {
        return newVersion;
    }

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
        getServer().getPluginManager().registerEvents(new PlayerConnectListener(this), this);
        bStats();
        update();
    }

    private void update() {
        new UpdateChecker(this, 111698).getVersion(version -> {
            newVersion = version;
            if (this.getDescription().getVersion().equals(version)) {
                updateAvailable = false;
                getLogger().info("No update available.");
            } else {
                updateAvailable = true;
                getLogger().info("There is a new update available, you are using version " + this.getDescription().getVersion() + " and the latest version is " + version + " !");
            }
        });
    }

    private void bStats() {
        int pluginId = 19518; // <-- Replace with the id of your plugin!
        new Metrics(this, pluginId);
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
