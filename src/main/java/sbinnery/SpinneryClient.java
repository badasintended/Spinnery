package sbinnery;

import net.fabricmc.api.ClientModInitializer;
import sbinnery.client.integration.SpinneryConfigurationScreen;
import sbinnery.common.registry.NetworkRegistry;
import sbinnery.common.registry.ThemeResourceRegistry;
import sbinnery.common.registry.WidgetRegistry;

public class SpinneryClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		NetworkRegistry.initializeClient();
		WidgetRegistry.initialize();
		ThemeResourceRegistry.initialize();

		SpinneryConfigurationScreen.initialize();
	}
}
