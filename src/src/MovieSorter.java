/**
 * MovieSorter Class stores the sorted database and contains methods to merge the databases
 */
package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MovieSorter {
	// Store the sorted database in this
	public MovieDatabase sortedDatabase;

	public MovieSorter(int capacity) {
		sortedDatabase = new MovieDatabase(capacity, "double");
	}

	/**
	 * Merge the two sorted databases in the variable sortedDatabase
	 * 
	 * @param intDatabase
	 * @param doubleDatabase
	 */
	public void merge(MovieDatabase intDatabase, MovieDatabase doubleDatabase) {
		int i = 0, j = 0, k = 0;
		while (i < intDatabase.movies.length
				&& j < doubleDatabase.movies.length) {
			if (intDatabase.movies[i].getRating() > doubleDatabase.movies[j]
					.getRating()) {
				sortedDatabase.movies[k] = intDatabase.movies[i];
				i++;
			} else {
				sortedDatabase.movies[k] = doubleDatabase.movies[j];
				j++;
			}
			k++;
		}
		while(i < intDatabase.movies.length){
			sortedDatabase.movies[k] = intDatabase.movies[i];
			i++;
			k++;
		}
		while(j < doubleDatabase.movies.length){
			sortedDatabase.movies[k] = doubleDatabase.movies[j];
			j++;
			k++;
		}
	}

	public static void main(String[] args) throws IOException {
		MovieSorter movieSorter;

		// The program will take filepaths as command line input
		if (args.length >= 2) {
			MovieDatabase intDatabase, doubleDatabase;
			// Read intDatabase from file, intDatabase filepath is the first
			// command line input
			FileReader fileReader = new FileReader(args[0]);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			int intDatabaseCapacity = Integer.parseInt(bufferedReader
					.readLine());
			intDatabase = new MovieDatabase(intDatabaseCapacity, "int");
			String line;

			for (int i = 0; i < intDatabaseCapacity; i++) {
				line = bufferedReader.readLine();
				String movie[] = line.split(",");
				intDatabase.movies[i] = new Movie(movie[0], movie[1]);
			}
			bufferedReader.close();

			// Read doubleDatabase from file, doubleDatabase filepath is the
			// second command line input
			fileReader = new FileReader(args[1]);
			bufferedReader = new BufferedReader(fileReader);
			int doubleDatabaseCapacity = Integer.parseInt(bufferedReader
					.readLine());
			doubleDatabase = new MovieDatabase(doubleDatabaseCapacity, "double");

			for (int i = 0; i < doubleDatabaseCapacity; i++) {
				line = bufferedReader.readLine();
				String movie[] = line.split(",");
				doubleDatabase.movies[i] = new Movie(movie[0], movie[1]);
			}
			bufferedReader.close();

			// Sort the databases
			intDatabase.sort();
			doubleDatabase.sort();

			// Initialize the sorted database and merge the two databases into
			// one
			movieSorter = new MovieSorter(intDatabaseCapacity
					+ doubleDatabaseCapacity);
			movieSorter.merge(intDatabase, doubleDatabase);

			// Print the sorted database
			movieSorter.sortedDatabase.print();
		} else {
			System.out.println("Not enough input");
		}
	}

}
