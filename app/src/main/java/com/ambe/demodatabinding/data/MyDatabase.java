package com.ambe.demodatabinding.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

/**
 * Created by AMBE on 10/1/2018 at 11:59 AM.
 */
@Database(entities = {TaskEntity.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    private static final String LOG_TAG = MyDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "drinkwatter.db";


    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static MyDatabase sInstance;

    public static MyDatabase getInstance(Context context) {
        Log.d(LOG_TAG, "Getting the database");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        MyDatabase.class, MyDatabase.DATABASE_NAME)
                        .allowMainThreadQueries()
                        .build();
                Log.d(LOG_TAG, "Made new database");
            }
        }
        return sInstance;
    }

    public abstract TaskDAO taskDAO();

}
