package fr.mathilde.inventories;

import dev.jcsoftware.minecraft.gui.GUI;
import fr.mathilde.FastItemEditor;
import fr.mathilde.utilities.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FastItemEditorGUI extends GUI<FastItemEditor> {

    public static HashMap<Player, ItemStack> playerLoreEdit = new HashMap<>();
    public static List<Player> dontReOpen = new ArrayList<>();
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

    public static void editLore(Player player, ItemStack stack, Boolean openMainGui) {
        player.sendMessage("§fWrite your lore in the chat. Type §ccancel §fto cancel.");
        player.sendMessage("§fFormat: line1; line2; line3; ...");
        playerLoreEdit.put(player, stack);
        if (!openMainGui)
            dontReOpen.add(player);
    }

    public static List<String> formatEnchantments(ItemStack item) {
        List<String> formattedEnchantments = new ArrayList<>();

        if (item != null && item.hasItemMeta() && item.getItemMeta().hasEnchants()) {
            ItemMeta itemMeta = item.getItemMeta();
            formattedEnchantments.add("§7Actual enchantments:");

            for (Enchantment enchantment : itemMeta.getEnchants().keySet()) {
                int level = itemMeta.getEnchantLevel(enchantment);
                String enchantName = "§c- §a" + enchantment.getKey().getKey() + " §7: §e";
                String formattedEnchant = formatEnchantment(enchantName, level);
                formattedEnchantments.add(formattedEnchant);
            }
        }

        return formattedEnchantments;
    }

    // Méthode utilitaire pour formater l'enchantement et son niveau
    private static String formatEnchantment(String enchantName, int level) {
        return enchantName + " " + ((level > 0) ? level : "");
    }

    // Méthode utilitaire pour formater l'enchantement et son niveau

    private void createInventory() {

        String actualName = stack.getItemMeta().hasDisplayName() ? stack.getItemMeta().getDisplayName() : null;

        set(10,
                new ItemBuilder(Material.ITEM_FRAME)
                        .setName("§3Edit name")
                        .setLore("§7Actual name: §c" + actualName)
                        .toItemStack(),
                (player, action) -> {
                    RenameGUI.openAnvilGui(player, actualName, plugin, true);
                    return ButtonAction.CANCEL;
                });

        List<String> lore = stack.getItemMeta().hasLore() ? stack.getItemMeta().getLore() : new ArrayList<>();

        for (int i = 0; i < lore.size(); i++) {
            String s = lore.get(i);
            if (s.equalsIgnoreCase(""))
                s = "§7§oEmpty line";

            lore.set(i, "§c- §r" + s);

        }
        if (lore.isEmpty()) {
            lore.add("§cNo lore");
        }
        lore.add(0, "§7Actual lores: ");

        set(11,
                new ItemBuilder(Material.WRITABLE_BOOK)
                        .setName("§3Edit lores")
                        .setLore(lore)

                        .toItemStack(),
                (player, action) -> {
                    editLore(player, stack, true);
                    return ButtonAction.CLOSE_GUI;
                });

        List<String> formattedEnchantments = formatEnchantments(stack);
        if (formattedEnchantments.isEmpty()) {
            formattedEnchantments.add("§7Actual enchantments:");

            formattedEnchantments.add("§cNo enchantments");
        }

        ItemStack enchants = new ItemBuilder(Material.ENCHANTING_TABLE)
                .setName("§3Edit enchants")
                .setLore(formattedEnchantments)
                .toItemStack();

        set(12, enchants
                ,
                (player, action) -> {
                    FastItemEditor.guiAPI.openGUI(player, new EnchantsGUI(plugin, player.getItemInHand(), player));
                    return ButtonAction.CANCEL;
                });

        set(13,
                new ItemBuilder(Material.GLOW_ITEM_FRAME)
                        .setName("§3Edit Item Flags")
                        .setLore("§7Actual flags: §c" + stack.getItemMeta().getItemFlags())

                        .toItemStack(),
                (player, item) -> {
                    ItemFlagsGUI.openAnvilGui(player, plugin, true);
                    return ButtonAction.CANCEL;
                });

        set(14,
                new ItemBuilder(Material.STRUCTURE_VOID)
                        .setName("§3Unbreakable ?")
                        .setLore("§7Actual value: §c" + stack.getItemMeta().isUnbreakable())
                        .toItemStack(),
                (player, action) -> {
                    ItemStack item = new ItemBuilder(stack).setUnbreakable(!stack.getItemMeta().isUnbreakable()).setDurability((short) 190000).toItemStack();
                    player.setItemInHand(item);
                    createInventory();
                    return ButtonAction.CANCEL;
                });

        set(16, stack);


        set(26,
                new ItemBuilder(Material.BARRIER)
                        .setName("§cClose")
                        .toItemStack(),
                (player, action) -> ButtonAction.CLOSE_GUI);


    }

    @Override
    public int getSize() {
        return 3 * 9;
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
