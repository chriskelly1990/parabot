package PestControl;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.events.MessageEvent;
import org.rev317.min.api.events.listeners.MessageListener;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;


@ScriptManifest(author = "Scriptss", category = Category.MINIGAMES, description = "Completes pest control on ikov", name = "Ipestcontrol", servers = { "Ikov" }, version = 1.0)

public class PestControl extends Script implements Paintable, MessageListener {

    private Image getImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch(IOException e) {
            return null;
        }
    }

    private final Color color1 = new Color(255, 255, 255);
    private final Font font1 = new Font("Trebuchet MS", 0, 14);
    private final Font font2 = new Font("Trebuchet MS", 0, 12);
    private final Image img1 = getImage("http://i.imgur.com/mvy1nzg.png");

    public void paint(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
        g.setColor(color1);
        g.drawImage(img1, 0, 0, null);


        // Other info
        g.setFont(font2);
        g.drawString("Time Run: " +(runTime(startTime)), 30, 55);
        g.drawString("Trips Made: " +Trips, 30, 86);
        g.drawString("Trips Failed: " +Failed, 175, 86);




        // Status
        g.setFont(font1);
        g.drawString("Current status: " +status, 32, 116);
    }

    static String status = "Starting up...";
    private static long startTime = System.currentTimeMillis();
    int Failed = 0;
    static int Trips = 0;
    static int points = 0;

    private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();


    public boolean onExecute() {

        strategies.add(new OnDock());
        strategies.add(new InBoat());
        strategies.add(new InPc());
        strategies.add(new InBrawlers());
        provide(strategies);
        return true;
    }

    public void messageReceived(MessageEvent m)
    {
        if (m.getMessage().contains("gain no points")) {
            Failed += 1;
        }
        if (m.getMessage().contains("board the")) {
            Trips += 1;
        }

    }


    public void onFinish() {
        System.out.println("trips: "+Trips);
        System.out.println("trips failed: "+Failed);
        System.out.println("rune time: "+(runTime(startTime)));
       }

    public String runTime(long i)
    {
        DecimalFormat nf = new DecimalFormat("00");

        long millis = System.currentTimeMillis() - i;
        long hours = millis / (1000 * 60 * 60);
        millis -= hours * (1000 * 60 * 60);
        long minutes = millis / (1000 * 60);
        millis -= minutes * (1000 * 60);
        long seconds = millis / 1000;

        return nf.format(hours) + ":" + nf.format(minutes) + ":" + nf.format(seconds);
    }





}
