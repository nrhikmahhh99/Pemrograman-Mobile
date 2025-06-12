package com.example.modul5.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TasEntity::class], version = 1, exportSchema = false)
abstract class TasDatabase : RoomDatabase() {
    abstract fun tasDao(): TasDao

    companion object {
        @Volatile
        private var INSTANCE: TasDatabase? = null

        fun getDatabase(context: Context): TasDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TasDatabase::class.java,
                    "tas_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
