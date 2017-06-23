package org.zonedabone.commandsigns.handler;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.PluginManager;
import org.zonedabone.commandsigns.CommandSigns;
import org.zonedabone.commandsigns.SignExecutor;
import org.zonedabone.commandsigns.proxy.CommandSenderProxy;
import org.zonedabone.commandsigns.proxy.PlayerProxy;

public class CommandHandler extends Handler {

	@Override
	public void handle(SignExecutor e, String command, boolean silent,
			boolean negate) {
		if (command.startsWith("/") || command.startsWith("\\")) {
			boolean op = false;
			PermissionAttachment grant = null;
			Player player = e.getPlayer();
			plugin = e.getPlugin();
			if (command.startsWith("/")) {
				command = command.substring(1);
				if (command.length() == 0) {
					return;
				}
				if (player != null) {
					try {
						if (command.startsWith("*")) {
							command = command.substring(1);
							if (plugin.hasPermission(player,
									"commandsigns.use.super", false)) {
								// Give player access to the '*' permission node
								// temporarily
								if (!CommandSigns.permission.playerHas(player,
										"*")) {
									grant = player.addAttachment(plugin, "*", true);
								}
								run(plugin, player, command, silent);
							} else {
								if (!silent)
									plugin.messenger.sendMessage(player,
											"cannot_use");
								return;
							}
						} else if (command.startsWith("^")) {
							command = command.substring(1);
							if (plugin.hasPermission(player,
									"commandsigns.use.super", false)) {
								if (!player.isOp()) {
									op = true;
									player.setOp(true);
								}
								run(plugin, player, command, silent);
							} else {
								if (!silent)
									plugin.messenger.sendMessage(player,
											"cannot_use");
								return;
							}
						} else if (command.startsWith("#")) {
							command = command.substring(1);
							if (plugin.hasPermission(player,
									"commandsigns.use.super", false)) {
								ConsoleCommandSender ccs = plugin.getServer()
										.getConsoleSender();
								CommandSender cs = new CommandSenderProxy(ccs,
										player, silent);
								plugin.getServer().dispatchCommand(cs, command);
							} else {
								if (!silent)
									plugin.messenger.sendMessage(player,
											"cannot_use");
								return;
							}
						} else {
							run(plugin, player, command, silent);
						}
					} finally {
						if (grant != null)
							player.removeAttachment(grant);
						if (op)
							player.setOp(false);
					}
				} else {
					if (command.startsWith("*") || command.startsWith("^")
							|| command.startsWith("#")) {
						command = command.substring(1);
					}
					ConsoleCommandSender ccs = plugin.getServer()
							.getConsoleSender();
					CommandSender cs = new CommandSenderProxy(ccs, silent);
					plugin.getServer().dispatchCommand(cs, command);
				}
			}
		}
	}

	private void run(CommandSigns plugin, Player p, String command,
			boolean silent) {
		p = new PlayerProxy(p, silent);
		PluginManager pm = Bukkit.getPluginManager();
		PlayerCommandPreprocessEvent e = new PlayerCommandPreprocessEvent(p,
				"/" + command);
		pm.callEvent(e);
		if (!e.isCancelled()) {
			Bukkit.dispatchCommand(p, command);
		}
	}

}
