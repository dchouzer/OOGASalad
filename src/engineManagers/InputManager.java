package engineManagers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import saladConstants.SaladConstants;
import util.AttributeMaker;
/**
 * @Author: Justin (Zihao) Zhang
 * Only intended for non-player keys
 */
public class InputManager {
	
	/**
	 * Maps keys (i.e. 'G') to the name of the method in GameEngine
	 */
	protected Map<Integer, String> myKeyMap;
	
	public InputManager(){
		myKeyMap = new HashMap<Integer, String>();
	}
	
	public void setKey(int key, String methodName){
		myKeyMap.put(key, methodName);
	}
	
	public Map<Integer, String> getKeyMap(){
		return myKeyMap;
	}
	
	public List<String> getAttributes(){
		List<String> answer = new ArrayList<String>();
		for(int key: myKeyMap.keySet()){
			answer.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_INPUTMANAGER, 
					String.valueOf(key), myKeyMap.get(key)));
		}
		return answer;
	}
}
