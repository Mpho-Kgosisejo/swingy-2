package mkgosisejo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import mkgosisejo.config.ConfigApp;
import mkgosisejo.providers.cache.Cache;

public class DatabaseHandler {
    private String _db_url = null;
    private boolean _db_stat = false;
    private String _db_stat_mssg = "";

    public DatabaseHandler(){
        this._db_url = "jdbc:sqlite:" + ConfigApp.GetPath(Cache.Config.DATABASE_SOURCE_NAME);

        this._db_stat = this.buildTables();
    }

    public Boolean getDBStat(){
        return (this._db_stat);
    }

    public String getDBStatMessage(){
        return (this._db_stat_mssg);
    }

    public Connection getConnection() throws Exception{
        Class.forName("org.sqlite.JDBC");
        return (DriverManager.getConnection(_db_url));
    }

    private boolean buildTables(){

        try {
            Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS heros" +
                "(" +
                    "id integer PRIMARY KEY," +
                    "type text NOT NULL," +
                    "name text NOT NULL," +
                    "xp integer NOT NULL," +
                    "attack integer NOT NULL," +
                    "defence integer NOT NULL," +
                    "hp integer NOT NULL," +
                    "artifact text NOT NULL" +
                ");";

            stmt.execute(sql);
            connection.close();
            return (true);
        } catch (Exception e) {
            this._db_stat_mssg = e.getMessage();
        }
        return (false);
    }

    public boolean insert(String tableName){
        boolean stat = false;

        try {
            Connection connection = this.getConnection();
            String sql = "INSERT INTO " + tableName + "(" +
                    "type," +
                    "name," +
                    "xp," +
                    "attack," +
                    "defence," +
                    "hp," +
                    "artifact" +
                ") VALUES (?,?,?,?,?,?,?);";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, "king");
            pstmt.setString(2, "Mpho");
            pstmt.setInt(3, 12);
            pstmt.setInt(4, 13);
            pstmt.setInt(5, 14);
            pstmt.setInt(6, 15);
            pstmt.setString(7, "helm");

            stat = (pstmt.executeUpdate() > 0);
            connection.close();
        } catch (Exception e) {
            this._db_stat_mssg = e.getMessage();
        }
        return (stat);
    }

    public boolean update(String tableName){
        boolean stat = false;

        try {
            Connection connection = this.getConnection();
            String sql = "UPDATE " + tableName + " SET " +
                "type = ?," +
                "name = ?," +
                "xp = ?," +
                "attack = ?," +
                "defence = ?," +
                "hp = ?," +
                "artifact = ?" +
                "WHERE id = ?;";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, "king");
            pstmt.setString(2, "Kaygo");
            pstmt.setInt(3, 22);
            pstmt.setInt(4, 23);
            pstmt.setInt(5, 24);
            pstmt.setInt(6, 25);
            pstmt.setString(7, "whatever");
            pstmt.setInt(8, 1);

            stat = (pstmt.executeUpdate() > 0);
            connection.close();
        } catch (Exception e) {
            this._db_stat_mssg = e.getMessage();
        }
        return (stat);
    }

    public boolean delete(String tableName, int id){
        boolean stat = false;

        try {
            Connection connection = this.getConnection();
            String sql = "DELETE FROM " + tableName + " WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql); 

            pstmt.setInt(1, id);
            stat = (pstmt.executeUpdate() > 0);
            connection.close();
        } catch (Exception e) {
            this._db_stat_mssg = e.getMessage();
        }
        return (stat);
    }

    public ResultSet select(String tableName){
        try {
            Connection connection = this.getConnection();
            String sql = "SELECT * FROM " + tableName + ";";
            Statement stmt = connection.createStatement();
            ResultSet results = stmt.executeQuery(sql);
            
            // connection.close();
            return (results);
        } catch (Exception e) {
            this._db_stat_mssg = e.getMessage();
        }
        return (null);
    }
}