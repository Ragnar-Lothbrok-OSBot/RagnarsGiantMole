package script;

import bot.Bot;
import gui.gui;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.script.Script;

import org.osbot.rs07.script.ScriptManifest;
import paint.Paint;
import settings.Settings;

import java.awt.*;
import java.util.Objects;

@ScriptManifest(name = "Ragnars Giant Mole", author = "Ragnar Lothbrok", version = 1.0, info = "Ragnar Lothbrok's Giant Mole Killer", logo = "")

public class RagnarsGiantMole extends Script {

    private Settings settings = new Settings();
    private Bot bot;
    private Paint paint;
    private gui gui;

    @Override
    public void onStart() {
        bot = new Bot(this.settings);
        paint = new Paint(this.settings);
        gui = new gui(this.settings);
        for (Item item : getInventory().getItems()) {
            if (item != null) {
                settings.addItemToInventory(item.getName(), (int) getInventory().getAmount(item.getName()));
            }
        }
        bot.exchangeContext(getBot());
        paint.exchangeContext(getBot());
        settings.setTimer(new utils.Timer());
        gui.main();
        //Code here will execute before the loop is started
    }

    @Override
    public void onExit() {
        //Code here will execute after the script ends
    }

    @Override
    public int onLoop() throws InterruptedException {
        if (settings.isQuit()) {
            stop(false);
        } else if (settings.isStarted()) {
            bot.onLoop();
        }
        return 100; //The amount of time in milliseconds before the loop starts over
    }

    @Override
    public void onMessage(Message message) {
        if (Objects.equals(message.getMessage(), "You look inside the mole hill and see no adventurers inside the mole tunnels.")) {
            settings.setWaitingOnMessage(false);
        } else if (message.getMessage().contains("You look inside the mole hill and see ")) {
            settings.setShouldHop(true);
        }
    }

    @Override
    public void onPaint(Graphics2D g) {
        paint.onLoop(g, getName(), getVersion());
    }

}