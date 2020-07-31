package nl.snicserr.easychat.checks.inferfaces;

import nl.snicserr.easychat.EasyChat;
import org.bukkit.entity.Player;

public interface ICheck {

    void initialize(EasyChat plugin);
    String sendMessage();
    boolean allowed (String message, Player player);

}
