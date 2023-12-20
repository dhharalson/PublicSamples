package edu.frcc.csc1061j.SentimentAnalysis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class SentimentAnalysis {
	// Declare the hashmap
	private MyHashMap<String, Integer> sentimentMap = new MyHashMap<>();

	public SentimentAnalysis() {
		loadSentiments();
	}

	private void loadSentiments() {
		// buffered reader reads the sentiments text file
		try (BufferedReader sentimentReader = new BufferedReader(new FileReader("sentiments.txt"))) {

			String line;
			// in this loop, while there are lines to be read, make them variable "lines"
			while ((line = sentimentReader.readLine()) != null) {
				// split the line of text file at the comma to create array of strings called
				// "parts"
				String[] parts = line.split(",");
				// parse the string of parts[1] into an Integer so it will read in the map.
				sentimentMap.put(parts[0], Integer.parseInt(parts[1]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// print test method for sentiments text file
//	public void printSentiments() {
//		for(Entry<String, Integer> entry: sentimentMap.entrySet()) {
//			System.out.println("Word/Phrase: " + entry.getKey() + "& Sentiment: " + entry.getValue());
//		}
//	}
	public void analyzeSentiment() {
		// Scanner for input
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter text: ");

		StringBuilder text = new StringBuilder();
		while (true) {
			String line = scanner.nextLine();
			if ("END".equalsIgnoreCase(line)) {
				break;
			}
			text.append(line).append(" ");
		}
		scanner.close();

		String[] words = text.toString().replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
		int sentimentRating = 0;
		String previousWord = "";
		// For each word in the string of words...
		for (String word : words) {
			// find if the map contains the key
			if (sentimentMap.containsKey(word)) {
				// add the word's sentiment value to the total rating
				sentimentRating += sentimentMap.get(word);
			}

			// Two word phrase
			String twoWordPhrase = previousWord + " " + word;
			if (sentimentMap.containsKey(twoWordPhrase)) {
				sentimentRating += sentimentMap.get(twoWordPhrase);
			}
			// update previousWord to currentWord for next iteration
			previousWord = word;
		}
		double AvgSentiment = (double) sentimentRating / words.length;

		System.out.println("Words: " + words.length);
		System.out.println("Sentiment: " + sentimentRating);
		System.out.println((String.format("Overall: " + "%.2f", AvgSentiment)));
	}

	// Main method for testing:
	public static void main(String[] args) {
		SentimentAnalysis test = new SentimentAnalysis();
		test.analyzeSentiment();

	}
}
