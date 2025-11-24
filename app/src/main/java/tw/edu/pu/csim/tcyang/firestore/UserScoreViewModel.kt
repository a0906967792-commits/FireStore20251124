package tw.edu.pu.csim.tcyang.firestore

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserScoreViewModel:ViewModel() {
    private val userScoreRepository = UserScoreRepository()

    var message by mutableStateOf("訊息")
        private set

    fun addUser(userScore: UserScoreModel) {
        // 在 viewModelScope 中啟動一個協程
        viewModelScope.launch {
            // 呼叫 suspend function，並等待結果
            message = userScoreRepository.addUser(userScore)
        }
    }

    fun updateUser(userScore: UserScoreModel) {
        // 在 viewModelScope 中啟動一個協程
        viewModelScope.launch {
            // 呼叫 suspend function，並等待結果
            message = userScoreRepository.updateUser(userScore)
        }
    }
}

