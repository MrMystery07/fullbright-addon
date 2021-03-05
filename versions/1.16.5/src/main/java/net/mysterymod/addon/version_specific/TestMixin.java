package net.mysterymod.addon.version_specific;

import net.minecraft.client.Minecraft;
import net.mysterymod.addon.MysteryModAddon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class TestMixin {
  private boolean inited = false;
  @Inject(method = "q", at = @At(value = "HEAD"))
  public void onTick(CallbackInfo callbackInfo){
    if(inited == false && Minecraft.getInstance().player != null){
      inited = true;
      if (MysteryModAddon.config.isEnabled()) {
        Minecraft.getInstance().gameSettings.gamma = MysteryModAddon.config.getSlider();
      }else{
        Minecraft.getInstance().gameSettings.gamma = 0.0F;
      }
    }

    if(MysteryModAddon.hasToUpdate) {
      if (MysteryModAddon.config.isEnabled()) {
        Minecraft.getInstance().gameSettings.gamma = MysteryModAddon.config.getSlider();
      }else{
        Minecraft.getInstance().gameSettings.gamma = 0.0F;
      }
      MysteryModAddon.hasToUpdate = false;
    }
  }

}
