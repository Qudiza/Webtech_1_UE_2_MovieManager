package Bean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Control.Search;
import java.sql.SQLException;

/**
 *
 * @author Altair
 */
public class Testklasse {
    
    public static void main(String[] args) throws SQLException{
        //Actor actor = new Actor("Bruce Willis", 'M');
        //actor.insertActor();
        
       // Genre genre = new Genre("Animation");
        //genre.insertGenre();
        
        //Moge moge = new Moge(2,7);
        //moge.insertMoge();
        
       // Movie movie = new Movie("Stirb Langsam 4.0", 2007);
        //movie.insertMovie();
        
       // MovieCharacter moch = new MovieCharacter("John McClane", 11, 6);
        //moch.insertMovieCharacter();
        
        //Regisseur reg = new Regisseur("Len Wiseman");
        //reg.insertRegisseur();
        
        //MovieRegisseur more = new MovieRegisseur(5, 6);
        //more.insertMovieRegisseur();
        
       // MovieUser user = new MovieUser("Philipp", "1234");
       // user.insertMovieUser();
        
       
    //   MovieCollection moco1 = new MovieCollection(2, 3);
    //   MovieCollection moco2 = new MovieCollection(2, 4);
    //   MovieCollection moco3 = new MovieCollection(2, 5);
    //   moco1.insertMovieCollection();
    //   moco2.insertMovieCollection();
    //   moco3.insertMovieCollection();
    
    Search search = new Search("", 2005, "", "", "AND", true);
    System.out.println("ERGEBNIS: " + search.generateQuery());
   
   //Collection col = new Collection();
   
   //System.out.println(col.collectionHasMovies);
   //System.out.println(col.collectionListSize);
   
   
   
    }
}