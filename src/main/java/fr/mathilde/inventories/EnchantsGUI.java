package fr.mathilde.inventories;

import dev.jcsoftware.minecraft.gui.GUI;
import fr.mathilde.FastItemEditor;
import fr.mathilde.utilities.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static fr.mathilde.inventories.FastItemEditorGUI.openMainGui;


public class EnchantsGUI extends GUI<FastItemEditor> {

    FastItemEditor plugin;
    ItemStack itemInHand;

    public EnchantsGUI(FastItemEditor plugin, ItemStack itemInHand, Player player) {
        super(plugin);

        this.plugin = plugin;
        this.itemInHand = itemInHand;
        createInventory(player);
    }

    static int enchantLevel = 1;

    private void createInventory(Player pl) {

        pl.getOpenInventory().setTitle("§3Fast Item Editor §7- §2Level: " + enchantLevel);

        int i = 0;

        for (String enchantment : getAllEnchantments()) {
            Enchantment enchant = Enchantment.getByKey(NamespacedKey.minecraft(enchantment));
            ItemStack stack = new ItemBuilder(Material.ENCHANTED_BOOK).toItemStack();
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) stack.getItemMeta();
            meta.addStoredEnchant(enchant, enchantLevel, true);
            stack.setItemMeta(meta);

            set(i, stack, (player, clickedItem) -> {

                // detect if player item already has the enchantment
                ItemStack playerItem = new ItemBuilder(player.getItemInHand()).toItemStack();

                Map<Enchantment, Integer> enchantments = player.getItemInHand().getEnchantments();
                if (enchantments.containsKey(Enchantment.getByKey(NamespacedKey.minecraft(enchantment)))) {
                    playerItem.removeEnchantment(Enchantment.getByKey(NamespacedKey.minecraft(enchantment)));
                    return ButtonAction.CANCEL;
                }
                playerItem.addUnsafeEnchantment(Enchantment.getByKey(NamespacedKey.minecraft(enchantment)), enchantLevel);

                player.setItemInHand(playerItem);
                return ButtonAction.CANCEL;
            });
            i++;
        }

        set(46, new ItemBuilder(Material.BLAZE_ROD, 1).setName("§2Level min: 1").toItemStack(), (player, action) -> {
            enchantLevel = 1;
            createInventory(player);
            return ButtonAction.CANCEL;
        });

        set(47, new ItemBuilder(Material.ARROW, 10).setName("§4-10").toItemStack(), (player, action) -> {
            setEnchantLevel(-10);
            createInventory(player);
            return ButtonAction.CANCEL;
        });
        set(48, new ItemBuilder(Material.ARROW).setName("§c-1").toItemStack(), (player, action) -> {
            setEnchantLevel(-1);
            createInventory(player);
            return ButtonAction.CANCEL;
        });

        set(49, new ItemBuilder(Material.EXPERIENCE_BOTTLE, enchantLevel).setName("§3Enchantement level: " + enchantLevel).toItemStack());

        set(50, new ItemBuilder(Material.ARROW).setName("§a+1").toItemStack(), (player, action) -> {
            setEnchantLevel(1);
            createInventory(player);
            return ButtonAction.CANCEL;
        });
        set(51, new ItemBuilder(Material.ARROW, 10).setName("§2+10").toItemStack(), (player, action) -> {
            setEnchantLevel(10);
            createInventory(player);
            return ButtonAction.CANCEL;
        });

        set(52, new ItemBuilder(Material.BLAZE_ROD, 64).setName("§2Level max: 255").toItemStack(), (player, action) -> {
            enchantLevel = 255;
            createInventory(player);
            return ButtonAction.CANCEL;
        });


        set(53, new ItemBuilder(Material.BARRIER).setName("§cBack").toItemStack(), (player, action) -> {
            openMainGui(player, plugin);
            return ButtonAction.CANCEL;
        });
    }


    public static void setEnchantLevel(int i){
        if (enchantLevel + i >= 1 && enchantLevel + i <= 255)
            enchantLevel = enchantLevel + i;
    }

    public List<String> getAllEnchantments() {
        List<String> enchantmentList = new ArrayList<>();

        // Récupérer tous les enchantements disponibles jusqu'au 45ème slot
        int counter = 0;
        for (Enchantment enchantment : Enchantment.values()) {
            enchantmentList.add(enchantment.getKey().getKey());
            counter++;
            if (counter >= 45) {
                break;
            }
        }

        enchantmentList.sort(String::compareToIgnoreCase);


        return enchantmentList;
    }

    @Override
    public int getSize() {
        return 54;
    }

    @Override
    public String getTitle() {
        return "§3Fast Item Editor §7- §2Level: " + enchantLevel;
    }

    @Override
    public boolean canClose(Player player) {
        return true;
    }
}
