package bot;

import bot.sections.Bank;
import bot.sections.FightMole;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.MethodProvider;
import settings.Settings;

public class Bot extends MethodProvider {

    private Settings settings;

    private Bank bank;
    private FightMole fightMole;

    public Bot(Settings settings) {
        this.settings = settings;
        this.bank = new Bank(this, settings);
        this.fightMole = new FightMole(this, settings);
    }

    public void onLoop() throws InterruptedException {
        if ((!hasPrayerPotions() && getSkills().getDynamic(Skill.PRAYER) <= 5) || (needsBank() && !settings.isInFight()) || settings.isShouldBank()) {
            settings.setCurrentAction("Banking");
            bank.bank();
        } else {
            // TODO: Fight Giant Mole
            fightMole.fightMole();
        }
    }

    private boolean hasPrayerPotions() {
        return getInventory().contains(i -> i.getName().contains("Prayer potion"));
    }

    private boolean needsBank() {
        return !getInventory().contains(i -> i.getName().contains("Super combat potion")) || !getInventory().contains(i -> i.getName().contains("Stamina potion")) || !getInventory().contains(i -> i.getName().contains("lantern"));
    }

}
