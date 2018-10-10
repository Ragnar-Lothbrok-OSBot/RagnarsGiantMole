package paint;

import org.osbot.rs07.script.MethodProvider;
import settings.Settings;

import java.awt.*;

public class Paint extends MethodProvider  {

    private Settings settings;

    public Paint(Settings settings) {
        this.settings = settings;
    }

    private final Font font = new Font("Lucida Sans Unicode", Font.PLAIN, 11);
    private final int x = 8;
    private final int y = 40;
    private final int spacing = 15;
    private int lines;

    public void onLoop(Graphics2D g, String scriptName, double scriptVersion) {
        RenderingHints antialiasing = new RenderingHints(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON
        );
        g.setRenderingHints(antialiasing);
        lines = 0;
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("[" + scriptName + " v" + scriptVersion + "]", x, nextY());
        g.drawString("Runtime: " + settings.getTimer().getElapsedToString(), x + 8, nextY());
        g.drawString("Status: " + settings.getCurrentAction(), x + 8, nextY());
        Point p = mouse.getPosition();
        Dimension d = bot.getCanvas().getSize();
        g.drawLine(0, p.y, d.width, p.y);
        g.drawLine(p.x, 0, p.x, d.height);
    }

    private int nextY() {
        return y + spacing * (lines++ );
    }

}
