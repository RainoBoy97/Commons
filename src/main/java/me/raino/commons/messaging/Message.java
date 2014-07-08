package me.raino.commons.messaging;

import java.util.List;

import me.raino.commons.Txt;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.google.common.collect.Lists;

/*
 * Inspired by bergerkiller's MessageBuilder from BKCommonLib
 * https://github.com/bergerkiller/BKCommonLib/blob/master/src/main/java/com/bergerkiller/bukkit/common/MessageBuilder.java
 */
public class Message {

    private List<StringBuilder> lines;
    private StringBuilder currentLine;

    public Message() {
        this(new StringBuilder());
    }

    public Message(StringBuilder currentLine) {
        this.lines = Lists.newArrayList(this.currentLine = currentLine);
    }

    public static Message start() {
        return new Message();
    }

    public Message append(Object... arguments) {
        if (arguments != null && arguments.length > 0) {
            String message = arguments[0].toString();
            this.currentLine.append(Txt.parse(message, ArrayUtils.remove(arguments, 0)));
        }
        return this;
    }

    public Message append(ChatColor color, Object... arguments) {
        this.currentLine.append(color.toString());
        return this.append(arguments);
    }

    public Message newLine() {
        this.lines.add(this.currentLine = new StringBuilder());
        return this;
    }
    
    public Message clear() {
        this.lines.clear();
        this.currentLine = new StringBuilder();
        return this;
    }
    
    public int lenght() {
        int length = 0;
        for (StringBuilder line : this.lines)
            length += line.length();
        return length;
    }

    public Message black(Object... arguments) {
        return this.append(ChatColor.BLACK, arguments);
    }

    public Message darkBlue(Object... arguments) {
        return this.append(ChatColor.DARK_BLUE, arguments);
    }

    public Message darkGreen(Object... arguments) {
        return this.append(ChatColor.DARK_GREEN, arguments);
    }

    public Message darkAqua(Object... arguments) {
        return this.append(ChatColor.DARK_AQUA, arguments);
    }

    public Message darkRed(Object... arguments) {
        return this.append(ChatColor.DARK_RED, arguments);
    }

    public Message darkPurple(Object... arguments) {
        return this.append(ChatColor.DARK_PURPLE, arguments);
    }

    public Message gold(Object... arguments) {
        return this.append(ChatColor.GOLD, arguments);
    }

    public Message gray(Object... arguments) {
        return this.append(ChatColor.GRAY, arguments);
    }

    public Message darkGray(Object... arguments) {
        return this.append(ChatColor.DARK_GRAY, arguments);
    }

    public Message blue(Object... arguments) {
        return this.append(ChatColor.BLUE, arguments);
    }

    public Message green(Object... arguments) {
        return this.append(ChatColor.GREEN, arguments);
    }

    public Message aqua(Object... arguments) {
        return this.append(ChatColor.AQUA, arguments);
    }

    public Message red(Object... arguments) {
        return this.append(ChatColor.RED, arguments);
    }

    public Message lightPurple(Object... arguments) {
        return this.append(ChatColor.LIGHT_PURPLE, arguments);
    }

    public Message yellow(Object... arguments) {
        return this.append(ChatColor.YELLOW, arguments);
    }

    public Message magic(Object... arguments) {
        return this.append(ChatColor.MAGIC, arguments);
    }

    public Message bold(Object... arguments) {
        return this.append(ChatColor.BOLD, arguments);
    }

    public Message strikethrough(Object... arguments) {
        return this.append(ChatColor.STRIKETHROUGH, arguments);
    }

    public Message underline(Object... arguments) {
        return this.append(ChatColor.UNDERLINE, arguments);
    }

    public Message italic(Object... arguments) {
        return this.append(ChatColor.ITALIC, arguments);
    }

    public Message reset(Object... arguments) {
        return this.append(ChatColor.RESET, arguments);
    }

    public Message send(CommandSender sender) {
        for (StringBuilder line : this.lines)
            sender.sendMessage(line.toString());
        return this;
    }
    
    public Message flush(CommandSender sender) {
        return this.send(sender).clear();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.lines.size(); i++) {
            if (i != 0)
                sb.append('\n');
            sb.append(this.lines.get(i));
        }
        return sb.toString();
    }

}
