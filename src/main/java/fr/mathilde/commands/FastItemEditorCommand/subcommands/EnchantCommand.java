package fr.mathilde.commands.FastItemEditorCommand.subcommands;

import fr.mathilde.FastItemEditor;
import fr.mathilde.commands.FastItemEditorCommand.SubCommands;
import fr.mathilde.inventories.EnchantsGUI;
import org.bukkit.entity.Player;

public class EnchantCommand extends SubCommands {

    FastItemEditor plugin;

    public EnchantCommand(FastItemEditor plugin) {
        super();
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "enchant";
    }

    @Override
    public String getSyntax() {
        return "§e/fie §aenchant";
    }

    @Override
    public void run(Player player, String[] args) {
        FastItemEditor.guiAPI.openGUI(player, new EnchantsGUI(plugin, player.getItemInHand(), player));

    }
}
