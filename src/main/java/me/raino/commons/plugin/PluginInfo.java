package me.raino.commons.plugin;

public @interface PluginInfo {

    public boolean config() default false;

    public boolean localization() default false;
    
}
