package fr.mathilde.inventories;

import dev.jcsoftware.minecraft.gui.GUI;
import fr.mathilde.FastItemEditor;
import fr.mathilde.utilities.ItemBuilder;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

import static fr.mathilde.commands.FieCommand.openMainGui;
import static javax.swing.UIManager.get;

public class FastItemEditorGUI extends GUI<FastItemEditor> {

    FastItemEditor plugin;
    ItemStack stack;

    String stackFormatedName;


    public FastItemEditorGUI(FastItemEditor plugin, ItemStack stack) {
        super(plugin);

        this.plugin = plugin;
        this.stack = stack;
        this.stackFormatedName = stack.getType().name().toLowerCase().replaceAll("_", " ");
        createInventory();
    }

    public static HashMap<Player, ItemStack> playerLoreEdit = new HashMap<>();

    private void createInventory() {

        String actualName = stack.getItemMeta().hasDisplayName() ? stack.getItemMeta().getDisplayName() : null;


        set(10,
                new ItemBuilder(Material.ITEM_FRAME)
                        .setName("§3Rename")
                        .setLore("§7Actual name: §c" + actualName)
                        .toItemStack(),
                (player, action) -> {
                    openAnvilGui(player, actualName);
                    return ButtonAction.CANCEL;
                });
        set(12,
                new ItemBuilder(Material.STRUCTURE_VOID)
                        .setName("§3Unbreakable")
                        .setLore("§7Actual value: §c" + stack.getItemMeta().isUnbreakable())
                        .toItemStack(),
                (player, action) -> {
                    ItemStack item = new ItemBuilder(stack).setUnbreakable(!stack.getItemMeta().isUnbreakable()).setDurability((short) 190000).toItemStack();
                    player.setItemInHand(item);
                    createInventory();
                    return ButtonAction.CANCEL;
                });

        set(16, stack);


        List<String> lore = stack.getItemMeta().hasLore() ? stack.getItemMeta().getLore() : new ArrayList<>();

        for (int i = 0; i < lore.size(); i++) {
            String s = lore.get(i);
            if (s.equalsIgnoreCase(""))
                s = "§7<blank>";

            lore.set(i, "§c- §r" + s);

        }
         if (lore.isEmpty()) {
            lore.add("§c- §fNo lore");
        }
        lore.add(0, "§7Actual lores: ");

        set(19,
                new ItemBuilder(Material.WRITABLE_BOOK)
                        .setName("§3Edit lores")
                        .setLore(lore)

                        .toItemStack(),
                (player, action) -> {
                    editLore(player);
                    return ButtonAction.CLOSE_GUI;
                });
        set(28,
                new ItemBuilder(Material.ENCHANTING_TABLE)
                        .setName("§3Edit enchants")

                        .toItemStack(),
                (player, action) -> {
                    FastItemEditor.guiAPI.openGUI(player, new editEnchantsGUI(plugin, player.getItemInHand()));
                    return ButtonAction.CANCEL;
                });

        set(44,
                new ItemBuilder(Material.BARRIER)
                        .setName("§cClose")
                        .toItemStack(),
                (player, action) -> {
                    return ButtonAction.CLOSE_GUI;
                });

//        for (int i = 0; i < getSize(); i++) {
//            if (getInventory().getItem(i) == null) {
//                ItemStack book = new ItemBuilder(Material.ENCHANTED_BOOK).setName(" ").toItemStack();
//                EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
//                meta.addStoredEnchant(org.bukkit.enchantments.Enchantment.DURABILITY, 1, true);
//                book.setItemMeta(meta);
//                set(i, book);
//            }
//        }
    }

    private void editLore(Player player) {
        player.sendMessage("§fWrite your lore in the chat. Type §ccancel §fto cancel.");
        player.sendMessage("§fFormat: line1; line2; line3; ...");
        playerLoreEdit.put(player, stack);
    }

    private void openAnvilGui(Player player, String actualName) {
        new AnvilGUI.Builder()
                .onClose(stateSnapshot -> {
                    stateSnapshot.getPlayer().sendMessage("Aborted.");
                    player.closeInventory();
                    BukkitRunnable runnable = new BukkitRunnable() {
                        @Override
                        public void run() {
                            openMainGui(stateSnapshot.getPlayer());
                        }
                    };
                    runnable.runTaskLater(plugin, 1);
                })
                .onClick((slot, stateSnapshot) -> { // Either use sync or async variant, not both
                    if (slot != AnvilGUI.Slot.OUTPUT) {
                        return Collections.emptyList();
                    }
                    String finalActualName = ChatColor.translateAlternateColorCodes('&', stateSnapshot.getOutputItem().getItemMeta().getDisplayName());
                    stateSnapshot.getPlayer().setItemInHand(new ItemBuilder(stateSnapshot.getPlayer().getItemInHand())
                            .setName(finalActualName)
                            .toItemStack());
                    openMainGui(stateSnapshot.getPlayer());

                    return Collections.emptyList();

                })
                .text("Escape for cancel")                              //sets the text the GUI should start with
                .title("§3Rename §2" + actualName)                                       //set the title of the GUI (only works in 1.14+)
                .plugin(plugin)                                          //set the plugin instance
                .open(player);                                                   //opens the GUI for the player provided

    }



    @Override
    public int getSize() {
        return 5 * 9;
    }

    @Override
    public String getTitle() {
        return "§cName can't be initialized";
    }

    @Override
    public boolean canClose(Player player) {
        return true;
    }
}
