package PestControl;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Npc;
import org.rev317.min.api.wrappers.Tile;

/**
 * Created by callum on 10/12/14.
 */
public class InBrawlers implements Strategy {



    Area InBrawlers = new Area(new Tile(2671, 2585), new Tile(2671, 2598), new Tile(2683, 2598), new Tile(2683, 2585));
    private Npc[] Brawler;


    public boolean activate() { //if true continue to execute

        if (InBrawlers.contains(Players.getMyPlayer().getLocation())) {

            return true;
        }

        return false;
    }


    public void execute() { //
        PestControl.status = "attacking npc's";
        Brawler = Npcs.getNearest(3776, 3740, 3739);
        if (Brawler.length > 0 &&!Players.getMyPlayer().isInCombat() && Players.getMyPlayer().getAnimation() == -1 && Brawler[0].distanceTo() < 8)  {
            Brawler[0].interact(1);
            Time.sleep(3500);

        }


    }
}
