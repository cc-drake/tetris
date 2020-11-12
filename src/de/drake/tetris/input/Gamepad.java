package de.drake.tetris.input;

import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import de.drake.tetris.config.Config;
import de.drake.tetris.input.util.Key;
import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Controller.Type;

public class Gamepad extends InputDevice implements Runnable {
	
	private final Controller controller;
	
	private final int lfdNr;
	
	private final Component pov;
	
	private final Key up;
	private final Key down;
	private final Key left;
	private final Key right;
	
	private final HashMap<Component, Key> component2key = new HashMap<Component, Key>();
	
	private boolean hasFocus = true;
	
	Gamepad(final Controller controller, final int lfdNr) {
		super(InputDevice.GAMEPAD);
		this.controller = controller;
		this.lfdNr = lfdNr;
		this.pov = controller.getComponent(Identifier.Axis.POV);
		this.up = new Key(KeyEvent.VK_UP, "\u2191");
		this.down = new Key(KeyEvent.VK_DOWN, "\u2193");
		this.left = new Key(KeyEvent.VK_LEFT, "\u2190");
		this.right = new Key(KeyEvent.VK_RIGHT, "\u2192");
		
		this.component2key.put(controller.getComponent(Identifier.Button._0),
				new Key(KeyEvent.VK_0, "Taste 0"));
		this.component2key.put(controller.getComponent(Identifier.Button._1),
				new Key(KeyEvent.VK_1, "Taste 1"));
		this.component2key.put(controller.getComponent(Identifier.Button._2),
				new Key(KeyEvent.VK_2, "Taste 2"));
		this.component2key.put(controller.getComponent(Identifier.Button._3),
				new Key(KeyEvent.VK_3, "Taste 3"));
		this.component2key.put(controller.getComponent(Identifier.Button._4),
				new Key(KeyEvent.VK_4, "Taste 4"));
		this.component2key.put(controller.getComponent(Identifier.Button._5),
				new Key(KeyEvent.VK_5, "Taste 5"));
		this.component2key.put(controller.getComponent(Identifier.Button._6),
				new Key(KeyEvent.VK_6, "Taste 6"));
		this.component2key.put(controller.getComponent(Identifier.Button._7),
				new Key(KeyEvent.VK_7, "Taste 7"));
		this.component2key.put(controller.getComponent(Identifier.Button._8),
				new Key(KeyEvent.VK_8, "Taste 8"));
		this.component2key.put(controller.getComponent(Identifier.Button._9),
				new Key(KeyEvent.VK_9, "Taste 9"));
		this.component2key.remove(null);
		
		Thread thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		long timePerTick = 1000000000L / Config.fps;
		long lastTick = System.nanoTime();
		while(true) {
			if ((System.nanoTime() - lastTick) >= timePerTick) {
				lastTick += timePerTick;
				if (this.controller.poll() == false)
					return;
				if (this.hasFocus) {
					this.updatePOVKeys();
					this.updateButtons();
				}
			}
		}
	}
	
	private void updatePOVKeys() {
		float povData = this.pov.getPollData();
		if (povData == 0.0) {
			this.keyReleased(this.left);
			this.keyReleased(this.up);
			this.keyReleased(this.right);
			this.keyReleased(this.down);
		} else if (povData == 1.0) {
			this.keyPressed(this.left);
			this.keyReleased(this.up);
			this.keyReleased(this.right);
			this.keyReleased(this.down);
		} else if (povData == 0.125) {
			this.keyPressed(this.left);
			this.keyReleased(this.up);
			this.keyReleased(this.right);
			this.keyReleased(this.down);
		} else if (povData == 0.25) {
			this.keyReleased(this.left);
			this.keyPressed(this.up);
			this.keyReleased(this.right);
			this.keyReleased(this.down);
		} else if (povData == 0.375) {
			this.keyReleased(this.left);
			this.keyReleased(this.up);
			this.keyPressed(this.right);
			this.keyReleased(this.down);
		} else if (povData == 0.5) {
			this.keyReleased(this.left);
			this.keyReleased(this.up);
			this.keyPressed(this.right);
			this.keyReleased(this.down);
		} else if (povData == 0.625) {
			this.keyReleased(this.left);
			this.keyReleased(this.up);
			this.keyPressed(this.right);
			this.keyReleased(this.down);
		} else if (povData == 0.75) {
			this.keyReleased(this.left);
			this.keyReleased(this.up);
			this.keyReleased(this.right);
			this.keyPressed(this.down);
		} else if (povData == 0.875) {
			this.keyPressed(this.left);
			this.keyReleased(this.up);
			this.keyReleased(this.right);
			this.keyReleased(this.down);
		}
	}
	
	private void updateButtons() {
		for (Component button : this.component2key.keySet()) {
			if (button.getPollData() == 1.0) {
				this.keyPressed(this.component2key.get(button));
			} else {
				this.keyReleased(this.component2key.get(button));
			}
		}
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		this.hasFocus = true;
	}

	@Override
	public void focusLost(FocusEvent e) {
		this.hasFocus = false;
		this.keyReleased(this.left);
		this.keyReleased(this.up);
		this.keyReleased(this.right);
		this.keyReleased(this.down);
		for (Key key : this.component2key.values()) {
			this.keyReleased(key);
		}
	}
	
	@Override
	public String toString() {
		return "Gamepad " + (this.lfdNr + 1);
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