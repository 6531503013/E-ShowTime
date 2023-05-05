import java.util.Scanner;

public class MovieDetail {
    private String movieName;
    private String movieDirector;
    private String movieType;
    private int movieLength;

    public MovieDetail() {}

    public MovieDetail(String movieName, String movieDirector, String movieType, int movieLength) {
        this.movieName = movieName;
        this.movieDirector = movieDirector;
        this.movieType = movieType;
        this.movieLength = movieLength;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public String getMovieType() {
        return movieType;
    }

    public int getMovieLength() {
        return movieLength;
    }

    public void printDetails() {
        System.out.println("Movie Name: " + movieName);
        System.out.println("Movie Director: " + movieDirector);
        System.out.println("Movie Type: " + movieType);
        System.out.println("Length of the Movie: " + movieLength + " min");
    }

    
}
