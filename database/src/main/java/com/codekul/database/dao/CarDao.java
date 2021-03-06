package com.codekul.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.codekul.database.domain.Car;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CAR".
*/
public class CarDao extends AbstractDao<Car, Long> {

    public static final String TABLENAME = "CAR";

    /**
     * Properties of entity Car.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property CarId = new Property(0, Long.class, "carId", true, "_id");
        public final static Property CarName = new Property(1, String.class, "carName", false, "car_name");
        public final static Property Manufacturer = new Property(2, String.class, "manufacturer", false, "manufacturer");
        public final static Property Model = new Property(3, Integer.class, "model", false, "model");
        public final static Property Country = new Property(4, String.class, "country", false, "country");
        public final static Property Price = new Property(5, Double.class, "price", false, "price");
        public final static Property IsSecond = new Property(6, Boolean.class, "isSecond", false, "is_second");
        public final static Property Color = new Property(7, Integer.class, "color", false, "color");
        public final static Property Date = new Property(8, Long.class, "date", false, "date");
        public final static Property CarNum = new Property(9, String.class, "carNum", false, "car_num");
        public final static Property Milage = new Property(10, Float.class, "milage", false, "milage");
    }


    public CarDao(DaoConfig config) {
        super(config);
    }
    
    public CarDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CAR\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: carId
                "\"car_name\" TEXT," + // 1: carName
                "\"manufacturer\" TEXT," + // 2: manufacturer
                "\"model\" INTEGER," + // 3: model
                "\"country\" TEXT," + // 4: country
                "\"price\" REAL," + // 5: price
                "\"is_second\" INTEGER," + // 6: isSecond
                "\"color\" INTEGER," + // 7: color
                "\"date\" INTEGER," + // 8: date
                "\"car_num\" TEXT," + // 9: carNum
                "\"milage\" REAL);"); // 10: milage
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CAR\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Car entity) {
        stmt.clearBindings();
 
        Long carId = entity.getCarId();
        if (carId != null) {
            stmt.bindLong(1, carId);
        }
 
        String carName = entity.getCarName();
        if (carName != null) {
            stmt.bindString(2, carName);
        }
 
        String manufacturer = entity.getManufacturer();
        if (manufacturer != null) {
            stmt.bindString(3, manufacturer);
        }
 
        Integer model = entity.getModel();
        if (model != null) {
            stmt.bindLong(4, model);
        }
 
        String country = entity.getCountry();
        if (country != null) {
            stmt.bindString(5, country);
        }
 
        Double price = entity.getPrice();
        if (price != null) {
            stmt.bindDouble(6, price);
        }
 
        Boolean isSecond = entity.getIsSecond();
        if (isSecond != null) {
            stmt.bindLong(7, isSecond ? 1L: 0L);
        }
 
        Integer color = entity.getColor();
        if (color != null) {
            stmt.bindLong(8, color);
        }
 
        Long date = entity.getDate();
        if (date != null) {
            stmt.bindLong(9, date);
        }
 
        String carNum = entity.getCarNum();
        if (carNum != null) {
            stmt.bindString(10, carNum);
        }
 
        Float milage = entity.getMilage();
        if (milage != null) {
            stmt.bindDouble(11, milage);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Car entity) {
        stmt.clearBindings();
 
        Long carId = entity.getCarId();
        if (carId != null) {
            stmt.bindLong(1, carId);
        }
 
        String carName = entity.getCarName();
        if (carName != null) {
            stmt.bindString(2, carName);
        }
 
        String manufacturer = entity.getManufacturer();
        if (manufacturer != null) {
            stmt.bindString(3, manufacturer);
        }
 
        Integer model = entity.getModel();
        if (model != null) {
            stmt.bindLong(4, model);
        }
 
        String country = entity.getCountry();
        if (country != null) {
            stmt.bindString(5, country);
        }
 
        Double price = entity.getPrice();
        if (price != null) {
            stmt.bindDouble(6, price);
        }
 
        Boolean isSecond = entity.getIsSecond();
        if (isSecond != null) {
            stmt.bindLong(7, isSecond ? 1L: 0L);
        }
 
        Integer color = entity.getColor();
        if (color != null) {
            stmt.bindLong(8, color);
        }
 
        Long date = entity.getDate();
        if (date != null) {
            stmt.bindLong(9, date);
        }
 
        String carNum = entity.getCarNum();
        if (carNum != null) {
            stmt.bindString(10, carNum);
        }
 
        Float milage = entity.getMilage();
        if (milage != null) {
            stmt.bindDouble(11, milage);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Car readEntity(Cursor cursor, int offset) {
        Car entity = new Car( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // carId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // carName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // manufacturer
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // model
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // country
            cursor.isNull(offset + 5) ? null : cursor.getDouble(offset + 5), // price
            cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6) != 0, // isSecond
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7), // color
            cursor.isNull(offset + 8) ? null : cursor.getLong(offset + 8), // date
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // carNum
            cursor.isNull(offset + 10) ? null : cursor.getFloat(offset + 10) // milage
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Car entity, int offset) {
        entity.setCarId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCarName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setManufacturer(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setModel(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setCountry(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPrice(cursor.isNull(offset + 5) ? null : cursor.getDouble(offset + 5));
        entity.setIsSecond(cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6) != 0);
        entity.setColor(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
        entity.setDate(cursor.isNull(offset + 8) ? null : cursor.getLong(offset + 8));
        entity.setCarNum(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setMilage(cursor.isNull(offset + 10) ? null : cursor.getFloat(offset + 10));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Car entity, long rowId) {
        entity.setCarId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Car entity) {
        if(entity != null) {
            return entity.getCarId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Car entity) {
        return entity.getCarId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
