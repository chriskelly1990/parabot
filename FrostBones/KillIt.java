package FrostBones;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Npc;
import org.rev317.min.api.wrappers.Tile;

import java.awt.*;



public class KillIt implements Strategy{

int FoodID = 386;

    Area InLair = new Area(new Tile(2857, 9917), new Tile(2857, 9930), new Tile(2870, 9930), new Tile(2870, 9917));

    public boolean activate() { //if true continue to execute
        Npc[] Dragon = Npcs.getNearest(10775, 10774, 10776);

        if (Dragon.length > 0 &&!Players.getMyPlayer().isInCombat() && !Dragon[0].isInCombat() && Dragon[0].distanceTo() < 10 && (InLair.contains(Players.getMyPlayer().getLocation()) && (Inventory.getCount(FoodID) >= 2))){
            return true;
        }

        return false;
    }


    public void execute() { //

        Npc[] Dragon = Npcs.getNearest(10775, 10774, 10776);
        if (Dragon.length > 0 &&!Players.getMyPlayer().isInCombat() && !Dragon[0].isInCombat() && Dragon[0].distanceTo() < 10)  {
            Dragon[0].interact(1);
        Time.sleep(2000);
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
