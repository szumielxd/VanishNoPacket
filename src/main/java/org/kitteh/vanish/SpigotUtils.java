package org.kitteh.vanish;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class SpigotUtils {
	
	
	public static boolean canJSON(CommandSender s) {
		try {
			Class.forName("net.md_5.bungee.api.chat.TextComponent");
		}catch(ClassNotFoundException e) {
			return false;
		}
		return (s instanceof Player && Settings.getCanHover() && VanishPerms.canJSON(s));
	}
	
	
	public static void sendHelp(Player p, String str) {
		
		p.sendMessage("§3------------------ "+Bukkit.getPluginManager().getPlugin("VanishNoPacket").getDescription().getFullName()+" ------------------");
		if(canJSON(p)) {
			
			// With hover text
			if(VanishPerms.canVanish(p)) {
				sendCommandUsage(p, "/"+str, "Toggle vanish");
			}
			if(VanishPerms.canSeeHelp(p)) {
				sendCommandUsage(p, "/"+str+" help", "Display help menu");
			}
			if(VanishPerms.canVanish(p)) {
				if(VanishPerms.canVanishOther(p)) sendCommandUsage(p, "/"+str+" on|off [<player>]", "Toggle vanish");
				else sendCommandUsage(p, "/"+str+" on|off", "Toggle vanish");
			}
			if(VanishPerms.canList(p)) {
				sendCommandUsage(p, "/"+str+" list", "List all vanished players");
			}
			if(VanishPerms.canToggleDamageIn(p)||VanishPerms.canToggleDamageOut(p)||VanishPerms.canToggleNoChat(p)||VanishPerms.canToggleNoFollow(p)||VanishPerms.canToggleNoHunger(p)||VanishPerms.canToggleNoInteract(p)||VanishPerms.canToggleNoPickup(p)||VanishPerms.canToggleSee(p)||VanishPerms.canToggleSilentChestReads(p)) {
				if(VanishPerms.canToggleOther(p)) sendCommandUsage(p, "/"+str+" toggle <option> [<player>]", "Toggle option");
				else sendCommandUsage(p, "/"+str+" toggle <option>", "Toggle option");
			}
			if(VanishPerms.canToggleEffectBats(p)||VanishPerms.canToggleEffectExplode(p)||VanishPerms.canToggleEffectFlames(p)||VanishPerms.canToggleEffectLightning(p)||VanishPerms.canToggleEffectSmoke(p)) {
				if(VanishPerms.canToggleEffectOther(p)) sendCommandUsage(p, "/"+str+" effects <effect> [<player>]", "Toggle effect");
				else sendCommandUsage(p, "/"+str+" effects <effect>", "Toggle effect");
			}
			if(VanishPerms.canCheck(p)) {
				if(VanishPerms.canCheckOther(p)) sendCommandUsage(p, "/"+str+" check [<player>]", "Check if player is vanished");
				else sendCommandUsage(p, "/"+str+" check", "Check if you're vanished");
			}
			if(VanishPerms.canReload(p)) {
				sendCommandUsage(p, "/"+str+" reload", "Reload configuration");
			}
			if(VanishPerms.canFakeAnnounce(p)) {
				sendCommandUsage(p, "/"+str+" fakejoin|fakequit", "Toggle vanish with announcement");
			}
		}else {
			
			// Without hover text
			if(VanishPerms.canVanish(p)) {
				p.sendMessage("  §b/"+str+" §7- §fToggle vanish");
			}
			if(VanishPerms.canSeeHelp(p)) {
				p.sendMessage("  §b/"+str+" help §7- §fDisplay help menu");
			}
			if(VanishPerms.canVanish(p)) {
				if(VanishPerms.canVanishOther(p)) p.sendMessage("  /"+str+" on|off [<player>] §7- §fToggle vanish");
				else p.sendMessage("  §b/"+str+" on|off §7- §fToggle vanish");
			}
			if(VanishPerms.canList(p)) {
				p.sendMessage("  §b/"+str+" list §7- §fList all vanished players");
			}
			if(VanishPerms.canToggleDamageIn(p)||VanishPerms.canToggleDamageOut(p)||VanishPerms.canToggleNoChat(p)||VanishPerms.canToggleNoFollow(p)||VanishPerms.canToggleNoHunger(p)||VanishPerms.canToggleNoInteract(p)||VanishPerms.canToggleNoPickup(p)||VanishPerms.canToggleSee(p)||VanishPerms.canToggleSilentChestReads(p)) {
				if(VanishPerms.canToggleOther(p)) p.sendMessage("  §b/"+str+" toggle <option> [<player>] §7- §fToggle option");
				else p.sendMessage("  §b/"+str+" toggle <option> §7- §fToggle option");
			}
			if(VanishPerms.canToggleEffectBats(p)||VanishPerms.canToggleEffectExplode(p)||VanishPerms.canToggleEffectFlames(p)||VanishPerms.canToggleEffectLightning(p)||VanishPerms.canToggleEffectSmoke(p)) {
				if(VanishPerms.canToggleEffectOther(p)) p.sendMessage("  §b/"+str+" effects <effect> [<player>] §7- §fToggle effect");
				else p.sendMessage("  §b/"+str+" effects <effect> §7- §fToggle effect");
			}
			if(VanishPerms.canCheck(p)) {
				if(VanishPerms.canCheckOther(p)) p.sendMessage("  §b/"+str+" check [<player>] §7- §fCheck if player is vanished");
				else p.sendMessage("  §b/"+str+" check §7- §fCheck if you're vanished");
			}
			if(VanishPerms.canReload(p)) {
				p.sendMessage("  §b/"+str+" reload §7- §fReload configuration");
			}
			if(VanishPerms.canFakeAnnounce(p)) {
				p.sendMessage("  §b/"+str+" fakejoin|fakequit §7- §fToggle vanish with announcement");
			}
			/*p.sendMessage("§b/"+str+" §7- §fToggle vanish");
			p.sendMessage("§b/"+str+" help §7- §fDisplay help menu"); //
			p.sendMessage("§b/"+str+" on|off <player> §7- §fToggle player's vanish");
			p.sendMessage("§b/"+str+" list §7- §fList all vanished players");
			p.sendMessage("§b/"+str+" toggle <option> <player> §7- §fToggle player's options");
			p.sendMessage("§b/"+str+" effects <effect> <player> §7- §fToggle player's effects");
			p.sendMessage("§b/"+str+" check <player> §7- §fCheck if player is vanished");
			p.sendMessage("§b/"+str+" reload §7- §fReload configuration");*/
		}
		sendToggleCommands(p);
		p.sendMessage("     ");
	}
	
	
	/*  /np, /nf, /nh, /ni, /nc  */
	private static void sendToggleCommands(Player p) {
		if(canJSON(p)) {
			// NP
			TextComponent text = new TextComponent("   ");
			if(VanishPerms.canToggleNoPickup(p)) {
				TextComponent base = new TextComponent(VanishPerms.canNotPickUp(p)?"§a/np":"§c/np");
				base.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§6/np")));
				base.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/np"));
				text.addExtra(base);
			}else text.addExtra("§7§m/np");
			text.addExtra("§7, ");
			// NF
			if(VanishPerms.canToggleNoFollow(p)) {
				TextComponent base = new TextComponent(VanishPerms.canNotFollow(p)?"§a/nf":"§c/nf");
				base.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§6/nf")));
				base.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/nf"));
				text.addExtra(base);
			}else text.addExtra("§7§m/nf");
			text.addExtra("§7, ");
			// NH
			if(VanishPerms.canToggleNoHunger(p)) {
				TextComponent base = new TextComponent(VanishPerms.canNotHunger(p)?"§a/nh":"§c/nh");
				base.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§6/nh")));
				base.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/nh"));
				text.addExtra(base);
			}else text.addExtra("§7§m/nh");
			text.addExtra("§7, ");
			// NI
			if(VanishPerms.canToggleNoInteract(p)) {
				TextComponent base = new TextComponent(VanishPerms.canNotInteract(p)?"§a/ni":"§c/ni");
				base.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§6/ni")));
				base.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ni"));
				text.addExtra(base);
			}else text.addExtra("§7§m/ni");
			text.addExtra("§7, ");
			// NC
			if(VanishPerms.canToggleNoChat(p)) {
				TextComponent base = new TextComponent(VanishPerms.canNotChat(p)?"§a/nc":"§c/nc");
				base.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§6/nc")));
				base.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/nc"));
				text.addExtra(base);
			}else text.addExtra("§7§m/nc");
			p.spigot().sendMessage(text);
		}else {
			// NP
			StringBuilder sb = new StringBuilder();
			if(VanishPerms.canToggleNoPickup(p)) {
				if(VanishPerms.canNotPickUp(p)) sb.append("§a/np");
				else sb.append("§c/np");
			}else sb.append("§7§m/np");
			sb.append("§7, ");
			// NF
			if(VanishPerms.canToggleNoFollow(p)) {
				if(VanishPerms.canNotFollow(p)) sb.append("§a/nf");
				else sb.append("§c/nf");
			}else sb.append("§7§m/nf");
			sb.append("§7, ");
			// NH
			if(VanishPerms.canToggleNoHunger(p)) {
				if(VanishPerms.canNotHunger(p)) sb.append("§a/nh");
				else sb.append("§c/nh");
			}else sb.append("§7§m/nh");
			sb.append("§7, ");
			// NI
			if(VanishPerms.canToggleNoInteract(p)) {
				if(VanishPerms.canNotInteract(p)) sb.append("§a/ni");
				else sb.append("§c/ni");
			}else sb.append("§7§m/ni");
			sb.append("§7, ");
			// NC
			if(VanishPerms.canToggleNoChat(p)) {
				if(VanishPerms.canNotChat(p)) sb.append("§a/nc");
				else sb.append("§c/nc");
			}else sb.append("§7§m/nc");
			p.sendMessage("   "+sb.toString());
		}
	}
	
	
	private static void sendCommandUsage(Player p, String cmd, String label) {
		
		TextComponent text = new TextComponent("  §b"+cmd+" §7- §f"+label);
		text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§6"+cmd).create()));
		text.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, cmd));
		p.spigot().sendMessage(text);
		
	}
	
	
	public static void sendTryHelp(CommandSender s, String str) {
		if(canJSON(s)) {
			TextComponent text = new TextComponent(Settings.getMessagePrefix()+"§bUnknown argument. Try ");
			TextComponent base = new TextComponent("§f§n/"+str+" help");
			base.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§6/"+str+" help")));
			base.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/"+str+" help"));
			text.addExtra(base);
			text.addExtra(" §bfor more information.");
			((Player) s).spigot().sendMessage(text);
		}else {
			s.sendMessage(Settings.getMessagePrefix()+"§bUnknown argument. try §f/"+str+" help §bfor more information.");
		}
	}
	
	
	public static void sendUsage(CommandSender s, String str) {
		
		if(!canJSON(s) || !(s instanceof Player)) {
			s.sendMessage(Settings.getMessagePrefix()+Settings.getMessageUsage().replaceAll("%usage%", str));
			return;
		}
		TextComponent text = new TextComponent(Settings.getMessagePrefix()+Settings.getMessageUsage().replaceAll("%usage%", str));
		text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§b"+str).create()));
		text.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, str));
		((Player) s).spigot().sendMessage(text);
	}
	
	
	public static void sendToggleList(Player p, String str, CommandSender s) {
		
		if(canJSON(p) && s instanceof Player) {
			TextComponent text = new TextComponent("§3You can toggle: ");
			List<BaseComponent> list = new ArrayList<BaseComponent>();
			boolean same = !p.equals((Player)s);
			if (VanishPerms.canToggleSee(p)) {
				TextComponent option = new TextComponent(VanishPerms.canSeeAll(p)? "§asee" : "§csee");
				option.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(VanishPerms.canSeeAll(p)? "§bClick to §cDisable" : "§bClick to §aEnable")));
				option.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/"+str+" toggle see"+(same?(" "+p.getName()):"")));
				list.add(option);
			}
			if (VanishPerms.canToggleNoPickup(p)) {
				TextComponent option = new TextComponent(VanishPerms.canNotPickUp(p)? "§anopickup" : "§cnopickup");
				option.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(VanishPerms.canNotPickUp(p)? "§bClick to §cDisable" : "§bClick to §aEnable")));
				option.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/"+str+" toggle nopickup"+(same?(" "+p.getName()):"")));
				list.add(option);
			}
			if (VanishPerms.canToggleNoFollow(p)) {
				TextComponent option = new TextComponent(VanishPerms.canNotFollow(p)? "§anofollow" : "§cnofollow");
				option.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(VanishPerms.canNotFollow(p)? "§bClick to §cDisable" : "§bClick to §aEnable")));
				option.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/"+str+" toggle nofollow"+(same?(" "+p.getName()):"")));
				list.add(option);
			}
			if (VanishPerms.canToggleNoInteract(p)) {
				TextComponent option = new TextComponent(VanishPerms.canNotInteract(p)? "§anointeract" : "§cnointeract");
				option.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(VanishPerms.canNotInteract(p)? "§bClick to §cDisable" : "§bClick to §aEnable")));
				option.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/"+str+" toggle nointeract"+(same?(" "+p.getName()):"")));
				list.add(option);
			}
			if (VanishPerms.canToggleDamageIn(p)) {
				TextComponent option = new TextComponent(VanishPerms.blockIncomingDamage(p)? "§adamage-in" : "§cdamage-in");
				option.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(VanishPerms.blockIncomingDamage(p)? "§bClick to §cDisable" : "§bClick to §aEnable")));
				option.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/"+str+" toggle damage-in"+(same?(" "+p.getName()):"")));
				list.add(option);
			}
			if (VanishPerms.canToggleDamageOut(p)) {
				TextComponent option = new TextComponent(VanishPerms.blockOutgoingDamage(p)? "§adamage-out" : "§cdamage-out");
				option.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(VanishPerms.blockOutgoingDamage(p)? "§bClick to §cDisable" : "§bClick to §aEnable")));
				option.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/"+str+" toggle damage-out"+(same?(" "+p.getName()):"")));
				list.add(option);
			}
			if (VanishPerms.canToggleNoChat(p)) {
				TextComponent option = new TextComponent(VanishPerms.canNotChat(p)? "§anochat" : "§cnochat");
				option.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(VanishPerms.canNotChat(p)? "§bClick to §cDisable" : "§bClick to §aEnable")));
				option.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/"+str+" toggle nochat"+(same?(" "+p.getName()):"")));
				list.add(option);
			}
			if (VanishPerms.canToggleNoHunger(p)) {
				TextComponent option = new TextComponent(VanishPerms.canNotHunger(p)? "§anohunger" : "§cnohunger");
				option.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(VanishPerms.canNotHunger(p)? "§bClick to §cDisable" : "§bClick to §aEnable")));
				option.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/"+str+" toggle nohunger"+(same?(" "+p.getName()):"")));
				list.add(option);
			}
			if (VanishPerms.canToggleSilentChestReads(p)) {
				TextComponent option = new TextComponent(VanishPerms.canReadChestsSilently(p)? "§achests" : "§cchests");
				option.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(VanishPerms.canReadChestsSilently(p)? "§bClick to §cDisable" : "§bClick to §aEnable")));
				option.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/"+str+" toggle chests"+(same?(" "+p.getName()):"")));
				list.add(option);
			}
			if(!list.isEmpty()) {
				for(int i=0; i<list.size(); i++) {
					if(i>0) text.addExtra("§3, ");
					text.addExtra(list.get(i));
				}
				((Player) s).spigot().sendMessage(text);
			}else {
				s.sendMessage(Settings.getMessagePrefix() + "§cYou cannot toggle anything");
			}
			return;
		}
		
		
		
		final StringBuilder toggleReply = new StringBuilder();
		if (VanishPerms.canToggleSee(p)) {
			toggleReply.append("§3, ");
			toggleReply.append(VanishPerms.canSeeAll(p)? "§asee§3" : "§csee§3");
		}
		if (VanishPerms.canToggleNoPickup(p)) {
			toggleReply.append("§3, ");
			toggleReply.append(VanishPerms.canNotPickUp(p)? "§anopickup§3" : "§cnopickup§3");
		}
		if (VanishPerms.canToggleNoFollow(p)) {
			toggleReply.append("§3, ");
			toggleReply.append(VanishPerms.canNotFollow(p)? "§anofollow§3" : "§cnofollow§3");
		}
		if (VanishPerms.canToggleNoInteract(p)) {
			toggleReply.append("§3, ");
			toggleReply.append(VanishPerms.canNotInteract(p)? "§anointeract§3" : "§cnointeract§3");
		}
		if (VanishPerms.canToggleDamageIn(p)) {
			toggleReply.append("§3, ");
			toggleReply.append(VanishPerms.blockIncomingDamage(p)? "§adamage-in§3" : "§cdamage-in§3");
		}
		if (VanishPerms.canToggleDamageOut(p)) {
			toggleReply.append("§3, ");
			toggleReply.append(VanishPerms.blockOutgoingDamage(p)? "§adamage-out§3" : "§cdamage-out§3");
		}
		if (VanishPerms.canToggleNoChat(p)) {
			toggleReply.append("§3, ");
			toggleReply.append(VanishPerms.canNotChat(p)? "§anochat§3" : "§cnochat§3");
		}
		if (VanishPerms.canToggleNoHunger(p)) {
			toggleReply.append("§3, ");
			toggleReply.append(VanishPerms.canNotHunger(p)? "§anohunger§3" : "§cnohunger§3");
		}
		if (VanishPerms.canToggleSilentChestReads(p)) {
			toggleReply.append("§3, ");
			toggleReply.append(VanishPerms.canReadChestsSilently(p)? "§achests§3" : "§cchests§3");
		}
		if (toggleReply.length() > 0) {
			toggleReply.replace(0, 4, "");
			toggleReply.insert(0, "§3You can toggle: ");
		} else {
			toggleReply.append("§3You cannot toggle anything");
		}
		s.sendMessage(toggleReply.toString());
	}
	
	
