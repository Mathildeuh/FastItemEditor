package fr.mathilde.events;

import fr.mathilde.FastItemEditor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerConnectListener implements Listener {

    FastItemEditor plugin;

    public PlayerConnectListener(FastItemEditor fastItemEditor) {
        plugin = fastItemEditor;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (e.getPlayer().hasPermission("fastitemeditor.update.alert")) {
            if (FastItemEditor.hasUpdate()) {
                e.getPlayer().sendMessage("§3[FastItemEditor] §aAn update is available.");
                e.getPlayer().sendMessage("§3[FastItemEditor] §cYou are using version §e§m" + plugin.getDescription().getVersion() + "§r §cand the latest version is §e" + FastItemEditor.getLatestVersion() + " §c!");
            }
        }
    }
}
