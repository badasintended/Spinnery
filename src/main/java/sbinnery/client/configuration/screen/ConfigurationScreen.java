package sbinnery.client.configuration.screen;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonElement;
import blue.endless.jankson.JsonObject;
import com.google.common.collect.BiMap;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.TranslatableText;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;
import sbinnery.Spinnery;
import sbinnery.client.configuration.widget.WOptionField;
import sbinnery.client.render.TextRenderer;
import sbinnery.client.screen.BaseScreen;
import sbinnery.common.configuration.data.ConfigurationHolder;
import sbinnery.common.configuration.registry.ConfigurationRegistry;
import sbinnery.widget.*;
import sbinnery.widget.api.Filter;
import sbinnery.widget.api.Position;
import sbinnery.widget.api.Size;
import sbinnery.widget.api.WInputFilter;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Map;

public abstract class ConfigurationScreen extends BaseScreen {
	protected static String name = "default";

	@Deprecated
	public static String getName() {
		return name;
	}

	public static File getFile() {
		return new File(FabricLoader.getInstance().getConfigDirectory().getPath() + "/" + getName() + ".json5");
	}

	public static void save() {
		try {
			Jankson jankson = Jankson.builder().build();

			JsonObject object = new JsonObject();

			for (Map.Entry<String, ConfigurationHolder<?>> entry : ConfigurationRegistry.getOptions(getName()).entrySet()) {
				object.put(entry.getKey(), jankson.toJson(entry.getValue().getValue()));
			}

			FileUtils.write(getFile(), object.toJson(), Charset.defaultCharset());
		} catch (Exception exception) {
			Spinnery.LOGGER.log(Level.ERROR, "Failed to write configuration file for " + getName() + "!");
		}

	}

	public static void load() {
		try {
			JsonObject object = Jankson.builder().build().load(Files.newInputStream(getFile().toPath()));

			BiMap<String, ConfigurationHolder<?>> holders = ConfigurationRegistry.getOptions(getName());

			for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
				ConfigurationHolder holder = holders.get(entry.getKey());
				holder.setValue(object.get(holder.getValue().getClass(), entry.getKey()));
			}
		} catch (Exception exception) {
			Spinnery.LOGGER.log(Level.ERROR, "Failed to read configuration file for " + getName() + "!");
		}
	}

	public ConfigurationScreen() {
		WInterface mainInterface = getInterface();

		int originalHeight = MinecraftClient.getInstance().getWindow().getScaledHeight();
		int originalWidth = MinecraftClient.getInstance().getWindow().getScaledWidth();

		WPanel mainPanel = mainInterface.createChild(WPanel::new, Position.ORIGIN, Size.of(originalWidth, originalHeight)).setLabel(new TranslatableText("text." + name + ".configuration.title"));

		WButton saveButton = mainPanel.createChild(WButton::new, Position.of(originalWidth - 44, originalHeight - 17, 0), Size.of(28, 12)).setLabel(new TranslatableText("text." + name + ".configuration.save"));
		WButton closeButton = mainPanel.createChild(WButton::new, Position.of(originalWidth - 76, originalHeight - 17, 0), Size.of(28, 12)).setLabel(new TranslatableText("text." + name + ".configuration.quit"));

		WVerticalScrollableContainer mainList = mainPanel.createChild(WVerticalScrollableContainer::new, Position.of(mainPanel, 16, 16, 0), Size.of(originalWidth - 32, originalHeight - 36)).setDivisionSpace(4);

		mainPanel.setOnAlign(widget -> {
			this.onClose();
		});

		saveButton.setOnMouseReleased(((widget, mouseX, mouseY, mouseButton) -> {
			if (saveButton.isWithinBounds(mouseX, mouseY)) {
				if (mouseButton == 0) {
					for (WAbstractWidget listWidget : mainList.getAllWidgets()) {
						if (listWidget instanceof WOptionField) {
							((WOptionField) listWidget).save();
						}
					}

					save();
				}
			}
		}));

		closeButton.setOnMouseReleased((widget, mouseX, mouseY, mouseButton) -> {
			if (closeButton.isWithinBounds(mouseX, mouseY)) {
				if (mouseButton == 0) {
					this.onClose();
				}
			}
		});

		float lastX = mainList.getX();
		float lastY = mainList.getY();
		float lastZ = mainList.getZ();

		for (ConfigurationHolder<?> holder : ConfigurationRegistry.getOptions(name).values()) {
			WInputFilter filter = Filter.get(holder.getValue().getClass());

			WStaticText label = new WStaticText()
					.setText(holder.getText())
					.setPosition(Position.of(mainList).setOffset(lastX + 16, lastY, lastZ))
					.setSize(Size.of(TextRenderer.width(holder.getText()), TextRenderer.height()))
					.setParent(mainList)
					.setInterface(mainInterface);

			WOptionField field = new WOptionField()
					.setHolder(holder)
					.setText(holder.toString())
					.setFilter(filter)
					.setPosition(Position.of(mainList).setOffset(mainList.getWideX() - 32 - 96, lastY, lastZ))
					.setSize(Size.of(96, 18))
					.setParent(mainList)
					.setInterface(mainInterface);

			mainList.add(label, field);

			field.setText(filter.toString(holder.getValue()));

			lastY += 24;
		}
	}
}
