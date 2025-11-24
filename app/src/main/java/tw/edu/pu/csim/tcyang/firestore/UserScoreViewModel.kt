package tw.edu.pu.csim.tcyang.firestore

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserScoreViewModel:ViewModel() {
    private val userScoreRepository = UserScoreRepository()

    var message by mutableStateOf("請輸入姓名與分數，並選擇操作。")
        private set

    fun addUser(userScore: UserScoreModel) {
        viewModelScope.launch {
            message = userScoreRepository.addUser(userScore)
        }
    }

    fun updateUser(userScore: UserScoreModel) {
        viewModelScope.launch {
            message = userScoreRepository.updateUser(userScore)
        }
    }

    fun deleteUser(userScore: UserScoreModel) {
        viewModelScope.launch {
            message = userScoreRepository.deleteUser(userScore)
        }
    }

    fun getUserScoreByName(userScore: UserScoreModel) {
        viewModelScope.launch {
            message = userScoreRepository.getUserScoreByName(userScore)
        }
    }

    fun orderUser() {
        viewModelScope.launch {
            message = userScoreRepository.orderByScore()
        }
    }
}

