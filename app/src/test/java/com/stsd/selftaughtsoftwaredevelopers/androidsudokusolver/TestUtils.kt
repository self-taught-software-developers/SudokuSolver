package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver

private val boardSize = (0..8)
const val defaultNumberEntry = 1

val emptySudokuBoard = (boardSize).map {
    (boardSize).map {
        0
    }.toTypedArray()
}.toTypedArray()

val inValidRowSudokuBoard = (boardSize).map { row ->
    (boardSize).map { column ->
        if (row == 1 && column % 3 == 0) 1 else 0
    }.toTypedArray()
}.toTypedArray()

val inValidColumnSudokuBoard = (boardSize).map { row ->
    (boardSize).map { column ->
        if (row % 2 == 0 && column == 1) 1 else 0
    }.toTypedArray()
}.toTypedArray()

val inValidGridSudokuBoard = (boardSize).map { row ->
    (boardSize).map { column ->

        when {
            row == 1 && column == 1 -> 1
            row == 2 && column == 2 -> 1
            else -> 0
        }
    }.toTypedArray()
}.toTypedArray()
