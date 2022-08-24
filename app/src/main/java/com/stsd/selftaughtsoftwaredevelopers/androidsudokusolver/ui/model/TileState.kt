package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme

data class TileState(
    var text: String = EMPTY_TILE,
    var position: Pair<Int, Int>
) {

    private val sameSubgrid = { coordinates : Triple<Int, Int, Int> ->
        val (sx,sy,ss) = coordinates

        val tileSubGrid = position.let { (x,y) -> Pair(x/ss,y/ss) }
        val selectedSubGrid = Pair(sx/ss,sy/ss)

        selectedSubGrid == tileSubGrid
    }
    private val sameColumn = { y : Int -> position.second == y }
    private val sameRow = { x : Int -> position.first == x }
    private val isSelected = { coordinates : Pair<Int, Int> -> position == coordinates }

    @Composable
    fun tileColor(coordinates: Triple<Int, Int, Int>?) : Color {

        return coordinates?.let { (x,y,_) ->
            return when {
                isSelected(Pair(x,y)) -> { CustomTheme.colors.primary.copy(alpha = 0.5F) }
                sameRow(x) || sameColumn(y) || sameSubgrid(coordinates) -> {
                    CustomTheme.colors.primary.copy(alpha = 0.15F)
                }
                else -> Color.Unspecified
            }
        } ?: Color.Unspecified

    }

    fun value() = if (text.isEmpty()) 0 else text.toInt()

    companion object {
        const val EMPTY_TILE = ""
        val toTileText = { value: Int ->
            if (value == 0) {
                EMPTY_TILE
            } else {
                value.toString()
            }
        }
    }
}