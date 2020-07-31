package nl.snicserr.easychat.commands;

import nl.snicserr.easychat.EasyChat;
import nl.snicserr.easychat.util.C;
import nl.snicserr.easychat.util.Constants;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChatCommand implements CommandExecutor {

    private final EasyChat plugin;

    public ClearChatCommand(EasyChat plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // TODO: Implement console also allowed to clear the chat
        if (!(sender instanceof Player)) {
            sender.sendMessage(Constants.PREFIX + C.TAC("&cOnly players can clear the chat"));
            return true;
        }

        Player player = (Player) sender;

        /*
         * Handling player permissions
         * Checking if the player has a permission or OP
         * If not then send message
         */
        if (!player.hasPermission("easychat.clearchat") || !player.isOp()) {
            player.sendMessage(Constants.PREFIX + C.TAC("&cYou don't have the permissions to execute this command."));
            return true;
        }

        /*
         * Checking if they not entered any extra parameters to the command
         * Otherwise send message
         */
        if (args.length == 0) {
            clearChat(20);
        } else {
            player.sendMessage(Constants.PREFIX + C.TAC("&fUse: &c/clearchat"));
        }

        return true;
    }

    /**
     * @param lines Lines to clear
     */
    private void clearChat(int lines) {
        Bukkit.getOnlinePlayers().forEach(player -> {

            for (int i = 0; i < lines; i++) {
                player.sendMessage(" ");
            }

            player.sendMessage(Constants.PREFIX + C.TAC("&fChat has been cleared by: &c" + player.getName()));
        });
    }
}
