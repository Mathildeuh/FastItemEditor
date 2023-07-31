package fr.mathilde.commands;

import fr.mathilde.FastItemEditor;
import fr.mathilde.inventories.FastItemEditorGUI;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FieCommand implements CommandExecutor {

    static FastItemEditor plugin;

    public FieCommand(FastItemEditor fastItemEditor) {
        plugin = fastItemEditor;
    }

    public static void openMainGui(Player player) {
        FastItemEditor.guiAPI.openGUI(player, new FastItemEditorGUI(plugin, player.getItemInHand()));
        String relocalizedName = player.getItemInHand().getType().name().toLowerCase().replaceAll("_", " ");

        player.getOpenInventory()
                .setTitle("§3Fast Item Editor §7- §2" + relocalizedName);

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

        openMainGui(player);
        return true;
    }
}
