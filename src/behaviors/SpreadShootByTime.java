package behaviors;

import java.util.List;

import engine.GameEngine;
import objects.GameObject;
import objects.NonPlayer;
import saladConstants.SaladConstants;
/**
 * Shoot a line of bullets and spread out
 * @author Main Justin (Zihao) Zhang
 */
public class SpreadShootByTime extends Shootable{

	public SpreadShootByTime(GameObject o) {
		super(o);
	}

	/**
	 * @param String bullet's Image Name
	 * @param int x size of the image
	 * @param int y size of the image
	 * @param int collision ID of the bullet
	 * @param double absolute speed of the bullet
	 * @param the number of bullets per shoot
	 * @param int time latency, the larger the slower
	 */
	@Override
	public void shoot(List<Object> objects) {
		GameEngine engine = (GameEngine) myObject.eng;
		
		String imageName = (String) objects.get(0);
		int xsize = (Integer) objects.get(1);
		int ysize = (Integer) objects.get(2);
		int colid = (Integer) objects.get(3);
		double shootSpeed = (Double) objects.get(4);
		int times = (Integer) objects.get(5);
		int latency = (Integer) objects.get(6);
		
		double[] property = locateShootLocation(xsize, ysize, shootSpeed);
		if( ( engine.getSaladTimer() + latency) % (latency) == 0){
			for(int i = 0; i < times; i ++){
				if(myObject.getXHead() == 0)
					createShootThing(engine, imageName, xsize, ysize, property[0], property[1], colid,
							shootSpeed*(-1.0*times/2 + i), property[3]);	
				else
					createShootThing(engine, imageName, xsize, ysize, property[0], property[1], colid,
							property[2], shootSpeed*(-times/2 + i));
			}
		}
	}

}