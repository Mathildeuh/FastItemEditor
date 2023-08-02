package fr.mathilde.events;

import fr.mathilde.FastItemEditor;
import fr.mathilde.inventories.FastItemEditorGUI;
import fr.mathilde.lang.Commands;
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

            if (e.getMessage().equalsIgnoreCase(Commands.SetLore.getWordForCancel())) {
                FastItemEditorGUI.playerLoreEdit.remove(e.getPlayer());
                if (FastItemEditorGUI.dontReOpen.contains(e.getPlayer())) {
                    FastItemEditorGUI.dontReOpen.remove(e.getPlayer());
                    e.getPlayer().sendMessage(Commands.SetLore.getLoreEditCanceled());

                    return;
                }
                reOpenMainGui(e.getPlayer(), plugin);


                return;
            }
            List<String> lore = new ArrayList<>();

            String[] message = e.getMessage().split(Commands.SetLore.getLoreSplitRegex());

            for (String s : message) {
                lore.add(ChatColor.translateAlternateColorCodes('&', s.replaceAll(Commands.SetLore.getBlank(), "")));
            }

            ItemStack item = new ItemBuilder(FastItemEditorGUI.playerLoreEdit.get(e.getPlayer())).setLore(lore).toItemStack();

            FastItemEditorGUI.playerLoreEdit.remove(e.getPlayer());
            e.getPlayer().getInventory().setItemInMainHand(item);
            if (FastItemEditorGUI.dontReOpen.contains(e.getPlayer())) {
                FastItemEditorGUI.dontReOpen.remove(e.getPlayer());
                e.getPlayer().sendMessage(Commands.SetLore.getLoreSet());
                return;
            }
            reOpenMainGui(e.getPlayer(), plugin);
        }
    }
}
