package bot.sections;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.MethodProvider;
import settings.Settings;

public abstract class Section {

    protected Area moleLair = new Area(1719, 5251, 1801, 5119);
    protected Area fally = new Area(2957, 3391, 3057, 3325);
    protected Area fallyPark = new Area(2990, 3380, 3001, 3373);

    protected MethodProvider api;
    protected Settings settings;

    public Section(MethodProvider api, Settings settings) {
        this.api = api;
        this.settings = settings;
    }

    protected boolean inArea(Area area) {
        if (area.contains(api.myPosition())) {
            return true;
        } else {
            return false;
        }
    }

    protected Area buildArea(Position position, int size) {
        return new Area(new Position(position.getX() - size, position.getY() - size, 0), new Position(position.getX() + size, position.getY() + size, 0));
    }

    protected boolean inHouse() {
        RS2Object portal = getPortal();
        if (portal != null) {
            return true;
        } else {
            return false;
        }
    }

    protected RS2Object getPortal() {
        return api.getObjects().closest("Portal");
    }

}
