package util;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Main Justin (Zihao) Zhang
 * Intended for use of some general methods
 *
 */
public class Util {
	
	public static List<String> convertStringArrayToList(String[] array){
		List<String> answer = new ArrayList<String>();
		for(int i = 0; i < array.length; i ++){
			answer.add(array[i]);
		}
		return answer;
	}
	
	public static void printStringList(List<String> list){
		System.out.println();
		System.out.print("StringList print starts: ");
		for(String s: list){
			System.out.print(s + "/");
		}
		System.out.print("//StringList print ends.");
	}

}