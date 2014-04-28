package behaviors;

import java.util.List;

import engine.GameEngine;
import objects.GameObject;
import objects.NonPlayer;
import saladConstants.SaladConstants;
/**
 * @param String bullet's Image Name
 * @param int x size of the image
 * @param int y size of the image
 * @param int collision ID of the bullet
 * @param double absolute speed of the bullet
 * 
 * @author Main Justin (Zihao) Zhang
 */
public class SlowShoot extends Shootable{

	public SlowShoot(GameObject o) {
		super(o);
	}

	@Override
	public void shoot(List<Object> objects) {
		GameEngine engine = (GameEngine) myObject.eng;
		
		String imageName = (String) objects.get(0);
		int xsize = (Integer) objects.get(1);
		int ysize = (Integer) objects.get(2);
		int colid = (Integer) objects.get(3);
		double shootSpeed = (Double) objects.get(4);
		
		double shootXSpeed, shootYSpeed, xpos, ypos;
		if(myObject.xdir < 0){ xpos = myObject.x - xsize; }
		else if (myObject.xdir > 0){ xpos = myObject.x + myObject.getXSize(); }
		else{ xpos = myObject.x + myObject.getXSize()/2; }
		if(myObject.ydir < 0){ ypos = myObject.y - ysize; }
		else if (myObject.ydir > 0){ ypos = myObject.y + myObject.getYSize(); }
		else{ ypos = myObject.y + myObject.getYSize()/2; }
		shootXSpeed = myObject.xdir*shootSpeed;
		shootYSpeed = myObject.ydir*shootSpeed;
		
		NonPlayer object = engine.createActor(SaladConstants.NULL_UNIQUE_ID, imageName, xsize, ysize, xpos, ypos, SaladConstants.SHOOT_NAME, colid, SaladConstants.SHOOT_LIVES);
		object.expiry = object.expire_off_view;
		object.setSpeed(shootXSpeed, shootYSpeed);
		object.setDieBehavior(SaladConstants.REGULAR_REMOVE);
	}

}
