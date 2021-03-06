package logisticspipes.items;

import java.util.List;

import logisticspipes.interfaces.IItemAdvancedExistance;
import logisticspipes.proxy.SimpleServiceLocator;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidStack;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LogisticsLiquidContainer extends LogisticsItem implements IItemAdvancedExistance {
	static int capacity = 8000;
	
	public LogisticsLiquidContainer(int i) {
		super(i);
	}

	@Override
	public boolean canExistInNormalInventory(ItemStack stack) {
		return false;
	}

	@Override
	public boolean canExistInWorld(ItemStack stack) {
		return false;
	}

	@Override
    @SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconReg) {
		this.itemIcon = iconReg.registerIcon("logisticspipes:liquids/empty");
	}
	
	@Override
	public int getItemStackLimit() {
		return 1;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			LiquidStack stack = SimpleServiceLocator.logisticsLiquidManager.getLiquidFromContainer(par1ItemStack);
			if(stack != null) {
				par3List.add("Type:  " + stack.asItemStack().getDisplayName());
				par3List.add("Value: " + stack.amount + "mB");
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void getSubItems(int par1, CreativeTabs ct, List list) {
		//Don't add to creative in any way
	}
}
