package de.ersterstreber.onlyonename;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import de.ersterstreber.onlyonename.listeners.PlayerLoginListener;

public class OnlyOneName extends JavaPlugin {

	private static File pfile;
	private static YamlConfiguration pconfig;
	
	@Override
	public void onEnable() {
		pfile = new File("plugins/OnlyOneName/", "uuid.yml");
		pconfig = YamlConfiguration.loadConfiguration(pfile);
		
		Bukkit.getPluginManager().registerEvents(new PlayerLoginListener(), this);
	}
	
	public static boolean hasName(String uuid) {
		if (pconfig.contains(uuid)) return true;
		return false;
	}
	
	public static String getNameByUUID(String uuid) {
		if (hasName(uuid)) {
			return getUUIDs().getString(uuid);
		}
		return null;
	}
	
	public static void setName(String uuid, String name) {
		if (!hasName(uuid)) {
			getUUIDs().addDefault(uuid, name);
			getUUIDs().options().copyDefaults(true);
			saveUUIDs();
			return;
		}
		getUUIDs().set(uuid, name);
		saveUUIDs();
	}
	
	public static YamlConfiguration getUUIDs() {
		return pconfig;
	}
	
	public static void saveUUIDs() {
		try {
			pconfig.save(pfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
