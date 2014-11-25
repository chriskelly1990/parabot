package ZMI;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.api.wrappers.Tile;

import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;


public class LeBank implements Strategy{

    Area InBank = new Area(new Tile(3265, 4854), new Tile(3265, 4862), new Tile(3277, 4862), new Tile(3277, 4854));

    int Essence = 7937;
    int AirRunes = 557;


    public boolean activate() { //if true continue to execute

         if (Inventory.getCount(Essence) <= 10 && (InBank.contains(Players.getMyPlayer().getLocation()))) {

            return true;
        }

        return false;
    }


    public void execute() { //

        System.out.println("banking executed");

        if (Game.getOpenInterfaceId() != 41320 && (Game.getOpenBackDialogId() != 4893 && (Game.getOpenInterfaceId() != 5292))) { //were not doing anything
            System.out.println("interacting with banker");
            Menu.sendAction(20, 2350, 0, 0 , 4875, 5);//Interact with banker
            Time.sleep(1000);
        }

        if (Game.getOpenBackDialogId() == 4893) { //click to continue
            System.out.println("clicking to continue");
            Menu.sendAction(679, 2350, 307, 4899, 32334, 1);//click to continue
            Time.sleep(1000);
        }

        if (Game.getOpenInterfaceId() == 41320) { //select rune to pay with
            System.out.println("selecting runes to pay with");
            Menu.sendAction(646, 2350, 474, 41344, 32334, 1);//select air rune
            Time.sleep(1000);
        }


        if (Game.getOpenInterfaceId() == 5292) { //If bank is open
            System.out.println("banks open");
            depositAllExcept(557); //deposit all except air runes
            ZMI.TripsMade += 1;
            Time.sleep(700);
            Menu.sendAction(431, Essence -1, 0, 5382, 32334, 4);//withdraw all essence
            Time.sleep(500);
            Menu.sendAction(200, 7936, 1, 5384, 32334, 1);//close bank
        }



      //  Menu.sendAction(679, 2350, 307, 4899, 32334, 1);//click to continue



      //  depositAllExcept(561, 566, 556, 567);

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

    public class Area {
        private Polygon p;
        public Area(Tile... tiles) {
            this.p = new Polygon();
            for (int i = 0; i < tiles.length; i++) {
                p.addPoint(tiles[i].getX(), tiles[i].getY());
            }
        }
        public boolean contains(Tile tile) {
            return this.contains(tile.getX(), tile.getY());
        }

        public boolean contains(int x, int y) {
            int i;
            int j;
            boolean result = false;
            for (i = 0, j = p.npoints - 1; i < p.npoints; j = i++) {
                if ((p.ypoints[i] > y - 1) != (p.ypoints[j] > y - 1)
                        && (x <= (p.xpoints[j] - p.xpoints[i]) * (y - p.ypoints[i])
                        / (p.ypoints[j] - p.ypoints[i]) + p.xpoints[i])) {
                    result = !result;
                }
            }
            return result;
        }
    }
    }

