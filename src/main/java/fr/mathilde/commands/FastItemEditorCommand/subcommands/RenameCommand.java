package fr.mathilde.commands.FastItemEditorCommand.subcommands;

import fr.mathilde.FastItemEditor;
import fr.mathilde.commands.FastItemEditorCommand.SubCommands;
import fr.mathilde.inventories.RenameGUI;
import fr.mathilde.lang.Commands;
import fr.mathilde.utilities.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RenameCommand extends SubCommands {
    FastItemEditor plugin;

    public RenameCommand(FastItemEditor plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "rename";
    }

    @Override
    public String getSyntax() {
        return Commands.Rename.getSyntax();
    }

    @Override
    public void run(Player player, String[] args) {
        ItemStack stack = new ItemBuilder(player.getItemInHand()).toItemStack();

        if (args.length == 1) {
            RenameGUI.openAnvilGui(player, plugin, false);
            return;
        }
        StringBuilder name = new StringBuilder();

        for (int i = 1; i < args.length; i++) {
            name.append(args[i]).append(" ");

        }
        name = new StringBuilder(ChatColor.translateAlternateColorCodes('&', name.toString()));
        ItemStack renamedStack = new ItemBuilder(stack).setName(name.toString()).toItemStack();

        player.setItemInHand(renamedStack);
        System.out.println(renamedStack.getItemMeta().getDisplayName());
        player.sendMessage(Commands.Rename.getRenamed().replace("%name%", name));
    }
}
