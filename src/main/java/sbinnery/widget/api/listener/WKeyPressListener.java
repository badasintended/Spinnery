package sbinnery.widget.api.listener;

import sbinnery.widget.WAbstractWidget;

/**
 * An interface for events called when a key is pressed.
 */
public interface WKeyPressListener<W extends WAbstractWidget> {
	void event(W widget, int keyPressed, int character, int keyModifier);
}
