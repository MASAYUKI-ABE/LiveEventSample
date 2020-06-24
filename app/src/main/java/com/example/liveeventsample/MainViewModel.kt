package com.example.liveeventsample

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent

class MainViewModel : ViewModel() {
    private var _transitEvent = LiveEvent<Unit>()
    val transitEvent: LiveData<Unit> = _transitEvent

    fun nextPage() {
        _transitEvent.value = Unit
    }
}