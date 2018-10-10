package bot.sections;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.script.MethodProvider;
import settings.Settings;
import utils.Sleep;

public class Teleport extends Section {

    public Teleport(MethodProvider api, Settings settings) {
        super(api, settings);
    }

    public void teleport() {
        Position startPos = api.myPosition();
        Item tab = getTeleportTab();
        if (tab != null) {
            if (tab.interact("Break")) {
                Sleep.sleepUntil(() -> inHouse() || inArea(fally), 10_000);
            }
        }
    }

    private Item getTeleportTab() {
        Item tab;
        if (settings.isUseHouseTeleport()) {
            tab = api.getInventory().getItem("Teleport to house");
        } else {
            tab = api.getInventory().getItem("Falador teleport");
        }
        return tab;
    }

}
