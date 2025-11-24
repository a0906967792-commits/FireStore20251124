package tw.edu.pu.csim.tcyang.firestore

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll


@Composable
fun UserScoreScreen( userScoreViewModel: UserScoreViewModel = viewModel() ) {

    var user by remember { mutableStateOf("") }
    var score by remember { mutableStateOf("") }

    val scrollState = rememberScrollState() // 建立捲動狀態

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState), // 啟用整個畫面的垂直捲動
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 姓名輸入欄位
        TextField(
            value = user,
            onValueChange = { user = it },
            label = { Text("姓名") },
            placeholder = { Text("請輸入您的姓名") }
        )
        Text("當前姓名：$user")
        Spacer(modifier = Modifier.size(10.dp))

        // 分數輸入欄位
        OutlinedTextField(
            value = score,
            onValueChange = { score = it.filter { it.isDigit() } }, // 只接受數字輸入
            label = { Text("分數") },
            placeholder = { Text("請輸入您的分數") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
        Text("當前分數：$score")
        Spacer(modifier = Modifier.size(20.dp))

        // --- 操作按鈕 ---
        Button(onClick = {
            val userScore = UserScoreModel(user = user, score = score.toIntOrNull() ?: 0)
            userScoreViewModel.addUser(userScore)
        }, modifier = Modifier.fillMaxWidth(0.8f)) {
            Text("新增資料 (ID自動生成)")
        }
        Spacer(modifier = Modifier.size(5.dp))

        Button(onClick = {
            val userScore = UserScoreModel(user = user, score = score.toIntOrNull() ?: 0)
            userScoreViewModel.updateUser(userScore)
        }, modifier = Modifier.fillMaxWidth(0.8f)) {
            Text("新增/異動資料 (以姓名為 ID)")
        }
        Spacer(modifier = Modifier.size(5.dp))

        Button(onClick = {
            val userScore = UserScoreModel(user = user, score = 0)
            userScoreViewModel.deleteUser(userScore)
        }, modifier = Modifier.fillMaxWidth(0.8f)) {
            Text("刪除資料 (依姓名)")
        }
        Spacer(modifier = Modifier.size(5.dp))

        Divider(modifier = Modifier.padding(vertical = 10.dp))

        Button(onClick = {
            val userScore = UserScoreModel(user = user, score = 0)
            userScoreViewModel.getUserScoreByName(userScore)
        }, modifier = Modifier.fillMaxWidth(0.8f)) {
            Text("查詢資料 (依姓名)")
        }
        Spacer(modifier = Modifier.size(5.dp))

        Button(onClick = {
            userScoreViewModel.orderUser()
        }, modifier = Modifier.fillMaxWidth(0.8f)) {
            Text("查詢前三名 (分數降冪)")
        }

        Divider(modifier = Modifier.padding(vertical = 10.dp))

        // --- 訊息顯示區塊 ---
        Text(
            text = "操作訊息：\n${userScoreViewModel.message}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        )
    }
}
