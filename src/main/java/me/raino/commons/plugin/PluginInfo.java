package me.raino.commons.plugin;

public @interface PluginInfo {

    public boolean config() default false;

    public boolean debug() default false;
    
}
