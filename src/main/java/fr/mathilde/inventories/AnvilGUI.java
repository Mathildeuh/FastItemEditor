package fr.mathilde.inventories;

import fr.mathilde.FastItemEditor;
import fr.mathilde.utilities.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collections;

import static fr.mathilde.inventories.FastItemEditorGUI.openMainGui;


public class AnvilGUI {
    public static void openAnvilGui(Player player, String actualName, FastItemEditor plugin, boolean reOpenGui) {
        net.wesjd.anvilgui.AnvilGUI.Builder builder = new net.wesjd.anvilgui.AnvilGUI.Builder()
                .onClose(stateSnapshot -> {
                    player.closeInventory();
                    BukkitRunnable runnable = new BukkitRunnable() {
                        @Override
                        public void run() {
                            openMainGui(stateSnapshot.getPlayer());
                        }
                    };
                    if (reOpenGui)
                        runnable.runTaskLater(plugin, 1);
                })

                .text("Escape for cancel")                              //sets the text the GUI should start with
                .title("§3Rename §2" + actualName)                                       //set the title of the GUI (only works in 1.14+)
                .plugin(plugin);

        builder.onClick((slot, stateSnapshot) -> { // Either use sync or async variant, not both
            if (slot != net.wesjd.anvilgui.AnvilGUI.Slot.OUTPUT) {
                return Collections.emptyList();
            }
            String finalActualName = ChatColor.translateAlternateColorCodes('&', stateSnapshot.getOutputItem().getItemMeta().getDisplayName());
            stateSnapshot.getPlayer().setItemInHand(new ItemBuilder(stateSnapshot.getPlayer().getItemInHand())
                    .setName(finalActualName)
                    .toItemStack());

            if (reOpenGui)
                openMainGui(stateSnapshot.getPlayer());
            else
                stateSnapshot.getPlayer().closeInventory();
            return Collections.emptyList();

        });

        builder.open(player);

    }
}
