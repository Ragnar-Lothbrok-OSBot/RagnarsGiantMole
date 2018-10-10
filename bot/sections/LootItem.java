package bot.sections;

import org.osbot.rs07.api.model.GroundItem;
import org.osbot.rs07.script.MethodProvider;
import settings.Settings;
import utils.Sleep;

public class LootItem extends Section {

    public LootItem(MethodProvider api, Settings settings) {
        super(api, settings);
    }

    public void lootItem(GroundItem itemToLoot) {
        if (itemToLoot != null) {
            if (api.getInventory().isFull()) {
                if (api.getInventory().dropAll("Vial")) {
                    Sleep.sleepUntil(() -> !api.getInventory().contains("Vial"), 20_000);
                }
            } else {
                if (itemToLoot.interact("Take")) {
                    Sleep.sleepUntil(() -> !itemToLoot.exists(), 5000);
                }
            }
        }
    }

}
