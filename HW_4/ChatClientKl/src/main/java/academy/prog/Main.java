package academy.prog;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Enter your login: ");
			String login = scanner.nextLine();

			Thread th = new Thread(new GetThread(login));
			th.setDaemon(true);
			th.start();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}

			System.out.println("Enter your message with addressee like: #'login' message ");
			while (true) {
				String input = scanner.nextLine();

				if (input.isEmpty()) break;

				String text = "";
				String to = "";

				if ("/users".equals(input)) {
					new GetUsersOnline().getPresentUsers();
				} else if (input.startsWith("#")) {
					int spaceIndex = input.indexOf(" ");
					if (spaceIndex != -1) {
						to = input.substring(1, spaceIndex);
						text = input.substring(spaceIndex + 1);
					} else {
						text = input.substring(1);
					}
				} else {
					text = input;
				}

				// Відправка повідомлення
				if (!text.isEmpty()) {
					Message message = new Message(login,to,text);
					int responseCode = message.send(Utils.getURL() + "/add");
					if (responseCode != 200) {
						System.out.println("HTTP error occurred: " + responseCode);
						return;
					}
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}}}
