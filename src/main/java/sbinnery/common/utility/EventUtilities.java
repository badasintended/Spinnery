package sbinnery.common.utility;

import sbinnery.widget.WAbstractWidget;
import sbinnery.widget.api.WEventListener;

public class EventUtilities {
	/**
	 * Returns whether a widget is eligible to receive a mouse event. If the widget is a focused mouse listener,
	 * returns true only if it is focused; otherwise, always returns true.
	 *
	 * @return true if mouse event should go through.
	 */
	public static <T extends WEventListener> boolean canReceiveMouse(T target) {
		if (target instanceof WAbstractWidget) {
			WAbstractWidget widget = (WAbstractWidget) target;
			return !widget.isFocusedMouseListener() || widget.isFocused();
		}
		return true;
	}

	/**
	 * Returns whether a widget is eligible to receive a keyboard event. If the widget is a focused keyboard listener,
	 * returns true only if it is focused; otherwise, always returns true.
	 *
	 * @return true if mouse event should go through.
	 */
	public static <T extends WEventListener> boolean canReceiveKeyboard(T target) {
		if (target instanceof WAbstractWidget) {
			WAbstractWidget widget = (WAbstractWidget) target;
			return !widget.isFocusedKeyboardListener() || widget.isFocused();
		}
		return true;
	}
}
