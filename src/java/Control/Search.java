/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Altair
 */
//Erwartet OR oder AND als operator (letzter Parameter)
public class Search {
    // Wird im Frontend kein releaseDate eingegeben, muss eine 0 übergeben werden !!!
    // werden die anderen Daten nicht eingegeben, wird ein leerer String ("") erwartet
    String regisseur, actor, genre, operator;
    int releaseDate;
    boolean initialise;
    private DBConnection DBC = new DBConnection();

    public Search() {
    }
    
public Search(String regisseur, int releaseDate, String actor, String genre, String operator, boolean initialise) {
    this.regisseur = regisseur;
    this.releaseDate = releaseDate;
    this.actor = actor;
    this.genre = genre;
    this.operator = operator;
}

private String getMoviesWithRegisseur() throws SQLException{
    if(!this.regisseur.equals("")){
            //filme raussuchen in denen regisseur mitspielt
            
            //RegisseurID ermitteln
            Regisseur reg = new Regisseur(this.regisseur);
            int regisseurId = reg.getRegisseurId();
            
            MovieRegisseur mreg = new MovieRegisseur(regisseurId, 0);
            ArrayList<Integer> al = mreg.getMovieIdsWithRegisseur();
            
            return generateWhereOr(al);
        }else {
            //regisseur aus Abfrage rauslassen
        }
    return "0";
}
private String getMoviesWithRleaseDate() throws SQLException{
    if(releaseDate != 0) {
        //filme raussuchen mit releaseDate
        Movie mov = new Movie("", releaseDate);
        ArrayList<Integer> al = mov.getMovieIdsWithReleaseDate();
        return generateWhereOr(al);
    }else {
            //releasedate aus abfrage rauslassen
    }
    return "0";
}
private String getMoviesWithActor() throws SQLException{
    if(!actor.equals("")){
            
            //alle filme raussuchen in denen actor mitgespielt hat
            Actor act = new Actor(actor, 'A');
            int actorId = act.getActorIdByName();
            
            MovieCharacter mcha = new MovieCharacter("", actorId, 0);
            ArrayList<Integer> al = mcha.getMovieIdsByCharacter();
            
            return generateWhereOr(al);
        }else{
            //actor aus abfrage rauslassen
        }
    return "0";
}
private String getMoviesWithGenre() throws SQLException{
    if(!genre.equals("")){
            
            //alle Filme raussuchen mit genre
            Genre gen = new Genre(genre);
            int genreId = gen.getGenreIdByGenre();
            
            Moge mog = new Moge(0, genreId);
            ArrayList<Integer> al = mog.getMovieIdsWithGenreId();
            
            return generateWhereOr(al);
        }else{
            //genre aus abfrage rauslassen
        }
    return "0";
}

public String generateWhereOr(ArrayList<Integer> al){
    String result="";
    for(int i = 0; i<al.size();i++) {
            result += al.get(i).toString() + ",";
        }
    return result.substring(0,result.length()-1);
}
    
    public String generateQuery() throws SQLException {
        boolean firstParamInserted = false;
        String query = "SELECT title, releaseDate FROM movie WHERE";
        
        String[] lists = new String[4];
        lists[0] = " movieId in(" + getMoviesWithRegisseur() + ")";
        lists[1] = " movieId in(" + getMoviesWithRleaseDate() + ")";
        lists[2] = " movieId in(" + getMoviesWithActor() + ")";
        lists[3] = " movieId in(" + getMoviesWithGenre() + ")";
        
        for(int i=0;i<=3;i++) {
            if(firstParamInserted && !lists[i].equals(" movieId in(0)")) {
                query += operator;
            }
            if(!lists[i].equals(" movieId in(0)")) {
                query += lists[i];
                firstParamInserted = true;
            }
        }
        System.out.println(query);
        return query;
    }
    
    public ArrayList<ArrayList<String>> getMovieListBySearchQuery(String query) throws SQLException{
        ArrayList<ArrayList<String>> movieList = new ArrayList<ArrayList<String>>(2);
         ResultSet rs = DBC.getRS(query);
         System.out.println("query aus getMovieList....." + query);

        if(!rs.isBeforeFirst())
        {
            return null;
        } else {
            
            int i = 0;
            while(rs.next()){
                System.out.println("~~~~~~~~ IN WHILE ~~~~~~~~");
                System.out.println("Titel: " + rs.getString(1));
                System.out.println("ReleaseDate: " + Integer.toString(rs.getInt(2)));
                
                
                movieList.add(new ArrayList<>());

                movieList.get(i).add(rs.getString(1));
                movieList.get(i).add(Integer.toString(rs.getInt(2)));

                i++;
            }
            return movieList;
        }
        }
    }
