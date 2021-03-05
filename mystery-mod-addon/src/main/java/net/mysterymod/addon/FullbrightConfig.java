package net.mysterymod.addon;

import com.google.inject.Singleton;
import lombok.Getter;
import lombok.Setter;
import net.mysterymod.mod.config.GsonConfig;

import java.io.File;

@Setter
@Getter
@Singleton
public class FullbrightConfig extends GsonConfig {

  public boolean enabled;
  public float slider;

  public FullbrightConfig() {
    super(new File("MysteryMod/fullbright.json"));
    this.initialize();
  }
}
