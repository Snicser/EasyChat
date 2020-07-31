package nl.snicserr.easychat.checks;

import nl.snicserr.easychat.EasyChat;
import nl.snicserr.easychat.checks.inferfaces.ICheck;
import nl.snicserr.easychat.commands.LockChatCommand;
import nl.snicserr.easychat.util.C;
import nl.snicserr.easychat.util.Constants;
import org.bukkit.entity.Player;

public class ChatLockCheck implements ICheck {

    @Override
    public void initialize(EasyChat plugin) {

    }

    @Override
    public String sendMessage() {
        return String.join("\n",
                Constants.PREFIX + C.TAC("&cYour message was not send."),
                Constants.PREFIX + C.TAC("&cThe chat is currently locked!")
        );
    }

    @Override
    public boolean allowed(String message, Player player) {
        if (LockChatCommand.isChatLocked()) {
            return false;
        }

        return true;
    }
}
