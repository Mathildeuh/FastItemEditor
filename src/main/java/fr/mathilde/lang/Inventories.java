package fr.mathilde.lang;

import fr.mathilde.FastItemEditor;
import org.bukkit.ChatColor;

public class Inventories {

    public static class RenameGUI {
        private static String cancel, title;
        FastItemEditor plugin;

        public RenameGUI(FastItemEditor fastItemEditor) {
            plugin = fastItemEditor;
            cancel = plugin.getLangConfig().getString("inventories.rename.cancel");
            title = plugin.getLangConfig().getString("inventories.rename.title");

        }

        public static String getCancel() {
            return ChatColor.translateAlternateColorCodes('&', cancel);
        }

        public static String getTitle() {
            return ChatColor.translateAlternateColorCodes('&', title);
        }
    }


    public static class ItemFlagGUI {
        private static String cancel, title, wrong_flag, flag_added, flag_removed;
        FastItemEditor plugin;

        public ItemFlagGUI(FastItemEditor fastItemEditor) {
            plugin = fastItemEditor;
            cancel = plugin.getLangConfig().getString("inventories.itemflags.cancel");
            title = plugin.getLangConfig().getString("inventories.itemflags.title");
            wrong_flag = plugin.getLangConfig().getString("inventories.itemflags.wrong-flag");
            flag_added = plugin.getLangConfig().getString("inventories.itemflags.flag-added");
            flag_removed = plugin.getLangConfig().getString("inventories.itemflags.flag-removed");

        }

        public static String getFlag_added() {
            return ChatColor.translateAlternateColorCodes('&', flag_added);
        }

        public static String getFlag_removed() {
            return ChatColor.translateAlternateColorCodes('&', flag_removed);
        }

        public static String getWrong_flag() {
            return ChatColor.translateAlternateColorCodes('&', wrong_flag);
        }

        public static String getCancel() {
            return ChatColor.translateAlternateColorCodes('&', cancel);
        }

        public static String getTitle() {
            return ChatColor.translateAlternateColorCodes('&', title);
        }
    }

    public static class MainGUI {
        private static String title, editname, editlore, editenchant, edititemflags, setunbreakable, close, empty_lore, empty_line, lore_format, noenchants;
        private static String loreeditname, loreeditlore, loreeditenchant, loreedititemflags, loresetunbreakable, enchantFormat;
        FastItemEditor plugin;

        public static String getEnchantFormat() {
            return ChatColor.translateAlternateColorCodes('&', enchantFormat);
        }

        public MainGUI(FastItemEditor fastItemEditor) {
            plugin = fastItemEditor;
            title = plugin.getLangConfig().getString("inventories.main.title");
            editname = plugin.getLangConfig().getString("inventories.main.items.editname");
            editlore = plugin.getLangConfig().getString("inventories.main.items.editlore");
            editenchant = plugin.getLangConfig().getString("inventories.main.items.editenchants");
            edititemflags = plugin.getLangConfig().getString("inventories.main.items.edititemflags");
            setunbreakable = plugin.getLangConfig().getString("inventories.main.items.setunbreakable");
            close = plugin.getLangConfig().getString("inventories.main.items.close");

            loreeditname = plugin.getLangConfig().getString("inventories.main.lores.editname");
            loreeditlore = plugin.getLangConfig().getString("inventories.main.lores.editlore");

            loreeditenchant = plugin.getLangConfig().getString("inventories.main.lores.editenchants");
            noenchants = plugin.getLangConfig().getString("inventories.main.lores.no-enchants");
            enchantFormat = plugin.getLangConfig().getString("inventories.main.lores.enchants-format");

            loreedititemflags = plugin.getLangConfig().getString("inventories.main.lores.edititemflags");
            loresetunbreakable = plugin.getLangConfig().getString("inventories.main.lores.setunbreakable");

            empty_lore = plugin.getLangConfig().getString("inventories.main.lores.empty-lore");
            empty_line = plugin.getLangConfig().getString("inventories.main.lores.empty-line");
            lore_format = plugin.getLangConfig().getString("inventories.main.lores.lore-format");

        }

        public static String getNoEnchants() {
            return ChatColor.translateAlternateColorCodes('&', noenchants);
        }

        public static String getLore_format() {
            return ChatColor.translateAlternateColorCodes('&', lore_format);
        }

        public static String getEmpty_lore() {
            return ChatColor.translateAlternateColorCodes('&', empty_lore);
        }

