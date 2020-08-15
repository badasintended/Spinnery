package sbinnery.widget.api.listener;

import sbinnery.widget.WAbstractWidget;

/**
 * An interface for events called when a mouse button is clicked.
 */
public interface WMouseClickListener<W extends WAbstractWidget> {
	void event(W widget, float mouseX, float mouseY, int mouseButton);
}
