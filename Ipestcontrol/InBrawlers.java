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
    private static Npc q;



    Area InBrawlers = new Area(new Tile(2671, 2585), new Tile(2671, 2598), new Tile(2683, 2598), new Tile(2683, 2585));



    public boolean activate() {
        for(Npc n : Npcs.getNearest(3776)){
            q = n;
            return n != null && !Players.getMyPlayer().isInCombat() && (InBrawlers.contains(Players.getMyPlayer().getLocation()));
        }

        return false;
    }

    public void execute() { //
        PestControl.status = "attacking npc's";
        if(q != null){
            q.interact(1); // Change to 1 if 0 doesn't work.
            Time.sleep(1000);


    }
}
}
