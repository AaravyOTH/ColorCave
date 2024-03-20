import java.util.*;
import java.util.Map.Entry;

public class CaveBot {
    private TreeMap<Integer, ArrayList<Integer>> paths;

    public void load(){
        AbstractRoomLoader loader = new RoomLoader();
        //loader = loader.deserialize("");
         
    }

    public CaveBot(){
        paths = new TreeMap<Integer, ArrayList<Integer>>();
    }
    public void move(Room startRoom){
        System.out.println("Started");
        Room currentRoom = startRoom;
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(currentRoom.getID());
        for(Door d: currentRoom.getDoors()){
            System.out.println("move method 1");
            move(d, currentRoom, ids);
        }
        Entry<Integer, ArrayList<Integer>> bestPath = paths.pollFirstEntry();
        System.out.println(bestPath.getKey() + " steps and the path was " + bestPath.getValue());
    }
    private void move(Door door, Room currentRoom, ArrayList<Integer> ids){
        System.out.println("move method");
        currentRoom = currentRoom.enter(door);
        ids.add(currentRoom.getID());
        if(currentRoom.getDescription().equalsIgnoreCase("end")){
            paths.put(ids.size(), ids);
            return;
        }
        //RECURSIVE CALL

        //Add or not to TreeMap
        if(currentRoom.getDoors().size() > 1){

            for(Door d: currentRoom.getDoors()){
                if(d != door)
                    move(d, currentRoom, ids);
            }
        }
        else{
            return;
        }

    }
}

