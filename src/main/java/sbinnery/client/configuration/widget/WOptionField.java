package sbinnery.client.configuration.widget;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import sbinnery.client.render.BaseRenderer;
import sbinnery.common.configuration.data.ConfigurationHolder;
import sbinnery.widget.WTextField;
import sbinnery.widget.api.Filter;

@Environment(EnvType.CLIENT)
public class WOptionField extends WTextField {
	public ConfigurationHolder holder = null;

	public ConfigurationHolder getHolder() {
		return holder;
	}

	public <W extends WOptionField> W setHolder(ConfigurationHolder<?> holder) {
		this.holder = holder;
		return (W) this;
	}

	public <W extends WOptionField> W save() {
		holder.setValue(filter.toValue(text));
		return (W) this;
	}

	@Override
	public void onMouseClicked(float mouseX, float mouseY, int mouseButton) {
		if (isWithinBounds(mouseX, mouseY) && Screen.hasControlDown() && filter == Filter.BOOLEAN_FILTER) {
			if (text.startsWith("t") && !text.endsWith("rue")) {
				setText("true");
			} else if (text.startsWith("f") && !text.endsWith("alse")) {
				setText("false");
			} else if (text.equals("true")) {
				setText("false");
			} else if (text.equals("false")) {
				setText("true");
			}
		} else {
			super.onMouseClicked(mouseX, mouseY, mouseButton);
		}
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void draw(MatrixStack matrices, VertexConsumerProvider.Immediate provider) {
		if (isHidden()) {
			return;
		}

		float x = getX();
		float y = getY();
		float z = getZ();

		float sX = getWidth();
		float sY = getHeight();

		if (filter == Filter.BOOLEAN_FILTER) {
			if (text.equals("true")) {
				BaseRenderer.drawBeveledPanel(matrices, provider, x, y, z, sX, sY, getStyle().asColor("top_left"), getStyle().asColor("background.boolean_true"), getStyle().asColor("bottom_right"));
			} else if (text.equals("false")) {
				BaseRenderer.drawBeveledPanel(matrices, provider, x, y, z, sX, sY, getStyle().asColor("top_left"), getStyle().asColor("background.boolean_false"), getStyle().asColor("bottom_right"));
			} else {
				BaseRenderer.drawBeveledPanel(matrices, provider, x, y, z, sX, sY, getStyle().asColor("top_left"), getStyle().asColor("background.incomplete"), getStyle().asColor("bottom_right"));
			}
		} else {
			if (text.isEmpty()) {
				BaseRenderer.drawBeveledPanel(matrices, provider, x, y, z, sX, sY, getStyle().asColor("top_left"), getStyle().asColor("background.incomplete"), getStyle().asColor("bottom_right"));
			} else {
				BaseRenderer.drawBeveledPanel(matrices, provider, x, y, z, sX, sY, getStyle().asColor("top_left"), getStyle().asColor("background.complete"), getStyle().asColor("bottom_right"));
			}
		}

		renderField(matrices, provider);
	}
}
