package nl.snicserr.easychat.util;

import org.bukkit.ChatColor;

public class C {

    private C() {

    }

    public static String TAC(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}
