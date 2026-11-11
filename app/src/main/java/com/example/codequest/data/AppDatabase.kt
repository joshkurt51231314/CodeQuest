package com.example.codequest.data

import android.content.Context
import androidx.room.*

@Entity(tableName = "users", indices = [Index(value = ["username"], unique = true)])
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val username: String,
    val displayName: String,
    val passwordHash: String,
    val salt: String
)

@Entity(tableName = "scores")
data class ScoreEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val score: Int,
    val correct: Int,
    val total: Int,
    val won: Boolean,
    val playedAt: Long
)

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(user: UserEntity): Long

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun findByUsername(username: String): UserEntity?
}

@Dao
interface ScoreDao {
    @Insert
    suspend fun insert(score: ScoreEntity)

    @Query("SELECT MAX(score) FROM scores WHERE userId = :userId")
    suspend fun bestScore(userId: Long): Int?

    @Query("SELECT * FROM scores WHERE userId = :userId ORDER BY playedAt DESC LIMIT :limit")
    suspend fun recent(userId: Long, limit: Int): List<ScoreEntity>
}

@Database(entities = [UserEntity::class, ScoreEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun scoreDao(): ScoreDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun get(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "codequest.db"
                ).build().also { instance = it }
            }
    }
}
