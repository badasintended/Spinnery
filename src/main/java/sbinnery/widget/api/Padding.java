package sbinnery.widget.api;

import blue.endless.jankson.JsonElement;
import sbinnery.common.utility.JanksonUtilities;

import java.util.Objects;

/**
 * Contains four directional dimensions, meant to be used like CSS padding (4-valued representations
 * are ordered clockwise from the top).
 */
public class Padding implements JanksonSerializable {
	protected final float top;
	protected final float bottom;
	protected final float left;
	protected final float right;

	public Padding(float top, float right, float bottom, float left) {
		this.top = top;
		this.bottom = bottom;
		this.left = left;
		this.right = right;
	}

	public static Padding of(float top, float right, float bottom, float left) {
		return new Padding(top, right, bottom, left);
	}

	public static Padding of(float vertical, float horizontal) {
		return new Padding(vertical, horizontal, vertical, horizontal);
	}

	public static Padding of(float all) {
		return new Padding(all, all, all, all);
	}

	public float getTop() {
		return top;
	}

	public float getBottom() {
		return bottom;
	}

	public float getLeft() {
		return left;
	}

	public float getRight() {
		return right;
	}

	@Override
	public int hashCode() {
		return Objects.hash(top, bottom, left, right);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Padding padding = (Padding) o;
		return top == padding.top &&
				bottom == padding.bottom &&
				left == padding.left &&
				right == padding.right;
	}

	@Override
	public String toString() {
		return "WPadding{" +
				"top=" + top +
				", right=" + right +
				", bottom=" + bottom +
				", left=" + left +
				'}';
	}

	@Override
	public JsonElement toJson() {
		return JanksonUtilities.arrayOfPrimitives(top, right, bottom, left);
	}
}
