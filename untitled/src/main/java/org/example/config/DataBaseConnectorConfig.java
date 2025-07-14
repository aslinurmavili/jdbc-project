package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnectorConfig {
    public static Connection connection;

    //setConnection
public static void setConnection(){
    try {
     connection= DriverManager.getConnection(DataBaseConfig.DATABASE_URL, DataBaseConfig.DATABASE_USERNAME, DataBaseConfig.PASSWORD);
    }catch (Exception e){
        throw new RuntimeException(e);
    }
}
    //getConnection
public static Connection getConnection(){
    return connection;
}
    //closeConnection
    public static void closeConnection(){
    try{
connection.close();
    }catch (Exception e){
        throw new RuntimeException(e);
    }
    }

}
