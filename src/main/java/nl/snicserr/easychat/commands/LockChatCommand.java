package nl.snicserr.easychat.commands;

import nl.snicserr.easychat.EasyChat;
import nl.snicserr.easychat.util.C;
import nl.snicserr.easychat.util.Constants;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LockChatCommand implements CommandExecutor {

    private static boolean chatLocked;

    public LockChatCommand() {
        chatLocked = false;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Constants.PREFIX + C.TAC("&cOnly players can lock or unlock the chat."));
            return true;
        }

        Player player = (Player) sender;

        /*
         * Handling player permissions
         * Checking if the player has a permission or OP
         * If not then send message
         */
        if (!player.hasPermission("easychat.lockchat") || !player.isOp()) {
            player.sendMessage(Constants.PREFIX + C.TAC("&cYou don't have the permissions to execute this command."));
            return true;
        }

        /*
         * Checking if they not entered any extra parameters to the command
         * Otherwise send message
         */
        if (args.length == 0) {
            handle();
        } else {
            player.sendMessage(Constants.PREFIX + C.TAC("&fUse: &c/lockchat"));
        }

        return true;
    }

    private void handle() {
        // Chat is locked
        if (chatLocked) {

            // Chat is now unlocked
            chatLocked = false;

            Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(Constants.PREFIX + C.TAC("&aThe chat is enabled!")));
        } else {

            // Chat is now locked
            chatLocked = true;

            Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(Constants.PREFIX + C.TAC("&fThe chat is disabled by: &c " + player.getName() + "&f.")));
        }
    }

    public static boolean isChatLocked() {
        return chatLocked;
    }
}
