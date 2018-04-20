package Client;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Disc extends Circle{

	public Disc(boolean turn) {
		this.setRadius(25);
		if(turn) {
			this.setFill(Color.YELLOW);
		}else {
			this.setFill(Color.RED);
		}
	}
}
