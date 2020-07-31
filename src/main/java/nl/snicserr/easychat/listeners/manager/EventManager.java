package nl.snicserr.easychat.listeners.manager;

import nl.snicserr.easychat.EasyChat;
import nl.snicserr.easychat.checks.listener.ChatListener;
import nl.snicserr.easychat.listeners.AntiCurseListener;
import nl.snicserr.easychat.listeners.ChatLockListener;
import nl.snicserr.easychat.listeners.TimeOutListener;
import org.bukkit.event.Listener;

public class EventManager {

    private final EasyChat plugin;

    public EventManager(EasyChat plugin) {
        this.plugin = plugin;
    }

    public void registerAll() {
//        registerEvent(new ChatLockListener());
//        registerEvent(new TimeOutListener());
//        registerEvent(new AntiCurseListener(plugin));
        registerEvent(new ChatListener(plugin));
    }

    private void registerEvent(Listener listener) {
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }
}
