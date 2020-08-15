package sbinnery.widget;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import sbinnery.client.render.BaseRenderer;
import sbinnery.widget.api.Color;
import sbinnery.widget.api.WLayoutElement;
import sbinnery.widget.api.WModifiableCollection;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class WTooltip extends WAbstractWidget implements WModifiableCollection {
	protected Set<WAbstractWidget> widgets = new LinkedHashSet<>();


	@Override
	public void draw(MatrixStack matrices, VertexConsumerProvider.Immediate provider) {
		if (isHidden()) {
			return;
		}

		float x = position.getX();
		float y = position.getY();
		float z = position.getRelativeZ();

		float width = size.getWidth();
		float height = size.getHeight();

		Color backgroundStart = getStyle().asColor("background.start");
		Color backgroundEnd = getStyle().asColor("background.end");
		Color colorStart = getStyle().asColor("outline.start");
		Color colorEnd = getStyle().asColor("outline.end");
		Color shadowStart = getStyle().asColor("shadow.start");
		Color shadowEnd = getStyle().asColor("shadow.end");

		// Vanilla drawing process
		BaseRenderer.drawGradientQuad(matrices, provider, x - 3, y - 4, x + width + 3, y - 3, z, shadowStart, shadowStart); // top border
		BaseRenderer.drawGradientQuad(matrices, provider, x - 3, y + height + 3, x + width + 3, y + height + 4, z, shadowEnd, shadowEnd); // bottom border
		BaseRenderer.drawGradientQuad(matrices, provider, x - 3, y - 3, x + width + 3, y + height + 3, z, backgroundStart, backgroundEnd); // body
		BaseRenderer.drawGradientQuad(matrices, provider, x - 4, y - 3, x - 3, y + height + 3, z, shadowStart, shadowEnd); // left border
		BaseRenderer.drawGradientQuad(matrices, provider, x + width + 3, y - 3, x + width + 4, y + height + 3, z, shadowStart, shadowEnd); // right border

		BaseRenderer.drawGradientQuad(matrices, provider, x - 3, y - 3 + 1, x - 3 + 1, y + height + 3 - 1, z, colorStart, colorEnd); // left outline
		BaseRenderer.drawGradientQuad(matrices, provider, x + width + 2, y - 3 + 1, x + width + 3, y + height + 3 - 1, z, colorStart, colorEnd); // right outline
		BaseRenderer.drawGradientQuad(matrices, provider, x - 3, y - 3, x + width + 3, y - 3 + 1, z, colorStart, colorStart); // top outline
		BaseRenderer.drawGradientQuad(matrices, provider, x - 3, y + height + 2, x + width + 3, y + height + 3, z, colorEnd, colorEnd); // bottom outline

		for (WLayoutElement widget : widgets) {
			widget.draw(matrices, provider);
		}

		super.draw(matrices, provider);
	}

	@Override
	public Set<WAbstractWidget> getWidgets() {
		return widgets;
	}

	@Override
	public boolean contains(WAbstractWidget... widgets) {
		return this.widgets.containsAll(Arrays.asList(widgets));
	}

	@Override
	public void add(WAbstractWidget... widgets) {
		this.widgets.addAll(Arrays.asList(widgets));
		onLayoutChange();
	}

	@Override
	public void remove(WAbstractWidget... widgets) {
		this.widgets.removeAll(Arrays.asList(widgets));
		onLayoutChange();
	}
}
