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
        new SetLore(plugin);
        new SetLoreLine(plugin);
        new Rename(plugin);


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



    public static class SetLoreLine {

        FastItemEditor plugin;

        private static String syntax, invalidLineNumber, lineNumberTooHigh, lineNotFound, emptyLine, loreSet;

        public static String getSyntax() {
            return ChatColor.translateAlternateColorCodes('&', syntax);
        }

        public static String getInvalidLineNumber() {
            return ChatColor.translateAlternateColorCodes('&', invalidLineNumber);
        }

        public static String getLineNumberTooHigh() {
            return ChatColor.translateAlternateColorCodes('&', lineNumberTooHigh);
        }

        public static String getLineNotFound() {
            return ChatColor.translateAlternateColorCodes('&', lineNotFound);
        }

        public static String getEmptyLine() {
            return ChatColor.translateAlternateColorCodes('&', emptyLine);
        }

        public static String getLoreSet() {
            return ChatColor.translateAlternateColorCodes('&', loreSet);
        }

        public SetLoreLine(FastItemEditor fastItemEditor) {
            this.plugin = fastItemEditor;

            syntax = plugin.getLangConfig().getString("fastitemeditor-command.setloreline.syntax");
            invalidLineNumber = plugin.getLangConfig().getString("fastitemeditor-command.setloreline.invalid-line-number");
            lineNumberTooHigh = plugin.getLangConfig().getString("fastitemeditor-command.setloreline.line-number-too-high");
            lineNotFound = plugin.getLangConfig().getString("fastitemeditor-command.setloreline.line-not-found");
            emptyLine = plugin.getLangConfig().getString("fastitemeditor-command.setloreline.empty-line");
            loreSet = plugin.getLangConfig().getString("fastitemeditor-command.setloreline.lore-set");


        }


    }
    public static class SetLore {
        private static String syntax, loreSet, loreSplitRegex, loreEditCanceled, wordForCancel, blank, alreadyEditingLore, enterEditor;
        FastItemEditor plugin;



        public SetLore(FastItemEditor fastItemEditor) {
            this.plugin = fastItemEditor;

            syntax = plugin.getLangConfig().getString("fastitemeditor-command.setlore.syntax");
            loreSet = plugin.getLangConfig().getString("fastitemeditor-command.setlore.lore-set");
            loreSplitRegex = plugin.getLangConfig().getString("fastitemeditor-command.setlore.lore-split-regex");
            loreEditCanceled = plugin.getLangConfig().getString("fastitemeditor-command.setlore.lore-edit-canceled");
            wordForCancel = plugin.getLangConfig().getString("fastitemeditor-command.setlore.word-for-cancel");
            blank = plugin.getLangConfig().getString("fastitemeditor-command.setlore.blank");
            alreadyEditingLore = plugin.getLangConfig().getString("fastitemeditor-command.setlore.already-editing");
            enterEditor = plugin.getLangConfig().getString("fastitemeditor-command.setlore.enter-editor");

        }

        public static String getEnterEditor() {
            return ChatColor.translateAlternateColorCodes('&', enterEditor);
        }
        public static String getSyntax() {
            return ChatColor.translateAlternateColorCodes('&', syntax);
        }

        public static String getAlreadyEditingLore() {
            return ChatColor.translateAlternateColorCodes('&', alreadyEditingLore);
        }

        public static String getLoreSet() {
            return ChatColor.translateAlternateColorCodes('&', loreSet);
        }

        public static String getLoreSplitRegex() {
            return ChatColor.translateAlternateColorCodes('&', loreSplitRegex);
        }

        public static String getLoreEditCanceled() {
            return ChatColor.translateAlternateColorCodes('&', loreEditCanceled);
        }

        public static String getWordForCancel() {
            return wordForCancel;
        }

        public static String getBlank() {
            return ChatColor.translateAlternateColorCodes('&', blank);
        }
    }

    public static class Rename {
        FastItemEditor plugin;

        private static String syntax, renamed;

        public Rename(FastItemEditor plugin) {
            this.plugin = plugin;
            syntax = plugin.getLangConfig().getString("fastitemeditor-command.rename.syntax");
            renamed = plugin.getLangConfig().getString("fastitemeditor-command.rename.item-renamed");
        }

        public static String getSyntax() {
            return ChatColor.translateAlternateColorCodes('&', syntax);
        }

        public static String getRenamed() {
            return ChatColor.translateAlternateColorCodes('&', renamed);
        }
    }
}
