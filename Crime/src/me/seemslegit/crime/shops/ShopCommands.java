package me.seemslegit.crime.shops;

import java.util.HashMap;

import me.seemslegit.crime.Messages;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class ShopCommands implements CommandExecutor{
	private HashMap<String, Location> pos1 = new HashMap<String, Location>();
	private HashMap<String, Location> pos2 = new HashMap<String, Location>();
	
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(!(cs instanceof Player)){
			cs.sendMessage("You have to be a player.");
		}
		Player p = (Player) cs;
		if(args[0].equalsIgnoreCase("pos1")){
			if(p.hasPermission("crime.admin")){
				pos1.put(p.getUniqueId().toString(), p.getLocation());
			}else{
				p.sendMessage(Messages.no_permissions);
			}
		}
		else if(args[0].equalsIgnoreCase("create")){
			if(args.length == 2){
				Main.instance.getShopManager().createShop(pos1.get(p.getName()), pos2.get(p.getName()), args[1]);
			}else{
				p.sendMessage(Messages.error);
			}
		}
		else if(args[0].equalsIgnoreCase("pos2")){
			if(p.hasPermission("crime.admin")){
				pos2.put(p.getUniqueId().toString(), p.getLocation());
			}else{
				p.sendMessage(Messages.no_permissions);
			}
		}else{
			p.sendMessage(Messages.error);
			
		}
		return false;
	}

}
