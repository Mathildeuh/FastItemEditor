package fr.mathilde.inventories;

import dev.jcsoftware.minecraft.gui.GUI;
import fr.mathilde.FastItemEditor;
import fr.mathilde.utilities.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FastItemEditorGUI extends GUI<FastItemEditor> {

    public static HashMap<Player, ItemStack> playerLoreEdit = new HashMap<>();
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

    public static void openMainGui(Player player, FastItemEditor plugin) {
        FastItemEditor.guiAPI.openGUI(player, new FastItemEditorGUI(plugin, player.getItemInHand()));
        String relocalizedName = player.getItemInHand().getType().name().toLowerCase().replaceAll("_", " ");

        player.getOpenInventory()
                .setTitle("§3Fast Item Editor §7- §2" + relocalizedName);

    }

    private void createInventory() {

        String actualName = stack.getItemMeta().hasDisplayName() ? stack.getItemMeta().getDisplayName() : null;


        set(10,
                new ItemBuilder(Material.ITEM_FRAME)
                        .setName("§3Rename")
                        .setLore("§7Actual name: §c" + actualName)
                        .toItemStack(),
                (player, action) -> {
                    AnvilGUI.openAnvilGui(player, actualName, plugin, true);
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

        set(21,
                new ItemBuilder(Material.GLOW_ITEM_FRAME)
                        .setName("§3Edit ITEM FLAGS")
                        .setLore("§7Actual flags: §c" + stack.getItemMeta().getItemFlags())

                        .toItemStack(),
                (player, item) -> {
                    String last_name = item.getItemMeta().hasDisplayName() ? item.getItemMeta().getDisplayName() : null;
                    final ItemStack[] newStack = {new ItemBuilder(item).setName("§c§lNot implemented yet !").toItemStack()};
                    set(21, newStack[0]);
                    BukkitRunnable run = new BukkitRunnable() {
                        @Override
                        public void run() {
                            newStack[0] = new ItemBuilder(item).setName(last_name).toItemStack();
                            set(21, newStack[0]);
                            createInventory();

                        }
                    };
                    run.runTaskLater(this.plugin, 20);

                    return ButtonAction.CANCEL;
                });
        set(28,
                new ItemBuilder(Material.ENCHANTING_TABLE)
                        .setName("§3Edit enchants")

                        .toItemStack(),
                (player, action) -> {
                    FastItemEditor.guiAPI.openGUI(player, new EditEnchantsGUI(plugin, player.getItemInHand()));
                    return ButtonAction.CANCEL;
                });

        set(44,
                new ItemBuilder(Material.BARRIER)
                        .setName("§cClose")
                        .toItemStack(),
                (player, action) -> ButtonAction.CLOSE_GUI);


    }

    private void editLore(Player player) {
        player.sendMessage("§fWrite your lore in the chat. Type §ccancel §fto cancel.");
        player.sendMessage("§fFormat: line1; line2; line3; ...");
        playerLoreEdit.put(player, stack);
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
