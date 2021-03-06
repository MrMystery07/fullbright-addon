package net.mysterymod.fullbright.version_specific;

import net.minecraft.client.Minecraft;
import net.mysterymod.fullbright.FullbrightAddon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {
  private boolean inited = false;

  @Inject(method = "runTick", at = @At(value = "HEAD"), cancellable = true)
  public void configureGammaValue(CallbackInfo callbackInfo){
    if(inited == false){
      inited = true;
      if (FullbrightAddon.config.isEnabled()) {
        Minecraft.getMinecraft().gameSettings.gammaSetting = FullbrightAddon.config.getSlider();
      }else{
        Minecraft.getMinecraft().gameSettings.gammaSetting = 0.0F;
      }
    }
    if(FullbrightAddon.hasToUpdate) {
      if (FullbrightAddon.config.isEnabled()) {
        Minecraft.getMinecraft().gameSettings.gammaSetting = FullbrightAddon.config.getSlider();
      }else{
        Minecraft.getMinecraft().gameSettings.gammaSetting = 0.0F;
      }
      FullbrightAddon.hasToUpdate = false;
    }
  }

}
