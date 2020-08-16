package sbinnery.mixin;

import net.minecraft.client.font.GlyphRenderer;
import net.minecraft.client.render.RenderLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GlyphRenderer.class)
public interface GlyphRendererAccessor {

	@Accessor
	RenderLayer getTextLayer();

	@Accessor
	RenderLayer getSeeThroughTextLayer();

	@Accessor
	float getUMin();

	@Accessor
	float getUMax();

	@Accessor
	float getVMin();

	@Accessor
	float getVMax();

	@Accessor
	float getXMin();

	@Accessor
	float getXMax();

	@Accessor
	float getYMin();

	@Accessor
	float getYMax();

}
