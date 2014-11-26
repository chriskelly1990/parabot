package PlateSmither;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import PlateSmither.Area;
import org.rev317.min.api.wrappers.Tile;
import org.rev317.min.api.wrappers.TilePath;



public class ToAnvil implements Strategy {

    Area InAnvil = new Area(new Tile(3185, 3423), new Tile(3185, 3427), new Tile(3190, 3427), new Tile(3190, 3423));

    int SteelBars = 2354;

    Tile[] Walk_To = {
            new Tile (3182, 3429, 0),
            new Tile (3187, 3424 ,0),};

    TilePath path = new TilePath(Walk_To);



    public boolean activate() { //if true continue to execute

        if (Inventory.getCount(SteelBars) >= 11 && (!path.hasReached()) && (!InAnvil.contains(Players.getMyPlayer().getLocation()))) {

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

}

