package ZMI;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;
import org.rev317.min.api.wrappers.TilePath;

import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;


public class InZmi implements Strategy{

    Area InAltar = new Area(new Tile(3308, 4804), new Tile(3308, 4820), new Tile(3322, 4820), new Tile(3322, 4804));

    int Essence = 7937;



    public boolean activate() { //if true continue to execute

        if (Inventory.getCount(Essence) >= 11 && (InAltar.contains(Players.getMyPlayer().getLocation()))) {

            return true;
        }

        return false;
    }


    public void execute() { //

        SceneObject[] Altar = SceneObjects.getNearest(26847);
        System.out.println("executing crafting");
        Altar[0].interact(0);
      //  Menu.sendAction(502, 1513609531, 59, 50, 26847, 3);//Interact with banker
        Time.sleep(2000);


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

