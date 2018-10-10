package bot.sections;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.GroundItem;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.event.WalkingEvent;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.Condition;
import settings.Settings;
import utils.Sleep;

import java.util.Arrays;

public class FightMole extends Section {

    private EnterMoleLair enterMoleLair;
    private LightLightSource lightLightSource;
    private DrinkPotion drinkPotion;
    private GuzzleRockCake guzzleRockCake;
    private ToggleQuickPrayer toggleQuickPrayer;
    private LootItem lootItem;

    private int staminaConfig = 1575;
    private Area centre = new Area(1756, 5190, 1766, 5180);

    private Area lastCheckedArea = null;

    public FightMole(MethodProvider api, Settings settings) {
        super(api, settings);
        this.enterMoleLair = new EnterMoleLair(api, settings);
        this.lightLightSource = new LightLightSource(api, settings);
        this.drinkPotion = new DrinkPotion(api, settings);
        this.guzzleRockCake = new GuzzleRockCake(api, settings);
        this.toggleQuickPrayer = new ToggleQuickPrayer(api, settings);
        this.lootItem = new LootItem(api, settings);
    }

    public void fightMole() throws InterruptedException {
        if (api.getInventory().isFull() && !api.getInventory().contains("Vial")) {
            settings.setShouldBank(true);
        } else if (!inArea(moleLair)) {
            if (getLightSource() != null) {
                enterMoleLair.enterLair();
            } else {
                lightLightSource.lightLightSource();
            }
        } else {
            NPC mole = findMole();
            if (mole != null) {
                if (api.getSkills().getDynamic(Skill.PRAYER) <= 10) {
                    settings.setCurrentAction("Drinking prayer potion");
                    drinkPotion.drink("Prayer potion");
                } else if (api.getSkills().getDynamic(Skill.HITPOINTS) > 1) {
                    settings.setCurrentAction("Guzzling rock cake");
                    guzzleRockCake.guzzle();
                } else if (api.getSkills().getDynamic(Skill.STRENGTH) == api.getSkills().getStatic(Skill.STRENGTH)) {
                    settings.setCurrentAction("Drinking super combat potion");
                    drinkPotion.drink("Super combat potion");
                } else if (api.getConfigs().get(staminaConfig) < 1 && api.getSettings().getRunEnergy() <= 70) {
                    settings.setCurrentAction("Drinking stamina potion");
                    drinkPotion.drink("Stamina potion");
                } else {
                    settings.setCurrentAction("Fighting Mole");
                    if (!isFighting()) {
                        if (!api.getPrayer().isQuickPrayerActive()) {
                            settings.setCurrentAction("Enabling quick prayer");
                            toggleQuickPrayer.toggleQuickPrayer(true);
                        } else {
                            if (mole.interact("Attack")) {
                                settings.setInFight(true);
                                Sleep.sleepUntil(() -> isFighting(), 5000);
                            }
                        }
                    }
                }
            } else {
                settings.setInFight(false);
                if (api.getPrayer().isQuickPrayerActive()) {
                    settings.setCurrentAction("Disabling quick prayer");
                    toggleQuickPrayer.toggleQuickPrayer(false);
                } else if (lootItem() != null) {
                    settings.setCurrentAction("Looting: " + lootItem().getName());
                    lootItem.lootItem(lootItem());
                } else if (api.getSkills().getDynamic(Skill.HITPOINTS) > 1) {
                    settings.setCurrentAction("Guzzling rock cake");
                    guzzleRockCake.guzzle();
                } else {
                    Area area = null;
                    if (lastCheckedArea != null) {
                        area = buildArea(moleLair.getRandomPosition(), 10);
                    } else {
                        area = buildArea(moleLair.getRandomPosition(), 10);
                        while (api.getMap().realDistance(area.getRandomPosition()) < 25) {
                            area = buildArea(moleLair.getRandomPosition(), 10);
                        }
                    }
                    if (!inArea(area)) {
                        lastCheckedArea = area;
                        settings.setCurrentAction("Walking to random position in mole lair");
                        if (api.getConfigs().get(staminaConfig) < 1 && api.getSettings().getRunEnergy() <= 70) {
                            settings.setCurrentAction("Drinking stamina potion");
                            drinkPotion.drink("Stamina potion");
                        }
                        WalkingEvent walkingEvent = new WalkingEvent(area);
                        walkingEvent.setMinDistanceThreshold(0);
                        walkingEvent.setBreakCondition(new Condition() {
                            @Override
                            public boolean evaluate() {
                                return findMole() != null;
                            }
                        });
                        api.execute(walkingEvent);
                    }
                }
            }
        }
    }

    private GroundItem lootItem() {
        GroundItem itemToLoot = api.getGroundItems().closest(i -> Arrays.asList(settings.getLootItems()).contains(i.getName()));
        return itemToLoot;
    }

    private boolean isFighting() {
        NPC enemy = api.getNpcs().closest(n -> n.getName().equals("Giant Mole") && n.isInteracting(api.myPlayer()));
        if (api.getCombat().isFighting() || api.myPlayer().isUnderAttack() || api.myPlayer().isHitBarVisible() || enemy != null) {
            return true;
        } else {
            return false;
        }
    }

    private Item getLightSource() {
        return api.getInventory().getItem(i -> i.hasAction("Extinguish"));
    }

    private NPC findMole() {
        return api.getNpcs().closest("Giant Mole");
    }

}
