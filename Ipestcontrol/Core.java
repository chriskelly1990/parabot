package PestControl;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.framework.LoopTask;
import org.rev317.min.Loader;
import org.rev317.min.accessors.Client;

import java.awt.*;
import java.lang.reflect.Field;

/**
 * @author JKetelaar
 */
public class Core extends Script implements LoopTask, Paintable {


    private Field field;
    private Field field2;
    private Client client;

    private void getFocusFields() {
        try {
            client = Loader.getClient();
            field = client.getClass().getSuperclass().getDeclaredField("x");
            field.setAccessible(true);
            field2 = client.getClass().getSuperclass().getDeclaredField("w");
            field2.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private void setFocusField(){
        try {
            field.set(client, true);
            field2.set(client, true);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onExecute() {
        this.getFocusFields();
        return true;
    }

    @Override
    public int loop() {
        return 0;
    }

    @Override
    public void paint(Graphics graphics) {
        setFocusField();
    }
}