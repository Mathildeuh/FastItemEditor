package fr.mathilde.lang;

import fr.mathilde.FastItemEditor;
import org.bukkit.ChatColor;

public class Commands {
    public static String permission;
    private static String noPermission;
    private static String needToHaveItemInHand;
    FastItemEditor plugin;

    public Commands(FastItemEditor fastItemEditor) {
        plugin = fastItemEditor;

        permission = plugin.getLangConfig().getString("fastitemeditor-command.permission");
        noPermission = plugin.getLangConfig().getString("fastitemeditor-command.no-permission");
        needToHaveItemInHand = plugin.getLangConfig().getString("need-to-have-item-in-hand");

    }

    public static String getPermission() {
        return permission;
    }

    public static String getNeedToHaveItemInHand() {
        return ChatColor.translateAlternateColorCodes('&', needToHaveItemInHand);
    }

    public static String getNoPermission() {
        return ChatColor.translateAlternateColorCodes('&', noPermission);
    }
}
