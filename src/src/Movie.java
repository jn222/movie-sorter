/**
 * Movie Class stores the movie name and rating of the movie, acts as an element of the data to be sorted
**/

package src;

public class Movie {
	// Name and rating of the movie
	private String name;
	private Double rating;	//For both intDatabase and doubleDatabase, the rating is stored as a double
	
	public Movie(String name, String rating) {
		this.name = name;
		this.rating = Double.parseDouble(rating);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}
	
}
