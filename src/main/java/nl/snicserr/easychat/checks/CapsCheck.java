package nl.snicserr.easychat.checks;

import nl.snicserr.easychat.EasyChat;
import nl.snicserr.easychat.checks.inferfaces.ICheck;
import nl.snicserr.easychat.util.C;
import nl.snicserr.easychat.util.Constants;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class CapsCheck implements ICheck {

    @Override
    public void initialize(EasyChat plugin) {

    }

    @Override
    public String sendMessage() {
        return String.join("\n",
                Constants.PREFIX + C.TAC("&cYour message was not send."),
                Constants.PREFIX + C.TAC("&cYour message contains too many uppercase characters.")
        );
    }

    @Override
    public boolean allowed(String message, Player player) {
        AtomicInteger count = new AtomicInteger();

        count.set((int) message.chars().filter(Character::isUpperCase).count());

        Bukkit.getOnlinePlayers().forEach(onlinePlayers -> {

            if (message.contains(onlinePlayers.getName())) {

                for (char name : onlinePlayers.getName().toCharArray()) {
                    if (Character.isUpperCase(name)) {
                        count.getAndDecrement();
                    }
                }
            }
        });

        return count.get() < 8;
    }
}
