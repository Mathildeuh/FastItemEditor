package fr.mathilde.events;

import fr.mathilde.FastItemEditor;
import fr.mathilde.commands.FieCommand;
import fr.mathilde.inventories.FastItemEditorGUI;
import fr.mathilde.utilities.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FieGuiListener implements Listener {


    FastItemEditor plugin;

    public FieGuiListener(FastItemEditor fastItemEditor) {
        this.plugin = fastItemEditor;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().getHolder() instanceof FastItemEditorGUI) {
            if (!e.isCancelled())
                e.setCancelled(true);
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        if(FastItemEditorGUI.playerLoreEdit.containsKey(e.getPlayer())){
            e.setCancelled(true);

            if (e.getMessage().equalsIgnoreCase("cancel")){
                FastItemEditorGUI.playerLoreEdit.remove(e.getPlayer());
                reOpenMainGui(e.getPlayer());


                return;
            }
            List<String> lore = new ArrayList<>();

            String[] message = e.getMessage().split("; ");

            for (String s : message) {
                lore.add(ChatColor.translateAlternateColorCodes('&', s.replaceAll("<blank>", "")));
            }

            ItemStack item = new ItemBuilder(FastItemEditorGUI.playerLoreEdit.get(e.getPlayer())).setLore(lore).toItemStack();

            FastItemEditorGUI.playerLoreEdit.remove(e.getPlayer());
            e.getPlayer().getInventory().setItemInMainHand(item);

            reOpenMainGui(e.getPlayer());
        }
    }


    public void reOpenMainGui(Player player){
        Bukkit.getScheduler().runTask(plugin, () -> {

            FastItemEditorGUI.playerLoreEdit.remove(player);
            FieCommand.openMainGui(player);

        });

    }
}
