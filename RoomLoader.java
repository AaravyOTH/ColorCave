import org.w3c.dom.Node;

public class RoomLoader extends AbstractRoomLoader {
    public RoomLoader(){
    }
    @Override
    public void load() {
        Room room1 = getStart();
        Room room2 = new Room("two", null);
        Room room3 = new Room("three", null);
        Room room4 = new Room("four", null);
        Room room5 = new Room("five", null);
        Room room6 = new Room("six",null);
        Room room7 = getEnd();

        room1.addDoor(Door.RED, room2);
        room1.addDoor(Door.BLUE, room3);
        room2.addDoor(Door.GREEN, room4);
        room2.addDoor(Door.PINK, room5);
        room3.addDoor(Door.BLUE, room6);
        room5.addDoor(Door.RED, room7);


    }

    @Override
    public Room getStart() {
        
         Room room1 = new Room("one", "start");
         return room1;
    }

    @Override
    public Room getEnd() {
        Room room7 = new Room("seven", "end");
        return room7;   
     }
    
}
