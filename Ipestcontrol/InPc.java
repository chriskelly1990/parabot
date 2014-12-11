package PestControl;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Tile;
import org.rev317.min.api.wrappers.TilePath;

/**
 * Created by callum on 10/12/14.
 */
public class InPc implements Strategy {

    Area InPc = new Area(new Tile(2626, 2566), new Tile(2626, 2617), new Tile(2684, 2617), new Tile(2684, 2566));

    Area InBrawlers = new Area(new Tile(2671, 2585), new Tile(2671, 2598), new Tile(2683, 2598), new Tile(2683, 2585));

    Tile[] Walk_To = {
            new Tile (2658, 2601 ,0), //2
            new Tile (2666, 2593, 0), //3
            new Tile (2676, 2591, 0),};

    TilePath path = new TilePath(Walk_To);



    public boolean activate() { //if true continue to execute

        if (InPc.contains(Players.getMyPlayer().getLocation()) &&(!InBrawlers.contains(Players.getMyPlayer().getLocation()))) {

            return true;
        }

        return false;
    }


    public void execute() { //
        PestControl.status = "walking to npc's";
        if (path != null && !path.hasReached()) {
            path.traverse();
            Time.sleep(1000);
        }


    }
}
