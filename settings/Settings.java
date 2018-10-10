package settings;

import utils.Timer;

import java.util.HashMap;

public class Settings {

    private boolean started = false;
    private boolean quit = false;
    private Timer timer;

    private boolean useHouseTeleport = false;
    private boolean drankFromPool = false;
    private boolean shouldHop = false;
    private boolean waitingOnMessage = true;
    private boolean shouldBank = false;

    private boolean isInFight = false;


    private String[] lootItems = new String[]{"Mole claw", "Mole skin", "Yew logs", "Clue scroll", "Shield left half", "Dragon spear"};

    private String currentAction = "Initializing Script";

    private HashMap<String, Integer> inventory = new HashMap<>();

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isQuit() {
        return quit;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public boolean isInFight() {
        return isInFight;
    }

    public void setInFight(boolean inFight) {
        isInFight = inFight;
    }

    public boolean isUseHouseTeleport() {
        return useHouseTeleport;
    }

    public void setUseHouseTeleport(boolean useHouseTeleport) {
        this.useHouseTeleport = useHouseTeleport;
    }

    public boolean isDrankFromPool() {
        return drankFromPool;
    }

    public void setDrankFromPool(boolean drankFromPool) {
        this.drankFromPool = drankFromPool;
    }

    public HashMap<String, Integer> getInventory() {
        return this.inventory;
    }

    public void addItemToInventory(String item, int amount) {
        if (!this.inventory.containsKey(item)) {
            this.inventory.put(item, amount);
        }
    }

    public boolean isShouldHop() {
        return shouldHop;
    }

    public void setShouldHop(boolean shouldHop) {
        this.shouldHop = shouldHop;
    }

    public boolean isWaitingOnMessage() {
        return waitingOnMessage;
    }

    public void setWaitingOnMessage(boolean waitingOnMessage) {
        this.waitingOnMessage = waitingOnMessage;
    }

    public String getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }

    public String[] getLootItems() {
        return lootItems;
    }

    public boolean isShouldBank() {
        return shouldBank;
    }

    public void setShouldBank(boolean shouldBank) {
        this.shouldBank = shouldBank;
    }
}
