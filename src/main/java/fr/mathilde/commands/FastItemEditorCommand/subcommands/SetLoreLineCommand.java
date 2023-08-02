package fr.mathilde.commands.FastItemEditorCommand.subcommands;

import fr.mathilde.FastItemEditor;
import fr.mathilde.commands.FastItemEditorCommand.SubCommands;
import fr.mathilde.utilities.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SetLoreLineCommand extends SubCommands {

    FastItemEditor plugin;

    public SetLoreLineCommand(FastItemEditor plugin) {
        super();
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "setloreline";
    }

    @Override
    public String getSyntax() {
        return "§e/fastitemeditor §asetloreline <line> <text>";
    }

    @Override
    public void run(Player player, String[] args) {
        if (args.length <= 2) {
            player.sendMessage("§cInvalid syntax.");
            player.sendMessage(getSyntax());
            return;
        }
        int i;
        try {
            i = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            player.sendMessage("§cInvalid line number.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int j = 2; j < args.length; j++) {
            sb.append(args[j]).append(" ");
        }
        String text = ChatColor.translateAlternateColorCodes('&', sb.toString().trim());
        if (text.equalsIgnoreCase("%blank")) {
            text = "";
        }
        if (i < 1) {
            player.sendMessage("§cInvalid line number.");
            return;
        }
        if (i > 64) {
            player.sendMessage("§cLine number too high.");
            return;
        }
        ItemBuilder stack = new ItemBuilder(player.getItemInHand());

        List<String> lore = player.getItemInHand().getItemMeta().hasLore() ? player.getItemInHand().getItemMeta().getLore() : new ArrayList<>();

        try {
            lore.set(i - 1, text);
        } catch (IndexOutOfBoundsException e) {
            player.sendMessage("§aLine not found, adding one line to the lore.");
            lore.add(text);
            i = lore.size();
        }
        if (text.isEmpty()) {
            text = "new empty line";
        }
        player.getInventory().setItemInMainHand(stack.setLore(lore).toItemStack());
        player.sendMessage("§aLore line §e" + i + "§a set to §e" + text);


    }
}
