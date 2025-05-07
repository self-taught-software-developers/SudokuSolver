package com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board

import androidx.compose.ui.geometry.Offset
import com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.Position

data class TileState(
    val value: Int = 0,
    val position: Position,
    val origin: PlacementOrigin
) {

    val text: String get() = if (value == 0) EMPTY_TILE else value.toString()

    fun centered(
        spacing: Int,
        valueOffset: Offset
    ) : Offset {
        val valueCenter = valueOffset.div(2f)
        val spacingCenterOffset = Offset(
            x = (spacing / 2).toFloat(),
            y = (spacing / 2).toFloat()
        )

        return position.toOffset.times(spacing.toFloat())
            .plus(spacingCenterOffset).minus(valueCenter)
    }

    fun topLeft(spacing: Int) : Offset {
        return position.toOffset.times(spacing.toFloat())
    }

    companion object {
        const val EMPTY_TILE = ""
    }
}