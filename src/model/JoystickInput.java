package model;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class JoystickInput {
    private Controller gamepad;
    private Component[] components;
    private int axisCount;

    public JoystickInput() {
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        gamepad = null;
        System.out.println("Controleurs : ");
        for(int i = 0; i < controllers.length; i++) {
            System.out.println(controllers[i].getName());
            if(controllers[i].getType() == Controller.Type.GAMEPAD)
                gamepad = controllers[i];
        }

        axisCount = 0;
        if(gamepad != null) {
            pollGamepad();
            for (int i = 0; i < getComponentCount(); i++) {
                if(components[i].isAnalog())
                    axisCount = i;
            }
        }
    }

    public boolean isGamepadConnected() {
        return gamepad != null;
    }

    public void pollGamepad() {
        if(isGamepadConnected()) {
            gamepad.poll();
            components = gamepad.getComponents();
        }
    }

    public int getComponentCount() {
        return components.length;
    }

    public int getAxisCount() {
        return axisCount;
    }

    public float getComponentValue(int index) {
        if(index > getComponentCount())
            return 0;

        return components[index].getPollData();
    }

    // TEST

    public static void main(String[] args) {
        JoystickInput test = new JoystickInput();

        if(test.isGamepadConnected()) {
            while (true) {
                test.pollGamepad();

                for (int i = 0; i < test.getComponentCount(); i++) {
                    float val = Math.round(test.getComponentValue(i) * 10f) / 10f;
                    System.out.print(i + (val < 0 ? ":" : ": ") + val + " ; ");
                }
                System.out.println();

                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
