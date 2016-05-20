/**
 * MovieDatabase Class stores all the Movie objects created from the movie database, also contains methods to sort
 */

package src;

import src.Movie;

public class MovieDatabase {
	public Movie[] movies;
	private int capacity;
	private String type; // Type = "int" for intDatabase and "double" for
							// doubleDatabase

	public MovieDatabase(int capacity, String type) {
		this.capacity = capacity;
		movies = new Movie[capacity];
		this.type = type;
	}

	/**
	 * Implement bucketsort using no more than O(capacity) extra memory
	 */
	public void bucketsort() {
		Node[] bucket = new Node[6];
		for (int i = 0; i < capacity; i++) {
			Node second = bucket[movies[i].getRating().intValue()];
			bucket[movies[i].getRating().intValue()] = new Node(movies[i],
					second);
		}
		int outPos = 0;
		for (int i = 5; i >= 0; i--) {
			while (bucket[i] != null) {
				movies[outPos++] = bucket[i].element;
				bucket[i] = bucket[i].next;
			}
		}
	}

	protected class Node {
		protected Movie element = null;
		protected Node next = null;

		public Node(Movie e, Node n) {
			element = e;
			next = n;
		}
	}

	/**
	 * Implement in-place quicksort using no more than O(1) extra memory
	 */
	public void quicksort() {
		quicksort(0, capacity - 1);
	}

	private void quicksort(int left, int right) {
		Movie tmp;
		if (right <= left)
			return;
		int i = left, j = right;
		Double pivot = movies[left].getRating();
		while (i <= j) {
			while (movies[i].getRating() > pivot)
				i++;
			while (movies[j].getRating() < pivot)
				j--;
			if (i <= j) {
				tmp = movies[j];
				movies[j] = movies[i];
				movies[i] = tmp;
				i++;
				j--;
			}
		}
		quicksort(left, j);
		quicksort(i, right);
	}

	public void sort() {
		if (type == "int") {
			bucketsort();
		} else {
			quicksort();
		}
	}

	public void print() {
		for (int i = 0; i < capacity; i++) {
			System.out.println(movies[i].getName() + ","
					+ movies[i].getRating());
		}
	}
}
