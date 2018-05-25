package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javafx.scene.paint.Color;

public class ServerClient {

	BufferedReader from_client;
	PrintStream to_client;

	String color;

	public ServerClient(Socket connection) {

		try {

			from_client = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			to_client = new PrintStream(connection.getOutputStream());

			if (Server.clients.size() >= 2) {
				to_client.println("YOU ARE SPECTATOR");
			} else if (Server.clients.size() == 0) {
				to_client.println("YOU ARE PLAYER 1");
				color = "RED";
			} else {
				to_client.println("YOU ARE PLAYER 2");
				color = "YELLOW";
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Runnable input_listener = () -> {
			while (true) {
				try {
					String input = from_client.readLine();

					if (input.contains("[") && input.contains("]")) {

						int x = Character.getNumericValue(input.charAt(1));
						int y = Character.getNumericValue(input.charAt(3));

						for (ServerClient client : Server.clients) {

							if (client != this) {			//If the other client, send info to client
								client.sendMsg(color + "[" + x + "," + y + "]");
								client.sendMsg("you");
							} else {						//If this client, send info to client
								client.sendMsg("not turn");		
							}
						}
					}
					
				} catch (IOException e) {
					// Dropped connection
					break;
				}
			}
		};

		new Thread(input_listener).start();

	}

	public void sendMsg(String msg) {
		to_client.println(msg);
	}

}
