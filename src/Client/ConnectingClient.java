package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ConnectingClient extends Group{

	BufferedReader from_server;
	PrintStream to_server;

	Socket connection;

	Color color;

	Disc[][] board;

	Boolean yourTurn = true;


	public static void main(String[] args) {
		new ConnectingClient();
	}

	public ConnectingClient() {

		try {

			connection = new Socket("localhost", 444);

			from_server = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			to_server = new PrintStream(connection.getOutputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Runnable output_listener = () -> {

			while (true) {
				try {
					String line = from_server.readLine();

					if (line.equals("YOU ARE PLAYER 1")) {
						this.color = Color.RED;
					} else if (line.equals("YOU ARE PLAYER 2")) {
						this.color = Color.YELLOW;
					} else if (line.equals("YOU ARE SPECTATOR")) {
						this.color = Color.TRANSPARENT;
					}


					/* If server sends a line with coordinates, client will take these coordinates and colour
					 * discs on those coordinates for the opposite player */
					if (line.contains("[") && line.contains("]")) {

						System.out.println(line);

						int index = line.indexOf("[");
						String color = line.substring(0, index);
						int x = Character.getNumericValue(line.charAt(index + 1));
						int y = Character.getNumericValue(line.charAt(index + 3));

						if (color.equals("RED")) {
							board[x][y].setFill(Color.RED);
						} else if (color.equals("YELLOW")) {
							board[x][y].setFill(Color.YELLOW);
						}

					}


				} catch (IOException e) {					//If it doesn't connect
					System.out.println("DISCONNECTED FROM SERVER.\nPRESS ENTER TO EXIT... ");
					connection = null;
					break;
				}
			}
		};

		new Thread(output_listener).start();

		Rectangle bg = new Rectangle(Game.WORLD_WIDTH, Game.WORLD_HEIGHT);
		bg.setFill(Color.BLUE);
		this.getChildren().add(bg);							//Background

		board = new Disc[7][6];								//Creates 2D array "board" for discs
		for (int row = 0; row < 7; row++) {
			for (int col = 0; col < 6; col++) {

				Disc disc = new Disc(Game.WORLD_HEIGHT / 6 / 2 - 10);
				disc.setTranslateX(row * Game.WORLD_WIDTH / 7 + Game.WORLD_WIDTH / 7 / 2);
				disc.setTranslateY(col * Game.WORLD_HEIGHT / 6 + Game.WORLD_HEIGHT / 6 / 2);
				disc.setFill(Color.WHITE);

				board[row][col] = disc;
				int x = row;
				this.getChildren().add(disc);				//Fills 2D array "board" with white discs

				//If it is your turn, you will be allowed to place discs
				if (col == 0) {
					disc.setOnMouseEntered(event -> {
						if (color == Color.TRANSPARENT)
							return;

						disc.setFill(color);
					});
					disc.setOnMouseExited(event -> {
						if (color == Color.TRANSPARENT)
							return;
						disc.setFill(Color.WHITE);
					});
					disc.setOnMouseClicked(event -> {
						if (yourTurn) {
							if (color == Color.TRANSPARENT)
								return;

							for (int y = 5; y >= 0; y--) {
								//Sets colour for a disc according to the current player (RED/YELLOW)
								if (board[x][y].getFill() == Color.WHITE) {
									board[x][y].setFill(color);			

									to_server.println("[" + x + "," + y + "]");		//Sends coordinates for the disc that was coloured

									break;
								}

							}
							if(checkWin()){
								System.exit(0);
							}
						}
					});
				}
			}	
		}
	}


	public boolean checkWin() {
		boolean b = false;
		if (checkVertical() || checkHorizontal() || checkDiagonal()) {
			b = true;
		}
		return b;
	}
	
	public boolean checkDiagonal(){
		int rCount = 0;
		int yCount = 0;
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 4; x++) {
				
				rCount = 0;
				yCount = 0;
				
				for (int z = 0; z < 4; z++) {
				
					
					if (board[x + z][y + z].getFill() == Color.RED) {
						rCount++;
						yCount = 0;
					}
					else if (board[x + z][y + z].getFill() == Color.YELLOW) {
						yCount++;
						rCount = 0;
					}

					if ((rCount == 4) || (yCount == 4)) {
						return true;
					}
				}
				rCount = 0;
				yCount = 0;
			}
		}
		
		if(!(rCount == 4) || !(yCount == 4)){
			
			rCount = 0;
			yCount = 0;
		
		for (int y = 5; y < 4; y--) {
			for (int x = 0; x < 4; x++) {
				
				rCount = 0;
				yCount = 0;
				
				for (int z = 0; z < 4; z++) {
				
					
					if (board[x - z][y - z].getFill() == Color.RED) {
						rCount++;
						yCount = 0;
					}
					else if (board[x - z][y - z].getFill() == Color.YELLOW) {
						yCount++;
						rCount = 0;
					}

					if ((rCount == 4) || (yCount == 4)) {
						return true;
					}
				}
				rCount = 0;
				yCount = 0;
			}
		}
		}
		
		return false;
		
	}

	public boolean checkVertical() {
		int rCount = 0;
		int yCount = 0;
		boolean b = false;
		for (int x = 0; x < 7; x++) {
			for (int y = 5; y > 0; y--) {

				if (board[x][y].getFill() == Color.RED) {
					rCount++;
					yCount = 0;
				}
				else if (board[x][y].getFill() == Color.YELLOW) {
					yCount++;
					rCount = 0;
				}

				if ((rCount == 4) || (yCount == 4)) {
					b = true;
				}
			}
		}
		return b;
	}

	public boolean checkHorizontal() {
		int rCount = 0;
		int yCount = 0;
		boolean b = false;
		for (int y = 5; y >= 0; y--) {
			for (int x = 0; x < 7; x++) {
				if (board[x][y].getFill() == Color.RED) {
					rCount++;
					yCount = 0;
				}
				else if (board[x][y].getFill() == Color.YELLOW) {
					yCount++;
					rCount = 0;
				}
				if ((rCount == 4) || (yCount == 4)) {
					b = true;
				}
			}
		}
		return b;
	}
}

