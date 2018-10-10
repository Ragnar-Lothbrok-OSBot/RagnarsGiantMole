package bot.sections;

import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.script.MethodProvider;
import settings.Settings;

public class LightLightSource extends Section {

    public LightLightSource(MethodProvider api, Settings settings) {
        super(api, settings);
    }

    public void lightLightSource() throws InterruptedException {
        if (!api.getInventory().isItemSelected()) {
            Item tinderbox = getTinderBox();
            if (tinderbox != null) {
                if (tinderbox.interact("Use")) {
                    utils.Sleep.sleepUntil(() -> api.getInventory().isItemSelected(), 5000);
                }
            }
        } else {
            Item lightSource = getLightSource();
            if (lightSource.interact("Use")) {
                utils.Sleep.sleepUntil(() -> !api.getInventory().isItemSelected(), 5000);
                api.sleep(1000);
            }
        }
    }

    private Item getLightSource() {
        return api.getInventory().getItem("Bullseye lantern");
    }

    private Item getTinderBox() {
        return api.getInventory().getItem("Tinderbox");
    }

}
