package bot.sections;

import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.MethodProvider;
import settings.Settings;
import utils.Sleep;

import static org.osbot.rs07.api.map.constants.Banks.FALADOR_EAST;

public class Bank extends Section {

    private Teleport teleport;

    public Bank(MethodProvider api, Settings settings) {
        super(api, settings);
        teleport = new Teleport(api, settings);
    }

    public void bank() throws InterruptedException {
        api.log("here");
        if (!inArea(FALADOR_EAST)) {
            if (inArea(fally)) {
                settings.setCurrentAction("Walking to bank");
                api.getWalking().webWalk(FALADOR_EAST);
            } else {
                if (settings.isUseHouseTeleport()) {
                    if (inHouse()) {
                        if (hasPool() && !settings.isDrankFromPool()) {
                            settings.setCurrentAction("Drinking from pool");
                            RS2Object pool = getPool();
                            if (pool.interact("Drink")) {
                                Sleep.sleepUntil(() -> api.myPlayer().isAnimating(), 20_000);
                                Sleep.sleepUntil(() -> !api.myPlayer().isAnimating(), 5000);
                                settings.setDrankFromPool(true);
                            }
                        } else {
                            settings.setCurrentAction("Entering Falador portal");
                            RS2Object fallyPortal = api.getObjects().closest("Falador Portal");
                            if (fallyPortal != null) {
                                if (fallyPortal.interact("Enter")) {
                                    Sleep.sleepUntil(() -> !inHouse(), 5000);
                                }
                            }
                        }
                    } else {
                        settings.setCurrentAction("Teleporting to house");
                        teleport.teleport();
                    }
                } else {
                    settings.setCurrentAction("Teleporting to Falador");
                    teleport.teleport();
                }
            }
        } else {
            if (!api.getBank().isOpen()) {
                settings.setCurrentAction("Opening bank");
                if (api.getBank().open()) {
                    Sleep.sleepUntil(() -> api.getBank().isOpen(), 10_000);
                }
            } else {
                settings.setCurrentAction("Restocking");
                if (api.getInventory().contains(settings.getLootItems())) {
                    if (api.getBank().depositAll(settings.getLootItems())) {
                        Sleep.sleepUntil(() -> !api.getInventory().contains(settings.getLootItems()), 10_000);
                    }
                } else if (!api.getBank().withdraw(settings.getInventory())) {
                    settings.setCurrentAction("Quiting script");
                    settings.setQuit(true);
                } else {
                    settings.setShouldBank(false);
                }
            }
        }
    }

    private boolean hasPool() {
        RS2Object pool = getPool();
        if (pool != null) {
            return true;
        } else {
            return false;
        }
    }

    private RS2Object getPool() {
        return api.getObjects().closest(p -> p.getName().contains("pool") && p.hasAction("Drink"));
    }

}
