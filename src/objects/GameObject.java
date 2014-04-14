package objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.jbox2d.collision.PolygonDef;
import org.jbox2d.common.Vec2;

import engineManagers.ScoreManager;
import reflection.Reflection;
import saladConstants.SaladConstants;
import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectRect;
import jboxGlue.WorldManager;
import jgame.JGObject;
/**
 * @Author: Justin (Zihao) Zhang
 */
public abstract class GameObject extends PhysicalObject {
	public static final int DEFAULT_LIVES = 1;
    public static final String DEFAULT_RESOURCE_PACKAGE = "engineResources/";
    public static final String DEFAULT_BEHAVIOR = "ObjectBehaviors";
    
	protected ResourceBundle myBehaviors;
//	protected ScoreManager myScoreManager;
	protected String myDieBehavior;
	protected String myMoveBehavior;
	protected double mySetXSpeed;
	protected double mySetYSpeed;
	protected Map<Integer, String> myCollisionMap;
//	protected Map<Integer, String>
	protected int myLives;
	protected int myUniqueID;
	protected String myJumpBehavior;
	protected double myJumpForceMagnitude;
	protected String myShootBehavior;
	protected double myInitX;
	protected double myInitY;
	
	public static final double DEFAULT_WIDTH = 10;
	public static final double DEFAULT_HEIGHT = 10;
	public static final double DEFAULT_MASS = 1;
	
	protected void initObject(int uniqueID, double xpos, double ypos){
		myBehaviors = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_BEHAVIOR);
		myCollisionMap = new HashMap<Integer, String>();
		init(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_MASS); // change later
		setPos(xpos, ypos);
		myInitX = xpos;
		myInitY = ypos;
		setLives(DEFAULT_LIVES); // change later
		myUniqueID = uniqueID;
		// copy the position and rotation from the JBox world to the JGame world
		updatePositionInJGame();
	}

	protected void updatePositionInJGame() {
		Vec2 position = myBody.getPosition();
		x = position.x;
		y = position.y;
		myRotation = -myBody.getAngle();
	}
	
	@Override
	public void setPos(double x, double y){
		super.setPos(x, y);
		myInitX = x;
		myInitY = y;
	}
	
//	protected void updatePosition
	
	protected GameObject(int uniqueID, String gfxname, double xpos, double ypos, String name, int collisionId){
		super(name, collisionId, gfxname);
		initObject(uniqueID, xpos, ypos);
	}
	
	public void init( double width, double height, double mass )
	{
		PolygonDef shape = new PolygonDef();
		shape.density = (float)mass;
		shape.setAsBox( (float)width, (float)height );
		createBody( shape );
		setBBox( -(int)width/2, -(int)height/2, (int)width, (int)height );
	}
	
	//may not be needed
	public void resetID(int uniqueID){
		myUniqueID = uniqueID;
	}
	
	//may not be needed
	public int getID(){
		return myUniqueID;
	}

	public void setDieBehavior(String s){
		myDieBehavior = s;
	}
	
	public void loseLife(){
		myLives --;
	}
	
	public void setLives(int lives){
		myLives = lives;
	}
	
	public void setJumpBehavior(String s, double forceMagnitude){
		myJumpBehavior = s;
		myJumpForceMagnitude = forceMagnitude;
	}
	
	public void setShootBehavior(String s){
		myShootBehavior = s;
	}
	
	public void setMoveBehavior(String s, double xspeed, double yspeed){
		myMoveBehavior = s;
		mySetXSpeed= xspeed;
		mySetYSpeed = yspeed;
	}
	
	public void setCollisionBehavior(String type, int id){
		myCollisionMap.put(id, type);
	}
	
	public void die(){
		behaviorNoParameterReflection(myBehaviors, myDieBehavior, "remove");	
	}
	
	public void resetCollisionID(int collisionID){
		colid = collisionID;
	}
	
	public void jump(){
		if(myJumpBehavior == null) return;
		try{
			Object behavior = Reflection.createInstance(myBehaviors.getString(myJumpBehavior), this);
			Reflection.callMethod(behavior, "jump", myJumpForceMagnitude);	
		} catch (Exception e){
			e.printStackTrace(); //should never reach here
		}
	}
	
	protected void behaviorNoParameterReflection(ResourceBundle myBundle, String myString, String methodName){
		if(myString == null) return;
		try{
			Object behavior = Reflection.createInstance(myBundle.getString(myString), this);
			Reflection.callMethod(behavior, methodName);	
		} catch (Exception e){
			e.printStackTrace(); // should never reach here
		}
	}
	
	@Override
	public void move(){
		super.move();
		if(myLives <= 0) die();
	}
	
	@Override
	public void hit (JGObject other)
    {
		if(!myCollisionMap.containsKey(other.colid)) return;
		try{
			Object behavior = Reflection.createInstance(myBehaviors.getString(myCollisionMap.get(other.colid)), this);
			Reflection.callMethod(behavior, "collide", other);	
		} catch (Exception e){
			e.printStackTrace(); // should never reach here
		}
    }
	
	public void autoMove(){
		if(myMoveBehavior == null) return;
		System.out.println("autoMove called");
		try{
			Object behavior = Reflection.createInstance(myBehaviors.getString(myMoveBehavior), this);
			Reflection.callMethod(behavior, "move", mySetXSpeed, mySetYSpeed);	
		} catch (Exception e){
			e.printStackTrace(); //should never reach here
		}
	}
	
	//need modification
	public void shoot(){
		if(myShootBehavior == null) return;
		try{
			Object behavior = Reflection.createInstance(myBehaviors.getString(myShootBehavior), this);
			Reflection.callMethod(behavior, "shoot"); // need modification
		} catch (Exception e){
			e.printStackTrace(); //should never reach here
		}
	}
	
	@Override
	public void paintShape() {
		// do nothing; image already set
	}
	
	/**
	 * Should be called by the Parser class to get all attributes of the GameObject
	 * Return a list of Strings that match with the Data Format but without Key 
	 */
	public List<String> getAttributes(){
		List<String> answer = new ArrayList<String>();
		answer.add(SaladConstants.CREATE_ACTOR + "," + SaladConstants.ID + "," + myUniqueID + "," + SaladConstants.IMAGE + "," + getGraphic() + "," + SaladConstants.POSITION + "," + x + "," + y + "," + SaladConstants.NAME + "," + getName() + "," + SaladConstants.COLLISION_ID + "," + colid);
		answer.add(SaladConstants.MODIFY_ACTOR + "," + SaladConstants.ID + "," + myUniqueID + "," + SaladConstants.MOVE + "," + myMoveBehavior + "," + mySetXSpeed + "," + mySetYSpeed);
		answer.add(SaladConstants.MODIFY_ACTOR + "," + SaladConstants.ID + "," + myUniqueID + "," + SaladConstants.DIE + "," + myDieBehavior);
		for(int otherID: myCollisionMap.keySet()){
			answer.add(SaladConstants.MODIFY_ACTOR + "," + SaladConstants.COLLISION_ID + "," + colid + "," + SaladConstants.COLLISION + "," + myCollisionMap.get(otherID) + "," + otherID);
		}
		return answer;
	}
	
}
