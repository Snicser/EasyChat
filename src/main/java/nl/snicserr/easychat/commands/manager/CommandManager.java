package nl.snicserr.easychat.commands.manager;

import nl.snicserr.easychat.EasyChat;
import nl.snicserr.easychat.commands.ClearChatCommand;
import nl.snicserr.easychat.commands.LockChatCommand;
import nl.snicserr.easychat.commands.TimeOutCommand;
import org.bukkit.command.CommandExecutor;

import java.util.Objects;

public class CommandManager {

    private final EasyChat plugin;

    public CommandManager(EasyChat plugin) {
        this.plugin = plugin;
    }

    public void registerAll() {
        registerCommand("clearchat", new ClearChatCommand(plugin));
        registerCommand("lockchat", new LockChatCommand());
        registerCommand("timeout", new TimeOutCommand());
    }

    private void registerCommand(String command, CommandExecutor executor) {
        // Make sure the are not entering empty string
        Objects.requireNonNull(plugin.getCommand(command)).setExecutor(executor);
    }
}
