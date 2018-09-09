package mkgosisejo.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import mkgosisejo.config.Config;
import mkgosisejo.providers.cache.Cache;

public class DatabaseHandler {
    private String db_url = null;

    public DatabaseHandler(){
        this.db_url = "jdbc:sqlite:" + Config.GetPath(Cache.Config.DATABASE_SOURCE_NAME);
        // this.init();
        this.createTabl();
    }

    // private void init(){
    //     this.db_url += Config.GetPath(Cache.Config.DATABASE_SOURCE_NAME);

    //     try {
    //         Class.forName("org.sqlite.JDBC");
    //         Connection connection = DriverManager.getConnection(db_url);

    //         if (connection != null){
    //             DatabaseMetaData metaData = connection.getMetaData();

    //             SwingyIO.ConsoleOutLine("Ok " + metaData.getDriverName());
    //         }else{
    //             SwingyIO.ConsoleOutLine("KO!");
    //         }
    //     } catch (Exception e) {
    //         SwingyIO.ConsoleOutLine("Error: " + e.getMessage());
    //     }
    // }

    public Connection getConnection() throws Exception{
        Class.forName("org.sqlite.JDBC");
        return (DriverManager.getConnection(db_url));
    }

    public Statement runQuery(String sql) throws Exception{
        Statement stmt = this.getConnection().createStatement();
        stmt.execute(sql);
        return (stmt);
    }

    private boolean createTabl(){
        try {
            String sql = "CREATE TABLE IF NOT EXISTS heros" +
            "(" +
                "id integer PRIMARY KEY" +
                // "ln text NOT NULL" +
                // "capacity real" +
            ");";
            Statement stmt = this.runQuery(sql);

            SwingyIO.ConsoleOutLine(">> " + stmt.getFetchSize());
            return (true);
        } catch (Exception e) {
            SwingyIO.ConsoleOutLine("err: " + e.getMessage());
        }
        return (false);
    }
}