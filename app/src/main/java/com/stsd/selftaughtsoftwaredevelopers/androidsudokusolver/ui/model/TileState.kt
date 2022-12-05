package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.cerve.co.material3extension.designsystem.ExtendedTheme.alphas
import com.cerve.co.material3extension.designsystem.ExtendedTheme.colors

data class TileState(
    val text: String = EMPTY_TILE,
    val position: Pair<Int, Int>,
    val subgrid: Pair<Int, Int>,
    val isValid: Boolean = true
) {

    private val isNotValid = !isValid
    private val isSameSubgrid = { sg: Pair<Int, Int> -> sg == subgrid }
    private val isSameColumn = { y: Int -> position.second == y }
    private val isSameRow = { x: Int -> position.first == x }
    private val isSelected = { coordinates: Pair<Int, Int> -> position == coordinates }

    val x = position.first
    val y = position.second

    @Composable
    fun tileColor(selected: TileState?, color: Color): Color {
        return if (isNotValid) {
            colors.error
        } else {
            color.copy(
                alpha = selected?.position?.let { (x, y) ->
                    when {
                        isSelected(selected.position) -> alphas.large_60
                        isSameRow(x) || isSameColumn(y) || isSameSubgrid(selected.subgrid) -> {
                            alphas.medium_30
                        }
                        else -> alphas.default_0
                    }
                } ?: alphas.default_0
            )
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
