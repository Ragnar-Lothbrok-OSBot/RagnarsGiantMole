package bot.sections;

import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.MethodProvider;
import settings.Settings;

public class GuzzleRockCake extends Section {

    public GuzzleRockCake(MethodProvider api, Settings settings) {
        super(api, settings);
    }

    public void guzzle() {
        Item rockCake = getRockCake();
        if (rockCake != null) {
            int currentHp = getHp();
            rockCake.interact("Guzzle");
        }
    }

    private Item getRockCake() {
        return api.getInventory().getItem("Dwarven rock cake");
    }

    private int getHp() {
        return api.getSkills().getDynamic(Skill.HITPOINTS);
    }

}
