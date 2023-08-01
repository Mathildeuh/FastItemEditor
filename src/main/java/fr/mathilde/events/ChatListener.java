package fr.mathilde.events;

import fr.mathilde.FastItemEditor;
import fr.mathilde.commands.FastItemEditorCommand.subcommands.SetLoreCommand;
import fr.mathilde.inventories.FastItemEditorGUI;
import fr.mathilde.utilities.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static fr.mathilde.events.FieGuiListener.reOpenMainGui;

public class ChatListener implements Listener {
    FastItemEditor plugin;
    public ChatListener(FastItemEditor fastItemEditor) {

        this.plugin = fastItemEditor;

    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (FastItemEditorGUI.playerLoreEdit.containsKey(e.getPlayer())) {
            e.setCancelled(true);

            if (e.getMessage().equalsIgnoreCase("cancel")) {
                FastItemEditorGUI.playerLoreEdit.remove(e.getPlayer());
                if (FastItemEditorGUI.dontReOpen.contains(e.getPlayer())) {
                    FastItemEditorGUI.dontReOpen.remove(e.getPlayer());
                    e.getPlayer().sendMessage("§cLore edit canceled !");

                    return;
                }
                reOpenMainGui(e.getPlayer(), plugin);


                return;
            }
            List<String> lore = new ArrayList<>();

            String[] message = e.getMessage().split("; ");

            for (String s : message) {
                lore.add(ChatColor.translateAlternateColorCodes('&', s.replaceAll("%blank", "")));
            }

            ItemStack item = new ItemBuilder(FastItemEditorGUI.playerLoreEdit.get(e.getPlayer())).setLore(lore).toItemStack();

            FastItemEditorGUI.playerLoreEdit.remove(e.getPlayer());
            e.getPlayer().getInventory().setItemInMainHand(item);
            if (FastItemEditorGUI.dontReOpen.contains(e.getPlayer())) {
                    FastItemEditorGUI.dontReOpen.remove(e.getPlayer());
                    e.getPlayer().sendMessage("§aLore set !");
                    return;
            }
            reOpenMainGui(e.getPlayer(), plugin);
        }
    }
}
