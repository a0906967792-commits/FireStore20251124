package tw.edu.pu.csim.tcyang.firestore

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class UserScoreModel(
    var user: String = "",
    var score: Int = 0,

    //讓 Firestore 在儲存文件時
    // 自動將 timestamp 欄位填入伺服器時間
    @ServerTimestamp
    var timestamp: Date? = null
)
