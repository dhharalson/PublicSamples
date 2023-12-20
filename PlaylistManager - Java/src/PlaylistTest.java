import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.w3c.dom.Node;

public class PlaylistTest {
	/*
	 * Tutor Notes:
	 * 
	 * Main is the boss, it tells the employee to do something, that is the method
	 * call. The method is the employee, if the employee needs info, that is the
	 * parameter. If the boss wants a report, that's the return type. Sometimes the
	 * job is to report, sometimes it is to say something was done successfully.
	 * Void is used when no report is needed.
	 * 
	 * Void: Printing is a good example. Sometimes that is the whole job. Setting is
	 * just putting information where it belongs. ToString is different because it's
	 * not printing but giving a report.
	 * 
	 * Static: If something needs to be done by an object, it's not static. If
	 * something can be done by the class itself, it is static. "There's only one."
	 * Static is talking to the idea as a whole.
	 */

	public static void main(String[] args) throws FileNotFoundException { // void is used when information is not given
																			// by the method
		// Declare list as "playlist"
		MyDoubleLinkedList<Song> playlist = new MyDoubleLinkedList<Song>(); // use angular brackets, especially on the
		// Other class declarations // left
		Scanner input = new Scanner(System.in);
		boolean keepRunning = true;

		// Start running loop
		while (keepRunning == true) {
			printMenu();
			int choice = input.nextInt();
			input.nextLine();

			if (choice == 0) {
				System.out.println("Exiting program, goodbye!");
				break;
			}

			else if (choice == 1) {
				// "Play" the playlist. Print the songs.
				if (playlist.isEmpty()) {
					System.out.println("Dude, playlist is empty!");
				} else {
					for (Song song : playlist) {
						System.out.println(song.getTitle() + " by " + song.getArtist());
					}
				}

			} else if (choice == 2) {
				// Add a song
				System.out.println("Enter song title: ");
				String title = input.nextLine();
				System.out.println("Enter artist name: ");
				String artist = input.nextLine();

				Song song = new Song();
				song.setTitle(title);
				song.setArtist(artist);

				playlist.add(song);

			} else if (choice == 3) {
				// Remove a song
				System.out.println("Enter song title to remove: ");
				String title = input.nextLine();

				Song songRemove = new Song(title, "");
				playlist.remove(songRemove);
				

			} else if (choice == 4) {
				// Show size of playlist
				System.out.println("Number of songs: " + playlist.size());

			} else if (choice == 5) {
				List<Song> temp = new ArrayList<Song>(playlist);
				Collections.shuffle(temp);
				playlist.clear();
				playlist.addAll(temp);
				System.out.println("Playlist shuffled!");

			} else if (choice == 6) {
				// Reverse playlist
				List<Song> reversed = new ArrayList<Song>(playlist);
				Collections.reverse(reversed);
				playlist.clear();
				playlist.addAll(reversed);
				System.out.println("Playlist reversed!");

				// no idea...
			} else if (choice == 7) {
				try (PrintWriter writer = new PrintWriter(new PrintWriter("PlaylistTest.txt"))) {
					for (Song s : playlist) {
						writer.println(s.getTitle() + " by " + s.getArtist());
					}
				} catch (IOException e) {
					System.out.println("Error writing file.");
				}

			} else if (choice == 8) {
				try (BufferedReader reader = new BufferedReader(new FileReader("PlaylistTest.txt"))) {
					playlist.clear();
					String line;
					while ((line = reader.readLine()) != null) {
						String[] parts = line.split(" by ");
						Song s = new Song();
						s.setTitle(parts[0]);
						s.setArtist(parts[1]);
						playlist.add(s);
						System.out.println("Loaded from file: " + playlist);
					}
				} catch (IOException e) {
					System.out.println("Error reading file.");
				}

			} else if (choice == 9) {
				playlist.clear();
			}

			else {
				System.out.println("Invalid choice. Choose from menu.");
			}

			System.out.println("\nWould you like to continue? yes/no");
			String continueChoice = input.nextLine();
			if (continueChoice.equalsIgnoreCase("no")) {
				keepRunning = false;
			}
		}
		input.close();
	}

	public static void printMenu() { // static is a qualifier:
		System.out.println("\nEnter 1 to play your playlist.");
		System.out.println("Enter 2 to add a song.");
		System.out.println("Enter 3 to remove a song.");
		System.out.println("Enter 4 to show number of songs.");
		System.out.println("Enter 5 to shuffle playlist.");
		System.out.println("Enter 6 to reverse order of playlist.");
		System.out.println("Enter 7 to save to text file.");
		System.out.println("Enter 8 to load previously saved text file.");
		System.out.println("Press 9 to clear the playlist.");
		System.out.println("Enter 0 at anytime to quit.");
		System.out.println("\n Please make a selection:  ");

	}

}
