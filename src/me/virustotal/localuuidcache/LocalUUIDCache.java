package me.virustotal.localuuidcache;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class LocalUUIDCache extends JavaPlugin {
	
	private File playerDataFolder;
	private File cachedUUIDFile;
	private static LocalUUIDCache plugin;
	public Logger logger;
	
	@SuppressWarnings("deprecation")
	@Override
	public void onEnable()
	{
		plugin = this;
		this.logger = this.getLogger();
		this.playerDataFolder = new File(Bukkit.getWorlds().get(0).getWorldFolder().getPath(), "playerdata");
		this.cachedUUIDFile = new File(this.getDataFolder(), "uuids.dat");
		Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
		UUIDApi.loadUUIDS(this, this.cachedUUIDFile, this.playerDataFolder.listFiles());
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new Runnable()
		{
			@Override
			public void run()
			{
				logger.log(Level.INFO, "UUIDS saved to file!");
				UUIDApi.saveData(cachedUUIDFile);
			}
		}, 1L, 72000L);
	}
	
	@Override
	public void onDisable()
	{
		UUIDApi.saveData(this.cachedUUIDFile);
	}
	
	protected static LocalUUIDCache getInstance()
	{
		return plugin;
	}
}