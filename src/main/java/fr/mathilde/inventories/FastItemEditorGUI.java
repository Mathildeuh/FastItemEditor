package fr.mathilde.inventories;

import dev.jcsoftware.minecraft.gui.GUI;
import fr.mathilde.FastItemEditor;
import fr.mathilde.lang.Commands;
import fr.mathilde.lang.Inventories;
import fr.mathilde.utilities.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

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
                .setTitle(Inventories.MainGUI.getTitle().replace("%item_name%", relocalizedName));
    }

    public static void editLore(Player player, ItemStack stack, Boolean openMainGui) {
        player.sendMessage(Commands.SetLore.getEnterEditor());
        playerLoreEdit.put(player, stack);
        if (!openMainGui)
            dontReOpen.add(player);
    }

    public static List<String> formatEnchantments(ItemStack item) {
        List<String> formattedEnchantments = new ArrayList<>();

        if (item != null && item.hasItemMeta() && item.getItemMeta().hasEnchants()) {
            ItemMeta itemMeta = item.getItemMeta();
            formattedEnchantments.add(Inventories.MainGUI.getLoreEditEnchant());

            for (Enchantment enchantment : itemMeta.getEnchants().keySet()) {
                int level = itemMeta.getEnchantLevel(enchantment);
                String enchantName = enchantment.getKey().getKey();
                String formattedEnchant = formatEnchantment(enchantName, level);
                formattedEnchantments.add(formattedEnchant);
            }
        }

        return formattedEnchantments;
    }

    private static String formatEnchantment(String enchantName, int level) {
        String format = Inventories.MainGUI.getEnchantFormat();
        return format.replace("%enchant%", enchantName).replace("%level%", ((level > 0) ? String.valueOf(level) : ""));
    }


    private void createInventory() {

        String actualName = stack.getItemMeta().hasDisplayName() ? stack.getItemMeta().getDisplayName() : Inventories.MainGUI.getNoName();

        set(10,
                new ItemBuilder(Material.ITEM_FRAME)
                        .setName(Inventories.MainGUI.getEditName())
                        .setLore(Inventories.MainGUI.getLoreEditName().replace("%name%", actualName))
                        .toItemStack(),
                (player, action) -> {

                    RenameGUI.openAnvilGui(player, plugin, true);

                    BukkitRunnable runnable = new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.getOpenInventory().setTitle(Inventories.RenameGUI.getTitle().replace("%name%", stack.getItemMeta().getDisplayName()));
                        }
                    };

                    runnable.runTaskLater(plugin, 1);

                    return ButtonAction.CANCEL;

                });

        List<String> lore = stack.getItemMeta().hasLore() ? stack.getItemMeta().getLore() : new ArrayList<>();

        for (int i = 0; i < lore.size(); i++) {
            String s = lore.get(i);
            if (s.equalsIgnoreCase(""))
                s = Inventories.MainGUI.getEmpty_line();

            lore.set(i, Inventories.MainGUI.getLore_format().replace("%line%", s));

        }
        if (lore.isEmpty()) {
            lore.add(Inventories.MainGUI.getEmpty_lore());
        }
        lore.add(0, Inventories.MainGUI.getLoreEditLore());

        set(11,
                new ItemBuilder(Material.WRITABLE_BOOK)
                        .setName(Inventories.MainGUI.getEditLore())
                        .setLore(lore)

                        .toItemStack(),
                (player, action) -> {
                    editLore(player, stack, true);
                    return ButtonAction.CLOSE_GUI;
                });

        List<String> formattedEnchantments = formatEnchantments(stack);
        if (formattedEnchantments.isEmpty()) {
            formattedEnchantments.add(Inventories.MainGUI.getLoreEditEnchant());
            System.out.println(Inventories.MainGUI.getLoreEditEnchant());

            formattedEnchantments.add(Inventories.MainGUI.getNoEnchants());
        }

        ItemStack enchants = new ItemBuilder(Material.ENCHANTING_TABLE)
                .setName(Inventories.MainGUI.getEditEnchant())
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
                        .setName(Inventories.MainGUI.getEditItemFlags())
                        .setLore(Inventories.MainGUI.getloreEditItemFlags().replace("%flags%", stack.getItemMeta().getItemFlags().toString()))

                        .toItemStack(),
                (player, item) -> {
                    ItemFlagsGUI.openAnvilGui(player, plugin, true);
                    return ButtonAction.CANCEL;
                });

        set(14,
                new ItemBuilder(Material.STRUCTURE_VOID)
                        .setName(Inventories.MainGUI.getSetUnbreakable())
                        .setLore(Inventories.MainGUI.getLoreSetUnbreakable().replace("%value%", String.valueOf(stack.getItemMeta().isUnbreakable())))
                        .toItemStack(),
                (player, action) -> {
                    ItemStack item = new ItemBuilder(stack).setUnbreakable(!stack.getItemMeta().isUnbreakable()).toItemStack();
                    player.setItemInHand(item);
                    createInventory();
                    return ButtonAction.CANCEL;
                });

        set(16, stack);


        set(26,
                new ItemBuilder(Material.BARRIER)
                        .setName(Inventories.MainGUI.getClose())
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
