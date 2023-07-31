package fr.mathilde.commands.FastItemEditorCommand.subcommands;

import fr.mathilde.commands.FastItemEditorCommand.FieCommand;
import org.bukkit.entity.Player;

public class HelpCommand extends SubCommands {
    public static void sendHelpMessage(Player player) {
        player.sendMessage("§6FastItemEditor commands:");
        for (SubCommands subCommand : FieCommand.subcommands.values()) {
            player.sendMessage(subCommand.getSyntax());
        }
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getSyntax() {
        return "§e/fastitemeditor §ahelp";
    }

    @Override
    public void run(Player player, String[] args) {
        sendHelpMessage(player);
    }


}
