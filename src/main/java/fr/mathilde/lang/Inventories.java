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
        private static String title, editname, editlore, editenchant, edititemflags, setunbreakable, close;
        private static String loreeditname, loreeditlore, loreeditenchant, loreedititemflags, loresetunbreakable;
        FastItemEditor plugin;

        public MainGUI(FastItemEditor fastItemEditor) {
            plugin = fastItemEditor;
            title = plugin.getLangConfig().getString("inventories.main.title");
            editname = plugin.getLangConfig().getString("inventories.main.items.editname");
            editlore = plugin.getLangConfig().getString("inventories.main.items.editlore");
            editenchant = plugin.getLangConfig().getString("inventories.main.items.editenchant");
            edititemflags = plugin.getLangConfig().getString("inventories.main.items.edititemflags");
            setunbreakable = plugin.getLangConfig().getString("inventories.main.items.setunbreakable");
            close = plugin.getLangConfig().getString("inventories.main.items.close");

            loreeditname = plugin.getLangConfig().getString("inventories.main.lores.editname");
            loreeditlore = plugin.getLangConfig().getString("inventories.main.lores.editlore");
            loreeditenchant = plugin.getLangConfig().getString("inventories.main.lores.editenchant");
            loreedititemflags = plugin.getLangConfig().getString("inventories.main.lores.edititemflags");
            loresetunbreakable = plugin.getLangConfig().getString("inventories.main.lores.setunbreakable");


        }

        public static String getEditname() {
            return ChatColor.translateAlternateColorCodes('&', editname);
        }

        public static String getEditlore() {
            return ChatColor.translateAlternateColorCodes('&', editlore);
        }

        public static String getEditenchant() {
            return ChatColor.translateAlternateColorCodes(  '&', editenchant);
        }

        public static String getEdititemflags() {
            return ChatColor.translateAlternateColorCodes('&', edititemflags);
        }

        public static String getSetunbreakable() {
            return ChatColor.translateAlternateColorCodes('&', setunbreakable);
        }

        public static String getLoreeditname() {
            return ChatColor.translateAlternateColorCodes('&', loreeditname);
        }

        public static String getLoreeditlore() {
            return ChatColor.translateAlternateColorCodes('&', loreeditlore);
        }

        public static String getLoreeditenchant() {
            return ChatColor.translateAlternateColorCodes(  '&', loreeditenchant);
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
            return ChatColor.translateAlternateColorCodes('&', editlore)
        }

        public static String getEditEnchant() {
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
}
