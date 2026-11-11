package com.example.codequest.data

import android.database.sqlite.SQLiteConstraintException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec


object Session {
    var userId: Long = 0
    var username: String = ""
}

object PasswordHasher {
    private const val ITERATIONS = 10_000

    fun newSalt(): String {
        val bytes = ByteArray(16)
        SecureRandom().nextBytes(bytes)
        return bytes.toHex()
    }

    fun hash(password: String, saltHex: String): String {
        val spec = PBEKeySpec(password.toCharArray(), saltHex.hexToBytes(), ITERATIONS, 256)
        val key = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(spec).encoded
        return key.toHex()
    }

    private fun ByteArray.toHex(): String = joinToString("") { "%02x".format(it) }
    private fun String.hexToBytes(): ByteArray = chunked(2).map { it.toInt(16).toByte() }.toByteArray()
}

class AccountRepository(private val userDao: UserDao) {

    suspend fun signUp(username: String, password: String): String? {
        val name = username.trim()
        if (name.isBlank() || password.isBlank()) return "FILL IN ALL FIELDS."
        if (name.length < 3) return "USERNAME NEEDS 3+ CHARACTERS."
        if (password.length < 6) return "PASSWORD NEEDS 6+ CHARACTERS."
        val key = name.lowercase()
        if (userDao.findByUsername(key) != null) return "USERNAME ALREADY TAKEN."

        val salt = PasswordHasher.newSalt()
        val hash = withContext(Dispatchers.Default) { PasswordHasher.hash(password, salt) }
        return try {
            userDao.insert(UserEntity(username = key, displayName = name, passwordHash = hash, salt = salt))
            null
        } catch (e: SQLiteConstraintException) {
            "USERNAME ALREADY TAKEN."
        }
    }

    suspend fun login(username: String, password: String): String? {
        val name = username.trim()
        if (name.isBlank() || password.isBlank()) return "FILL IN ALL FIELDS."
        val wrong = "WRONG USERNAME OR PASSWORD."
        val user = userDao.findByUsername(name.lowercase()) ?: return wrong
        val hash = withContext(Dispatchers.Default) { PasswordHasher.hash(password, user.salt) }
        if (hash != user.passwordHash) return wrong
        Session.userId = user.id
        Session.username = user.displayName
        return null
    }
}

class ScoreRepository(private val dao: ScoreDao) {

    suspend fun save(score: Int, correct: Int, total: Int, won: Boolean) {
        if (Session.userId == 0L) return
        dao.insert(
            ScoreEntity(
                userId = Session.userId,
                score = score,
                correct = correct,
                total = total,
                won = won,
                playedAt = System.currentTimeMillis()
            )
        )
    }

    suspend fun best(): Int = dao.bestScore(Session.userId) ?: 0

    suspend fun recent(limit: Int = 5): List<ScoreEntity> = dao.recent(Session.userId, limit)
}
