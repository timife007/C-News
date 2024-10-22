package com.timife.c_news.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.timife.c_news.data.local.entities.RemoteKey

@Dao
interface RemoteKeyDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertOrReplace(remoteKey: RemoteKey)

  @Query("SELECT * FROM remote_keys WHERE title = :query")
  suspend fun getRemoteKeyByQuery(query:String): RemoteKey

  @Query("DELETE FROM remote_keys WHERE title = :query")
  suspend fun deleteAllRemoteKeys(query:String)
}