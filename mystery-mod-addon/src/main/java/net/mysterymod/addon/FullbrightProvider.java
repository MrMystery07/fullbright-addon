package net.mysterymod.addon;

import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import net.mysterymod.mod.addon.AddonSettingsProvider;
import net.mysterymod.mod.gui.settings.SettingsGui;
import net.mysterymod.mod.gui.settings.component.SettingsComponent;
import net.mysterymod.mod.gui.settings.component.SettingsComponentProvider;
import net.mysterymod.mod.gui.settings.component.slider.SlimSliderComponent;
import net.mysterymod.mod.gui.settings.component.toggle.ToggleComponent;

import java.util.List;

public class FullbrightProvider implements AddonSettingsProvider {

  public FullbrightConfig config;

  public FullbrightProvider(){
    config = MysteryModAddon.config;
  }

  @Override
  public void addSettings(
    SettingsGui settingsGui,
    SettingsComponentProvider settingsComponentProvider,
    List<SettingsComponent> components) {
    components.add(
      ToggleComponent.create(
        "Fullbright",
        null,
        toggleState -> {
          // Toggle listener
          config.setEnabled(toggleState);
          config.saveConfig();
          MysteryModAddon.onSliderChanged();
        },
        config.isEnabled()
      ));

    int minValue = 1;
    int maxValue = 100;
    int steps = 1;
    components.add(
      new SlimSliderComponent(
        "Helligkeit",
        null,
        minValue,
        maxValue,
        steps,
        config.getSlider(),
        newValue -> {
          config.setSlider(newValue.floatValue());
          config.saveConfig();
          MysteryModAddon.onSliderChanged();
        }));
  }
}
