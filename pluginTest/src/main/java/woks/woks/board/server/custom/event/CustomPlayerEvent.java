package woks.woks.board.server.custom.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CustomPlayerEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
