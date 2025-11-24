package tw.edu.pu.csim.tcyang.firestore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun UserScoreScreen( userScoreViewModel: UserScoreViewModel = viewModel()
) {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            // 在按鈕點擊時，直接呼叫 ViewModel 的函式
            var userScore = UserScoreModel("姿佳", 39)
            userScoreViewModel.addUser(userScore)
        }) {
            Text("新增資料")
        }

        Button(onClick = {
            // 在按鈕點擊時，直接呼叫 ViewModel 的函式
            var userScore = UserScoreModel("姿佳", 21)
            userScoreViewModel.updateUser(userScore)
        }) {
            Text("新增/異動資料")
        }
        Button(onClick = {
            // 在按鈕點擊時，直接呼叫 ViewModel 的函式
            var userScore = UserScoreModel("姿佳", 21)
            userScoreViewModel.deleteUser(userScore)
        }) {
            Text("刪除資料")
        }
        Text(userScoreViewModel.message)
    }
}

private fun UserScoreViewModel.deleteUser(userScore: UserScoreModel) {}
