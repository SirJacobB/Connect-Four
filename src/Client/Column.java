package Client;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Column extends Group{
	
	private Rectangle r;
	private Circle bgCircle;
	
	public Column(int i) {
		createRectangle(i);
		setColour();
		columnInteraction(i);
		createBgCircles();
	}
	
	private void createRectangle(int cr) {
		r = new Rectangle(Game.WORLD_WIDTH*cr/7,0, Game.WORLD_WIDTH/7,Game.WORLD_HEIGHT);
		this.getChildren().add(r);
	}
	
	private void setColour() {
		r.setFill(Color.rgb(92, 189, 234));
	}
	
	private void columnInteraction(int colu) {
		r.setOnMouseClicked(event -> {
			Game.spawnDisc(colu);
		});
	}
	
	private void createBgCircles() {
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 6; j++){
				bgCircle = new Circle(50);
				bgCircle.setFill(Color.rgb(227, 242, 253));
				this.getChildren().add(bgCircle);
				bgCircle.setTranslateX(i*Game.WORLD_WIDTH/7 + Game.WORLD_WIDTH/14);
				bgCircle.setTranslateY(j*Game.WORLD_HEIGHT/6 + bgCircle.getRadius() + bgCircle.getRadius()/3);
			}
		}
	}
}
