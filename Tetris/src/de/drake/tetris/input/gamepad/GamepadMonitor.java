package de.drake.tetris.input.gamepad;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Controller.Type;

public class GamepadMonitor {
	
	private Controller controller;
	
	private Component pov;
	
	private GamepadKey up = new GamepadKey(KeyEvent.VK_UP, '?');
	private GamepadKey down = new GamepadKey(KeyEvent.VK_DOWN, '?');
	private GamepadKey left = new GamepadKey(KeyEvent.VK_LEFT, '?');
	private GamepadKey right = new GamepadKey(KeyEvent.VK_RIGHT, '?');
	
	private HashMap<Component, GamepadKey> component2button;
	
	public GamepadMonitor(final int gamepadNr) {
		int currentGamepadNr = 0;
		for (Controller controller : ControllerEnvironment.getDefaultEnvironment().getControllers()) {
			if (!controller.getType().equals(Type.GAMEPAD))
        		continue;
			if (currentGamepadNr == gamepadNr) {
				this.controller = controller;
				break;
			} else {
				currentGamepadNr++;
			}
		}
		if (this.controller != null) {
			this.pov = controller.getComponent(Identifier.Axis.POV);
			this.component2button.put(controller.getComponent(Identifier.Button._0),
					new GamepadKey(KeyEvent.VK_0, '0'));
			this.component2button.put(controller.getComponent(Identifier.Button._1),
					new GamepadKey(KeyEvent.VK_1, '1'));
			this.component2button.put(controller.getComponent(Identifier.Button._2),
					new GamepadKey(KeyEvent.VK_2, '2'));
			this.component2button.put(controller.getComponent(Identifier.Button._3),
					new GamepadKey(KeyEvent.VK_3, '3'));
			this.component2button.put(controller.getComponent(Identifier.Button._4),
					new GamepadKey(KeyEvent.VK_4, '4'));
			this.component2button.put(controller.getComponent(Identifier.Button._5),
					new GamepadKey(KeyEvent.VK_5, '5'));
			this.component2button.put(controller.getComponent(Identifier.Button._6),
					new GamepadKey(KeyEvent.VK_6, '6'));
			this.component2button.put(controller.getComponent(Identifier.Button._7),
					new GamepadKey(KeyEvent.VK_7, '7'));
			this.component2button.put(controller.getComponent(Identifier.Button._8),
					new GamepadKey(KeyEvent.VK_8, '8'));
			this.component2button.put(controller.getComponent(Identifier.Button._9),
					new GamepadKey(KeyEvent.VK_9, '9'));
		}
	}
	
	public void triggerKeyEvents() {
		if (this.controller == null) {
			return;
		}
		this.controller.poll();
		this.updatePOVKeys();
		this.updateButtons();
		}
	
	private void updatePOVKeys() {
		float povData = this.pov.getPollData();
		if (povData == 0.0) {
			this.left.setPressed(false);
			this.up.setPressed(false);
			this.right.setPressed(false);
			this.down.setPressed(false);
		} else if (povData == 1.0) {
			this.left.setPressed(true);
			this.up.setPressed(false);
			this.right.setPressed(false);
			this.down.setPressed(false);
		} else if (povData == 0.125) {
			this.left.setPressed(true);
			this.up.setPressed(true);
			this.right.setPressed(false);
			this.down.setPressed(false);
		} else if (povData == 0.25) {
			this.left.setPressed(false);
			this.up.setPressed(true);
			this.right.setPressed(false);
			this.down.setPressed(false);
		} else if (povData == 0.375) {
			this.left.setPressed(false);
			this.up.setPressed(true);
			this.right.setPressed(true);
			this.down.setPressed(false);
		} else if (povData == 0.5) {
			this.left.setPressed(false);
			this.up.setPressed(false);
			this.right.setPressed(true);
			this.down.setPressed(false);
		} else if (povData == 0.625) {
			this.left.setPressed(false);
			this.up.setPressed(false);
			this.right.setPressed(true);
			this.down.setPressed(true);
		} else if (povData == 0.75) {
			this.left.setPressed(false);
			this.up.setPressed(false);
			this.right.setPressed(false);
			this.down.setPressed(true);
		} else if (povData == 0.875) {
			this.left.setPressed(true);
			this.up.setPressed(false);
			this.right.setPressed(false);
			this.down.setPressed(true);
		}
	}
	
	private void updateButtons() {
		for (Component button : this.component2button.keySet()) {
			this.component2button.get(button).setPressed(button.getPollData() == 1.0);
		}
	}
	
	public void setKeyListener(final KeyListener listener) {
		this.up.setListener(listener);
		this.right.setListener(listener);
		this.down.setListener(listener);
		this.left.setListener(listener);
		for (GamepadKey key : this.component2button.values()) {
			key.setListener(listener);
		}
	}

	public static void testGamepad() {
		Controller[] ca = ControllerEnvironment.getDefaultEnvironment().getControllers();

        for(int i =0;i<ca.length;i++){
        	
        	if (!ca[i].getType().equals(Type.GAMEPAD))
        		continue;

            /* Get the name of the controller */
            System.out.println(ca[i].getName());
            System.out.println("Type: "+ca[i].getType().toString());

            /* Get this controllers components (buttons and axis) */
            Component[] components = ca[i].getComponents();
            System.out.println("Component Count: "+components.length);
            for(int j=0;j<components.length;j++){
                
                /* Get the components name */
                System.out.println("Component "+j+": "+components[j].getName());
                System.out.println("    Identifier: "+ components[j].getIdentifier().getName());System.out.print("    ComponentType: ");
                if (components[j].isRelative()) {
                    System.out.print("Relative");
                } else {
                    System.out.print("Absolute");
                }
                if (components[j].isAnalog()) {
                    System.out.print(" Analog");
                } else {
                    System.out.print(" Digital");
                }
                System.out.println();
            }
        }
	}
	
	public static void testGamepadInput() {
		  Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
	      Controller firstJoypad=null;
	      for(int i=0;i<controllers.length && firstJoypad==null;i++) {
    	     if(controllers[i].getType()==Controller.Type.GAMEPAD) {
                 // Found a gamePad
    	    	 firstJoypad = controllers[i];
    	     }
	      }
          if(firstJoypad==null) {
             // Couldn't find a GAMEpAD
             System.out.println("Found no gamepad");
             System.exit(0);
          }
          
          System.out.println("First gamepad is: " + firstJoypad.getName());
          
          while(true) {
        	  firstJoypad.poll();
              Component[] components = firstJoypad.getComponents();
              StringBuffer buffer = new StringBuffer();
              for(int i=0;i<components.length;i++) {
                 if(i>0) {
                    buffer.append(", ");
                 }
                 buffer.append(components[i].getName());
                 buffer.append(": ");
                 buffer.append(components[i].getPollData());
               }
               System.out.println(buffer.toString());
               
               try {
                  Thread.sleep(20);
               } catch (InterruptedException e) {
               }
          }
	}

}
