package Client;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Disc extends Circle{
	
	private int player;

	public Disc(boolean turn) {
		this.setRadius(50);
		if(turn) {
			this.setFill(Color.YELLOW);
			player = 1;
		}else {
			this.setFill(Color.RED);
			player = 0;
		}
	}
	
	public int getPlayer() {
		return player;
	}
}
