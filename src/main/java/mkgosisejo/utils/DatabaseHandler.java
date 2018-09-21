package mkgosisejo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import mkgosisejo.config.ConfigApp;
import mkgosisejo.models.Hero;
import mkgosisejo.providers.cache.Cache;

public class DatabaseHandler {
    private String _db_url = null;
    private boolean _db_stat = false;
    private String _db_stat_mssg = "";
    private String tableName;

    public DatabaseHandler(String tableName){
        this.tableName = tableName;
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

    public boolean insert(Hero hero){
        boolean stat = false;

        try {
            Connection connection = this.getConnection();
            String sql = "INSERT INTO " + this.tableName + "(" +
                    "type," +
                    "name," +
                    "xp," +
                    "attack," +
                    "defence," +
                    "hp," +
                    "artifact" +
                ") VALUES (?,?,?,?,?,?,?);";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, hero.getType());
            pstmt.setString(2, hero.getName());
            pstmt.setInt(3, hero.getXp());
            pstmt.setInt(4, hero.getAttack());
            pstmt.setInt(5, hero.getDefence());
            pstmt.setInt(6, hero.getHp());
            pstmt.setString(7, hero.getArtifact().toString());

            stat = (pstmt.executeUpdate() > 0);
            connection.close();
        } catch (Exception e) {
            this._db_stat_mssg = e.getMessage();
        }
        return (stat);
    }

    public boolean update(Hero hero){


        boolean stat = false;

        try {
            Connection connection = this.getConnection();
            String sql = "UPDATE " + this.tableName + " SET " +
                "type = ?," +
                "name = ?," +
                "xp = ?," +
                "attack = ?," +
                "defence = ?," +
                "hp = ?," +
                "artifact = ?" +
                "WHERE id = ?;";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, hero.getType());
            pstmt.setString(2, hero.getName());
            pstmt.setInt(3, hero.getXp());
            pstmt.setInt(4, hero.getAttack());
            pstmt.setInt(5, hero.getDefence());
            pstmt.setInt(6, hero.getHp());
            pstmt.setString(7, hero.getArtifact().toString());
            pstmt.setInt(8, hero.getId());

            stat = (pstmt.executeUpdate() > 0);
            connection.close();
        } catch (Exception e) {
            this._db_stat_mssg = e.getMessage();
        }
        return (stat);
    }

    public boolean delete(int id){
        boolean stat = false;

        try {
            Connection connection = this.getConnection();
            String sql = "DELETE FROM " + this.tableName + " WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql); 

            pstmt.setInt(1, id);
            stat = (pstmt.executeUpdate() > 0);
            connection.close();
        } catch (Exception e) {
            this._db_stat_mssg = e.getMessage();
        }
        return (stat);
    }

    public ResultSet select(){
        try {
            Connection connection = this.getConnection();
            String sql = "SELECT * FROM " + this.tableName + ";";
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