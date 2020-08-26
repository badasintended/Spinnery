package sbinnery.client.integration;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import sbinnery.Spinnery;

@Environment(EnvType.CLIENT)
public class SpinneryModMenu implements ModMenuApi {
	@Override
	public String getModId() {
		return Spinnery.MOD_ID;
	}

	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return (ConfigScreenFactory<Screen>) parent -> new SpinneryConfigurationScreen();
	}
}
