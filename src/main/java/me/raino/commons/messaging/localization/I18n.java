package me.raino.commons.messaging.localization;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Map;

import me.raino.commons.Txt;
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

    public static boolean init(Plugin plugin, String directory) {
        I18n.plugin = plugin;
        I18n.directory = new File(plugin.getDataFolder(), directory);
        I18n.languages = Maps.newHashMap();
        I18n.playerLanguages = Maps.newHashMap();
        I18n.load();
        return I18n.languages.containsKey(I18n.DEFAULT_LOCALE);
    }

    public static boolean init(Plugin plugin) {
        return init(plugin, "lang");
    }

    private static void load() {
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return StringUtility.split(name, "\\.").get(1).equalsIgnoreCase("lang");
            }
        };
        for (File file : directory.listFiles(filter)) {
            if (file.isFile() && file.canRead()) {
                LanguageFile languageFile = new LanguageFile(plugin, file);
                Language language = Language.fromLocale(languageFile.getName());
                if (language == null)
                    continue;
                languages.put(language, languageFile);
            }
        }
    }

    public static void setLanguage(CommandSender sender, Language language) {
        playerLanguages.put(sender, language);
    }

    public static Language getLanguage(CommandSender sender) {
        Language language = playerLanguages.get(sender);
        return language != null ? language : DEFAULT_LOCALE;
    }

    public static LanguageFile getLanguageFile(Language language) {
        LanguageFile languageFile = languages.get(language);
        return languageFile != null ? languageFile : getDefaultLanguageFile();
    }

    public static LanguageFile getDefaultLanguageFile() {
        LanguageFile languageFile = languages.get(DEFAULT_LOCALE);
        return languageFile != null ? languageFile : null;
    }

    public static LanguageFile getLanguageFile(CommandSender sender) {
        return getLanguageFile(getLanguage(sender));
    }

    public static String get(CommandSender sender, String key, Object... arguments) {
        LanguageFile languageFile = getLanguageFile(sender);
        if (languageFile == null)
            return Txt.parse("Localization error! Default locale ({0}) not loaded!", I18n.DEFAULT_LOCALE.getLocale());
        String message = languageFile.get(key);
        if (message == null)
            message = I18n.getDefaultLanguageFile().get(key);
        return message != null ? Txt.parse(message, arguments) : Txt.parse("Localization error! No message found for key '{0}'!", key);
    }

}
