import org.w3c.dom.Node;

public class RoomLoader extends AbstractRoomLoader {
    public RoomLoader(){
        cave = new CaveData();
        load();
   //     serialize("YellowBlueStart;");
    }
    @Override
    public void load() {
        Room room1 = new Room("one", "");
        Room room2 = new Room("two", "");
        Room room3 = new Room("three", "");
        Room room4 = new Room("four", "");
        Room room5 = new Room("five", "");
        Room room6 = new Room("six","");
        Room room7 = new Room("seven", "");
        room1.addDoor(Door.RED, room2);
        room1.addDoor(Door.BLUE, room3);
        room2.addDoor(Door.GREEN, room4);
        room2.addDoor(Door.PINK, room5);
        room3.addDoor(Door.YELLOW, room6);
        room5.addDoor(Door.RED, room7);
        //add rooms to cave using addRoom method (cave.addRoom(room1))
        //maybe make array
        cave.addRoom(room1);
        cave.addRoom(room2);
        cave.addRoom(room3);
        cave.addRoom(room4);
        cave.addRoom(room5);
        cave.addRoom(room6);
        cave.addRoom(room7);

        //set start and set end
        cave.setStart(room1);
        cave.setEnd(room7);

    }



     public static void main(String[] args) {
        //AbstractRoomLoader rL = new RoomLoader();
       // rL = rL.deserialize("YelloBlueStart.ser");
        //System.out.println(rL.getStart());
     }
    
}
