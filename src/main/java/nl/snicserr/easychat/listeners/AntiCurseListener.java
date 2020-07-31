package nl.snicserr.easychat.listeners;

import nl.snicserr.easychat.EasyChat;
import nl.snicserr.easychat.util.C;
import nl.snicserr.easychat.util.Constants;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AntiCurseListener implements Listener {

    private final EasyChat plugin;

    public AntiCurseListener(EasyChat plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        /*
         * Checking if the player has permission to bypass
         *
         * If they don't have the permission or the player isn't OP then
         * the message got cancelled
         */
        if (player.hasPermission("easychat.curse.bypass") || player.isOp()) {
            return;
        }

        for (String message : event.getMessage().split(" ")) {
            if (plugin.getWords().contains(message.toLowerCase())) {

                event.setCancelled(true);
                player.sendMessage(Constants.PREFIX + C.TAC("&cIt is not allowed to curse."));
                break;
            }
        }
    }
}
