package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.cerve.co.material3extension.designsystem.ExtendedTheme.alphas

data class TileState(
    val text: String = EMPTY_TILE,
    val position: Position,
    val subgrid: Position,
    val isValid: Boolean = true
) {

    private val isNotValid = !isValid
    private val isSameSubgrid = { sg: Position -> sg == subgrid }
    private val isSameColumn = { y: Int -> position.y == y }
    private val isSameRow = { x: Int -> position.x == x }
    private val isSelected = { coordinates: Position -> position == coordinates }

    @Composable
    fun tileColor(selected: TileState?, color: Color): Color {
        return if (isNotValid) {
            Color.Red
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

    fun value() = text.toIntOrNull() ?: 0

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
