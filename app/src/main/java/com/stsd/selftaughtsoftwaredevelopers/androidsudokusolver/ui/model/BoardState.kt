package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model

data class BoardState(
    val board: Array<Array<Int>>,
    val solved: Boolean
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BoardState

        if (!board.contentDeepEquals(other.board)) return false
        if (solved != other.solved) return false

        return true
    }

    override fun hashCode(): Int {
        var result = board.contentDeepHashCode()
        result = 31 * result + solved.hashCode()
        return result
    }
}
