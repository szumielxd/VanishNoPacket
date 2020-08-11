package org.kitteh.vanish;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public final class Settings {
	private static boolean enablePermTest;
	private static String fakeQuit;
	private static String fakeJoin;
	private static boolean autoFakeJoinSilent;
	private static boolean worldChangeCheck;
	private static int lightningEffectCount;
	private static boolean useActionBar;
	private static int actionBarCooldown;
	private static String actionBarMessage;
	private static String placeholderIsVanished;
	private static String placeholderIsNotVanished;
	private static String placeholderOtherIsVanished;
	private static String placeholderOtherIsNotVanished;
	private static String placeholderListSpacer;
	private static String placeholderListNick;
	private static boolean canHover;
	private static String messagePrefix;
	private static String messageUsage;

	private static final int confVersion = 7; // Tracking config version

	public static boolean getAutoFakeJoinSilent() {
		return Settings.autoFakeJoinSilent;
	}
	public static boolean getEnablePermTest() {
		return Settings.enablePermTest;
	}
	public static String getFakeJoin() {
		return Settings.fakeJoin;
	}
	public static String getFakeQuit() {
		return Settings.fakeQuit;
	}
	public static int getLightningCount() {
		return Settings.lightningEffectCount;
	}
	public static boolean getWorldChangeCheck() {
		return Settings.worldChangeCheck;
	}	
	public static boolean isUsingActionBar() {
		return Settings.useActionBar;
	}
	
	public static int getActionBarCooldown() {
		return Settings.actionBarCooldown;
	}
	public static String getActionBarMessage() {
		return Settings.actionBarMessage;
	}
	
	public static String getPlaceholderIsVanished() {
		return Settings.placeholderIsVanished;
	}	
	public static String getPlaceholderIsNotVanished() {
		return Settings.placeholderIsNotVanished;
	}
	public static String getPlaceholderOtherIsVanished() {
		return Settings.placeholderOtherIsVanished;
	}	
	public static String getPlaceholderOtherIsNotVanished() {
		return Settings.placeholderOtherIsNotVanished;
	}	
	public static String getPlaceholderListSpacer() {
		return Settings.placeholderListSpacer;
	}	
	public static String getPlaceholderListNick() {
		return Settings.placeholderListNick;
	}
	
	public static boolean getCanHover() {
		return Settings.canHover;
	}
	public static String getMessagePrefix() {
		return Settings.messagePrefix;
	}
	public static String getMessageUsage() {
		return Settings.messageUsage;
	}
	

	static void freshStart(VanishPlugin plugin) {
		final FileConfiguration config = plugin.getConfig();
		config.options().copyDefaults(true);
		final int ver = config.getInt("configVersionDoNotTouch.SeriouslyThisWillEraseYourConfig", 0);
		if (ver != Settings.confVersion) {
			plugin.getLogger().info("Attempting to update your configuration. Check to make sure it's ok");
			if (ver < 1) {
				config.set("hooks.spoutcraft", config.getBoolean("spoutcraft.enable", true));
				config.set("spoutcraft.enable", null);
				config.set("spoutcraft", null);
			}
			if ((ver <= 1) || config.contains("permtest.enable")) {
				final boolean permtest = config.getBoolean("permtest.enable", false);
				config.set("permtest.enable", null);
				config.set("permtest", permtest);
				config.set("enableColoration", null);
				config.set("enableTabControl", null);
				final boolean updates = config.getBoolean("updates.check", true);
				config.set("updates.check", null);
				config.set("checkupdates", updates);
			}
			if ((ver <= 3)) {
				config.set("effects.lightning.count", 30);
			}
			if ((ver <= 4)) {
				config.set("colornametags", true);
			}
			if ((ver <= 5)) {
				config.set("actionbar.enabled", true);
				config.set("actionbar.enabled", 20);
				config.set("actionbar.enabled", "&3You're vanished");
				ConfigurationSection section = config.createSection("placeholderapi.placeholders");
				section.set("is_vanished", "&3&o[&b&oV&3&o]");
				section.set("is_not_vanished", "");
				section.set("other_is_vanished", "&aYes");
				section.set("other_is_not_vanished", "&4No");
				section.set("list_spacer", "&7, ");
				section.set("list_nick", "&b%player%");
			}
			if((ver <= 6)) {
				config.set("messages.json", true);
				config.set("messages.prefix", "&3[&bVanish&3] ");
				config.set("messages.usage", "&cCorrect usage: %usage%");
			}
			config.set("configVersionDoNotTouch.SeriouslyThisWillEraseYourConfig", Settings.confVersion);
			plugin.saveConfig();
		}
		Settings.enablePermTest = config.getBoolean("permtest", false);
		Settings.fakeJoin = ChatColor.translateAlternateColorCodes('&', config.getString("fakeannounce.join", "%p joined the game."));
		Settings.fakeQuit = ChatColor.translateAlternateColorCodes('&', config.getString("fakeannounce.quit", "%p left the game."));
		Settings.autoFakeJoinSilent = config.getBoolean("fakeannounce.automaticforsilentjoin", false);
		Settings.worldChangeCheck = config.getBoolean("permissionsupdates.checkonworldchange", false);
		Settings.lightningEffectCount = config.getInt("effects.lightning.count", 30);
		Settings.useActionBar = config.getBoolean("actionbar.enabled", true);
		Settings.actionBarCooldown = config.getInt("actionbar.cooldown", 20);
		Settings.actionBarMessage = config.getString("actionbar.message", "&3You're vanished");
		ConfigurationSection section = plugin.getConfig().getConfigurationSection("placeholderapi.placeholders");
		Settings.placeholderIsVanished = section.getString("is_vanished", "&o&3[&b&oV&o&3]");
		Settings.placeholderIsNotVanished = section.getString("is_not_vanished", "");
		Settings.placeholderOtherIsVanished = section.getString("other_is_vanished", "&aYes");
		Settings.placeholderOtherIsNotVanished = section.getString("other_is_not_vanished", "&4No");
		Settings.placeholderListSpacer = section.getString("list_spacer", "&7, ");
		Settings.placeholderListNick = section.getString("list_nick", "&b%player%");
		Settings.canHover = config.getBoolean("messages.json", false);
		Settings.messagePrefix = ChatColor.translateAlternateColorCodes('&', config.getString("messages.prefix", "&3[&bVanish&3] "));
		Settings.messageUsage = ChatColor.translateAlternateColorCodes('&', config.getString("messages.usage", "&cCorrect usage: %usage%"));
		if (Settings.lightningEffectCount < 1) {
			Settings.lightningEffectCount = 1;
		}
		if (config.getBoolean("debug", false)) {
			Debuggle.itsGoTime(plugin);
		} else {
			Debuggle.nah();
		}
	}
}