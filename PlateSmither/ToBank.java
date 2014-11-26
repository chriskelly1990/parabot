package PlateSmither;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Tile;
import org.rev317.min.api.wrappers.TilePath;
import PlateSmither.Area;




public class ToBank implements Strategy {

    Area InBank = new Area(new Tile(3180, 3433), new Tile(3180, 3446), new Tile(3186, 3446), new Tile(3186, 3433));

    int SteelBars = 2354;

    Tile[] Walk_To = {
            new Tile (3182, 3429, 0),
            new Tile (3183, 3439 ,0),};

    TilePath path = new TilePath(Walk_To);



    public boolean activate() { //if true continue to execute

        if (Inventory.getCount(SteelBars) <= 2 && (!path.hasReached()) && (!InBank.contains(Players.getMyPlayer().getLocation()))) {

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

