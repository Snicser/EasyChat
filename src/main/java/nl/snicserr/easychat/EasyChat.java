package nl.snicserr.easychat;

import nl.snicserr.easychat.checks.AntiCurseCheck;
import nl.snicserr.easychat.checks.CapsCheck;
import nl.snicserr.easychat.checks.ChatLockCheck;
import nl.snicserr.easychat.checks.TimeOutCheck;
import nl.snicserr.easychat.checks.inferfaces.ICheck;
import nl.snicserr.easychat.commands.TimeOutCommand;
import nl.snicserr.easychat.commands.manager.CommandManager;
import nl.snicserr.easychat.listeners.manager.EventManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class EasyChat extends JavaPlugin {

    private List<List<String>> words;
    private List<ICheck> checks;
    private CommandManager commandManager;
    private EventManager eventManager;

    @Override
    public void onEnable() {
        initialize();
        cache();

        commandManager.registerAll();
        eventManager.registerAll();

        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onDisable() {
        TimeOutCommand.getTimedOutPlayers().clear();
        words.clear();
        checks.clear();
    }

    private void initialize() {
        commandManager = new CommandManager(this);
        eventManager = new EventManager(this);

        words = new ArrayList<List<String>>();
        checks = new ArrayList<>();
    }

    private void cache() {
        words.add(getConfig().getStringList("words"));

        checks.add(new AntiCurseCheck());
        checks.add(new ChatLockCheck());
        checks.add(new TimeOutCheck());
        checks.add(new CapsCheck());
    }

    public List<List<String>> getWords() {
        return words;
    }

    public List<ICheck> getChecks() {
        return checks;
    }
}
