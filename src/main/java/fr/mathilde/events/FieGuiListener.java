package fr.mathilde.events;

import fr.mathilde.FastItemEditor;
import fr.mathilde.inventories.EnchantsGUI;
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
import java.util.List;

import static fr.mathilde.inventories.FastItemEditorGUI.openMainGui;


public class FieGuiListener implements Listener {


    FastItemEditor plugin;

    public FieGuiListener(FastItemEditor fastItemEditor) {
        this.plugin = fastItemEditor;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().getHolder() instanceof FastItemEditorGUI || e.getInventory().getHolder() instanceof EnchantsGUI) {
            if (!e.isCancelled())
                e.setCancelled(true);
        }
    }




    public static void reOpenMainGui(Player player, FastItemEditor plugin) {
        Bukkit.getScheduler().runTask(plugin, () -> {

            FastItemEditorGUI.playerLoreEdit.remove(player);
            openMainGui(player, plugin);

        });

    }
}
