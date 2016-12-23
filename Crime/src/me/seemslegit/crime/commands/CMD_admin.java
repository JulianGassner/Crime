package me.seemslegit.crime.commands;

import me.seemslegit.crime.Messages;
import me.seemslegit.crime.playerapi.User;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_admin implements CommandExecutor{

	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cs instanceof Player){
			Player p = (Player) cs;
			if(!p.hasPermission(Main.admin_permission)){
				
				return false;
			}
			if(args.length > 4){
				if(args[0].equalsIgnoreCase("coins")){
					if(args.length == 4){
						if(args[1].equalsIgnoreCase("add")){
							User b = new User(args[2]);
							b.addCoins(Integer.parseInt(args[3]));
							p.sendMessage(Messages.prefix+"§aYou successfully set the coins of §6"+b.getName()+" §ato §6"+b.getCoins());
						}else if(args[1].equalsIgnoreCase("remove")){
							User b = new User(args[2]);
							b.removeCoins(Integer.parseInt(args[3]));
							p.sendMessage(Messages.prefix+"§aYou successfully set the coins of §6"+b.getName()+" §ato §6"+b.getCoins());
						}else if(args[1].equalsIgnoreCase("reset")){
							User b = new User(args[2]);
							b.resetCoins();
							p.sendMessage(Messages.prefix+"§aYou successfully resetted the coins of §6"+b.getName());
						}
					}else{
						p.sendMessage("§7> §6/admin coins add/remove [Player] [Amount]");
					}
				}else if(args[0].equalsIgnoreCase("forceswitch")){
					if(args.length == 1){
						User pu = new User(p);
						pu.resetCrime();
						pu.resetJailTime();
						Main.instance.getCopManager().switchCop(pu);
						p.sendMessage(Messages.prefix+"§aYou successfully forceswitched.");
					}else if(args.length == 2){
						User b = new User(args[1]);
						b.resetCrime();
						b.resetJailTime();
						Main.instance.getCopManager().switchCop(b);
						p.sendMessage(Messages.prefix+"§aYou successfully forceswitched player §6"+b.getName()+" §a to cop.");
						
					}else{
						p.sendMessage("§7> §6/admin forceswitch (Player)");
					}
				}
			}else{
				printArguments(p);
			}
		}else{
			cs.sendMessage("You have to be a player");
		}
		
		return true;
	}
	
	private void printArguments(Player p){
		p.sendMessage("§7> §6/admin coins add/remove [Player] [Amount]");
		p.sendMessage("§7> §6/admin forceswitch (Player)");
	}

}
