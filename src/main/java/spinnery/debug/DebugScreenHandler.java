package spinnery.debug;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerType;
import spinnery.common.handler.BaseScreenHandler;
import spinnery.widget.WSlot;

public class DebugScreenHandler extends BaseScreenHandler {
	public DebugScreenHandler(int synchronizationID, PlayerInventory playerInventory) {
		super(null, synchronizationID, playerInventory);

		getInterface().createChild(WSlot::new).setInventoryNumber(0).setSlotNumber(0);
	}

	@Override
	public ScreenHandlerType<?> getType() {
		return DebugScreenHandlers.DEBUG;
	}
}
