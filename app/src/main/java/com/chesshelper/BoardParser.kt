package com.chesshelper

import android.graphics.Bitmap

object BoardParser {

    // FILL: mede com Layout Inspector no app alvo
    private const val BOARD_X    = 50
    private const val BOARD_Y    = 200
    private const val BOARD_SIZE = 900

    fun parse(screen: Bitmap): String {
        val cellSize = BOARD_SIZE / 8
        val board = Array(8) { CharArray(8) { '.' } }

        for (row in 0..7) {
            for (col in 0..7) {
                val x = BOARD_X + col * cellSize
                val y = BOARD_Y + row * cellSize
                if (x + cellSize < screen.width && y + cellSize < screen.height) {
                    val cell = Bitmap.createBitmap(screen, x, y, cellSize, cellSize)
                    board[row][col] = classifyCell(cell)
                }
            }
        }
        return toFen(board)
    }

    private fun classifyCell(cell: Bitmap): Char {
        // FILL: substituir por template matching com sprites do app alvo
        return '.'
    }

    private fun toFen(board: Array<CharArray>): String {
        val sb = StringBuilder()
        for (row in board) {
            var empty = 0
            for (cell in row) {
                if (cell == '.') empty++
                else {
                    if (empty > 0) { sb.append(empty); empty = 0 }
                    sb.append(cell)
                }
            }
            if (empty > 0) sb.append(empty)
            sb.append('/')
        }
        return sb.dropLast(1).toString() + " w KQkq - 0 1"
    }
}
