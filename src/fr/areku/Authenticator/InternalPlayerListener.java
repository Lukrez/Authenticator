package fr.areku.Authenticator;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class InternalPlayerListener implements Listener {
	private OfflineMode controller;
	public InternalPlayerListener(OfflineMode controller){
		this.controller = controller;
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (controller.isUsingOfflineModePlugin()) {
			Authenticator.d("Player " + event.getPlayer().getName()
					+ " loggedin, but using Offline-Mode");
			controller.watchPlayerLogin(event.getPlayer().getName());
			controller.enableTimer();
		}
	}
	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onPlayerQuitEvent(PlayerQuitEvent event) {
		if (controller.isUsingOfflineModePlugin()) {
			if(controller.isWatchingPlayer(event.getPlayer().getName()))
			{
				controller.removeWatchedPlayer(event.getPlayer().getName());
			}
		}
	}
	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onPlayerMoveEvent(PlayerMoveEvent event) {
	}
}
  