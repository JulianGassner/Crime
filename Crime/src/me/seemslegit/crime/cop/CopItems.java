package me.seemslegit.crime.cop;

import me.seemslegit.crime.api.PlayerCache;
import me.seemslegit.crime.items.CrimeItem;
import me.seemslegit.crime.managment.ItemManager;
import me.seemslegit.crime.playerapi.User;
import me.seemslegit.crime.playerapi.UserBase;
import me.seemslegit.crime.plugin.Main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
			Main.instance.getCopManager().cuff(new User(t));
		} else if (name.equalsIgnoreCase("copsword")) {

		} else if (name.equalsIgnoreCase("uncuff")) {
			Main.instance.getCopManager().uncuff(new User(t));
		}
	}

}
