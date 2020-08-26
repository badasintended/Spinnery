package sbinnery.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sbinnery.client.screen.InGameHudScreen;
import sbinnery.widget.WInterface;

/**
 * Injections into InGameHudScreen to
 * allow for addition of Spinner widgets.
 */
@Mixin(InGameHud.class)
@Environment(EnvType.CLIENT)
public class InGameHudMixin implements InGameHudScreen.Accessor {

	private final WInterface sbinnery$hudInterface = new WInterface();

	@Inject(method = "<init>", at = @At("RETURN"))
	public void onInitialize(MinecraftClient client, CallbackInfo ci) {
		InGameHudScreen.onInitialize(sbinnery$getInGameHud());
	}

	@Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;F)V", at = @At("RETURN"))
	public void renderInterfaces(MatrixStack matrices, float f, CallbackInfo callbackInformation) {
		VertexConsumerProvider.Immediate provider = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();

		sbinnery$hudInterface.draw(matrices, provider);

		provider.draw();
	}

	@Override
	public WInterface sbinnery$getInterface() {
		return sbinnery$hudInterface;
	}

	@Override
	public InGameHud sbinnery$getInGameHud() {
		return (InGameHud) (Object) this;
	}

}
