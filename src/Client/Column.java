package Client;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class Column extends Group{
	
	private Rectangle r;
	
	public Column(int i) {
		createRectangle(i);
		setColour();
		columnInteraction(i);
	}
	
	private void createRectangle(int cr) {
		r = new Rectangle(Game.WORLD_WIDTH*cr/7,0, Game.WORLD_WIDTH/7,Game.WORLD_HEIGHT);
		this.getChildren().add(r);
	}
	
	private void setColour() {
		r.setFill(Color.rgb(0, 0, 255, 0.2));
		r.setStroke(Color.rgb(15, 120, 255));
		r.setStrokeWidth(5.0);
		r.setStrokeType(StrokeType.OUTSIDE);
	}
	
	private void columnInteraction(int coli) {
		r.setOnMouseClicked(event -> {
			Game.spawnDisc(coli);
		});
	}
}
