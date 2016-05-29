package bq_standard.client.gui.rewards;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;
import betterquesting.client.gui.GuiQuesting;
import betterquesting.client.gui.misc.GuiEmbedded;
import betterquesting.client.themes.ThemeRegistry;
import bq_standard.rewards.RewardCommand;

public class GuiRewardCommand extends GuiEmbedded
{
	RewardCommand reward;
	
	public GuiRewardCommand(RewardCommand reward, GuiQuesting screen, int posX, int posY, int sizeX, int sizeY)
	{
		super(screen, posX, posY, sizeX, sizeY);
		this.reward = reward;
	}
	
	@Override
	public void drawGui(int mx, int my, float partialTick)
	{
		String txt1 = I18n.format("advMode.command");
		String txt2 = TextFormatting.ITALIC + (reward.hideCmd? "[HIDDEN]" : reward.command);
		
		screen.mc.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		TextureAtlasSprite blockSprite = screen.mc.getTextureMapBlocks().getAtlasSprite("minecraft:blocks/command_block_front");
		blockSprite = blockSprite != null? blockSprite : screen.mc.getTextureMapBlocks().getAtlasSprite("missingno");
		
		GlStateManager.pushMatrix();
		GlStateManager.scale(2F, 2F, 1F);
		screen.drawTexturedModalRect(posX/2, (posY + sizeY/2 - 16)/2, blockSprite, 16, 16);
		GlStateManager.popMatrix();
		
		GlStateManager.color(1F, 1F, 1F);
		screen.mc.fontRendererObj.drawString(txt1, posX + 40, posY + sizeY/2 - 16, ThemeRegistry.curTheme().textColor().getRGB());
		screen.mc.fontRendererObj.drawString(screen.mc.fontRendererObj.trimStringToWidth(txt2, sizeX - (32 + 8)), posX + 40, posY + sizeY/2, ThemeRegistry.curTheme().textColor().getRGB());
	}
}
