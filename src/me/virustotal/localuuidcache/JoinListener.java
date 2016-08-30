package me.virustotal.localuuidcache;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public void onLogin(PlayerJoinEvent e)
	{
			Player player = e.getPlayer();
			UUID uuid = player.getUniqueId();
			if(!UUIDApi.containsUUID(uuid))
			{
				UUIDApi.addUUID(player);
			}
			else if(!UUIDApi.nameFromUUID(uuid).equals(player.getName()))
			{
				UUIDApi.updateName(player);
			}
		}
}