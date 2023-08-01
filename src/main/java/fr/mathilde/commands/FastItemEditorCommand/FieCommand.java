package fr.mathilde.commands.FastItemEditorCommand;

import fr.mathilde.FastItemEditor;
import fr.mathilde.commands.FastItemEditorCommand.subcommands.*;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static fr.mathilde.inventories.FastItemEditorGUI.openMainGui;


public class FieCommand implements CommandExecutor, TabCompleter {

    public static HashMap<String, SubCommands> subcommands = new HashMap<>();

    static FastItemEditor plugin;
    List<String> commands = new ArrayList<>();

    public FieCommand() {
    }

    public FieCommand(FastItemEditor fastItemEditor) {
        plugin = fastItemEditor;

        subcommands.put("help", new HelpCommand());
        subcommands.put("rename", new RenameCommand(plugin));
        subcommands.put("setlore", new SetLoreCommand(plugin));
        subcommands.put("enchant", new EnchantCommand(plugin));
        subcommands.put("itemflags", new ItemFlagsCommand(plugin));

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
            openMainGui(player, plugin);
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

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length <= 1) {
            for (SubCommands cmd : subcommands.values()) {
                commands.add(cmd.getName());
            }
            commands.sort(String::compareToIgnoreCase);
            return commands;
        }
        return null;
    }
}
