package bot.sections;

import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.script.MethodProvider;
import settings.Settings;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class DrinkPotion extends Section {

    public DrinkPotion(MethodProvider api, Settings settings) {
        super(api, settings);
    }

    public void drink(String potionName) {
        Optional<Item> pot = getPotion(potionName);
        if (pot != null) {
            if (pot.get().interact("Drink")) {
                utils.Sleep.sleepUntil(() -> !pot.get().getName().equals(getPotion(potionName)), 5000);
            }
        }
    }

    private Optional<Item> getPotion(String potionName) {
        Optional<Item> potion = Arrays.stream(api.getInventory().getItems())
                .filter(item -> item != null && item.getName().startsWith(potionName))
                .min(Comparator.comparing(Item::getName));
        if (potion.isPresent()) {
            return potion;
        } else {
            return null;
        }
    }

}
