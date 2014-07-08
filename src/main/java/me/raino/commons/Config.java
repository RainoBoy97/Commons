package me.raino.commons;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public final class Config extends YamlConfiguration {

    private final File configFile;

    public Config(Plugin plugin, String file) {
        this(plugin, new File(plugin.getDataFolder(), file));
    }

    public Config(Plugin plugin, File file) {
        this.configFile = file;
        if (!this.configFile.exists()) {
            if (plugin.getResource(file.getName()) == null) {
                try {
                    this.configFile.createNewFile();
                } catch (Exception e) {
                    Log.exception(e);
                }
            } else {
                plugin.saveResource(file.getName(), true);
            }
        }
        this.reload();
    }

    public Config(Plugin plugin) {
        this(plugin, "config.yml");
    }

    public boolean reload() {
        try {
            super.load(this.configFile);
            return true;
        } catch (Exception e) {
            Log.exception(e);
            return false;
        }
    }

    public boolean save() {
        try {
            super.save(this.configFile);
            return true;
        } catch (Exception e) {
            Log.exception(e);
            return false;
        }
    }

}
