package gameFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import objects.GameObject;
import reflection.Reflection;
import stage.Game;
import jgame.JGObject;
import jgame.platform.JGEngine;
import util.reflection.*;
/*
 * @Author: Steve (Siyang) Wang
 */
public class GameFactory {
    private JGEngine myEngine;
    private String myOrder;
    private int  myLevelID, mySceneID;
    private Game myGame;
    private GameObject myObject;
    public static final String RESOURCE_PACKAGE = "engineResources/";
    public static final String DEFAULT_FORMAT= "DataFormat";
    public static final String DEFAULT_NULL = "null";
    
    private static final String TEST_STRING = ""; 

        protected ResourceBundle myFormat;
        protected GameFactory(JGEngine engine){
            myEngine = engine;
            myFormat = ResourceBundle.getBundle(RESOURCE_PACKAGE + DEFAULT_FORMAT);
        }
        
        /**
         * Only couple things as argument, use reflection to creat or modify object instance.
         */
        public GameObject processOrder(Game game, int levelID, int sceneID, String order){
//            myLevelID = levelID;
//            mySceneID = sceneID;
//            myGame = game;
//            myOrder = order;
//            myObject = object;
//            checkModifyOrCreate(order);
            parseOrder(order);
            try{
                    Object myObject = Reflection.createInstance(myFormat.getString(myMoveMethod), this);
                    Reflection.callMethod(behavior, "move", mySetXSpeed, mySetYSpeed);      
            } catch (Exception e){
                    e.printStackTrace(); //should never reach here
            }


            return null;
            
            /*this part (Enum) used when creating many instances of object at the same time ==> when playing game */
            List<GameObject> results = new ArrayList<GameObject>();
            Enumeration<String> iter = myFormat.getKeys();
        }

        private void parseOrder (String order) {
            // TODO Auto-generated method stub
            if (testLegitimateOrder(order)){
                String[] orderSplit = order.split("=");
                String instruction = orderSplit[0];
//                checkModifyOrCreate(orderSplit[0]);
                List<String> parameterSplit = Arrays.asList(orderSplit[1].split("\\,"));
                for 
            }
        }

        private boolean testLegitimateOrder (String order) {
            if (order.contains("=")) {
                return true;
            } else {
                throw new IllegalArgumentException("String " + order + " does not contain =");
                return false;
            }
        }
        
        private GameObject reflectCreate(){
            
        }
        
        private void reflectModify(){
            
        }

        private void checkModifyOrCreate (String order) {
            // TODO Auto-generated method stub
            order.toLowerCase().contains()
            str1.toLowerCase().contains(str2.toLowerCase())
            
        }

        
        
        
        
        
        
        /**
         * Takes Object instance and String order as argument, for modification.
         */
        public void processOrder(GameObject object, String order){
            parseOrder(order);
            try{
//                http://docs.oracle.com/javase/tutorial/reflect/member/ctorInstance.html
//                http://java.sun.com/docs/books/tutorial/reflect/member/methodInvocation.html
//                http://docs.oracle.com/javase/tutorial/reflect/member/fieldValues.html
//                Object instance = Class.forName("Foobar").newInstance();
                Object behavior = Reflection.createInstance(myFormat.getString(myMoveMethod), this);
                Reflection.callMethod(behavior, "move", mySetXSpeed, mySetYSpeed);      
            } catch (Exception e){
                e.printStackTrace(); //should never reach here
            }

        }



}
