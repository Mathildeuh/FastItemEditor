package fr.mathilde.commands.FastItemEditorCommand.subcommands;

import fr.mathilde.commands.FastItemEditorCommand.FieCommand;
import fr.mathilde.commands.FastItemEditorCommand.SubCommands;
import fr.mathilde.lang.Commands;
import org.bukkit.entity.Player;

public class HelpCommand extends SubCommands {
    public static void sendHelpMessage(Player player) {
        player.sendMessage(Commands.Help.getHelpMesasge());
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
        return Commands.Help.getSyntax();
    }


    @Override
    public void run(Player player, String[] args) {
        sendHelpMessage(player);
    }


}
