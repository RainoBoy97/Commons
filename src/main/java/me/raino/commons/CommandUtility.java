package me.raino.commons;

import me.raino.commons.Validate;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandPermissionsException;

public class CommandUtility {

    public static Player toPlayer(CommandSender sender) throws CommandException {
        Validate.notNull(sender, "sender cannot be null");
        if (!(sender instanceof Player))
            throw new CommandException("You must be a player to execute this command!");
        return (Player) sender;
    }

    public static void assertPermission(CommandSender sender, String permission) throws CommandPermissionsException {
        Validate.notNull(sender, "sender");
        Validate.notNull(permission, "permission");
        if (!sender.hasPermission(permission))
            throw new CommandPermissionsException();
    }

    private CommandUtility() {}

}
