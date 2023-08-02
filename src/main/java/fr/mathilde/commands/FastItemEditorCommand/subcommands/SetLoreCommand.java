package fr.mathilde.commands.FastItemEditorCommand.subcommands;

import fr.mathilde.FastItemEditor;
import fr.mathilde.commands.FastItemEditorCommand.SubCommands;
import fr.mathilde.inventories.FastItemEditorGUI;
import fr.mathilde.lang.Commands;
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
        return Commands.SetLore.getSyntax();
    }


    @Override
    public void run(Player player, String[] args) {
        if (FastItemEditorGUI.playerLoreEdit.containsKey(player)) {
            player.sendMessage(Commands.SetLore.getAlreadyEditingLore());
            return;
        }
        if (args.length >= 2) {
            List<String> lore = new ArrayList<>();

            StringJoiner loreString = new StringJoiner(" ");

            for (int i = 1; i < args.length; i++) {
                loreString.add(args[i].replaceAll(Commands.SetLore.getLoreSplitRegex(), ""));
            }


            for (String s : loreString.toString().split(Commands.SetLore.getLoreSplitRegex())) {
                lore.add(ChatColor.translateAlternateColorCodes('&', s.replaceAll(Commands.SetLore.getBlank(), "")));
            }


            ItemStack stack = new ItemBuilder(player.getItemInHand()).setLore(lore).toItemStack();


            player.setItemInHand(stack);
            player.sendMessage(Commands.SetLore.getLoreSet());
            return;
        }

        FastItemEditorGUI.editLore(player, player.getItemInHand(), false);


    }
}