package Client;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Application{
	public static double WORLD_WIDTH = 1200;
	public static double WORLD_HEIGHT = 800;

	public static ArrayList<Disc[]> master = new ArrayList<>();

	private static Group root = new Group();
	private static Group discs = new Group();
	private Scene scene = new Scene(root, WORLD_WIDTH, WORLD_HEIGHT);

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("ConnectFour");  //TODO

		root.getChildren().add(discs);

		createArrays();
		setupGrid();

		primaryStage.setScene(scene);	
		primaryStage.show();
	}

	public static void spawnDisc(int i) {
		boolean b = true;
		Disc disc = new Disc(b);	//TODO

		int counter = 0;
		while(moveDown(counter, i)) {
			counter++;
		}
		System.out.println(counter);
		master.get(counter)[i] = disc;
		disc.setTranslateX(i*WORLD_WIDTH/7);
		disc.setTranslateY(counter*WORLD_HEIGHT/6);
		discs.getChildren().add(disc);	//TODO
		
		for (int j = 0; j < master.size(); j++) {
			for (int j2 = 0; j2 < master.get(j).length; j2++) {

				System.out.println(master.get(j)[j2]);
			}
		}
	}

	private static boolean moveDown(int row, int column) {
		if(row == 5) {
			return false;
		}
		else if (master.get(row)[column] == null){
			System.out.println(master.get(row)[column]);
			return true;
		}else {
			System.out.println("usch");
			return false;
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
			Disc[] array = {null, null, null, null, null, null, null};
			master.add(array);
		}	
	}

	public static void main(String[] args) {
		launch(args);
	}

}
