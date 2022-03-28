package lab.oodp.io.movie;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import lab.oodp.Keyboard;

public class MovieReader {
	String fileName = null;
	Movie[] films = null;
	
    public void setFileName(String fileName) {
		this.fileName = fileName;
	}
    
	public Movie[] getFilms() {
		return films;
	}

	public void start() {

        // Get a file name from the user
    	if(fileName == null) {
	        System.out.print("Enter a file name: ");
	        fileName = Keyboard.readInput();
    	}

        // Load the movie data
       films = loadMovies(fileName);
       
       if (films == null) {
    	   System.out.println("This file is not have a any Film");
    	   return;
       }

        // Do some stuff with the data to check that its working
        printMoviesArray(films);
        Movie mostRecentMovie = getMostRecentMovie(films);
        Movie longestMovie = getLongestMovie(films);
        printResults(mostRecentMovie, longestMovie);
        System.out.println();
        printDirector("Searching for Sugar Man", films);
        printDirector("Liberal Arts", films);
        printDirector("The Intouchables", films);

    }

    /**
     * Reads movies from a file.
     *
     * @param fileName
     * @return
     */
	public Movie[] loadMovies(String fileName) {
    	//TODO: remove return null below, load movies from data file,
    	File loadedFile =  new File(fileName);
    	
    	try (DataInputStream dIn = new DataInputStream(new FileInputStream(loadedFile))) {
    		//Loop fir specific array
    		while (dIn.available() > 0) {
    			//Create MOvie object from file data reader stream
    			String movieName = dIn.readUTF();
    			int year = dIn.readInt();
    			int movieLength = dIn.readInt();
    			String director = dIn.readUTF();
    			Movie movie = new Movie(movieName, year, movieLength, director);
    			//Add movie to films
    			if (films == null) {
    				films = new Movie[]{movie};
    			}else {
    				Movie[] temp = new Movie[films.length+1];
        			System.arraycopy(films, 0, temp, 0, films.length);
        			films = temp;
        			films[temp.length-1] = movie;
    			}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error : " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error : " + e.getMessage());
		}
        
    	return  films;

    }

    private void printMoviesArray(Movie[] films) {
        System.out.println("Movie Collection");
        System.out.println("================");
        // Step 2.  Complete the printMoviesArray() method
        for(int i = 0; i<films.length; i++){
            System.out.println(films[i].toString());
        }
    }
    private Movie getMostRecentMovie(Movie[] films) {
        // Step 3.  Complete the getMostRecentMovie() method.
        Movie temp=null;
        for(int i = 1; i<films.length; i++){
            if(films[i].isMoreRecentThan(films[i-1])){
                temp = films[i];
            }
        }
        return temp;
    }
    private Movie getLongestMovie(Movie[] films) {
        // Step 4.  Complete the getLongest() method.
        Movie temp=null;
        for(int i = 1; i<films.length; i++){
            if(films[i].isLongerThan(films[i-1])){
                temp = films[i];
            }
        }
        return temp;
    }
    private void printResults(Movie mostRecent, Movie longest) {
        System.out.println();
        System.out.println("The most recent movie is: " + mostRecent.toString());
        System.out.println("The longest movie is: " + longest.toString());
    }
    private void printDirector(String movieName, Movie[] movies) {
        // Step 5. Complete the printDirector() method
        for(int i = 0; i<movies.length; i++){
            if(movieName.equals(movies[i].getName())){
                System.out.println(movieName + " was directed by " + movies[i].getDirector());
                return;
            }
        }
        System.out.println(movieName + " is not in the collection.");
    }

    public static void main(String[] args) {
        new MovieReader().start();
    }
}
