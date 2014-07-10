package me.raino.commons.messaging.localization;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Map;

import me.raino.commons.utility.StringUtility;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import com.google.common.collect.Maps;

public class I18n {

    public static final Language DEFAULT_LOCALE = Language.AMERICAN_ENGLISH;

    private static Plugin plugin;
    private static File directory;

    private static Map<Language, LanguageFile> languages;
    private static Map<CommandSender, Language> playerLanguages;

    public static void init(Plugin plugin, String directory) {
        I18n.plugin = plugin;
        I18n.directory = new File(plugin.getDataFolder(), directory);
        I18n.languages = Maps.newHashMap();
        I18n.playerLanguages = Maps.newHashMap();
        I18n.load();
    }

    public static void init(Plugin plugin) {
        init(plugin, "lang");
    }

    private static void load() {
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return StringUtility.split(name, "\\.").get(1).equalsIgnoreCase("lang");
            }
        };
        for (File file : I18n.directory.listFiles(filter)) {
            if (file.isFile() && file.canRead()) {
                LanguageFile languageFile = new LanguageFile(I18n.plugin, file);
                languages.put(Language.fromLocale(languageFile.getName()), languageFile);
            }
        }
    }

    public static void setLanguage(CommandSender sender, Language language) {
        I18n.playerLanguages.put(sender, language);
    }

    public static Language getLanguage(CommandSender sender) {
        Language language = I18n.playerLanguages.get(sender);
        return language != null ? language : I18n.DEFAULT_LOCALE;
    }

    public static LanguageFile getLanguageFile(Language language) {
        LanguageFile languageFile = I18n.languages.get(language);
        return languageFile != null ? languageFile : I18n.getDefaultLanguageFile();
    }

    public static LanguageFile getDefaultLanguageFile() {
        LanguageFile languageFile = I18n.languages.get(I18n.DEFAULT_LOCALE);
        return languageFile != null ? languageFile : null;
    }

    public static LanguageFile getLanguageFile(CommandSender sender) {
        return I18n.getLanguageFile(I18n.getLanguage(sender));
    }

    public static String get(CommandSender sender, String key, Object... arguments) {
        LanguageFile languageFile = I18n.getLanguageFile(sender);
        String message = languageFile.get(key, arguments);
        return message != null ? message : "Internal error while getting translation!";
    }

}
