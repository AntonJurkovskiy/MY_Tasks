package com.azimut4946777.my_tasks

import android.view.View
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class TasksViewModel(val dao: TaskDao) : ViewModel() {

    var newTaskName = ""
    val tasks = dao.getAll()
private val _navigateToTask = MutableLiveData<Long?>()
    val  navigateToTask: LiveData<Long?>
    get() = _navigateToTask

    fun addTask() {
        viewModelScope.launch {
            val task = Task()
            task.taskName = newTaskName
            dao.insert(task)

        }
    }

    fun onTaskClicked(taskId: Long) {
        _navigateToTask.value = taskId
    }

    fun onTaskNavigated() {
        _navigateToTask.value = null
    }

}

