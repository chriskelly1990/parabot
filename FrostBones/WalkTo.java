package FrostBones;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Tile;

import java.awt.*;


public class WalkTo implements Strategy{

    Area InLair = new Area(new Tile(2863, 9950), new Tile(2863, 9961), new Tile(2871, 9961), new Tile(2871, 9950));

    public boolean activate() { //if true continue to execute

        if  (InLair.contains(Players.getMyPlayer().getLocation())) { //if i just teled to drags

            return true;
        }

        return false;
    }


    public void execute() { //
        new Tile(2867, 9949).walkTo();
        Time.sleep(5000);
        new Tile(2866, 9944).walkTo();
        Time.sleep(5000);
        new Tile(2867, 9937).walkTo();
        Time.sleep(5000);
        new Tile(2867, 9933).walkTo();
        Time.sleep(5000);
        new Tile(2866, 9929).walkTo();
        Time.sleep(5000);
        new Tile(2866, 9924).walkTo();

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
