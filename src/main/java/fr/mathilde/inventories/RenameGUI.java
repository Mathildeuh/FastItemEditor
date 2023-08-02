package fr.mathilde.inventories;

import fr.mathilde.FastItemEditor;
import fr.mathilde.lang.Inventories;
import fr.mathilde.utilities.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collections;

import static fr.mathilde.inventories.FastItemEditorGUI.openMainGui;


public class RenameGUI {
    public static void openAnvilGui(Player player, FastItemEditor plugin, boolean reOpenGui) {
        net.wesjd.anvilgui.AnvilGUI.Builder builder = new net.wesjd.anvilgui.AnvilGUI.Builder()
                .onClose(stateSnapshot -> {
                    player.closeInventory();
                    BukkitRunnable runnable = new BukkitRunnable() {
                        @Override
                        public void run() {
                            openMainGui(stateSnapshot.getPlayer(), plugin);
                        }
                    };
                    if (reOpenGui)
                        runnable.runTaskLater(plugin, 1);
                })

                .text(Inventories.RenameGUI.getCancel())
                .itemLeft(new ItemBuilder(player.getItemInHand().getType()).toItemStack())
                .plugin(plugin);

        builder.onClick((slot, stateSnapshot) -> {
            if (slot != net.wesjd.anvilgui.AnvilGUI.Slot.OUTPUT) {
                return Collections.emptyList();
            }
            String finalActualName = ChatColor.translateAlternateColorCodes('&', stateSnapshot.getOutputItem().getItemMeta().getDisplayName());
            stateSnapshot.getPlayer().setItemInHand(new ItemBuilder(stateSnapshot.getPlayer().getItemInHand())
                    .setName(finalActualName)
                    .toItemStack());

            if (reOpenGui)
                openMainGui(stateSnapshot.getPlayer(), plugin);
            else
                stateSnapshot.getPlayer().closeInventory();
            return Collections.emptyList();

        });


        builder.title(Inventories.RenameGUI.getTitle()).open(player);


    }
}
