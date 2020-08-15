package sbinnery.widget.api.listener;

import sbinnery.widget.WAbstractWidget;

/**
 * An interface for events called when a key is released.
 */
public interface WKeyReleaseListener<W extends WAbstractWidget> {
	void event(W widget, int keyReleased, int character, int keyModifier);
}
