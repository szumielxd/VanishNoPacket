package org.kitteh.vanish.hooks.plugins;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.kitteh.vanish.Settings;
import org.kitteh.vanish.VanishPlugin;
import org.kitteh.vanish.hooks.Hook;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class PlaceholderAPIHook extends Hook {

	private final VanishPlugin plugin;
	private PlaceholderExpansion expansion;

	public PlaceholderAPIHook(VanishPlugin plugin) {
		super(plugin);
		this.plugin = plugin;
	}

	@Override
	public void onDisable() {
		try {
			PlaceholderAPI.unregisterExpansion(expansion);
		} catch (NoSuchMethodError e) {
			try {
				expansion.getClass().getMethod("unregister").invoke(expansion);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void onEnable() {
		final Plugin grab = this.plugin.getServer().getPluginManager().getPlugin("PlaceholderAPI");
		if (grab != null) {
			this.expansion = new PlaceholderExpansion() {

				@Override
				public String getAuthor() {
					return plugin.getDescription().getAuthors().toString();
				}

				@Override
				public String getIdentifier() {
					return "vanishnopacket";
				}

				@Override
				public String getVersion() {
					return plugin.getCurrentVersion();
				}
				
				@Override
				public boolean persist() {
					return true;
				}
				
				@Override
				public String onPlaceholderRequest(Player p, String param) {
					
					if(param.equals("is_vanished")) {
						if(plugin.getManager().isVanished(p)) return Settings.getPlaceholderIsVanished();
						else return Settings.getPlaceholderIsNotVanished();
					}
					if(param.startsWith("is_vanished_")) {
						param = param.replaceFirst("is_vanished_", "");
						Player pl = Bukkit.getPlayerExact(param);
						if(pl != null && plugin.getManager().isVanished(pl)) return Settings.getPlaceholderOtherIsVanished();
						else return Settings.getPlaceholderOtherIsNotVanished();
					}
					if(param.startsWith("count")) {
						return plugin.getManager().numVanished()+"";
					}
					if(param.equals("list")) {
						Set<String> list = plugin.getManager().getVanishedPlayers();
						if(list.isEmpty()) return "";
						StringBuilder sb = new StringBuilder();
						for(String str : list) {
							sb.append(Settings.getPlaceholderListSpacer());
							sb.append(Settings.getPlaceholderListNick().replaceAll("%player%", str));
						}
						String str = sb.toString();
						str = str.replaceFirst(Settings.getPlaceholderListSpacer(), "");
						return str;
					}
					
					return param;
				}
				
			};
			expansion.register();
			this.plugin.getLogger().info("Now hooking into PlaceholderAPI");
		} else {
			this.plugin.getLogger().info("You wanted PlaceholderAPI support. I could not find PlaceholderAPI.");
		}
	}

}