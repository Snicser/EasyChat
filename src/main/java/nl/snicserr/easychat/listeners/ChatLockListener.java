package nl.snicserr.easychat.listeners;

import nl.snicserr.easychat.commands.LockChatCommand;
import nl.snicserr.easychat.util.C;
import nl.snicserr.easychat.util.Constants;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatLockListener implements Listener {

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        // Chat is locked
        if (LockChatCommand.isChatLocked()) {

            /*
             * Handling player permissions
             * Checking if the player has a permission or OP
             * If not then send message and make sure they not send the message
             */
            if (!player.hasPermission("easychat.lockchat.bypass") || !player.isOp()) {
                event.setCancelled(true);
                player.sendMessage(Constants.PREFIX + C.TAC("&cThe chat is currently locked!"));
            }
        }
    }
}
