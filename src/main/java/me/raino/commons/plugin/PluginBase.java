package me.raino.commons.plugin;

import me.raino.commons.Config;
import me.raino.commons.Log;
import me.raino.commons.Scheduler;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.bukkit.util.CommandsManagerRegistration;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandPermissionsException;
import com.sk89q.minecraft.util.commands.CommandUsageException;
import com.sk89q.minecraft.util.commands.CommandsManager;
import com.sk89q.minecraft.util.commands.MissingNestedCommandException;
import com.sk89q.minecraft.util.commands.WrappedCommandException;

public abstract class PluginBase extends JavaPlugin {

    private static PluginBase instance;

    private CommandsManager<CommandSender> commands;
    private CommandsManagerRegistration commandRegistration;

    private boolean config;
    private Config mainConfig;

    public PluginBase() {
        PluginInfo info = getClass().getAnnotation(PluginInfo.class);
        if (info == null)
            return;
        this.config = info.config();
    }

    @Override
    public void onEnable() {
        PluginBase.instance = this;
        Scheduler.init(this);
        Log.init(this);
        if (this.config)
            this.saveDefaultConfig();
        this.registerCommands();
        this.enable();
    }

    @Override
    public void onDisable() {
        this.disable();
        PluginBase.instance = null;
    }

    protected void register(Class<?> commandClass) {
        this.commandRegistration.register(commandClass);
    }

    protected void register(Listener listener) {
        this.getServer().getPluginManager().registerEvents(listener, this);
    }

    @Override
    public Config getConfig() {
        if (!config)
            return null;
        return this.mainConfig;
    }

    @Override
    public void reloadConfig() {
        if (!config)
            return;
        this.mainConfig.reload();
    }

    @Override
    public void saveDefaultConfig() {
        this.mainConfig = new Config(this);
    }

    private void registerCommands() {
        this.commands = new CommandsManager<CommandSender>() {
            @Override
            public boolean hasPermission(CommandSender sender, String perm) {
                return sender.hasPermission(perm);
            }
        };
        this.commandRegistration = new CommandsManagerRegistration(this, this.commands);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        try {
            this.commands.execute(cmd.getName(), args, sender, sender);
        } catch (CommandPermissionsException e) {
            sender.sendMessage(ChatColor.RED + "You don't have permission.");
        } catch (MissingNestedCommandException e) {
            sender.sendMessage(ChatColor.RED + e.getUsage());
        } catch (CommandUsageException e) {
            sender.sendMessage(ChatColor.RED + e.getMessage());
            sender.sendMessage(ChatColor.RED + e.getUsage());
        } catch (WrappedCommandException e) {
            if (e.getCause() instanceof NumberFormatException) {
                sender.sendMessage(ChatColor.RED + "Number expected, string received.");
            } else {
                sender.sendMessage(ChatColor.RED + "An error has occurred.");
                Log.exception(e);
            }
        } catch (CommandException e) {
            sender.sendMessage(ChatColor.RED + e.getMessage());
        }
        return true;
    }

    public static PluginBase get() {
        return PluginBase.instance;
    }

    public abstract void enable();

    public abstract void disable();

}
