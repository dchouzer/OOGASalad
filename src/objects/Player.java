package objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import reflection.Reflection;
import saladConstants.SaladConstants;
import util.Util;
/**
 * @Author: Justin (Zihao) Zhang
 */
public class Player extends GameObject {
    public static final String DEFAULT_RESOURCE_PACKAGE = "engineResources/";
    public static final String DEFAULT_NONCLEAR_KEYS = "PlayerKeys";
    public static final double DEFAULT_SPEED = 3;
	
	protected Map<Integer, String> myKeyMap;
	protected List<String> myNonClearKeys;
	protected List<String> mySuperMethods;
	
	public Player(int uniqueID, String gfxname, double xpos, double ypos, String name, int collisionId, int lives) {
		super(uniqueID, gfxname, xpos, ypos, name, collisionId, lives);
		myKeyMap = new HashMap<Integer, String>();
		myNonClearKeys = Util.getListFromPropertiesFile(DEFAULT_RESOURCE_PACKAGE + DEFAULT_NONCLEAR_KEYS, "NonClearKeys", ",");
		mySuperMethods = Util.getListFromPropertiesFile(DEFAULT_RESOURCE_PACKAGE + DEFAULT_NONCLEAR_KEYS, "SuperClassMethods", ",");
	}
	
	public void setKey(int key, String type){
		myKeyMap.put(key, type);
	}
	
	@Override
	public void move(){
		super.move();
		if(!myIsAir) setDir(0, 0);
		checkKeys();
	}
	
	public void checkKeys(){
		for(int key: myKeyMap.keySet()){
			if(eng.getKey(key)){
				String methodName = myKeyMap.get(key);
				Reflection.callMethod(this, methodName);
				if(!myNonClearKeys.contains(methodName)) eng.clearKey(key);
			}
		}
	}
	
	public void moveUp(){
		if (y > 0) ydir = -1;
	}
	
	public void moveDown(){
		if (y < eng.pfHeight()) ydir = 1;
	}
	
	public void moveLeft(){
		if (x > 0) xdir = -1;
	}
	
	public void moveRight(){
		if (x < eng.pfWidth())	xdir = 1;
	}
	
	@Override
	public List<String> getAttributes(){
		List<String> answer = new ArrayList<String>();
		answer.add(SaladConstants.CREATE_PLAYER + "," + SaladConstants.ID + "," + myUniqueID + "," + SaladConstants.IMAGE + "," + getGraphic() + "," + SaladConstants.POSITION + "," + x + "," + y + "," + SaladConstants.NAME + "," + getName() + "," + SaladConstants.COLLISION_ID + "," + colid);
		answer.add(SaladConstants.MODIFY_PLAYER + "," + SaladConstants.ID + "," + myUniqueID + "," + SaladConstants.MOVE + "," + myMoveBehavior + "," + mySetXSpeed + "," + mySetYSpeed);
		answer.add(SaladConstants.MODIFY_PLAYER + "," + SaladConstants.ID + "," + myUniqueID + "," + SaladConstants.DIE + "," + myDieBehavior);
		for(int otherID: myCollisionMap.keySet()){
			answer.add(SaladConstants.MODIFY_PLAYER + "," + SaladConstants.COLLISION_ID + "," + colid + "," + SaladConstants.COLLISION + "," + myCollisionMap.get(otherID) + "," + otherID);
		}
		for(int key: myKeyMap.keySet()){
			answer.add(SaladConstants.MODIFY_PLAYER + "," + SaladConstants.SET_KEY + "," + key + "," + myKeyMap.get(key));
		}
		return answer;
	}

}
