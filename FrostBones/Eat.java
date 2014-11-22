package FrostBones;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Item;


public class Eat implements Strategy{

    int FoodID = 386;


    public boolean activate() { //if true continue to execute

        if (Players.getMyPlayer().getHealth() < 60 && (Players.getMyPlayer().isInCombat())) {

            return true;
        }

        return false;
    }


    public void execute() { //
        Item[] food = Inventory.getItems(FoodID);
        if (Players.getMyPlayer().getHealth() <= 60 && food[0] != null) {
            System.out.println("eating");
            Menu.sendAction(74, FoodID - 1, food[0].getSlot(), 3214, 9398, 4); //eat sharks
            Time.sleep(2000); //sleep so it doesnt spam

        }
    }
}
