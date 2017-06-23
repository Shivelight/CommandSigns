package org.zonedabone.commandsigns.listener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.gravitydevelopment.updater.Updater;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.zonedabone.commandsigns.CommandSigns;
import org.zonedabone.commandsigns.util.PlayerState;
import org.zonedabone.commandsigns.util.SignText;

public class CommandListener implements CommandExecutor {

	private CommandSigns plugin;

	public CommandListener(CommandSigns plugin) {
		this.plugin = plugin;
	}

	protected boolean add(final CommandSender sender, Player player,
			int lineNumber, String[] args) {
		if (player == null) {
			plugin.messenger.sendMessage(sender, "failure.player_only");
		}
		if (plugin.hasPermission(player, "commandsigns.create.regular")) {
			clipboard(sender, player, lineNumber, 1, args);
			if (plugin.playerStates.get(player) != PlayerState.EDIT) {
				plugin.playerStates.put(player, PlayerState.ENABLE);
				plugin.messenger.sendMessage(player, "progress.add");
			}
		} else {
			plugin.messenger.sendMessage(player, "failure.no_perms");
		}
		return true;
	}

	protected boolean batch(final CommandSender sender, Player player,
			String[] args) {
		PlayerState ps = plugin.playerStates.get(player);
		if (ps == null) {
			plugin.messenger.sendMessage(player, "failure.not_in_mode");
			return false;
		}
		switch (ps) {
		case REMOVE:
			player.sendMessage("Switched to batch remove mode.");
			ps = PlayerState.BATCH_REMOVE;
			break;
		case BATCH_REMOVE:
			player.sendMessage("Switched to single remove mode.");
			ps = PlayerState.REMOVE;
			break;
		case ENABLE:
			player.sendMessage("Switched to batch enable mode.");
			ps = PlayerState.BATCH_ENABLE;
			break;
		case BATCH_ENABLE:
			player.sendMessage("Switched to single enable mode.");
			ps = PlayerState.ENABLE;
			break;
		case READ:
			player.sendMessage("Switched to batch read mode.");
			ps = PlayerState.BATCH_READ;
			break;
		case BATCH_READ:
			player.sendMessage("Switched to single read mode.");
			ps = PlayerState.READ;
			break;
		case TOGGLE:
			player.sendMessage("Switched to batch toggle mode.");
			ps = PlayerState.BATCH_TOGGLE;
			break;
		case BATCH_TOGGLE:
			player.sendMessage("Switched to single toggle mode.");
			ps = PlayerState.TOGGLE;
			break;
		case REDSTONE:
			player.sendMessage("Switched to batch redstone mode.");
			ps = PlayerState.BATCH_REDSTONE;
			break;
		case BATCH_REDSTONE:
			player.sendMessage("Switched to single redstone mode.");
			ps = PlayerState.REDSTONE;
			break;
		default:
			plugin.messenger.sendMessage(player, "failure.no_batch");
		}
		plugin.playerStates.put(player, ps);
		return true;
	}

	protected boolean clear(final CommandSender sender, Player player,
			String[] args) {
		if (player == null) {
			plugin.messenger.sendMessage(sender, "failure.player_only");
		}
		if (plugin.hasPermission(player, "commandsigns.remove")) {
			PlayerState ps = plugin.playerStates.get(player);
			if (ps == PlayerState.EDIT || ps == PlayerState.EDIT_SELECT) {
				finishEditing(player);
			}
			plugin.playerStates.remove(player);
			plugin.playerText.remove(player);
			plugin.messenger.sendMessage(player, "success.cleared");
		} else {
			plugin.messenger.sendMessage(player, "failure.no_perms");
		}
		return true;
	}

