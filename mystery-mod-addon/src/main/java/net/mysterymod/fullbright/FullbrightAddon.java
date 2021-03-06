package net.mysterymod.fullbright;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import net.mysterymod.mod.addon.Addon;

@Singleton
public class FullbrightAddon extends Addon {
  public static Injector injector;
  public static FullbrightConfig config;
  public static boolean hasToUpdate = false;

  @Inject
  public FullbrightAddon(Injector injector1){
    injector = injector1;
  }

  public static void onSliderChanged(){
    hasToUpdate = true;
  }

  @Override
  public void onEnable() {
    config = injector.getInstance(FullbrightConfig.class);
    setSettingsProvider(injector.getInstance(FullbrightProvider.class));
    hasToUpdate = true;
  }
}
