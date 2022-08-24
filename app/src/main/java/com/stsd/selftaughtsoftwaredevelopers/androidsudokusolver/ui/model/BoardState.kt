package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model

import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState.Companion.EMPTY_TILE
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.*

class BoardState(var solved: Boolean? = null) {

    private val _selectedPosition = MutableStateFlow<Pair<Int,Int>?>(null)
    var selectedPosition : SharedFlow<Pair<Int, Int>?> = _selectedPosition.asSharedFlow()

    private val _initialBoard = MutableStateFlow(emptySudokuBoard)
    var initialBoard : SharedFlow<Array<Array<TileState>>> = _initialBoard.asSharedFlow()

    private var previousPosition : Pair<Int, Int>? = null

    fun selectPosition(position: Pair<Int, Int>) {
        previousPosition = _selectedPosition.value
        _selectedPosition.update { position }
    }

    fun fromUiBoard() : Array<Array<Int>> {
        return _initialBoard.value.map { row ->
            row.map { it.value() }.toTypedArray()
        }.toTypedArray()
    }

    fun changeValue(value: String) {
        _selectedPosition.value?.let { (x, y) ->
            _initialBoard.updateCopy { it[x][y].text = value }
        }
    }

    private fun <T> MutableStateFlow<T>.updateCopy(function: (T) -> Unit) {
        when(val array = this.value) {
            is Array<*> -> this.update {
                array.copy { function(array as T) } as T
            }
        }
    }
    private fun <T> Array<T>.copy(function: (Array<T>) -> Unit) = this
        .copyOf().apply { function(this@copy) }

    fun undoLast() {

        _selectedPosition.value?.let { (x, y) ->
            _initialBoard.value[x][y].text = EMPTY_TILE
            _selectedPosition.update { previousPosition }
        }

    }
    fun clearBoard() {
        previousPosition = null
        _selectedPosition.update { null }
        _initialBoard.update { emptySudokuBoard }
    }

    companion object {
        val emptySudokuBoard = Array(9) { x ->
            Array(9) { y ->
                TileState(position = Pair(x,y))
            }
        }
        val sudokuBoardFilled = Array(9) { x ->
            Array(9) { y ->
                TileState(
                    text = x.toString(),
                    position = Pair(x,y)
                )
            }
        }
    }

}

//class ConversationUiState(
//    val channelName: String,
//    val channelMembers: Int,
//    initialMessages: List<Message>
//) {
//    private val _messages: MutableList<Message> =
//        mutableStateListOf(*initialMessages.toTypedArray())
//    val messages: List<Message> = _messages
//
//    fun addMessage(msg: Message) {
//        _messages.add(0, msg) // Add to the beginning of the list
//    }
//}
//
//@Immutable
//data class Message(
//    val author: String,
//    val content: String,
//    val timestamp: String,
//    val image: Int? = null,
//    val authorImage: Int = if (author == "me") R.drawable.ali else R.drawable.someone_else
//)
