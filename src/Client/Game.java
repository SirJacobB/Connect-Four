package Client;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Application{
	public static double WORLD_WIDTH = 1200;
	public static double WORLD_HEIGHT = 800;

	public static ArrayList<ArrayList<Disc>> master = new ArrayList<>();

	private static Group root = new Group();
	private static Group discs = new Group();
	private Scene scene = new Scene(root, WORLD_WIDTH, WORLD_HEIGHT);

	private static boolean turnTest;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("ConnectFour");  //TODO

		root.getChildren().add(discs);

		createArrays();
		setupGrid();
		isTurn();

		primaryStage.setScene(scene);	
		primaryStage.show();
	}

	public static void createDisc(int i, boolean turn) {
		if(master.get(i).size() < 6){
			Disc disc = new Disc(turn);
			master.get(i).add(disc);
			disc.setTranslateX(i*WORLD_WIDTH/7+WORLD_WIDTH/14);
			disc.setTranslateY(WORLD_HEIGHT - ((master.get(i).size())*WORLD_HEIGHT/6));
			root.getChildren().add(disc);	//TODO
		}
	}

	public static boolean isTurn() {
		root.setOnMouseClicked(event -> {
			if(turnTest) {
				turnTest = false;
			}else {
				turnTest = true;
			}
		});
		return turnTest;
	}

	public static void spawnDisc(int i) {
		if(turnTest) {
			createDisc(i, true);
		}else {
			createDisc(i, false);
		}	
	}

	private void setupGrid() {
		for (int i = 0; i < 7; i++) {
			Column c = new Column(i);
			root.getChildren().add(c);
		}
	}

	private void createArrays() {
		for (int i = 0; i < 6; i++) {
			ArrayList<Disc> array = new ArrayList<>();
			master.add(array);
		}	
	}

	public static void main(String[] args) {
		launch(args);
	}

}
