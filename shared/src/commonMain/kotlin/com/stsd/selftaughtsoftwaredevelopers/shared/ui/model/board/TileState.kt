package com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.Position

data class TileState(
    val value: Int? = null,
    val position: Offset? = null,
    val subgrid: Position? = null,
    val isValid: Boolean = true
) {

    val text: String = (value ?: "").toString()

    private val isNotValid = !isValid

    fun centered(
        spacing: Int,
        valueOffset: Offset
    ) : Offset? {
        val valueCenter = valueOffset.div(2f)
        val spacingCenterOffset = Offset(
            x = (spacing / 2).toFloat(),
            y = (spacing / 2).toFloat()
        )

        return position?.plus(spacingCenterOffset)?.minus(valueCenter)
    }
//    private val isSameSubgrid = { sg: Position -> sg == subgrid }
//    private val isSameColumn = { y: Int -> position.y == y }
//    private val isSameRow = { x: Int -> position.x == x }
//    private val isSelected = { coordinates: Position -> position == coordinates }

    @Composable
    fun tileColor(selected: TileState?, color: Color): Color {
        return if (isNotValid) {
            Color.Red
        } else {
            color.copy(
//                alpha = selected?.position?.let { (x, y) ->
//                    when {
//                        isSelected(selected.position) -> alphas.large_60
//                        isSameRow(x) || isSameColumn(y) || isSameSubgrid(selected.subgrid) -> {
//                            alphas.medium_30
//                        }
//                        else -> alphas.default_0
//                    }
//                } ?: alphas.default_0
            )
        }
    }

//    fun value() = text.toIntOrNull() ?: 0

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