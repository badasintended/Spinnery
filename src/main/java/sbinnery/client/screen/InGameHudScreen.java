package sbinnery.client.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.hud.InGameHud;
import sbinnery.widget.WInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Environment(EnvType.CLIENT)
public class InGameHudScreen {
	protected static WInterface hudInterface = null;
	protected static InGameHud inGameHudCache = null;
	protected static List<Runnable> onInitialize = new ArrayList<>();

	public static void onInitialize(InGameHud inGameHud) {
		inGameHudCache = inGameHud;
		hudInterface = ((Accessor) inGameHud).sbinnery$getInterface();
		for (Runnable runnable : onInitialize) {
			runnable.run();
		}
	}

	public static void addOnInitialize(Runnable... r) {
		onInitialize.addAll(Arrays.asList(r));
	}

	public static void removeOnInitialize(Runnable... r) {
		onInitialize.removeAll(Arrays.asList(r));
	}

	public static WInterface getInterface() {
		return hudInterface;
	}

	public static InGameHud getInGameHud() {
		return inGameHudCache;
	}

	public interface Accessor {
		WInterface sbinnery$getInterface();

		InGameHud sbinnery$getInGameHud();
	}
}
