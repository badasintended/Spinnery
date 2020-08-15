package sbinnery.widget;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import sbinnery.client.render.BaseRenderer;
import sbinnery.common.utility.MouseUtilities;
import sbinnery.widget.api.Color;
import sbinnery.widget.api.WVerticalScrollable;

public class WVerticalScrollbar extends WAbstractWidget {
	protected WVerticalScrollable scrollable;
	protected float clickMouseY;
	protected boolean dragging = false;
	protected boolean hasArrows = true;

	public WVerticalScrollbar setScrollable(WVerticalScrollable scrollable) {
		this.scrollable = scrollable;
		return this;
	}

	public WVerticalScrollable getScrollable() {
		return scrollable;
	}

	public boolean hasArrows() {
		return hasArrows;
	}

	public void setHasArrows(boolean hasArrows) {
		this.hasArrows = hasArrows;
	}

	@Override
	public void draw(MatrixStack matrices, VertexConsumerProvider.Immediate provider) {
		if (isHidden()) {
			return;
		}

		BaseRenderer.drawBeveledPanel(matrices, provider, getX(), getY(), getZ(), getWidth(), getHeight(), getStyle().asColor("scroll_line.top_left"), getStyle().asColor("scroll_line.background"), getStyle().asColor("scroll_line.bottom_right"));

		Color scrollerColor = getStyle().asColor("scroller.background_default");

		if (MouseUtilities.mouseX > getX() + 1 && MouseUtilities.mouseX < getWideX() - 1
				&& MouseUtilities.mouseY > getScrollerY() + 1 && MouseUtilities.mouseY < getScrollerY() + getScrollerHeight() - 1 && !isHeld()) {
			scrollerColor = getStyle().asColor("scroller.background_hovered");
		} else if (isHeld()) {
			scrollerColor = getStyle().asColor("scroller.background_held");
		}

		BaseRenderer.drawBeveledPanel(matrices, provider, getX() + 1, getScrollerY() + 1, getZ(), getWidth() - 2, Math.min(getHighY() - getScrollerY(), getScrollerHeight()) - 2, getStyle().asColor("scroller.top_left"), scrollerColor, getStyle().asColor("scroller.bottom_right"));

		super.draw(matrices, provider);
	}

	@Override
	public void onMouseClicked(float mouseX, float mouseY, int mouseButton) {
		if (mouseButton == 0) {
			if (isWithinBounds(mouseX, mouseY)) {
				if (mouseY >= getScrollerY() && mouseY <= getScrollerY() + getScrollerHeight()) {
					dragging = true;
					clickMouseY = mouseY - getScrollerY();
				} else {
					dragging = false;

					if (mouseY > getScrollerY()) {
						if (((WVerticalScrollableContainer) scrollable).hasSmoothing()) {
							((WVerticalScrollableContainer) scrollable).kineticScrollDelta -= 3.5;
						} else {
							scrollable.scroll(0, -50);
						}
					} else {
						if (((WVerticalScrollableContainer) scrollable).hasSmoothing()) {
							((WVerticalScrollableContainer) scrollable).kineticScrollDelta += 3.5;
						} else {
							scrollable.scroll(0, +50);
						}
					}
				}
			} else {
				dragging = false;
			}
		}

		super.onMouseClicked(mouseX, mouseY, mouseButton);
	}

	public float getScrollerY() {
		float outerHeight = scrollable.getVisibleHeight();
		float innerHeight = scrollable.getUnderlyingHeight();
		float topOffset = scrollable.getStartOffsetY();
		float percentToEnd = topOffset / (innerHeight - outerHeight);
		float maximumOffset = getHeight() - getScrollerHeight();
		return getY() + (maximumOffset * percentToEnd);
	}

	public float getScrollerHeight() {
		float outerHeight = getHeight();
		float innerHeight = scrollable.getUnderlyingHeight();
		float calculated = (outerHeight * (outerHeight / Math.max(innerHeight, outerHeight)));
		return Math.max(calculated, 4);
	}

	@Override
	public void onMouseDragged(float mouseX, float mouseY, int mouseButton, double deltaX, double deltaY) {
		if (mouseButton == 0) {
			if (dragging) {
				double scrollerOffsetY = getScrollerY() + clickMouseY - mouseY;

				scrollable.scroll(0, scrollerOffsetY);
			}
		}

		super.onMouseDragged(mouseX, mouseY, mouseButton, deltaX, deltaY);
	}
}
