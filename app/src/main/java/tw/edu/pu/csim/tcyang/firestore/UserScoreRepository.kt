package tw.edu.pu.csim.tcyang.firestore

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class UserScoreRepository {
    val db = Firebase.firestore

    suspend fun addUser(userScore: UserScoreModel): String {
        return try {
            val documentReference =
                db.collection("UserScore")
                    .add(userScore)
                    .await()
            "新增資料成功！Document ID:\n ${documentReference.id}"
        } catch (e: Exception) {
            // await() 失敗時會拋出例外，在這裡捕捉並處理
            "新增資料失敗：${e.message}"
        }
    }
    suspend fun updateUser(userScore: UserScoreModel): String {
        return try {
            db.collection("UserScore")
                .document(userScore.user)
                .set(userScore)
                .await()
            "新增/異動資料成功！Document ID:\n ${userScore.user}"
        } catch (e: Exception) {
            // await() 失敗時會拋出例外，在這裡捕捉並處理
            "新增/異動資料失敗：${e.message}"
        }
    }

    suspend fun deleteUser(userScore: UserScoreModel): String {
        return try {
            // 1. 取得文件參考
            val documentRef = db.collection("UserScore").document(userScore.user)

            // 2. 執行讀取操作，確認文件是否存在
            val documentSnapshot = documentRef.get().await()

            if (documentSnapshot.exists()) {
                // 3. 如果文件存在，才執行刪除
                documentRef.delete().await()
                "刪除資料成功！Document ID: ${userScore.user}"
            } else {
                // 4. 如果文件不存在，回傳對應的訊息
                "刪除失敗：Document ID ${userScore.user} 不存在。"
            }
        } catch (e: Exception) {
            // await() 失敗時會拋出例外，在這裡捕捉並處理
            "刪除資料失敗：${e.message}"
        }
    }


}



