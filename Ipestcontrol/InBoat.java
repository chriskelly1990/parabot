package PestControl;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Tile;
/**
 * Created by callum on 10/12/14.
 */
public class InBoat implements Strategy {

    Area InBoat = new Area(new Tile(2660, 2638), new Tile(2660, 2643), new Tile(2663, 2643), new Tile(2663, 2638));



    public boolean activate() { //if true continue to execute

        if (InBoat.contains(Players.getMyPlayer().getLocation())) {

            return true;
        }

        return false;
    }


    public void execute() { //
        PestControl.status = "Waiting for boat";
        Time.sleep(1000);


    }
}
