package nl.snicserr.easychat.checks;

import nl.snicserr.easychat.EasyChat;
import nl.snicserr.easychat.checks.inferfaces.ICheck;
import nl.snicserr.easychat.commands.TimeOutCommand;
import nl.snicserr.easychat.util.C;
import nl.snicserr.easychat.util.Constants;
import org.bukkit.entity.Player;

public class TimeOutCheck implements ICheck {

    @Override
    public void initialize(EasyChat plugin) {

    }

    @Override
    public String sendMessage() {
        return String.join("\n",Constants.PREFIX + C.TAC("&cYour message was not send."));
    }

    @Override
    public boolean allowed(String message, Player player) {
        // Player is in the map
        if (TimeOutCommand.getTimedOutPlayers().containsKey(player.getUniqueId())) {

            // Getting the seconds from the map and put it into the long variable
            long secondsOver = ((TimeOutCommand.getTimedOutPlayers().get(player.getUniqueId()) / 1000) + TimeOutCommand.getTimedOutPlayers().get(player.getUniqueId())) - (System.currentTimeMillis() / 1000);

            if (secondsOver > 0) {

                player.sendMessage(Constants.PREFIX + C.TAC("&fWait " + "&c" + secondsOver + " &f to send messages."));
                return false;

            } else {
                TimeOutCommand.getTimedOutPlayers().remove(player.getUniqueId());
            }

        }

        return true;
    }
}
