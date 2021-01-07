package com.jsn.great.db

import android.content.Context
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "user")
data class User(@PrimaryKey val id:Int,
                @ColumnInfo(name="name") val name:String,
                @ColumnInfo(name = "address") val address:String=""
                )

@Dao
interface UserDao{
    @Query("SELECT * FROM user")
    suspend fun allUsers():List<User>

    @Query("SELECT * FROM user")
    fun userFlow():Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)
}

@Database(entities =[User::class],version = 1,exportSchema = false) //todo: implement a migration
abstract class UserRoomDatabase:RoomDatabase(){
    companion object{

        @Volatile private var sInstance:UserRoomDatabase?=null

        fun getInstance(context: Context): UserRoomDatabase {
            sInstance?: synchronized(this){
                sInstance?: buildDatabase(context).also { sInstance=it }
            }
            return sInstance!!
        }

        fun buildDatabase(context: Context): UserRoomDatabase {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                UserRoomDatabase::class.java,
                "user_database"
            ).build()
            return instance
        }
    }

    abstract fun dao():UserDao

}