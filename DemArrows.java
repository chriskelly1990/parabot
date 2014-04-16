import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.*;
import org.rev317.api.methods.Menu;
import org.rev317.api.wrappers.hud.Item;
import org.rev317.api.wrappers.hud.Tab;
import org.rev317.api.wrappers.interactive.Npc;
import org.rev317.api.wrappers.scene.SceneObject;
import org.rev317.api.wrappers.scene.Tile;
import org.rev317.api.methods.Camera;
import java.awt.*;
import java.util.ArrayList;




@ScriptManifest(author = "Lulzs3c", category = Category.FLETCHING, description = "Shafts dem arrows", name = "DemArrows", servers = { "PKHonor" }, version = 1.0)


    public class DemArrows extends Script {

    private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();




    @Override
    public boolean onExecute() {
        strategies.add(new BobsIsland());
        strategies.add(new OldMan());
        strategies.add(new SandwichLady());
        strategies.add(new Checkafk());
        strategies.add(new ChopTrees());
        strategies.add(new MakeShaft());
        provide(strategies);
        return true;
    }

    int [] trees = {1276, 1286, 1277, 1280, 1278};


    @Override
    public void onFinish() {
    }

    public static class SandwichLady implements Strategy {

        static Npc sandwichLady;

        @Override
        public boolean activate() {

            Npc[] sandwichLady = Npcs.getNearest(3117);
            if (sandwichLady != null) {
                for (Npc i : sandwichLady) {
                    if (i != null && i.isOnScreen() && i.distanceTo() <= 5 && i.getDisplayedText().contains(Players.getLocal().getName())) {
                        SandwichLady.sandwichLady = i;
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public void execute() {

            sandwichLady.interact("Talk");
            Time.sleep(2000);
            Item[] sandwich = Inventory.getItems(6962);
            for (int x = 0; x < sandwich.length; x++) {
                sandwich[x].interact("Eat");
                Time.sleep(2500);
            }
            System.out.println("Sandwich Lady Random Solved");
        }
    }


    public static class OldMan implements Strategy {

        static Npc oldMan;

        @Override
        public boolean activate() {

            Npc[] oldMan = Npcs.getNearest(410);
            if (oldMan != null) {
                for (Npc i : oldMan) {
                    if (i != null && i.isOnScreen() && i.distanceTo() <= 5&& i.getDisplayedText().contains(Players.getLocal().getName())) {
                        OldMan.oldMan = i;
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public void execute() {

            oldMan.interact("Talk");
            Time.sleep(2000);
            System.out.println("Old Man Random Solved");
        }
    }

    public static class BobsIsland implements Strategy {

        private final SceneObject[] portal = SceneObjects.getNearest(8987);

        ArrayList<Tile> done = new ArrayList<>();

        private static Npc bob;

        @Override
        public boolean activate() {

            Npc[] bob = Npcs.getNearest(1091);
            if (bob != null) {
                for (Npc i : bob) {
                    if (i != null) {
                        done.clear();
                        BobsIsland.bob = i;
                        return true;
                    }
                }
            }

            return false;

        }

        @Override
        public void execute() {
            for (int x = 0; x < portal.length; x++) {


                if (!done.contains(portal[x].getLocation())) {
                    if (!portal[x].isOnScreen()) {
                        Camera.turnTo(portal[x]);

                    }
                    portal[x].getLocation().clickMM();
                    Time.sleep(2000);
                    while (Players.getLocal().isWalking()) {
                        Time.sleep(500);
                    }
                    portal[x].interact("Enter");
                    Time.sleep(2000);
                    done.add(portal[x].getLocation());
                }

            }
            System.out.println("Evil Bob Random Solved");
        }
    }

    public class Checkafk implements Strategy {

        @Override
        public boolean activate() { // if your afk
            if (Players.getLocal().getAnimation() == 1353){
                System.out.println("your afk");
                return true; //yes you are afk
            }
            return false; // no your not afk

        }



        @Override
        public void execute() {
            if (Players.getLocal().getAnimation() == 1353) { //if the animation is the afk anim
                System.out.println("trying the new afk fix");
                Keyboard.getInstance().sendKeys("::unafk");
                Time.sleep(1000);
                Camera.setRotation(180);





                } else {
               // LogArea.log("Your not afk so the script is continuing");
                return;
            }

        }

    }


    public class ChopTrees implements Strategy {

        final SceneObject[] Tree1 = SceneObjects.getNearest(trees);
        final SceneObject Tree = Tree1[0];

        @Override
        public boolean activate() {
            return Tree1 !=null
                    && Tree1.length > 12
                    && Tree.isOnScreen()
                    && Players.getLocal().getAnimation() == -1
                    && !Inventory.isFull()
                    && !Players.getLocal().isWalking()
                    && !Players.getLocal().isInCombat();

        }


        @Override
        public void execute() {
            final SceneObject[] Tree1 = SceneObjects.getNearest(trees);
            if(Tree1 != null){
                Camera.turnTo(Tree1[0]);
                Tree1[0].interact("Tree");
             //   LogArea.log("chopping the tree because your animation is minus 1");
                Time.sleep(1000);

            }
        }
    }




    public class MakeShaft implements Strategy {
        @Override
        public boolean activate() {
            if (Inventory.isFull()){ //changed to .isFull to stop the script stopping under a certain amount of logs
               // LogArea.log("checking if your inventory is full");
                return true;
            }
            return false;
        }



        @Override
        public void execute() {
            if (Inventory.getItems(946, 1511) != null) {
                if (Tab.INVENTORY.isOpen()) {
                    Item[] knife = Inventory.getItems(946);
                    Item[] log = Inventory.getItems(1511);
                    knife[0].interact("Use");
                    Time.sleep(1000);
                  //  LogArea.log("making the arrow shafts because your inv is full");
                    Mouse.getInstance().click(log[0].getScreenLocation());
                    Time.sleep(1000);
                    Menu.interact("Make X", new Point(73, 420));
                    Time.sleep(1000);
                    Keyboard.getInstance().sendKeys("123");
                    Time.sleep(5000);
                    if ((Interfaces.getChatboxInterfaceId() == 2492)) {
                        // click make all/
                        Mouse.getInstance().click(new Point(80, 419));

                    }

                } else if (!Tab.INVENTORY.isOpen()) {
                    // open inventory
                    Mouse.getInstance().click(new Point(643, 184));
                    System.out.println("opening the inventory");
                    //LogArea.log("opening your inventory as it was closed");

                }
            }
        }
    }

}