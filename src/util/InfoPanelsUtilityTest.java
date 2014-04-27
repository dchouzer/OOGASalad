package util;

import static org.junit.Assert.*;

import org.junit.Test;

public class InfoPanelsUtilityTest {

	HTMLFormattedInfoPanelsFactory factory ; 
	
	public InfoPanelsUtilityTest(){
		factory = new HTMLFormattedInfoPanelsFactory(); 
	}
		
	public void test() {
		factory.constructAndDisplayInfoPanel("help","./src/game_authoring_environment/resources/help.html");
		factory.constructAndDisplayInfoPanel("about","./src/game_authoring_environment/resources/about.html"); 
	}
	
	public static void main(String [] args){
		InfoPanelsUtilityTest test = new InfoPanelsUtilityTest(); 
		test.test();
	}
	

}
