/*
 * Copyright (c) bdew, 2013
 * https://github.com/bdew/gendustry
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * https://raw.github.com/bdew/gendustry/master/MMPL-1.0.txt
 */

package net.bdew.gendustry.machines.apiary

import net.bdew.lib.gui.widgets.Widget
import net.bdew.lib.gui.{Point, Rect}
import net.minecraft.client.renderer.texture.TextureMap
import scala.collection.mutable
import net.bdew.lib.Misc
import net.bdew.gendustry.Gendustry
import net.bdew.gendustry.gui.Textures

class WidgetError(x: Int, y: Int, apiary: TileApiary) extends Widget {
  val rect: Rect = new Rect(x, y, 16, 16)
  override def draw() {
    val err = apiary.errorState.cval
    if (err == -1) {
      bindTexture(Textures.texture)
      parent.drawTexturedModalRect(rect.x, rect.y, Textures.texturePowerError.x, Textures.texturePowerError.y, rect.w, rect.h)
    } else {
      bindTexture(TextureMap.locationItemsTexture)
      if (ErrorCodes.isValid(err)) {
        parent.drawTexturedModelRectFromIcon(rect.x, rect.y, ErrorCodes.getIcon(err), 16, 16)
      } else {
        parent.drawTexturedModelRectFromIcon(rect.x, rect.y, ErrorCodes.getIcon(0), 16, 16)
      }
    }
  }
  override def handleTooltip(p: Point, tip: mutable.MutableList[String]) {
    val err = apiary.errorState.cval
    if (err == -1) {
      tip += Misc.toLocal(Gendustry.modId + ".error.power")
    } else if (ErrorCodes.isValid(err)) {
      tip += ErrorCodes.getDescription(err)
    } else {
      tip += ErrorCodes.getDescription(0)
    }
    apiary.addStats(tip)
  }
}