	private void clipboard(final CommandSender sender, Player player,
			int lineNumber, int textStart, String[] args) {
		if (lineNumber < 1) {
			plugin.messenger.sendMessage(player, "failure.invalid_line");
		} else {
			if (plugin.playerStates.get(player) == PlayerState.EDIT_SELECT) {
				plugin.messenger.sendMessage(player, "failure.must_select");
			}
			SignText text = plugin.playerText.get(player);
			if (text == null) {
				text = new SignText(player.getName(), false);
				plugin.playerText.put(player, text);
			}
			String line = StringUtils.join(args, " ", textStart, args.length);
			if (line.startsWith("/*")
					&& !plugin.hasPermission(player,
							"commandsigns.create.super", false)) {
				plugin.messenger.sendMessage(player, "failure.no_super");
			}
			if ((line.startsWith("/^") || line.startsWith("/#"))
					&& !plugin.hasPermission(player, "commandsigns.create.op",
							false)) {
				plugin.messenger.sendMessage(player, "failure.no_op");
			}
			text.setLine(lineNumber, line);
			plugin.messenger.sendRaw(player, "success.line_print",
					new String[] { "NUMBER", "LINE" }, new String[] {
							"" + lineNumber, line });
		}
	}

	protected boolean copy(final CommandSender sender, Player player,
			String[] args) {
		if (player == null) {
			plugin.messenger.sendMessage(sender, "failure.player_only");
		}
		if (plugin.hasPermission(player, "commandsigns.create.regular")) {
			PlayerState ps = plugin.playerStates.get(player);
			if (ps == PlayerState.EDIT || ps == PlayerState.EDIT_SELECT) {
				finishEditing(player);
			}
			plugin.playerStates.put(player, PlayerState.COPY);
			plugin.messenger.sendMessage(player, "progress.copy");
		} else {
			plugin.messenger.sendMessage(player, "failure.no_perms");
		}
		return true;
	}

	protected boolean edit(final CommandSender sender, Player player,
			String[] args) {
		if (plugin.hasPermission(sender, "commandsigns.edit", false)) {
			PlayerState ps = plugin.playerStates.get(player);
			if (ps == PlayerState.EDIT_SELECT || ps == PlayerState.EDIT) {
				finishEditing(player);
			} else {
				plugin.playerStates.put(player, PlayerState.EDIT_SELECT);
				plugin.playerText.remove(player);
				plugin.messenger.sendMessage(player, "progress.select_sign");
			}
		}
		return true;
	}

	public void finishEditing(Player player) {
		plugin.playerStates.remove(player);
		plugin.playerText.remove(player);
		plugin.messenger.sendMessage(player, "success.done_editing");
	}

	protected boolean insert(final CommandSender sender, Player player,
			int lineNumber, String[] args) {
		if (player == null) {
			plugin.messenger.sendMessage(sender, "failure.player_only");
		}
		if (plugin.hasPermission(player, "commandsigns.create.regular")) {
			clipboard(sender, player, lineNumber, 2, args);
			if (plugin.playerStates.get(player) != PlayerState.EDIT) {
				plugin.playerStates.put(player, PlayerState.INSERT);
				plugin.messenger.sendMessage(player, "progress.add");
			}
		} else {
			plugin.messenger.sendMessage(player, "failure.no_perms");
		}
		return true;
	}

