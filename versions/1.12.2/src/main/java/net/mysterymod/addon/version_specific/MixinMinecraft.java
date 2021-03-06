package net.mysterymod.addon.version_specific;

import net.minecraft.client.Minecraft;
import net.mysterymod.addon.MysteryModAddonMain;
import net.mysterymod.mod.MysteryMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {
  private boolean inited = false;

  @Inject(method = "runTick", at = @At(value = "HEAD"))
  public void configureGammaValue(CallbackInfo callbackInfo){

    if(inited == false){
      inited = true;
      if (MysteryModAddonMain.config.isEnabled()) {
        Minecraft.getMinecraft().gameSettings.gammaSetting = MysteryModAddonMain.config.getSlider();
      }else{
        Minecraft.getMinecraft().gameSettings.gammaSetting = 0.0F;
      }
    }

    if(MysteryModAddonMain.hasToUpdate) {
      if (MysteryModAddonMain.config.isEnabled()) {
        Minecraft.getMinecraft().gameSettings.gammaSetting = MysteryModAddonMain.config.getSlider();
      }else{
        Minecraft.getMinecraft().gameSettings.gammaSetting = 0.0F;
      }
      MysteryModAddonMain.hasToUpdate = false;
    }
  }

}
