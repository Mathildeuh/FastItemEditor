package fr.mathilde.commands.FastItemEditorCommand.subcommands;

import fr.mathilde.FastItemEditor;
import fr.mathilde.commands.FastItemEditorCommand.SubCommands;
import fr.mathilde.inventories.ItemFlagsGUI;
import org.bukkit.entity.Player;

public class ItemFlagsCommand extends SubCommands {
    FastItemEditor plugin;

    public ItemFlagsCommand(FastItemEditor plugin) {
        super();
        this.plugin = plugin;
    }


    @Override
    public String getName() {
        return "itemflags";
    }

    @Override
    public String getSyntax() {
        return "§e/fastitemeditor §aitemflags";
    }

    @Override
    public void run(Player player, String[] args) {
        ItemFlagsGUI.openAnvilGui(player, plugin, false);
    }
}
