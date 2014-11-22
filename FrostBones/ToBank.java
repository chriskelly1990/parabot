package FrostBones;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Tile;

import java.awt.*;


public class ToBank implements Strategy{

    int FoodID = 386;

    Area InBank = new Area(new Tile(3091, 3487), new Tile(3091, 3500), new Tile(3099, 3500), new Tile(3099, 3487));

    public boolean activate() { //if true continue to execute

        if (Inventory.getCount(FoodID) <= 2) /*&& (!InBank.contains(Players.getMyPlayer().getLocation())))*/ {

            return true;
        }

        return false;
    }


    public void execute() { //
        if (Game.getOpenInterfaceId() != 5292 && (Inventory.getCount(FoodID) <= 2) && (!InBank.contains(Players.getMyPlayer().getLocation())) && (Players.getMyPlayer().getLocation().equals(new Tile(3088,3502)))) {
        Keyboard.getInstance().sendKeys("::home");
        Time.sleep(8000);

        }

        if (Players.getMyPlayer().getLocation().equals(new Tile(3088,3502))) {
            new Tile(3093, 3494).walkTo();
            Time.sleep(3000);
        }



        if (InBank.contains(Players.getMyPlayer().getLocation()) && Game.getOpenInterfaceId() != 5292 && (Inventory.getCount(FoodID) <= 2)) {
            Menu.sendAction(502, 1110006199, 55, 51, 2213, 6);//open bank
            Time.sleep(3000);
        }

        if (Game.getOpenInterfaceId() == 5292) {
            Time.sleep(1000);
            FrostBones.BonesLooted += Inventory.getCount(18831);
            FrostBones.VisagesLooted += Inventory.getCount(11287);
            Menu.sendAction(646, 385, 0, 21012, 2213, 1); //deposit all
            Time.sleep(1000);
         //   Menu.sendAction(431, FoodID -1, 0, 5382, 2213, 4);//withdraw sharks withdraw all
            Menu.sendAction(867, FoodID -1, 0, 5382, 2213, 6);//withdraw sharks 10
            Menu.sendAction(867, FoodID -1, 0, 5382, 2213, 6);//withdraw sharks 10
            Time.sleep(1000);
            Menu.sendAction(200, 14293, 7, 21012, 4878, 1);//exit bank
        }

        if (Inventory.getCount(FoodID) >= 20 && (InBank.contains(Players.getMyPlayer().getLocation())) && (Game.getOpenInterfaceId() != 5292)) {
            Menu.sendAction(315, 18145, 506, 13087, 516, 1);
            Time.sleep(1000);
            Menu.sendAction(315, 18145, 506, 2496, 516, 1);
            Time.sleep(1000);
            Menu.sendAction(315, 18145, 506, 2494, 516, 1);
            Time.sleep(3000);

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
