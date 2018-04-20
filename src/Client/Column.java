package Client;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Column extends Group{

	
	public Column(int i) {
		Rectangle r = new Rectangle(Game.WORLD_WIDTH*i/7,0, Game.WORLD_WIDTH/7,Game.WORLD_HEIGHT);
		r.setFill(Color.rgb(0, 0, 255, 0.2));
		this.getChildren().add(r);
		r.setOnMouseClicked(event -> {
			Game.spawnDisc(i);
		});
	}
}
