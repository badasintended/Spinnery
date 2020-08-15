package sbinnery.widget.api.listener;

import sbinnery.widget.WAbstractWidget;

/**
 * An interface for events called when a widget has to be aligned.
 */
public interface WAlignListener<W extends WAbstractWidget> {
	void event(W widget);
}
