import java.util.*;
import java.util.Map.Entry;
import java.awt.*;

//Our algorith recursively goes through the map by going sending different paths through all the children of the door it is in
//accounts so it does not backtrack 
//takes in shortest path possible
//still some fixes need to be made, work in progress
public class CaveBot {
    private TreeMap<Integer, ArrayList<String>> paths;
    private static Room currRoom;
    Room room, end;
    Map<Rectangle, Door> path;
    AbstractRoomLoader loader;
    public void load(){
        loader = new RoomLoader();
        //loader.load();
        loader.deserialize("TrialMaze4-2.ser");
        room = loader.getStart();
        end = loader.getEnd();
        currRoom = loader.getStart();
        path = new HashMap<Rectangle, Door>();
       // System.out.println("Current Room: " + currRoom.getDoors());
         
    }

    public CaveBot(){
        paths = new TreeMap<Integer, ArrayList<String>>();
    }
    public void move(Room startRoom){
        //System.out.println("Started");
        Room currentRoom = startRoom;
        ArrayList<String> ids = new ArrayList<>();
        //ids.add(currentRoom.getName());
        //System.out.println(currentRoom.getDoors());
        for(Door d: currentRoom.getDoors()){
           // System.out.println("move method 1");
            move(d, currentRoom, new ArrayList<String>());
        }
        //System.out.println(paths);
        Entry<Integer, ArrayList<String>> bestPath = paths.pollFirstEntry();
        System.out.println(bestPath.getKey() + " steps and the path was " + bestPath.getValue());
    }
    private void move(Door door, Room currentRoom, ArrayList<String> ids){
        
        ArrayList<String> tempIds = (ArrayList<String>)ids.clone();
        //System.out.println("move method");
        currentRoom = currentRoom.enter(door);
        //if(currentRoom.getDoors().size() > 1 || currentRoom.getDescription() == "end")
        if(!tempIds.contains(currentRoom.getID()))
            tempIds.add(currentRoom.getName());
        if(currentRoom.getDescription().equalsIgnoreCase("end")){
            //System.out.println("Adding path " + tempIds);
            paths.put(tempIds.size(), tempIds);
            //System.out.println("All paths " + paths);
            return;
        }
        //RECURSIVE CALL
        if(currentRoom.getDoors().size() > 1){

            for(Door d : currentRoom.getDoors()){
                if(d != door){
                    move(d, currentRoom, tempIds);
                }
              
            }
        }
        else{
            //System.out.println("Dead end hit at " + tempIds);
            return;
        }

    }

    public static void main(String[] args){
        CaveBot caveBot = new CaveBot();
        caveBot.load();
        System.out.println("Current Room: " + currRoom);
        caveBot.move(currRoom);
    }
}

