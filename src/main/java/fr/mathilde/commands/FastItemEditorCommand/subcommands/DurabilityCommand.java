package fr.mathilde.commands.FastItemEditorCommand.subcommands;

import fr.mathilde.FastItemEditor;
import fr.mathilde.commands.FastItemEditorCommand.SubCommands;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DurabilityCommand extends SubCommands {
    FastItemEditor plugin;

    public DurabilityCommand(FastItemEditor plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "setdurability";
    }

    @Override
    public String getSyntax() {
        return "§e/fastitemeditor §asetdurability <durability>";
    }

    @Override
    public void run(Player player, String[] args) {
        if (args.length <= 1) {
            player.sendMessage("§cInvalid syntax");
            player.sendMessage(getSyntax());
            return;
        }
        ItemStack stack = player.getItemInHand();

        int durability;
        int finalDurability;

        try {
            durability = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            if (args[1].equalsIgnoreCase("max"))
                durability = player.getItemInHand().getType().getMaxDurability();

            else if (args[1].equalsIgnoreCase("half"))
                durability = player.getItemInHand().getType().getMaxDurability() / 2;
            else {
                player.sendMessage("§cInvalid durability");
                return;
            }

        }
        finalDurability = stack.getType().getMaxDurability() - durability;
        stack.setDurability((short) finalDurability);
        player.sendMessage("§aItem durability set to §e" + durability);
    }
}
