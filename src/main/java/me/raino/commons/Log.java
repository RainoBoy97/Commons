package me.raino.commons;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.bukkit.plugin.Plugin;

public final class Log {

    private static Logger log;

    private static boolean debug = false;

    public static void log(Level level, String message, Object... arguments) {
        Validate.notNull(level, "level cannot be null");
        Validate.notNull(message, "message cannot be null");
        log.log(level, Txt.parse(message, arguments));
    }

    public static void exception(Exception e) {
        Validate.notNull(e, "e cannot be null");
        warning(ExceptionUtils.getFullStackTrace(e));
    }

    public static void info(String message, Object... arguments) {
        log(Level.INFO, message, arguments);
    }

    public static void warning(String message, Object... arguments) {
        log(Level.WARNING, message, arguments);
    }

    public static void severe(String message, Object... arguments) {
        log(Level.SEVERE, message, arguments);
    }

    public static void debug(String message, Object... arguments) {
        if (debug)
            info("[DEBUG] " + message, arguments);
    }

    public static void debug(boolean debug) {
        Validate.notNull(debug, "debug");
        Log.debug = debug;
    }
    
    public static void init(Plugin plugin) {
        Validate.notNull(plugin, "plugin");
        Log.log = plugin.getLogger();
    }
    
    public static void init(Plugin plugin, boolean debug) {
        debug(debug);
    }
    
    private Log() {}

}
