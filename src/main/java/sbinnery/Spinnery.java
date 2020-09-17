package sbinnery;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sbinnery.common.configuration.registry.ConfigurationRegistry;
import sbinnery.common.registry.NetworkRegistry;

public class Spinnery implements ModInitializer {
	public static final String LOG_ID = "sbinnery";
	public static final String MOD_ID = "sbinnery";
	public static Logger LOGGER = LogManager.getLogger(LOG_ID);

	public static final EnvType ENVIRONMENT = FabricLoader.getInstance().getEnvironmentType();

	@Override
	public void onInitialize() {
		NetworkRegistry.initialize();
		ConfigurationRegistry.initialize();
	}
}
