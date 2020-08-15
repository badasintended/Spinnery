package sbinnery.widget;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;
import sbinnery.Spinnery;

@Environment(EnvType.CLIENT)
public final class WKibbyImage extends WStaticImage {
	public WKibbyImage() {
		setTexture(new Identifier(Spinnery.MOD_ID, "textures/kirby.png"));
	}
}
