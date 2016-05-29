package bq_standard.client.gui.tasks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.math.MathHelper;
import betterquesting.client.gui.GuiQuesting;
import betterquesting.client.gui.misc.GuiEmbedded;
import betterquesting.client.themes.ThemeRegistry;
import betterquesting.utils.RenderUtils;
import bq_standard.tasks.TaskMeeting;

public class GuiTaskMeeting extends GuiEmbedded
{
	TaskMeeting task;
	Entity target;
	
	public GuiTaskMeeting(TaskMeeting task, GuiQuesting screen, int posX, int posY, int sizeX, int sizeY)
	{
		super(screen, posX, posY, sizeX, sizeY);
		this.task = task;
	}

	@Override
	public void drawGui(int mx, int my, float partialTick)
	{
		if(target != null)
		{
			GlStateManager.pushMatrix();
			
			GlStateManager.scale(1F, 1F, 1F);
			GlStateManager.color(1F, 1F, 1F, 1F);
			
			float angle = ((float)Minecraft.getSystemTime()%30000F)/30000F * 360F;
			float scale = 64F;
			
			if(target.height * scale > (sizeY - 48))
			{
				scale = (sizeY - 48)/target.height;
			}
			
			if(target.width * scale > sizeX)
			{
				scale = sizeX/target.width;
			}
			
			try
			{
				RenderUtils.RenderEntity(posX + sizeX/2, posY + sizeY/2 + MathHelper.ceiling_float_int(target.height/2F*scale) + 8, (int)scale, angle, 0F, target);
			} catch(Exception e)
			{
			}
			
			GlStateManager.popMatrix();
		} else
		{
			if(EntityList.NAME_TO_CLASS.containsKey(task.idName))
			{
				target = EntityList.createEntityByName(task.idName, screen.mc.theWorld);
				target.readFromNBT(task.targetTags);
			}
		}
		
		String tnm = !task.ignoreNBT && target != null? target.getName() : task.idName;
		String txt = I18n.format("bq_standard.gui.meet", tnm);
		screen.mc.fontRendererObj.drawString(txt, posX + sizeX/2 - screen.mc.fontRendererObj.getStringWidth(txt)/2, posY, ThemeRegistry.curTheme().textColor().getRGB());
	}
	
}
