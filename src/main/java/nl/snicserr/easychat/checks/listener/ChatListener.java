package nl.snicserr.easychat.checks.listener;

import nl.snicserr.easychat.EasyChat;
import nl.snicserr.easychat.util.C;
import nl.snicserr.easychat.util.Constants;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private final EasyChat plugin;

    public ChatListener(EasyChat plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (message.isEmpty() || message.startsWith("/")) {
            return;
        }

        /*
         * Checking if the player has permission to bypass
         */
        if (player.hasPermission("easychat.bypass") || player.isOp()) {
            return;
        }

        /*
         * Checking the checks
         *
         * If the check returns false then cancel event en send the message
         */
        plugin.getChecks().forEach(checks -> {
            if (!checks.allowed(message, player)) {
                event.setCancelled(true);
                player.sendMessage(checks.sendMessage());
            }
        });
    }
}
