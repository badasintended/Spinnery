package sbinnery.widget;

import com.google.common.collect.Lists;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import sbinnery.client.render.BaseRenderer;
import sbinnery.client.render.TextRenderer;

import java.util.List;

@Environment(EnvType.CLIENT)
public class WButton extends WAbstractButton {
	@Override
	public void draw(MatrixStack matrices, VertexConsumerProvider.Immediate provider) {
		if (isHidden()) {
			return;
		}

		matrices.push();

		if (isLowered()) {
			BaseRenderer.drawBeveledPanel(matrices, provider, getX(), getY(), getZ(), getWidth(), getHeight(), getStyle().asColor("top_left.on"), getStyle().asColor("background.on"), getStyle().asColor("bottom_right.on"));
		} else {
			BaseRenderer.drawBeveledPanel(matrices, provider, getX(), getY(), getZ(), getWidth(), getHeight(), getStyle().asColor("top_left.off"), getStyle().asColor("background.off"), getStyle().asColor("bottom_right.off"));
		}

		if (hasLabel()) {
			TextRenderer.pass().text(getLabel()).at(getX() + (getWidth() / 2 - TextRenderer.width(getLabel()) / 2), getY() + (getHeight() / 2 - 4), getZ())
					.shadow(getStyle().asBoolean("label.shadow")).shadowColor(getStyle().asColor("label.shadow_color"))
					.color(getStyle().asColor("label.color")).render(matrices, provider);
		}

		matrices.pop();

		super.draw(matrices, provider);
	}

	@Override
	public List<Text> getTooltip() {
		return Lists.newArrayList();
	}

	@Override
	public boolean isFocusedMouseListener() {
		return true;
	}
}
