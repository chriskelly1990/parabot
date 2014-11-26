package PlateSmither;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;



public class SmithPlates implements Strategy {

    Area InAnvil = new Area(new Tile(3185, 3423), new Tile(3185, 3427), new Tile(3190, 3427), new Tile(3190, 3423));

    int SteelBars = 2354;


    private final int[] Anvil = {2783, 2782};


    public boolean activate() { //if true continue to execute

        if (Inventory.getCount(SteelBars) >= 10 && (InAnvil.contains(Players.getMyPlayer().getLocation()))) {

            return true;
        }

        return false;
    }


    public void execute() { //
        SceneObject Anvils = SceneObjects.getClosest(Anvil);
        if(Anvils != null && (Game.getOpenInterfaceId() != 41320) && (Players.getMyPlayer().getAnimation() == -1) && (Inventory.getCount(SteelBars) >= 2)) {
            Menu.sendAction(447, SteelBars -1 , Inventory.getItems(SteelBars)[0].getSlot(), 3214, 2783, 1);
            Time.sleep(300);
            Menu.sendAction(62, Anvils.getHash(), 28, 48, 2783, 1);
            Time.sleep(1000);
        }

        if (Game.getOpenInterfaceId() == 994) { //check the interface is open
            System.out.println("make 10 action");
            Menu.sendAction(867, 1119, 3, 1121, 356, 2);//make 10
            Time.sleep(8000);
        }


//end
    }
}

