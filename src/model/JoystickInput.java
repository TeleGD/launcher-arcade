package model;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import java.io.File;

public class JoystickInput {
    public static void main(String[] args) {
        System.setProperty("net.java.games.input.librarypath", new File("sys").getAbsolutePath());
        Controller[] ca = ControllerEnvironment.getDefaultEnvironment().getControllers();

        for(int i =0 ; i<ca.length; i++) {
            if (ca[i].getType() == Controller.Type.GAMEPAD) {
                while (true) {
                    Component[] components = ca[i].getComponents();
                    for (int j = 0; j < components.length; j++) {
                        if (components[i].isAnalog()) {
                            System.out.println(components[i].getPollData());
                        }

                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
