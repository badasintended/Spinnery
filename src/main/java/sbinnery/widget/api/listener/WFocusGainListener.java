package sbinnery.widget.api.listener;

import sbinnery.widget.WAbstractWidget;

/**
 * An interface for events called when a widget gains focus.
 */
public interface WFocusGainListener<W extends WAbstractWidget> {
	void event(W widget);
}
