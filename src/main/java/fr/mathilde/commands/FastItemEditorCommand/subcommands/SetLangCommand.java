package fr.mathilde.commands.FastItemEditorCommand.subcommands;

import fr.mathilde.FastItemEditor;
import fr.mathilde.commands.FastItemEditorCommand.SubCommands;
import fr.mathilde.lang.Languages;
import org.bukkit.entity.Player;

public class SetLangCommand extends SubCommands {

    private final FastItemEditor plugin;

    public SetLangCommand(FastItemEditor plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "setlang";
    }

    @Override
    public String getSyntax() {
        return "§a/fie §esetlang <lang>";
    }

    @Override
    public void run(Player player, String[] args) {
        if (args.length <= 1) {
            player.sendMessage(getSyntax());
            return;
        }

        Languages lang;

        try {
            lang = Languages.valueOf(args[1].toUpperCase());
        } catch (IllegalArgumentException e) {
            player.sendMessage("§cInvalid language.");
            return;
        }


        switch (lang){
            case EN_US -> {
                plugin.getConfig().set("lang", "en_us");
                plugin.saveConfig();
                plugin.reloadConfig();
                player.sendMessage("§aLanguage set to English (US).");
            }
            case FR_FR -> {
                plugin.getConfig().set("lang", "fr_fr");
                plugin.saveConfig();
                plugin.reloadConfig();
                player.sendMessage("§aLangue définie sur Français (FR).");
            }
        }

        plugin.createLangConfig();
    }
}
