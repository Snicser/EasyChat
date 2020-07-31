package nl.snicserr.easychat.listeners;

import nl.snicserr.easychat.commands.LockChatCommand;
import nl.snicserr.easychat.commands.TimeOutCommand;
import nl.snicserr.easychat.util.C;
import nl.snicserr.easychat.util.Constants;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class TimeOutListener implements Listener {

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        // Player is in the map
        if (TimeOutCommand.getTimedOutPlayers().containsKey(player.getUniqueId())) {

            /*
             * Handling player permissions
             * Checking if the player has a permission or OP
             * If not then send message and make sure they not send the message
             */
            if (!player.hasPermission("easychat.timeout.bypass") || !player.isOp()) {

                // Getting the seconds from the map and put it into the long variable
                long secondsOver = ((TimeOutCommand.getTimedOutPlayers().get(player.getUniqueId()) / 1000) + TimeOutCommand.getTimedOutPlayers().get(player.getUniqueId())) - (System.currentTimeMillis() / 1000);

                if (secondsOver > 0) {

                    event.setCancelled(true);
                    player.sendMessage(Constants.PREFIX + C.TAC("&fWait " + "&c" + secondsOver + " &f to send messages."));

                } else {
                    TimeOutCommand.getTimedOutPlayers().remove(player.getUniqueId());
                }
            }
        }

    }
}
