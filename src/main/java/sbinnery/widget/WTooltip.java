package sbinnery.widget;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
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
	@Environment(EnvType.CLIENT)
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
		BaseRenderer.drawTooltip(matrices, provider, x, y, width, height, shadowStart, shadowEnd, backgroundStart, backgroundEnd, colorStart, colorEnd);

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
