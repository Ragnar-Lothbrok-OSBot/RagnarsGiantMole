package bot.sections;

import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.event.WalkingEvent;
import org.osbot.rs07.script.MethodProvider;
import settings.Settings;
import utils.Sleep;

public class EnterMoleLair extends Section {

    public EnterMoleLair(MethodProvider api, Settings settings) {
        super(api, settings);
    }

    public void enterLair() {
        if (!inArea(fallyPark)) {
            settings.setCurrentAction("Walking to falador park");
            api.getWalking().webWalk(fallyPark);
        } else {
            if (!settings.isShouldHop()) {
                RS2Object moleHill = api.getObjects().closest("Mole hill");
                if (moleHill != null) {
                    if (settings.isWaitingOnMessage()) {
                        settings.setCurrentAction("Looking inside mole hill");
                        if (moleHill.interact("Look-inside")) {
                            Sleep.sleepUntil(() -> !settings.isWaitingOnMessage(), 5000);
                        }
                    } else {
                        if (!moleHill.getPosition().equals(api.myPosition())) {
                            settings.setCurrentAction("Walking to mole hill position");
                            WalkingEvent walkingEvent = new WalkingEvent(moleHill.getPosition());
                            walkingEvent.setMinDistanceThreshold(0);
                            api.execute(walkingEvent);
                        } else {
                            RS2Widget enterWidget = getEnterWidget();
                            if (enterWidget != null) {
                                if (enterWidget.interact("Yes")) {
                                    Sleep.sleepUntil(() -> inArea(moleLair), 5000);
                                }
                            } else {
                                settings.setCurrentAction("Entering mole hill");
                                if (api.getInventory().getItem("Spade").interact("Dig")) {
                                    Sleep.sleepUntil(() -> getEnterWidget() != null, 5000);
                                }
                            }
                        }
                    }
                }
            } else {
                settings.setCurrentAction("Hopping worlds");
                api.getWorlds().hopToP2PWorld();
                settings.setShouldHop(true);
            }
        }
    }

    private RS2Widget getEnterWidget() {
        return api.getWidgets().getWidgetContainingText("Proceed regardless");
    }

}
