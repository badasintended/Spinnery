package sbinnery.widget.api;

import sbinnery.widget.WInterface;

/**
 * Generic interface for providing a widget interface. Widget interface providers are generally
 * various implementations of screens.
 */
public interface WInterfaceProvider {
	WInterface getInterface();
}
