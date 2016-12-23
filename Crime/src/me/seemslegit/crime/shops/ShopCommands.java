package me.seemslegit.crime.shops;

import me.seemslegit.crime.Messages;
import me.seemslegit.crime.plugin.Main;
import me.seemslegit.crime.regions.RegionManager;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class ShopCommands implements CommandExecutor{
	
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(!(cs instanceof Player)){
			cs.sendMessage("You have to be a player.");
			return true;
		}
			
		Player p = (Player) cs;
		
		RegionManager rm = Main.instance.getRegionManager();

		if(!p.hasPermission(Main.admin_permission)){ 
			p.sendMessage(Messages.no_permissions);
			return true;
		}
		
		if(args.length == 0) {
			
			p.sendMessage("§8> §e/shop pos1");
			p.sendMessage("§8> §e/shop pos2");
			p.sendMessage("§8> §e/shop create");
			
		}else if(args[0].equalsIgnoreCase("pos1")){
			rm.setPos1(p, p.getLocation());
		}else if(args[0].equalsIgnoreCase("create")){
			if(args.length > 1){
				Location pos1 = rm.getPos1(p);
				Location pos2 = rm.getPos2(p);
				if(pos1 == null || pos2 == null) {
					p.sendMessage("§8> §cYou have to set both locations! (pos1/pos2)");
					return true;
				}
				Main.instance.getShopManager().createShop(pos1, pos2, args[1]);
				p.sendMessage("§dShop created");
			}else{
				p.sendMessage("§8> §e/shop create <Name>");
			}
		}else if(args[0].equalsIgnoreCase("pos2")){
			rm.setPos2(p, p.getLocation());
		}else{
			p.sendMessage(Messages.error);
		}
		return false;
	}

}
