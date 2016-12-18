package me.seemslegit.crime.cop;

import me.seemslegit.crime.Messages;
import me.seemslegit.crime.api.PlayerCache;
import me.seemslegit.crime.items.CrimeItem;
import me.seemslegit.crime.managment.ItemManager;
import me.seemslegit.crime.playerapi.User;
import me.seemslegit.crime.playerapi.UserBase;
import me.seemslegit.crime.plugin.Main;
import net.minecraft.server.v1_8_R3.Material;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class CopItems implements Listener {

	/**
	 * 
	 * @param p
	 *            {@link UserBase}
	 */
	public static void giveCopItems(UserBase u) {

		Player p = u.getPlayer();
		if(p == null) return;
		
		ItemManager im = Main.instance.getItemManager();

		ItemStack copsword = im.getItem("copsword");
		ItemStack handcuffs = im.getItem("handcuffs");
		ItemStack shears = im.getItem("uncuff");
		ItemStack tortch = im.getItem("cit");
		ItemStack tnt = im.getItem("c4");

		new PlayerCache(p).clearPlayer();
		
		p.getInventory().setItem(0, copsword);
		p.getInventory().setItem(1, handcuffs);
		p.getInventory().setItem(2, shears);
		p.getInventory().setItem(3, tortch);
		p.getInventory().setItem(4, tnt);
		p.updateInventory();

	}

	/**
	 * 
	 * @param e {@link PlayerInteractEntityEvent}
	 */
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e) {
		if (!(e.getRightClicked() instanceof Player))
			return;

		Player t = (Player) e.getRightClicked();
		Player p = e.getPlayer();
		ItemStack inhand = p.getItemInHand();
		String name = CrimeItem.getCrimeName(inhand);
		if (name == null)
			return;

		if (name.equalsIgnoreCase("handcuffs")) {
			p.sendMessage(Messages.prefix+"§aYou successfully cuffed §6"+t.getName());
			Main.instance.getCopManager().cuff(new User(t));
		} else if (name.equalsIgnoreCase("copsword")) {
			if(Main.instance.getCopManager().isCuffed(new User(t)) == true){
				UserBase b = new User(t);
				Main.instance.getJailManager().sendToJail(b);
			}

		} else if (name.equalsIgnoreCase("uncuff")) {
			p.sendMessage(Messages.prefix+"§cYou successfully uncuffed §6"+t.getName());
			Main.instance.getCopManager().uncuff(new User(t));
			
		}else if (name.equalsIgnoreCase("cit")){
			p.sendMessage(Messages.prefix+"§aYou successfully §cremoved all illegal items §aof §6"+t.getName());
			Main.instance.getCopManager().removeIllegalItems(new User(t));
			
		}
	}
	/**
	 * 
	 * @param e {@link BlockPlaceEvent}
	 */
	@SuppressWarnings("deprecation")
	public void onBlockPlace(BlockPlaceEvent e){
		
		if(e.getBlock().getType().equals(Material.TNT)){
			Location block = e.getBlock().getLocation();
			block.getBlock().setTypeId(0);
			e.getPlayer().getWorld().spawnEntity(block, EntityType.PRIMED_TNT);
			UserBase u = new User(e.getPlayer());
			if(!u.isCop()){
				u.addCrime(50);
			}
		}
	}

}
