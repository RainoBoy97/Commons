package me.raino.commons.messaging.localization;

import java.io.File;

import me.raino.commons.Config;
import me.raino.commons.utility.StringUtility;

import org.bukkit.plugin.Plugin;

public class LanguageFile {

    private String name;
    private Config config;

    public LanguageFile(Plugin plugin, File file) {
        this.name = StringUtility.split(file.getName(), "\\.").get(0);
        this.config = new Config(plugin, file);
    }

    public String get(String key) {
        return this.config.getString(key);
    }

    public String getName() {
        return this.name;
    }

}
