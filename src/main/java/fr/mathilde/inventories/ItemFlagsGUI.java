package fr.mathilde.inventories;

import fr.mathilde.FastItemEditor;
import fr.mathilde.lang.Inventories;
import fr.mathilde.utilities.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collections;

import static fr.mathilde.inventories.FastItemEditorGUI.openMainGui;


public class ItemFlagsGUI {
    public static void openAnvilGui(Player player, FastItemEditor plugin, boolean reOpenGui) {
        net.wesjd.anvilgui.AnvilGUI.Builder builder = new net.wesjd.anvilgui.AnvilGUI.Builder();

        builder.text(Inventories.ItemFlagGUI.getCancel())
                .title(Inventories.ItemFlagGUI.getTitle())

                .plugin(plugin);

        builder.onClose(stateSnapshot -> {
            player.closeInventory();
            BukkitRunnable runnable = new BukkitRunnable() {
                @Override
                public void run() {
                    openMainGui(stateSnapshot.getPlayer(), plugin);
                }
            };
            if (reOpenGui)
                runnable.runTaskLater(plugin, 1);
        });


        builder.onClick((slot, stateSnapshot) -> {
            if (slot != net.wesjd.anvilgui.AnvilGUI.Slot.OUTPUT) {
                return Collections.emptyList();
            }
            String item_flag = stateSnapshot.getOutputItem().getItemMeta().getDisplayName();

            if (!isItemFlag(item_flag)) {
                if (reOpenGui) {
                    openMainGui(stateSnapshot.getPlayer(), plugin);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            String title = stateSnapshot.getPlayer().getOpenInventory().getTitle();
                            stateSnapshot.getPlayer().getOpenInventory().setTitle(Inventories.ItemFlagGUI.getWrong_flag());

                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    stateSnapshot.getPlayer().getOpenInventory().setTitle(title);
                                }
                            }.runTaskLater(plugin, 20);
                        }
                    }.runTaskLater(plugin, 1);
                } else {
                    stateSnapshot.getPlayer().closeInventory();
                    stateSnapshot.getPlayer().sendMessage(Inventories.ItemFlagGUI.getWrong_flag());

                }
                return Collections.emptyList();
            }

            ItemStack item = stateSnapshot.getPlayer().getItemInHand();
            ItemBuilder builderWithFlags = new ItemBuilder(stateSnapshot.getPlayer().getItemInHand());
            if (item.getItemMeta().hasItemFlag(ItemFlag.valueOf(item_flag))) {
                builderWithFlags.removeFlag(ItemFlag.valueOf(item_flag));
                stateSnapshot.getPlayer().sendMessage(Inventories.ItemFlagGUI.getFlag_removed().replace("%flag%", item_flag));

            } else {
                builderWithFlags.addFlag(ItemFlag.valueOf(item_flag));
                stateSnapshot.getPlayer().sendMessage(Inventories.ItemFlagGUI.getFlag_added().replace("%flag%", item_flag));
            }


            stateSnapshot.getPlayer().setItemInHand(
                    builderWithFlags.toItemStack());

            if (reOpenGui) {
                openMainGui(stateSnapshot.getPlayer(), plugin);

            } else {
                stateSnapshot.getPlayer().closeInventory();
            }

            return Collections.emptyList();

        });

        builder.open(player);

    }

    public static boolean isItemFlag(String flagName) {
        if (flagName == null || flagName.isEmpty()) {
            return false;
        }

        for (ItemFlag flag : ItemFlag.values()) {
            if (flag.name().equalsIgnoreCase(flagName)) {
                return true;
            }
        }

        return false;
    }
}
