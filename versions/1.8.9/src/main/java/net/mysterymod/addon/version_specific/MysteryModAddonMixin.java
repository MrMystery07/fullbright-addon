package net.mysterymod.addon.version_specific;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.mysterymod.addon.MysteryModAddon;
import net.mysterymod.mod.MysteryMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MysteryModAddonMixin {
  private boolean inited = false;

  @Inject(method = "s", at = @At(value = "HEAD"), cancellable = true)
  public void onTick(CallbackInfo callbackInfo){
    if(inited == false){
      inited = true;
      if (MysteryModAddon.config.isEnabled()) {
        Minecraft.getMinecraft().gameSettings.gammaSetting = MysteryModAddon.config.getSlider();
      }else{
        Minecraft.getMinecraft().gameSettings.gammaSetting = 0.0F;
      }
    }
    if(MysteryModAddon.hasToUpdate) {
      if (MysteryModAddon.config.isEnabled()) {
        Minecraft.getMinecraft().gameSettings.gammaSetting = MysteryModAddon.config.getSlider();
      }else{
        Minecraft.getMinecraft().gameSettings.gammaSetting = 0.0F;
      }
      MysteryModAddon.hasToUpdate = false;
    }
  }

}
