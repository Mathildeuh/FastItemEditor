package fr.mathilde.events;

import fr.mathilde.FastItemEditor;
import fr.mathilde.inventories.EnchantsGUI;
import fr.mathilde.inventories.FastItemEditorGUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;



public class FieGuiListener implements Listener {


    FastItemEditor plugin;

    public FieGuiListener(FastItemEditor fastItemEditor) {
        this.plugin = fastItemEditor;
    }



    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().getHolder() instanceof FastItemEditorGUI || e.getInventory().getHolder() instanceof EnchantsGUI || e.getInventory().getHolder() instanceof fr.mathilde.inventories.RenameGUI || e.getInventory().getHolder() instanceof fr.mathilde.inventories.ItemFlagsGUI) {
            if (!e.isCancelled())
                e.setCancelled(true);
        }
    }
}
