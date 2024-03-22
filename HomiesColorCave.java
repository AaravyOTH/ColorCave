import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class HomiesColorCave extends JPanel implements MouseListener {

	JFrame frame;
	AbstractRoomLoader loader;
	Room room, end;
	Map<Rectangle, Door> path;

	public HomiesColorCave() {
		frame=new JFrame("Color Cave");
		frame.setSize(1000,1000);
		frame.add(this);
		frame.addMouseListener(this);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		loader = new RoomLoader();
		//loader.deserialize("YellowBlueStart.ser"); //uncomment this when using  .ser file.
		loader.load();
		room = loader.getStart();
		end = loader.getEnd();
		path = new HashMap<Rectangle, Door>();
		//loader.serialize("Homies.ser");
	}

	public void paintComponent(Graphics g) {
		////////////// PAINT COMPONENT /////////////////
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;

		////////////// BACKGROUND /////////////////
		g2.setColor(Color.BLACK);
		g2.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g2.setColor(Color.DARK_GRAY);
		g2.fillArc(0, 0, 1000, 1000, 0, 180);
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillArc(100, 100, 800, 800, 0, 180);
		g2.setColor(Color.GRAY);
		g2.fillRect(0, 500, 1000, 500);


		////////////// HEADER /////////////////
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Arial", Font.BOLD, 44));
		g2.drawString("COLOR CAVE!",80, 40);
		g2.setFont(new Font("Arial", Font.BOLD, 24));
		g2.drawString(room.getName(),80, 80);
		g2.drawString(room.getDescription(),80, 120);
		g2.drawString("Number of Moves: "+Room.getNumMoves(),80, 600);

		/////////// PAINT DOORS ///////////////
		Set<Door> doors = room.getDoors();
		int count = 0;
		for (Door door : doors) {
			g2.setColor(enumToColor(door));
			Rectangle r = new Rectangle(200 + 150*count,300,100,200);
			g2.fill(r);
			path.put(r, door);
			count++;
		}
	}

	public void mouseClicked(MouseEvent e) {
		for (Map.Entry<Rectangle, Door> entry : path.entrySet()) {
			if (entry.getKey().contains(e.getX(),e.getY())){
				room = room.enter(entry.getValue());
			}
		}
		repaint();
	}

	// Other mouse listener methods we don't need to use
	public void mouseExited(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}

	private Color enumToColor(Door d) {
		switch(d){
			case RED: return Color.RED;
			case BLUE: return Color.BLUE;
			case GREEN: return Color.GREEN;
			case PINK: return Color.PINK;
			case YELLOW: return Color.YELLOW;

			default:
				return Color.WHITE;
		}
	}

	public static void main(String[] args) {
		HomiesColorCave app = new HomiesColorCave();
	}

}