package de.ersterstreber.onlyonename.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import de.ersterstreber.onlyonename.OnlyOneName;

public class PlayerLoginListener implements Listener {

	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		if (OnlyOneName.hasName(e.getPlayer().getUniqueId().toString())) {
			String name = OnlyOneName.getNameByUUID(e.getPlayer().getUniqueId().toString());
			if (e.getPlayer().getName().equalsIgnoreCase(name)) {
				e.allow();
				return;
			} else {
				e.disallow(Result.KICK_OTHER, "§cYou already play on this server with another name!");
				return;
			}
		} else {
			OnlyOneName.setName(e.getPlayer().getUniqueId().toString(), e.getPlayer().getName());
			e.allow();
			return;
		}
	}
	
}
