package fr.mathilde.commands.FastItemEditorCommand.subcommands;

import fr.mathilde.FastItemEditor;
import fr.mathilde.commands.FastItemEditorCommand.SubCommands;
import fr.mathilde.lang.Commands;
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
        return Commands.Durability.getSyntax();
    }

    @Override
    public void run(Player player, String[] args) {
        if (args.length <= 1) {
            player.sendMessage(getSyntax());
            return;
        }
        ItemStack stack = player.getItemInHand();

        int durability;
        int finalDurability;

        try {
            durability = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            if (args[1].equalsIgnoreCase(Commands.Durability.getWord_max()))
                durability = player.getItemInHand().getType().getMaxDurability();

            else if (args[1].equalsIgnoreCase(Commands.Durability.getWord_half()))
                durability = player.getItemInHand().getType().getMaxDurability() / 2;
            else {
                player.sendMessage(Commands.Durability.getInvalid());
                return;
            }

        }
        if (durability > stack.getType().getMaxDurability()) {
            player.sendMessage(Commands.Durability.getInvalid());
            return;
        }
        finalDurability = stack.getType().getMaxDurability() - durability;
        stack.setDurability((short) finalDurability);
        player.sendMessage(Commands.Durability.getComplete().replace("%value%", String.valueOf(durability)));
    }
}
