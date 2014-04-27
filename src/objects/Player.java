package objects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import engineManagers.AnimationManager;
import engineManagers.BloodManager;
import engineManagers.CollisionManager;
import engineManagers.RevivalManager;
import engineManagers.ScoreManager;
import reflection.Reflection;
import saladConstants.SaladConstants;
import util.AttributeMaker;
import util.SaladUtil;
/**
 * @Author: Justin (Zihao) Zhang
 */
public class Player extends GameObject {
	
	protected Map<Integer, String> myKeyMap;
	protected List<String> myNonClearKeys;
	protected double myMovingXSpeed;
	protected double myMovingYSpeed;
	
	public Player(int uniqueID, String gfxname, int xsize, int ysize, double xpos, double ypos, 
			String name, int collisionId, int lives, 
			CollisionManager collisionManager, ScoreManager scoreManager, BloodManager bloodManager, RevivalManager revivalManager) {
		super(uniqueID, gfxname, xsize, ysize, xpos, ypos, name, collisionId, lives, collisionManager, scoreManager, bloodManager, revivalManager);
		myKeyMap = new HashMap<Integer, String>();
		myMovingXSpeed = SaladConstants.DEFAULT_ACTOR_SPEED;
		myMovingYSpeed = SaladConstants.DEFAULT_ACTOR_SPEED;
		myNonClearKeys = SaladUtil.getListFromPropertiesFile(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE + SaladConstants.NONCLEAR_KEYS_FILE, SaladConstants.NON_CLEAR_KEYS, SaladConstants.SEPARATOR);
	}
	
	public void setKey(int key, String type){
		myKeyMap.put(key, type);
	}
	
	@Override
	public void move(){
		checkKeys();
		super.move();
	}
	
	protected void checkKeys(){
		for(int key: myKeyMap.keySet()){
			if(eng.getKey(key)){
				String methodName = myKeyMap.get(key);
				Reflection.callMethod(this, methodName);
				if(!myNonClearKeys.contains(methodName)) eng.clearKey(key);
			}
		}
	}
	
	public void moveUp(){
		if (y > 0) {
			y -= myMovingYSpeed*eng.getGameSpeed();
			ydir = SaladConstants.NEGATIVE_DIRECTION;
		}
	}
	
	public void moveDown(){
		if (y + getYSize() < eng.pfHeight()) {
			y += myMovingYSpeed*eng.getGameSpeed();
			ydir = SaladConstants.POSITIVE_DIRECTION;
		}
	}
	
	public void moveLeft(){
		if (x > 0) {
			x -= myMovingXSpeed*eng.getGameSpeed();
			xdir = SaladConstants.NEGATIVE_DIRECTION;
		}
	}
	
	public void moveRight(){
		if (x + getXSize() < eng.pfWidth()) {
			x += myMovingXSpeed*eng.getGameSpeed();
			xdir = SaladConstants.POSITIVE_DIRECTION;
		}
	}
	
	@Override
	public List<String> getAttributes(){
		List<String> answer = super.getAttributes();
		for(int key: myKeyMap.keySet()){
			answer.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_PLAYER, SaladConstants.ID, myUniqueID, 
					SaladConstants.SET_KEY, false, key, myKeyMap.get(key)));
		}
		return answer;
	}

}