        public static String getEmpty_line() {
            return ChatColor.translateAlternateColorCodes('&', empty_line);
        }

        public static String getEditname() {
            return ChatColor.translateAlternateColorCodes('&', editname);
        }

        public static String getEditlore() {
            return ChatColor.translateAlternateColorCodes('&', editlore);
        }

        public static String getLoreEditEnchant() {
            return ChatColor.translateAlternateColorCodes('&', loreeditenchant);
        }

        public static String getloreEdititemflags() {
            return ChatColor.translateAlternateColorCodes('&', loreedititemflags);
        }

        public static String getLoreSetUnbreakable() {
            return ChatColor.translateAlternateColorCodes('&', loresetunbreakable);
        }

        public static String getLoreeditname() {
            return ChatColor.translateAlternateColorCodes('&', loreeditname);
        }

        public static String getLoreeditlore() {
            return ChatColor.translateAlternateColorCodes('&', loreeditlore);
        }

        public static String getLoreeditenchant() {
            return ChatColor.translateAlternateColorCodes('&', loreeditenchant);
        }

        public static String getLoreedititemflags() {
            return ChatColor.translateAlternateColorCodes('&', loreedititemflags);
        }

        public static String getLoresetunbreakable() {
            return ChatColor.translateAlternateColorCodes('&', loresetunbreakable);
        }

        public static String getEditName() {
            return ChatColor.translateAlternateColorCodes('&', editname);
        }

        public static String getEditLore() {
            return ChatColor.translateAlternateColorCodes('&', editlore);
        }


        public static String getEditEnchants() {
            return ChatColor.translateAlternateColorCodes('&', editenchant);
        }

        public static String getEditItemFlags() {
            return ChatColor.translateAlternateColorCodes('&', edititemflags);
        }

        public static String getSetUnbreakable() {
            return ChatColor.translateAlternateColorCodes('&', setunbreakable);
        }

        public static String getClose() {
            return ChatColor.translateAlternateColorCodes('&', close);
        }

        public static String getTitle() {
            return ChatColor.translateAlternateColorCodes('&', title);
        }
    }

    public static class EnchantGUI {
        private static String title, cancel, item_level_min, item_level_max, item_minus_10, item_minus_1, item_level, item_plus_1, item_plus_10, item_level_text;
        private final FastItemEditor plugin;

        public EnchantGUI(FastItemEditor plugin) {
            this.plugin = plugin;
            title = plugin.getLangConfig().getString("inventories.enchants.title");
            cancel = plugin.getLangConfig().getString("inventories.enchants.cancel");
            item_level_min = plugin.getLangConfig().getString("inventories.enchants.item-level-min");
            item_level_max = plugin.getLangConfig().getString("inventories.enchants.item-level-max");
            item_minus_10 = plugin.getLangConfig().getString("inventories.enchants.item-minus-10");
            item_minus_1 = plugin.getLangConfig().getString("inventories.enchants.item-minus-1");
            item_level = plugin.getLangConfig().getString("inventories.enchants.item-level");
            item_level_text = plugin.getLangConfig().getString("inventories.enchants.item-level-text");
            item_plus_1 = plugin.getLangConfig().getString("inventories.enchants.item-plus-1");
            item_plus_10 = plugin.getLangConfig().getString("inventories.enchants.item-plus-10");

        }

        public static String getItem_level_min() {
            return ChatColor.translateAlternateColorCodes('&', item_level_min);
        }

        public static String getItem_level_max() {
            return ChatColor.translateAlternateColorCodes('&', item_level_max);
        }

        public static String getItem_minus_10() {
            return ChatColor.translateAlternateColorCodes('&', item_minus_10);
        }

        public static String getItem_minus_1() {
            return ChatColor.translateAlternateColorCodes('&', item_minus_1);
        }

        public static String getItem_level() {
            return ChatColor.translateAlternateColorCodes('&', item_level);
        }

        public static String getItem_plus_1() {
            return ChatColor.translateAlternateColorCodes('&', item_plus_1);
        }

        public static String getItem_plus_10() {
            return ChatColor.translateAlternateColorCodes('&', item_plus_10);
        }

        public static String getItem_level_text() {
            return ChatColor.translateAlternateColorCodes('&', item_level_text);
        }

        public static String getTitle() {
            return ChatColor.translateAlternateColorCodes('&', title);
        }

        public static String getCancel() {
            return ChatColor.translateAlternateColorCodes('&', cancel);
        }
    }
}
