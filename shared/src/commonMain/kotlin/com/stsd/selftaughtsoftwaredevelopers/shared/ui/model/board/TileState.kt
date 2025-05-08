package com.stsd.selftaughtsoftwaredevelopers.shared.ui.model.board

import androidx.compose.ui.geometry.Offset
import com.cerve.development.ui.canvas.model.CervePosition

data class TileState(
    val value: Int = 0,
    val position: CervePosition,
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

        return position.offset.offset.times(spacing.toFloat())
            .plus(spacingCenterOffset).minus(valueCenter)
    }

    fun topLeft(spacing: Int) : Offset {
        return position.offset.offset.times(spacing.toFloat())
    }

    companion object {
        const val EMPTY_TILE = ""
    }
}