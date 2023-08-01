package fr.mathilde.commands.FastItemEditorCommand.subcommands;

import fr.mathilde.FastItemEditor;
import fr.mathilde.commands.FastItemEditorCommand.SubCommands;
import fr.mathilde.utilities.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class SetLoreCommand extends SubCommands {
    FastItemEditor plugin;

    public SetLoreCommand(FastItemEditor plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "setlore";
    }

    @Override
    public String getSyntax() {
        return "§e/fastitemeditor §asetlore <lore (whit ; for new line)>";
    }


    @Override
    public void run(Player player, String[] args) {
        if (args.length >= 1) {
            List<String> lore = new ArrayList<>();

            StringJoiner loreString = new StringJoiner(" ");

            for (int i = 1; i < args.length; i++) {
                loreString.add(args[i].replaceAll("<blank>", ""));
            }


            for (String s : loreString.toString().split("; ")) {
                lore.add(ChatColor.translateAlternateColorCodes('&', s.replaceAll("<blank>", "")));
            }


            ItemStack stack = new ItemBuilder(player.getItemInHand()).setLore(lore).toItemStack();


            player.setItemInHand(stack);
            player.sendMessage("§aLore set to " + loreString);
        }
    }
}