package fr.mathilde.inventories;

import dev.jcsoftware.minecraft.gui.GUI;
import fr.mathilde.FastItemEditor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class editEnchantsGUI extends GUI<FastItemEditor> {

    FastItemEditor plugin;
    ItemStack itemInHand;

    public editEnchantsGUI(FastItemEditor plugin, ItemStack itemInHand) {
        super(plugin);

        this.plugin = plugin;
        this.itemInHand = itemInHand;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public boolean canClose(Player player) {
        return false;
    }
}
