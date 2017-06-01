/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Altair
 */
public class ManageSessionId {
    private DBConnection DBC = new DBConnection();
    private String sessionId="";

    public ManageSessionId(String sessionId) {
    this.sessionId = sessionId;
    }
    
    public void saveSessionId(String username){
        
         //mssql
        if(DBC.getDb_used().equals("ms")){
            DBC.executeQuery("UPDATE movieUser SET sessionId ='" + sessionId + "' WHERE userName = '" + username + "'");
            
        }
        //mysql
        else{
            DBC.executeQuery("UPDATE movieuser SET sessionId ='" + sessionId + "' WHERE userName = '" + username + "'");
        }
        
    }
    
    //gibt die BenutzerID zurück, 0 wenn SessionId nicht vorhanden ist (=> Sitzung abgelaufen)
    public int getUserIdBySessionId() throws SQLException{
        ResultSet rs;
        
        //mssql
        if(DBC.getDb_used().equals("ms")){
            rs = DBC.getRS("SELECT userId FROM movieUser WHERE sessionId = '" + sessionId + "'");
        }
        //mysql
        else{
            rs = DBC.getRS("SELECT userId FROM movieuser WHERE sessionId = '" + sessionId + "'");
        }
        
        if(!rs.next())
        {
            return 0;
        }
        return rs.getInt(1);
    }
}