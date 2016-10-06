package com.codekul.database.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by aniruddha on 6/10/16.
 */

public class DaoFactory {

    private DaoMaster.DevOpenHelper helper;
    private SQLiteDatabase sqDb;
    private DaoMaster daoMaster;
    private DaoSession session;

    private static DaoFactory factory;

    private DaoFactory(Context context) {

        helper = new DaoMaster.DevOpenHelper(context, "cardb", null); //create database db file if not exist
        sqDb = helper.getWritableDatabase();  //get the created database db file
        daoMaster = new DaoMaster(sqDb);//create masterDao
        session = daoMaster.newSession(); //Creates Session session
    }

    public static synchronized DaoFactory getInstance(Context context){
        return factory == null ? new DaoFactory(context) : factory;
    }

    public CarDao getCarDao(){
        return  session.getCarDao();
    }
    @Deprecated
    public MyUserDao getUserDao(){
        return session.getMyUserDao();
    }
}
