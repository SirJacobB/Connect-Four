package Client;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Column extends Group{

	private boolean turn = true;
	
	public Column(int i) {
		Rectangle r = new Rectangle(Game.WORLD_WIDTH*i/7,0, Game.WORLD_WIDTH/7,Game.WORLD_HEIGHT);
		r.setFill(Color.rgb(0, 0, 255, 0.2));
		this.getChildren().add(r);
		r.setOnMouseClicked(event -> {
			if(turn) {
				turn = false;
				Game.spawnDisc(i, turn);
			}else {
				turn = true;
				Game.spawnDisc(i, turn);
			}
		});
	}
}
