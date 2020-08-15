package sbinnery.client.rei;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.DisplayHelper;
import me.shedaniel.rei.api.plugins.REIPluginV0;
import net.minecraft.util.Identifier;
import sbinnery.Spinnery;
import sbinnery.client.screen.BaseHandledScreen;
import sbinnery.common.handler.BaseScreenHandler;

public class SpinneryPlugin implements REIPluginV0 {
	private static final Identifier IDENTIFIER = new Identifier(Spinnery.MOD_ID, "rei_plugin");

	@Override
	public Identifier getPluginIdentifier() {
		return IDENTIFIER;
	}

	@Override
	public void registerBounds(DisplayHelper displayHelper) {
		displayHelper.registerHandler(new DisplayHelper.DisplayBoundsProvider<BaseHandledScreen<BaseScreenHandler>>() {
			@Override
			public Class<?> getBaseSupportedClass() {
				return BaseHandledScreen.class;
			}

			@Override
			public Rectangle getScreenBounds(BaseHandledScreen<BaseScreenHandler> screen) {
				screen.updateDimensions();
				return new Rectangle(screen.getMinX(), screen.getMinY(), screen.getMaxX() - screen.getMinX(), screen.getMaxY() - screen.getMinY());
			}

			@Override
			public float getPriority() {
				return 16;
			}
		});
	}
}
