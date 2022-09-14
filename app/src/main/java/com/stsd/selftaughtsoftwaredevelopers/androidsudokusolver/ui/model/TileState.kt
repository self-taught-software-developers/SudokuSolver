package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.ExtendedTheme.alpha
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.ExtendedTheme.colors

data class TileState(
    val text: String = EMPTY_TILE,
    val position: Pair<Int, Int>,
    val subgrid: Pair<Int, Int>,
    val rect: Rect? = null,
    val isValid: Boolean = true
) {

    private val isNotValid = !isValid
    private val isSameSubgrid = { sg : Pair<Int, Int> -> sg == subgrid }
    private val isSameColumn = { y : Int -> position.second == y }
    private val isSameRow = { x : Int -> position.first == x }
    private val isSelected = { coordinates : Pair<Int, Int> -> position == coordinates }

    val x = position.first
    val y = position.second

    @Composable
    fun tileColor(selected: TileState?, color: Color) : Color {
        return if (isNotValid) {
            colors.error
        } else {
            color.copy(
                alpha = selected?.position?.let { (x, y) ->
                    when {
                        isSelected(selected.position) -> alpha.large_60
                        isSameRow(x) || isSameColumn(y) || isSameSubgrid(selected.subgrid) -> {
                            alpha.medium_30
                        }
                        else -> alpha.default_0
                    }
                } ?: alpha.default_0
            )
        }

    }

    @Composable
    fun tileSize() : Dp {

        with(LocalDensity.current) {
            return rect?.let { it ->
                minOf(it.width, it.height).toDp()
            } ?: 64.dp
        }

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