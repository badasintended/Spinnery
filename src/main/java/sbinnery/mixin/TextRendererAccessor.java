package sbinnery.mixin;

import net.minecraft.client.font.FontStorage;
import net.minecraft.client.font.TextHandler;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Function;

@Mixin(TextRenderer.class)
public interface TextRendererAccessor {

	@Accessor
	Function<Identifier, FontStorage> getFontStorageAccessor();

	@Accessor
	TextHandler getHandler();

}
