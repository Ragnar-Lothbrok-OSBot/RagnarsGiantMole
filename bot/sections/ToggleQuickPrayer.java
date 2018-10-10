package bot.sections;

import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.script.MethodProvider;
import settings.Settings;
import utils.Sleep;

public class ToggleQuickPrayer extends Section {

    public ToggleQuickPrayer(MethodProvider api, Settings settings) {
        super(api, settings);
    }

    public void toggleQuickPrayer(boolean enable) {
        if (enable == true) {
            if (!isQuickPrayerEnabled()) {
                RS2Widget quickPrayerWidget = getQuickPrayerWidget();
                if (quickPrayerWidget != null) {
                    if (quickPrayerWidget.interact("Activate")) {
                        Sleep.sleepUntil(() -> isQuickPrayerEnabled(), 5000);
                    }
                }
            }
        } else if (enable == false) {
            if (isQuickPrayerEnabled()) {
                RS2Widget quickPrayerWidget = getQuickPrayerWidget();
                if (quickPrayerWidget != null) {
                    if (quickPrayerWidget.interact("Deactivate")) {
                        Sleep.sleepUntil(() -> !isQuickPrayerEnabled(), 5000);
                    }
                }
            }
        }
    }

    private boolean isQuickPrayerEnabled() {
        if (api.getPrayer().isQuickPrayerActive()) {
            return true;
        } else {
            return false;
        }
    }

    private RS2Widget getQuickPrayerWidget() {
        RS2Widget quickPrayerWidget = api.getWidgets().getAll().stream().filter(w -> w.getSpellName().equals("Quick-prayers")).findFirst().get();
        return quickPrayerWidget;
    }
}
