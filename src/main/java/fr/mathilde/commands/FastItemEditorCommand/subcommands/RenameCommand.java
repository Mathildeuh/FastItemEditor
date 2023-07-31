package fr.mathilde.commands.FastItemEditorCommand.subcommands;

import fr.mathilde.FastItemEditor;
import fr.mathilde.inventories.AnvilGUI;
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
        return "§e/fastitemeditor §arename <name>";
    }

    @Override
    public void run(Player player, String[] args) {
        ItemStack stack = new ItemBuilder(player.getItemInHand()).toItemStack();

        if (args.length == 1) {
            AnvilGUI.openAnvilGui(player, stack.getItemMeta().getDisplayName(), plugin, false);
            return;
        }
        StringBuilder name = new StringBuilder();

        for (int i = 1; i < args.length; i++) {
            name.append(args[i]).append(" ");

        }
        name = new StringBuilder(ChatColor.translateAlternateColorCodes('&', name.toString()));
        stack.getItemMeta().setDisplayName(name.toString());
        player.setItemInHand(stack);
        player.sendMessage("§aItem renamed to §e" + name);
    }
}
