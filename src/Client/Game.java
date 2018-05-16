package Client;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Application{
	public static double WORLD_WIDTH = 1200;
	public static double WORLD_HEIGHT = 800;

//	public static ArrayList<ArrayList<Disc>> master = new ArrayList<>();

	private static Group root = new Group();
	private Scene scene = new Scene(root, WORLD_WIDTH, WORLD_HEIGHT);
	
	Disc[][] board;

	@Override
	public void start(Stage primaryStage) throws Exception {
		  //TODO

		ConnectingClient client = new ConnectingClient();
		root.getChildren().add(client);
		
		
		primaryStage.setTitle("ConnectFour");
		primaryStage.setScene(scene);	
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
