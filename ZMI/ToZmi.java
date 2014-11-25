package ZMI;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.api.wrappers.Tile;
import org.rev317.min.api.wrappers.TilePath;

import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;


public class ToZmi implements Strategy{

    Area InBank = new Area(new Tile(3265, 4854), new Tile(3265, 4862), new Tile(3277, 4862), new Tile(3277, 4854));

    int Essence = 7937;

    Tile[] Walk_To = {new Tile (3269, 4848, 0), //1
                      new Tile (3270, 4841 ,0), //2
                      new Tile (3269, 4833, 0), //3
                      new Tile (3270, 4822, 0), //4
                      new Tile (3271, 4811, 0), //5
                      new Tile (3280, 4809, 0), //6
                      new Tile (3288, 4811, 0), //7
                      new Tile (3301, 4810, 0), //8
                      new Tile (3312, 4811, 0), //9
                      new Tile (3315, 4813, 0),}; //10

    TilePath path = new TilePath(Walk_To);



    public boolean activate() { //if true continue to execute

        if (Inventory.getCount(Essence) >= 11 && (!path.hasReached())) {

            return true;
        }

        return false;
    }


    public void execute() { //

        System.out.println("path walk executed");

        if (path != null && !path.hasReached()) {
            path.traverse();
            Time.sleep(2000);
        }




    } //end






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

