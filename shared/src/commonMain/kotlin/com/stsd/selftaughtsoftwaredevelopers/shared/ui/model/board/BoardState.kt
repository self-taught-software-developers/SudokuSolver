package com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.geometry.Offset
import com.cerve.development.ui.canvas.model.CerveCanvasState
import com.cerve.development.ui.canvas.model.CerveCell
import com.cerve.development.ui.canvas.model.CerveOffset.Companion.offset
import com.cerve.development.ui.canvas.model.CerveSize
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.Position

data class BoardState(
    val dimensions: GridState = GridState.GRID_3X3,
    val canvasState: CerveCanvasState
) {
    val board: SnapshotStateList<TileState> = List(81) { index ->
        TileState(
            point = index.getPosition
        )
    }.also { println(it) }.toMutableStateList()

    fun getCell(index: Int, size: Int) : CerveCell {
        val row = (index / 9).times(size).toFloat()
        val column = (index % 9).times(size).toFloat()

        val shift = Offset(x = row, column)
        return CerveCell(
            topLeft = shift.offset,
            cellSize = CerveSize(size)
        )
    }

    val Int.getPosition : Position get() = run {
        Position(
            row = this@getPosition / dimensions.vector(),
            column = this@getPosition % dimensions.vector()
        )
    }
    fun upsert(tile: TileState) {
        val index = board.indexOfFirst { element ->
            element.point == tile.point
        }

        if (index != -1) {
            board.removeAt(index)
        }

        board.add(tile) // Add if not found
    }

    private val placementBackStack: SnapshotStateList<Position?> = mutableStateListOf()

    init {

//        if (board.isEmpty()) {
//            List(dimensions.vector()) { x ->
//                List(dimensions.vector()) { y ->
//                    Position(x = x, y = y).let { position ->
//                        board.add(
//                            TileState(
//                                position = position,
//                                subgrid = position.div(dimensions.multiplier)
//                            )
//                        )
//                    }
//                }
//            }
//        }
    }

//    private val _solutionState = MutableStateFlow(SolutionState.IDLE)
//    val solutionState = _solutionState.asStateFlow()
//
//
//    private val _placementSpeed = MutableStateFlow(TimeState.DEFAULT_SPEED)
//    val placementSpeed: StateFlow<TimeState> = _placementSpeed.asStateFlow()
//
//    fun updatePlacementSpeed(value: TimeState) = _placementSpeed.update { value }
//
//    fun selectedPosition(position: Position? = null): Position? {
//        position?.let { placementBackStack.add(position) }
//        return placementBackStack.lastOrNull()
//    }
//
//    /**
//     * @[updateSelectedPosition] takes a nullable @[Position].
//     *
//     * When null is passed for the position parameter.
//     * The last item in @[placementBackStack] is removed, this updates the @[selectedPosition] to
//     * it's previous position.
//     *
//     * When a value is passed for the position parameter.
//     * That value is added to the top of our @[placementBackStack], this updates the
//     * @[selectedPosition] to it's previous position.
//     */
//    fun updateSelectedPosition(position: Position?) {
//        position?.let {
//            placementBackStack.add(position)
//        } ?: placementBackStack.removeLastOrNull()
//    }
//
//    fun changeValue(value: String, position: Position? = null) {
//        selectedPosition(position)?.let { selectedPosition ->
//
//            board.apply {
//                val index = indexOfFirst { it.position == selectedPosition }
//                val changedTile = get(index).copy(text = value, isValid = true)
//
//                set(index, changedTile)
//            }
//
//            if (value.isEmpty()) {
//                updateSelectedPosition(null)
//            }
//        }
//        _solutionState.update { SolutionState.IDLE }
//    }
//
//    fun undoLast() {
//        changeValue(TileState.Companion.EMPTY_TILE)
//    }
//
//    suspend fun undoAll() {
//        while (selectedPosition() != null) {
//            delay(placementSpeed.value.time)
//            undoLast()
//        }
//    }
//
//    suspend fun solveTheBoard() {
////        if (solvable()) {
////            setBoard(findSolutionInstantly(fromUiBoard()))
////        } else {
////            _solutionState.update { SolutionState.ERROR }
////        }
//    }
//
//    private suspend fun setBoard(solvedBoard: Array<Array<Int>>) {
//        fromUiBoard().forEachIndexed { x, ints ->
//            ints.forEachIndexed { y, i ->
//                if (i == 0) {
//                    delay(placementSpeed.value.time).also {
//                        changeValue(
//                            value = TileState.Companion.toTileText(solvedBoard[x][y]),
//                            position = Position(x = x, y = y)
//                        )
//                    }
//                }
//            }
//        }
//        _solutionState.update { SolutionState.SUCCESS }
//    }
//    private fun findSolutionInstantly(board: Array<Array<Int>>): Array<Array<Int>> {
//        findEmptyPosition(board).also { position ->
//            if (position.isEmpty()) {
//                return board
//            } else {
//                (1..9).forEach { candidate ->
//                    if (validatePlacement(board, candidate, position)) {
//                        board[position[0]][position[1]] = candidate
//
//                        if (findEmptyPosition(findSolutionInstantly(board)).isEmpty()) return board
//
//                        board[position[0]][position[1]] = 0
//                    }
//                }
//                return board
//            }
//        }
//    }
//    private fun fromUiBoard(): Array<List<Int>> {
//        return board
//            .map { it.value() }
//            .chunked(dimensions.vector())
//            .toTypedArray()
//    }

//    private suspend fun solvable(): Boolean {
//        return CompletableDeferred<Boolean>().apply {
//            board.apply {
//                (indices).forEach { index ->
//
////                    get(index).also { tile ->
////                        this[index] = get(index).copy(
////                            isValid = validatePlacement(
////                                position = tile.position,
////                                number = tile.value(),
////                                board = fromUiBoard()
////                            )
////                        )
////                    }
//                }
//                val result = board.all { it.isValid } && board.any { it.text.isEmpty() }
//                println(result)
//                complete(result)
//            }
//        }.await()
//    }

    fun solveSudokuListNullableInt(board: MutableList<TileState>): Boolean {
        val n = 9
        for (i in 0 until n * n) {
            if (board[i].value == null) {
                val row = i / n
                val col = i % n
                for (digit in 1..9) {
                    if (isValidNullableInt(board, row, col, digit)) {
                        board[i] = board[i].copy(digit)
                        if (solveSudokuListNullableInt(board)) {
                            return true
                        } else {
                            board[i] = board[i].copy(null) // Backtrack
                        }
                    }
                }
                return false // Trigger backtracking
            }
        }
        return true // Board is full, solution found
    }

    fun isValidNullableInt(board: List<TileState?>, row: Int, col: Int, digit: Int): Boolean {
        val n = 9

        // Check row
        for (c in 0 until n) {
            if (board[row * n + c]?.value == digit) {
                return false
            }
        }

        // Check column
        for (r in 0 until n) {
            if (board[r * n + col]?.value == digit) {
                return false
            }
        }

        // Check 3x3 subgrid
        val startRow = row - row % 3
        val startCol = col - col % 3
        for (r in 0 until 3) {
            for (c in 0 until 3) {
                if (board[(startRow + r) * n + (startCol + c)]?.value == digit) {
                    return false
                }
            }
        }

        return true
    }

    fun findAllSudokuSolutions(board: MutableList<Int?>): List<List<Int?>> {
        val n = 9
        val solutions = mutableListOf<List<Int?>>()

        fun isValid(currentBoard: List<Int?>, row: Int, col: Int, digit: Int): Boolean {
            // Check row
            for (c in 0 until n) {
                if (currentBoard[row * n + c] == digit) return false
            }
            // Check column
            for (r in 0 until n) {
                if (currentBoard[r * n + col] == digit) return false
            }
            // Check 3x3 subgrid
            val startRow = row - row % 3
            val startCol = col - col % 3
            for (r in 0 until 3) {
                for (c in 0 until 3) {
                    if (currentBoard[(startRow + r) * n + (startCol + c)] == digit) return false
                }
            }
            return true
        }

        fun solve(currentBoard: MutableList<Int?>): Boolean {
            for (i in 0 until n * n) {
                if (currentBoard[i] == null) {
                    val row = i / n
                    val col = i % n
                    for (digit in 1..9) {
                        if (isValid(currentBoard, row, col, digit)) {
                            currentBoard[i] = digit
                            if (solve(currentBoard)) {
                                return true // Keep searching for more solutions
                            } else {
                                currentBoard[i] = null // Backtrack
                            }
                        }
                    }
                    return false // Trigger backtracking
                }
            }
            // If no empty cells are found, we have a solution
            solutions.add(currentBoard.toList()) // Add a copy of the solution
            return true // Continue searching for other solutions
        }

        solve(board)
        return solutions
    }
}