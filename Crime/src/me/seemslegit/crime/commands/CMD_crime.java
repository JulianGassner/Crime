package me.seemslegit.crime.commands;

import me.seemslegit.crime.Messages;
import me.seemslegit.crime.playerapi.User;
import me.seemslegit.crime.playerapi.UserBase;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_crime implements CommandExecutor{

	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(!(cs instanceof Player)){
			cs.sendMessage("You have to be a Player");
			return true;
		}
		Player p = (Player) cs;
		
		if(!p.hasPermission(Main.admin_permission) || args.length == 0) {
			Main.instance.getCopManager().printInfos(p, new User(p));
			return true;
		}
		
		if(args.length >= 3){
			//mindest länge 3
			if(args[0].equalsIgnoreCase("add")){
				UserBase u = new User(args[1]);
				if(isInteger(args[2])){
					u.addCrime(Integer.parseInt(args[2]));
					p.sendMessage(Messages.prefix+"§aYou successfully added crime to"+u.getName());
				}else{
					p.sendMessage(Messages.prefix+"§4You need to enter a crime amount.");
				}
			}else if(args[0].equalsIgnoreCase("remove")){
				UserBase u = new User(args[1]);
				if(isInteger(args[2])){
					u.removeCrime(Integer.parseInt(args[2]));
					p.sendMessage(Messages.prefix+"§aYou successfully removed crime from"+u.getName());
				}else{
					p.sendMessage(Messages.prefix+"§4You need to enter a crime amount.");
				}
			}
		}else{
			//mindest länge 1
			if(args[0].equalsIgnoreCase("reset")){
				if(args.length == 2){
					UserBase u = new User(args[1]);
					u.resetCrime();
					p.sendMessage(Messages.prefix+"§aYou successfully reseted the crime of"+u.getName());
				}else{
					p.sendMessage("§7> §c/crime reset [UserName]");
				}
			}else if(args[0].equalsIgnoreCase("help")) {
				sendArguments(p);
			}else{
				
				p.sendMessage(Messages.invalid_argument);
				
			}
		}
		return true;
	}

	private boolean isInteger(String str) {
		
		try{
			Integer.parseInt(str);
		}catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	private void sendArguments(Player p){
		p.sendMessage("§7> §6/crime remove/add [Player] [Amount]");
		p.sendMessage("§7> §6/crime reset [Player]");
	}
	
}