public static void sendEffectList(Player p, String str, CommandSender s) {
		
		if(canJSON(p) && s instanceof Player) {
			boolean same = !p.equals((Player)s);
			TextComponent text = new TextComponent("§3You can toggle: ");
			List<BaseComponent> list = new ArrayList<BaseComponent>();
			if (VanishPerms.canToggleEffectBats(p)) {
				TextComponent option = new TextComponent(VanishPerms.canEffectBats(p)? "§abats" : "§cbats");
				option.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(VanishPerms.canEffectBats(p)? "§bClick to §cDisable" : "§bClick to §aEnable")));
				option.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/"+str+" effects bats"+(same?(" "+p.getName()):"")));
				list.add(option);
			}
			if (VanishPerms.canToggleEffectExplode(p)) {
				TextComponent option = new TextComponent(VanishPerms.canEffectExplode(p)? "§aexplode" : "§cexplode");
				option.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(VanishPerms.canEffectExplode(p)? "§bClick to §cDisable" : "§bClick to §aEnable")));
				option.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/"+str+" effects explode"+(same?(" "+p.getName()):"")));
				list.add(option);
			}
			if (VanishPerms.canToggleEffectFlames(p)) {
				TextComponent option = new TextComponent(VanishPerms.canEffectFlames(p)? "§aflames" : "§cflames");
				option.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(VanishPerms.canEffectFlames(p)? "§bClick to §cDisable" : "§bClick to §aEnable")));
				option.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/"+str+" effects flames"+(same?(" "+p.getName()):"")));
				list.add(option);
			}
			if (VanishPerms.canToggleEffectLightning(p)) {
				TextComponent option = new TextComponent(VanishPerms.canEffectLightning(p)? "§alightning" : "§clightning");
				option.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(VanishPerms.canEffectLightning(p)? "§bClick to §cDisable" : "§bClick to §aEnable")));
				option.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/"+str+" effects lightning"+(same?(" "+p.getName()):"")));
				list.add(option);
			}
			if (VanishPerms.canToggleEffectSmoke(p)) {
				TextComponent option = new TextComponent(VanishPerms.canEffectSmoke(p)? "§asmoke" : "§csmoke");
				option.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(VanishPerms.canEffectSmoke(p)? "§bClick to §cDisable" : "§bClick to §aEnable")));
				option.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/"+str+" effects smoke"+(same?(" "+p.getName()):"")));
				list.add(option);
			}
			if(!list.isEmpty()) {
				for(int i=0; i<list.size(); i++) {
					if(i>0) text.addExtra("§3, ");
					text.addExtra(list.get(i));
				}
				((Player) s).spigot().sendMessage(text);
			}else {
				s.sendMessage(Settings.getMessagePrefix() + "§cYou cannot toggle anything");
			}
			return;
		}		
		
		final StringBuilder toggleReply = new StringBuilder();
		if (VanishPerms.canToggleEffectBats(p)) {
			toggleReply.append("§3, ");
			toggleReply.append(VanishPerms.canEffectBats(p)? "§abats§3" : "§cbats§3");
		}
		if (VanishPerms.canToggleEffectExplode(p)) {
			toggleReply.append("§3, ");
			toggleReply.append(VanishPerms.canEffectExplode(p)? "§aexplode§3" : "§cexplode§3");
		}
		if (VanishPerms.canToggleEffectFlames(p)) {
			toggleReply.append("§3, ");
			toggleReply.append(VanishPerms.canEffectFlames(p)? "§aflames§3" : "§cflames§3");
		}
		if (VanishPerms.canToggleEffectLightning(p)) {
			toggleReply.append("§3, ");
			toggleReply.append(VanishPerms.canEffectLightning(p)? "§alightning§3" : "§clightning§3");
		}
		if (VanishPerms.canToggleEffectSmoke(p)) {
			toggleReply.append("§3, ");
			toggleReply.append(VanishPerms.canEffectSmoke(p)? "§asmoke§3" : "§csmoke§3");
		}
		if (toggleReply.length() > 0) {
			toggleReply.replace(0, 4, "");
			toggleReply.insert(0, "§3You can toggle: ");
		} else {
			toggleReply.append("§3You cannot toggle anything");
		}
		s.sendMessage(toggleReply.toString());
	}
	

}
