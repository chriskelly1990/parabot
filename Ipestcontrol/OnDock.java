package PestControl;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;

/**
 * Created by callum on 10/12/14.
 */
public class OnDock implements Strategy {

    private final int[] GangPlank = {14315, 14314, 14316}; //3 id's incase they change it like before

    public boolean activate() { //if true continue to execute

        if (Players.getMyPlayer().getLocation().equals(new Tile(2657,2639))) {

            return true;
        }

        return false;
    }


    public void execute() { //
        SceneObject Plank = SceneObjects.getClosest(GangPlank);
        if(Plank != null) {
            Menu.sendAction(502, Plank.getHash(), Plank.getLocalRegionX(), Plank.getLocalRegionY(), 14315, 3);
            PestControl.status = "Waiting for boat";
            Time.sleep(1000);
        }
    }
}
