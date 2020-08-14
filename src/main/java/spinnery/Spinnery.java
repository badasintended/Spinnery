package spinnery;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import spinnery.common.configuration.registry.ConfigurationRegistry;
import spinnery.common.registry.NetworkRegistry;
import spinnery.debug.DebugCommands;
import spinnery.debug.DebugScreenHandlers;

public class Spinnery implements ModInitializer {
	public static final String LOG_ID = "Spinnery";
	public static final String MOD_ID = "spinnery";
	public static Logger LOGGER = LogManager.getLogger(LOG_ID);

	public static final EnvType ENVIRONMENT = FabricLoader.getInstance().getEnvironmentType();

	@Override
	public void onInitialize() {
		NetworkRegistry.initialize();
		ConfigurationRegistry.initialize();

		if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
			DebugScreenHandlers.initialize();
			DebugCommands.initialize();
		}
	}
}
