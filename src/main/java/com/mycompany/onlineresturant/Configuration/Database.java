package com.mycompany.onlineresturant.Configuration;

public class Database {

    private static final String persistnceUnitName = "com.mycompany_OnlineResturant_war_1.0-SNAPSHOTPU";
                                                       
    public static String getPuName(){
        return Database.persistnceUnitName;
    }    
}
