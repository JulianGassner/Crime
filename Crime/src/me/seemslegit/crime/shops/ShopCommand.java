package me.seemslegit.crime.shops;

import me.seemslegit.crime.Messages;
import me.seemslegit.crime.plugin.Main;
import me.seemslegit.crime.regions.Region;
import me.seemslegit.crime.shops.api.Shop;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShopCommand implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd,
			String label, String[] args) {
		
		if(!(sender instanceof Player)) return true;
		
		Player p = (Player) sender;
		
		if(!p.hasPermission(Main.admin_permission)) {
			p.sendMessage(Messages.no_permissions);
			return true;
		}
		
		if(args.length == 0) {
			
			p.sendMessage("§8> §e/shop create");
			
			return true;
		}
		
		String arg = args[0];
		
		if(arg.equalsIgnoreCase("create")) {
			
			if(args.length < 4) {
				p.sendMessage("§8> §e/shop create <Region> <ID> <Name>");
				return true;
			}

			String region = args[1];
			
			String id = args[2];
			
			String name = "";
			
			for(int i = 3;i<args.length;i++) name += " " + args[i];
			
			name = name.replaceFirst(" ", "");
			
			Region r = Main.instance.getRegionManager().getRegion(p.getWorld(), region);
			
			if(r == null) {
				
				p.sendMessage("§8> §cRegion '" + args[1] + "' not found.");
				
				return true;
			}
			
			new Shop(id, p.getLocation(), r).setName(name);
			Main.instance.getShopManager().load();
			
			p.sendMessage("§8> §eShop '" + id + "' created.");
			
		}else{
			p.sendMessage(Messages.invalid_argument);
		}
		
		return true;
	}

	
	
}
