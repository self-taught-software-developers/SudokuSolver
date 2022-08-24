package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.theme.CustomTheme

data class TileState(
    var text: String = EMPTY_TILE,
    var position: Pair<Int, Int>
) {

    private val sameSubgrid = { position : Pair<Int, Int> ->

        false
    }
    private val sameColumn = { position : Pair<Int, Int> ->
        false
    }
    private val sameRow = { position : Pair<Int, Int> ->
        false
    }
    private val isSelected = { position : Pair<Int, Int> ->
        false
    }

    @Composable
    fun tileColor(coordinates: Pair<Int, Int>) : Color {

        return when {
            isSelected(coordinates) -> { CustomTheme.colors.primary.copy(alpha = 0.15F) }
            sameRow(coordinates) || sameColumn(coordinates) || sameSubgrid(coordinates) -> {
                CustomTheme.colors.primary.copy(alpha = 0.05F)
            }
            else -> Color.Unspecified
        }
    }

    fun value() = if (text.isEmpty()) 0 else text.toInt()

    companion object {
        const val EMPTY_TILE = ""
    }
}