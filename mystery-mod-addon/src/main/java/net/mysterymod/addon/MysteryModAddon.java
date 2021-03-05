package net.mysterymod.addon;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import net.mysterymod.mod.addon.Addon;

import java.util.logging.Logger;

@Singleton
public class MysteryModAddon extends Addon {
  public static Injector injector;
  public static FullbrightConfig config;
  public static boolean hasToUpdate = false;

  @Inject
  public MysteryModAddon(Injector injector1){
    injector = injector1;
  }

  public static void onSliderChanged(){
    hasToUpdate = true;
    System.out.println(config.getSlider());
  }

  @Override
  public void onEnable() {
    config = injector.getInstance(FullbrightConfig.class);
    setSettingsProvider(injector.getInstance(FullbrightProvider.class));
  }
}
