package xyz.sintcraft.DragonFight;

import xyz.sintcraft.DragonFight.listeners.PlayerPortalEvent;

public class EventsManager {
    private DragonFight dragonFight;

    public EventsManager(DragonFight p) {
        this.dragonFight = p;
        loadEvents();
    }

    private void loadEvents() {
        dragonFight.getServer().getPluginManager().registerEvents(new PlayerPortalEvent(dragonFight), dragonFight);
    }
}
