package me.raino.commons.utility;

import me.raino.commons.reflection.SafeField;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandPermissionsException;

public class CommandUtility {

    public static Player toPlayer(CommandSender sender) throws CommandException {
        if (sender == null || !(sender instanceof Player))
            throw new CommandException("You must be a player to execute this command!");
        return (Player) sender;
    }

    public static void assertPermission(CommandSender sender, String permission) throws CommandPermissionsException {
        if (sender == null || permission == null || !sender.hasPermission(permission))
            throw new CommandPermissionsException();
    }

    public static CommandMap getCommandMap() {
        return SafeField.<CommandMap> of(Bukkit.getServer().getPluginManager(), "commandMap").get();
    }

    private CommandUtility() {}

}
