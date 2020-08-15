package sbinnery.common.utility;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import sbinnery.Spinnery;
import sbinnery.common.configuration.registry.ConfigurationRegistry;
import sbinnery.common.registry.ThemeResourceRegistry;

/**
 * A basic implementation of
 * a resource listener for
 * Spinnery's resources/themes.
 */
@Environment(EnvType.CLIENT)
public class ResourceListener implements SimpleSynchronousResourceReloadListener {
	private static final Identifier ID = new Identifier(Spinnery.MOD_ID, "reload_listener");

	@Override
	public void apply(ResourceManager resourceManager) {
		ThemeResourceRegistry.clear();
		ThemeResourceRegistry.load(resourceManager);

		ConfigurationRegistry.load(resourceManager);
	}

	@Override
	public Identifier getFabricId() {
		return ID;
	}
}
