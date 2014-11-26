package PlateSmither;

import ZMI.ZMI;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.*;


import java.util.ArrayList;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.api.wrappers.Tile;
import PlateSmither.Area;



public class InBank implements Strategy {

    Area InBank = new Area(new Tile(3180, 3433), new Tile(3180, 3446), new Tile(3186, 3446), new Tile(3186, 3433));

    int SteelBars = 2354;
    private final int[] BankBooth = {2213, 2214, 2212};


    public boolean activate() { //if true continue to execute

        if (Inventory.getCount(SteelBars) <= 10 && (InBank.contains(Players.getMyPlayer().getLocation()))) {

            return true;
        }

        return false;
    }


    public void execute() { //
        SceneObject Booths = SceneObjects.getClosest(BankBooth);
        System.out.println("banking executed");

        if (Game.getOpenInterfaceId() != 5292 && (Inventory.getCount(SteelBars) <= 10 )) {
            if(Booths != null) {
                Menu.sendAction(502, Booths.getHash(), Booths.getLocalRegionX(), Booths.getLocalRegionY(),2213, 7);
                Time.sleep(1000);
            }
     }


        if (Game.getOpenInterfaceId() == 5292) { //If bank is open
            System.out.println("banks open");
            depositAllExcept(2348); //deposit all except hammer
            Time.sleep(700);
            Menu.sendAction(431, SteelBars -1, 0, 5382, 2213, 4);//withdraw all steel
            PlateSmither.PlatesMade += 5;
            Time.sleep(500);
            Menu.sendAction(200, 7936, 1, 5384, 32334, 1);//close bank
        }


    } //end


    public static void depositAllExcept(int... itemIDs) {
        final ArrayList<Integer> dontDeposit = new ArrayList<Integer>();
        if (Inventory.getCount(false) <= 2) {
            return;
        } else {
            for (int i : itemIDs) {
                dontDeposit.add(i);
            }
        }
        for (Item inventoryItem : Inventory.getItems()) {
            if (!dontDeposit.contains(inventoryItem.getId())) {
                Menu.sendAction(431, inventoryItem.getId() -1, inventoryItem.getSlot(), 5064, 2213, 3);
                Time.sleep(80);
            }
        }
    }
}

