package nl.snicserr.easychat.commands;

import nl.snicserr.easychat.util.C;
import nl.snicserr.easychat.util.Constants;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TimeOutCommand implements CommandExecutor {

    private static Map<UUID, Long> timedOutPlayers = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Constants.PREFIX + C.TAC("&cOnly players can give time out to players."));
            return true;
        }

        Player player = (Player) sender;

        /*
         * Handling player permissions
         * Checking if the player has a permission or OP
         * If not then send message
         */
        if (!player.hasPermission("easychat.timeout") || !player.isOp()) {
            player.sendMessage(Constants.PREFIX + C.TAC("&cYou don't have the permissions to execute this command."));
            return true;
        }

        /*
         * Checking if the entered enough parameters to the command
         * Otherwise send message
         */
        if (args.length < 2) {
            sender.sendMessage(Constants.PREFIX + C.TAC("&fUse: &c/timeout <player> <time>"));
            sender.sendMessage(Constants.PREFIX + C.TAC("&fTime in seconds!"));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        // Checking if target is null or the target isn't online
        if (target == null || !target.isOnline()) {
            player.sendMessage(Constants.PREFIX + C.TAC("&cCould not find the player &f" + args[0] + " &c!"));
            return true;
        }

        long seconds;

        try {
            seconds = Long.parseLong(args[1]);

            /*
             * Checking if the player already has time out
             * If he has time out then send a message
             *
             * Otherwise put the target player into a Map
             */
            if (timedOutPlayers.containsKey(target.getUniqueId())) {
                player.sendMessage(Constants.PREFIX + C.TAC("&cThis player has already a time out."));
            } else {
                timedOutPlayers.put(target.getUniqueId(), seconds);
                target.sendMessage(Constants.PREFIX + C.TAC("&fYou got a time out for &c" + seconds + " &fseconds!"));
            }

        } catch (NumberFormatException e) {
            sender.sendMessage(Constants.PREFIX + C.TAC("&cInvalid amount of seconds!"));
        }

        return true;
    }

    public static Map<UUID, Long> getTimedOutPlayers() {
        return timedOutPlayers;
    }
}
