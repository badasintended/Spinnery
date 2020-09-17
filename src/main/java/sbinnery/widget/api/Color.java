package sbinnery.widget.api;

import blue.endless.jankson.JsonElement;
import blue.endless.jankson.JsonPrimitive;

/**
 * Data object representing a color. Components may be accessed via the fields
 * A, R, G, B; the color int may be accessed via the fields
 * RGB and ARGB.
 */
public class Color implements JanksonSerializable {
	public static final Color DEFAULT = Color.of(0xffffffff);

	public float A, R, G, B;

	public int ARGB = 0xffffffff;
	public int RGB = 0xffffff;

	public Color(int r, int g, int b, int a) {
		this(r / 255f, g / 255f, b / 255f, a / 255f);
	}

	public Color(float r, float g, float b, float a) {
		R = r;
		G = g;
		B = b;
		A = a;
		RGB = packRgb((int) Math.floor(r * 255.0F), (int) Math.floor(g * 255.0F), (int) Math.floor(b * 255.0F));
		ARGB = RGB + ((int) (a * 255) << 24);
	}

	public static int packRgb(int r, int g, int b) {
		int i = (r << 8) + g;
		i = (i << 8) + b;
		return i;
	}

	public Color(String str) {
		if (str.length() == 8) {
			this.RGB = Integer.parseUnsignedInt(str.substring(2), 16);
			this.ARGB = RGB + (0xFF << 24);
		} else if (str.length() == 10) {
			this.ARGB = Integer.parseUnsignedInt(str.substring(2), 16);
			this.RGB = ARGB & 0xFFFFFF;
		}
		A = (ARGB >> 24 & 0xFF) / 255f;
		R = (ARGB >> 16 & 0xFF) / 255f;
		G = (ARGB >> 8 & 0xFF) / 255f;
		B = (ARGB & 0xFF) / 255f;
	}

	public static Color of(String ARGB) {
		return new Color(ARGB);
	}

	public static Color of(Number color) {
		int intColor = color.intValue();
		int a = (intColor >> 24) & 0xFF;
		int r = (intColor >> 16) & 0xFF;
		int g = (intColor >> 8) & 0xFF;
		int b = (intColor & 0xFF);
		return new Color(r, g, b, a);
	}

	@Override
	public JsonElement toJson() {
		return new JsonPrimitive(ARGB);
	}
}
