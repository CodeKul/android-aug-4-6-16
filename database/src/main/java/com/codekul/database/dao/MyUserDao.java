package com.codekul.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.codekul.database.domain.MyUser;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MY_USER".
*/
public class MyUserDao extends AbstractDao<MyUser, Long> {

    public static final String TABLENAME = "MY_USER";

    /**
     * Properties of entity MyUser.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property IdUser = new Property(0, Long.class, "idUser", true, "_id");
        public final static Property UserName = new Property(1, String.class, "userName", false, "user_name");
        public final static Property Password = new Property(2, String.class, "password", false, "user_pasword");
    }


    public MyUserDao(DaoConfig config) {
        super(config);
    }
    
    public MyUserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MY_USER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: idUser
                "\"user_name\" TEXT," + // 1: userName
                "\"user_pasword\" TEXT);"); // 2: password
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MY_USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, MyUser entity) {
        stmt.clearBindings();
 
        Long idUser = entity.getIdUser();
        if (idUser != null) {
            stmt.bindLong(1, idUser);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(2, userName);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(3, password);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, MyUser entity) {
        stmt.clearBindings();
 
        Long idUser = entity.getIdUser();
        if (idUser != null) {
            stmt.bindLong(1, idUser);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(2, userName);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(3, password);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public MyUser readEntity(Cursor cursor, int offset) {
        MyUser entity = new MyUser( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // idUser
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // userName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // password
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, MyUser entity, int offset) {
        entity.setIdUser(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUserName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPassword(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(MyUser entity, long rowId) {
        entity.setIdUser(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(MyUser entity) {
        if(entity != null) {
            return entity.getIdUser();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(MyUser entity) {
        return entity.getIdUser() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
