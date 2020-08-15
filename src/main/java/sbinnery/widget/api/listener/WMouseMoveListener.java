package sbinnery.widget.api.listener;

import sbinnery.widget.WAbstractWidget;

/**
 * An interface for events called when the mouse is moved.
 */
public interface WMouseMoveListener<W extends WAbstractWidget> {
	void event(W widget, float mouseX, float mouseY);
}
