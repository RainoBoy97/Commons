package me.raino.commons;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public abstract class Scheduler implements Runnable {

    private static Plugin plugin;
    private static BukkitScheduler scheduler;

    private BukkitTask task;

    public synchronized void runTask() {
        this.task = scheduler.runTask(plugin, this);
    }

    public synchronized void runTaskAsync() {
        this.task = scheduler.runTaskAsynchronously(plugin, this);
    }

    public synchronized void runTaskLater(long delay) {
        this.task = scheduler.runTaskLater(plugin, this, delay);
    }

    public synchronized void runTaskLaterAsync(long delay) {
        this.task = scheduler.runTaskLaterAsynchronously(plugin, this, delay);
    }

    public synchronized void runTaskTimer(long delay, long period) {
        this.task = scheduler.runTaskTimer(plugin, this, delay, period);
    }

    public synchronized void runTaskTimerAsync(long delay, long period) {
        this.task = scheduler.runTaskTimerAsynchronously(plugin, this, delay, period);
    }

    public boolean isRunning() {
        return scheduler.isCurrentlyRunning(this.task.getTaskId());
    }

    public void cancel() {
        scheduler.cancelTask(this.task.getTaskId());
    }

    public static void init(Plugin plugin) {
        Scheduler.plugin = plugin;
        Scheduler.scheduler = plugin.getServer().getScheduler();
    }

}
