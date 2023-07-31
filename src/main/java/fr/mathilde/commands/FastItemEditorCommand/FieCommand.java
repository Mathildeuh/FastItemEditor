package fr.mathilde.commands.FastItemEditorCommand;

import fr.mathilde.FastItemEditor;
import fr.mathilde.commands.FastItemEditorCommand.subcommands.HelpCommand;
import fr.mathilde.commands.FastItemEditorCommand.subcommands.RenameCommand;
import fr.mathilde.commands.FastItemEditorCommand.subcommands.SetLoreCommand;
import fr.mathilde.commands.FastItemEditorCommand.subcommands.SubCommands;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

import static fr.mathilde.inventories.FastItemEditorGUI.openMainGui;

public class FieCommand implements CommandExecutor {

    public static HashMap<String, SubCommands> subcommands = new HashMap<>();

    static FastItemEditor plugin;

    public FieCommand(FastItemEditor fastItemEditor) {
        plugin = fastItemEditor;
        subcommands.put("help", new HelpCommand());
        subcommands.put("rename", new RenameCommand(plugin));
        subcommands.put("setlore", new SetLoreCommand(plugin));
    }

    @Deprecated
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("You must be a player to use this command.");
            return true;
        }

        ItemStack itemInHand = player.getItemInHand();

        if (itemInHand.getType() == Material.AIR) {
            player.sendMessage("You must hold an item in your hand to use this command.");
            return true;
        }

        if (args.length == 0) {
            openMainGui(player);
            return true;
        }

        if (subcommands.containsKey(args[0])) {
            subcommands.get(args[0]).run(player, args);
            return true;
        } else {
            HelpCommand.sendHelpMessage(player);
        }

        return true;
    }
}
