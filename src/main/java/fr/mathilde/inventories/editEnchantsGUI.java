package fr.mathilde.inventories;

import dev.jcsoftware.minecraft.gui.GUI;
import fr.mathilde.FastItemEditor;
import fr.mathilde.commands.FieCommand;
import fr.mathilde.utilities.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class editEnchantsGUI extends GUI<FastItemEditor> {

    FastItemEditor plugin;
    ItemStack itemInHand;

    public editEnchantsGUI(FastItemEditor plugin, ItemStack itemInHand) {
        super(plugin);

        this.plugin = plugin;
        this.itemInHand = itemInHand;
        createInventory();
    }

    private void createInventory() {
        set(44, new ItemBuilder(Material.LOOM).toItemStack(), (player, action) -> {
            FieCommand.openMainGui(player);
            return ButtonAction.CANCEL;
        });
    }

    @Override
    public int getSize() {
        return 6*9;
    }

    @Override
    public String getTitle() {
        return "§3Fast Item Editor §7- §2Enchantments";
    }

    @Override
    public boolean canClose(Player player) {
        return true;
    }
}
