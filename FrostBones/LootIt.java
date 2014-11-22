package FrostBones;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.GroundItems;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.GroundItem;
import org.rev317.min.api.wrappers.Item;

public class LootIt implements Strategy {

    int foodId = 386;

public boolean activate() {
    if (!Players.getMyPlayer().isInCombat() && Players.getMyPlayer().getInteractingCharacter() == null && !Inventory.isFull() && isDragonLoot() == true) {

        return true;
    }
return false;
}



    @Override
    public void execute() {
        if (!Players.getMyPlayer().isInCombat() && Players.getMyPlayer().getInteractingCharacter() == null && !Inventory.isFull() && isDragonLoot() == true) {
            GroundItem[] toPickup = GroundItems.getNearest(18830, 11286);
            if (toPickup.length > 0 && toPickup[0] != null) {
                toPickup[0].interact(2);
                Time.sleep(1500);
            }

        } else if (Inventory.isFull() && GroundItems.getNearest().length > 0) {
            Item[] food = Inventory.getItems(foodId);
            if (food.length > 0 && food[0] != null) {
                Menu.sendAction(74, foodId - 1, food[0].getSlot(), 3214, 9398, 4);
                Time.sleep(800);
            }
        }
    }

    public boolean isDragonLoot() {
        GroundItem[] DragonLoot = GroundItems.getNearest(18830, 11286);
        if (DragonLoot.length > 0
                && DragonLoot[0] != null
                && DragonLoot[0].distanceTo() < 8) {

            return true;
        }
        return false;
    }
}