	@Override
	public boolean onCommand(final CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("commandsigns")) {
			if (args.length < 1 || args[0].equalsIgnoreCase("help")) {
				// Messaging.sendMessage(sender, "usage");
				return false;
			}
			Player tp = null;
			if (sender instanceof Player) {
				tp = (Player) sender;
			}
			final Player player = tp;
			String command = args[0].toLowerCase();
			Pattern pattern = Pattern.compile("(line|l)?(\\d+)");
			Matcher matcher = pattern.matcher(command);
			if (matcher.matches()) {
				return add(sender, player, Integer.parseInt(matcher.group(2)),
						args);
			} else if (command.equals("batch")) {
				return batch(sender, player, args);
			} else if (command.equals("clear")) {
				return clear(sender, player, args);
			} else if (command.equals("copy")) {
				return copy(sender, player, args);
			} else if (command.equals("edit")) {
				return edit(sender, player, args);
			} else if (command.equals("insert") && args.length > 1) {
				pattern = Pattern.compile("(line|l)?(\\d+)");
				matcher = pattern.matcher(args[1].toLowerCase());
				if (matcher.matches())
					return insert(sender, player,
							Integer.parseInt(matcher.group(2)), args);
			} else if (command.equals("read")) {
				return read(sender, player, args);
			} else if (command.equals("redstone")) {
				return redstone(sender, player, args);
			} else if (command.equals("reload")) {
				return reload(sender, player, args);
			} else if (command.equals("remove")) {
				return remove(sender, player, args);
			} else if (command.equals("save")) {
				return save(sender, player, args);
			} else if (command.equals("toggle")) {
				return toggle(sender, player, args);
			} else if (command.equals("update")) {
				return update(sender, player, args);
			} else if (command.equals("view")) {
				return view(sender, player, args);
			} else {
				plugin.messenger.sendMessage(sender, "failure.wrong_syntax");
				return true;
			}
		}
		return false;
	}

	protected boolean read(final CommandSender sender, Player player,
			String[] args) {
		if (player == null) {
			plugin.messenger.sendMessage(sender, "failure.player_only");
		}
		if (plugin.hasPermission(player, "commandsigns.create.regular")) {
			PlayerState ps = plugin.playerStates.get(player);
			if (ps == PlayerState.EDIT || ps == PlayerState.EDIT_SELECT) {
				finishEditing(player);
			}
			plugin.playerStates.put(player, PlayerState.READ);
			plugin.messenger.sendMessage(player, "progress.read");
		} else {
			plugin.messenger.sendMessage(player, "failure.no_perms");
		}
		return true;
	}

	protected boolean redstone(final CommandSender sender, Player player,
			String[] args) {
		if (player == null) {
			plugin.messenger.sendMessage(sender, "failure.player_only");
		}
		if (plugin.hasPermission(player, "commandsigns.create.redstone")) {
			PlayerState ps = plugin.playerStates.get(player);
			if (ps == PlayerState.EDIT || ps == PlayerState.EDIT_SELECT) {
				finishEditing(player);
			}
			plugin.playerStates.put(player, PlayerState.REDSTONE);
			plugin.messenger.sendMessage(player, "progress.redstone");
		} else {
			plugin.messenger.sendMessage(player, "failure.no_perms");
		}
		return true;
	}

	protected boolean reload(final CommandSender sender, Player player,
			String[] args) {
		if (plugin.hasPermission(sender, "commandsigns.reload", false)) {
			plugin.load();
			plugin.messenger.sendMessage(sender, "success.reloaded");
		} else {
			plugin.messenger.sendMessage(player, "failure.no_perms");
		}
		return true;
	}

	protected boolean remove(final CommandSender sender, Player player,
			String[] args) {
		if (player == null) {
			plugin.messenger.sendMessage(sender, "failure.player_only");
		}
		if (plugin.hasPermission(player, "commandsigns.remove")) {
			PlayerState ps = plugin.playerStates.get(player);
			if (ps == PlayerState.EDIT || ps == PlayerState.EDIT_SELECT) {
				finishEditing(player);
			}
			plugin.playerStates.put(player, PlayerState.REMOVE);
			plugin.messenger.sendMessage(player, "progress.remove");
		} else {
			plugin.messenger.sendMessage(player, "failure.no_perms");
		}
		return true;
	}

	protected boolean save(final CommandSender sender, Player player,
			String[] args) {
		if (plugin.hasPermission(sender, "commandsigns.save", false)) {
			plugin.loader.saveFile();
			plugin.messenger.sendMessage(sender, "success.saved");
		}
		return true;
	}

	protected boolean toggle(final CommandSender sender, Player player,
			String[] args) {
		if (player == null) {
			plugin.messenger.sendMessage(sender, "failure.player_only");
		}
		if (plugin.hasPermission(player, "commandsigns.toggle")) {
			PlayerState ps = plugin.playerStates.get(player);
			if (ps == PlayerState.EDIT || ps == PlayerState.EDIT_SELECT) {
				finishEditing(player);
			}
			plugin.playerStates.put(player, PlayerState.TOGGLE);
			plugin.messenger.sendMessage(player, "progress.toggle");
		} else {
			plugin.messenger.sendMessage(player, "failure.no_perms");
		}
		return true;
	}

	protected boolean update(final CommandSender sender, Player player, String[] args) {
        if (plugin.hasPermission(sender, "commandsigns.update")) {
            Updater updater = plugin.updater;
            if (updater == null) updater = new Updater(plugin, plugin.getBukkitId(), plugin.getFile(), Updater.UpdateType.NO_DOWNLOAD, false);
            
            if (updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE) {
                if (args.length > 0 && args[0].equals("check")) {
                    
                    // Check only
                    plugin.messenger.sendMessage(player, "update.notify", new String[] { "VERSION" }, new String[] { updater.getLatestName() });
                    
                } else {
                    
                    // Update
                    plugin.messenger.sendMessage(player, "update.start");
                    double time = System.currentTimeMillis();
                    Updater downloader = new Updater(plugin, plugin.getBukkitId(), plugin.getFile(), Updater.UpdateType.NO_VERSION_CHECK, false);
                    switch(downloader.getResult()) {
                        case SUCCESS:
                            plugin.messenger.sendMessage(player, "update.finish", new String[] { "VERSION", "TIME" }, new String[] { downloader.getLatestName(), new Double((System.currentTimeMillis() - time) / 1000).toString() });
                            break;
                        case NO_UPDATE:
                            plugin.messenger.sendMessage(player, "update.up_to_date", new String[] { "VERSION" }, new String[] { downloader.getLatestName() });
                            break;
                        case DISABLED:
                            plugin.messenger.sendMessage(player, "update.fetch_error", new String[] { "ERROR" }, new String[] { "updater is disabled in configuration (plugins/Updater/config.yml)."});
                            break;
                        case FAIL_DOWNLOAD:
                            plugin.messenger.sendMessage(player, "update.fetch_error", new String[] { "ERROR" }, new String[] { "failed to download."});
                            break;
                        case FAIL_DBO:
                            plugin.messenger.sendMessage(player, "update.fetch_error", new String[] { "ERROR" }, new String[] { "unable to contact Bukkit Dev at this time."});
                            break;
                        case FAIL_NOVERSION:
                            plugin.messenger.sendMessage(player, "update.fetch_error", new String[] { "ERROR" }, new String[] { "unable to check latest version."});
                            break;
                        case FAIL_BADID:
                            plugin.messenger.sendMessage(player, "update.fetch_error", new String[] { "ERROR" }, new String[] { "the plugin was not found on Bukkit Dev!"});
                            break;
                        case FAIL_APIKEY:
                            plugin.messenger.sendMessage(player, "update.fetch_error", new String[] { "ERROR" }, new String[] { "the API key provided is invalid (plugins/Updater/config.yml)."});
                            break;
                        default:
                            plugin.messenger.sendMessage(player, "update.fetch_error", new String[] { "ERROR" }, new String[] { "I have no idea what just happened."});
                            break;
                    }
                    
                }  
            } else {
                plugin.messenger.sendMessage(player, "update.up_to_date", new String[] { "VERSION" }, new String[] { updater.getLatestGameVersion() });
            }
        }
        return true;
    }

	protected boolean view(final CommandSender sender, Player player,
			String[] args) {
		if (player == null) {
			plugin.messenger.sendMessage(sender, "failure.player_only");
		}
		if (plugin.hasPermission(player, "commandsigns.create.regular")) {
			SignText text = plugin.playerText.get(player);
			if (text == null) {
				player.sendMessage("No text in clipboard");
			} else {
				int i = 1;
				for (String s : text) {
					if (!s.equals("")) {
						player.sendMessage(i + ": " + s);
					}
					i++;
				}
			}
			plugin.playerStates.remove(player);
		} else {
			plugin.messenger.sendMessage(player, "failure.no_perms");
		}
		return true;
	}

}
