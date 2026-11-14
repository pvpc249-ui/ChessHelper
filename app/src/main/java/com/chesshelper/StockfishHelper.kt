package com.chesshelper

import java.io.*

object StockfishHelper {

    private var process: Process? = null
    private var writer: BufferedWriter? = null
    private var reader: BufferedReader? = null

    fun init(binaryPath: String) {
        process = Runtime.getRuntime().exec(binaryPath)
        writer = BufferedWriter(OutputStreamWriter(process!!.outputStream))
        reader = BufferedReader(InputStreamReader(process!!.inputStream))
        send("uci")
        waitFor("uciok")
        send("isready")
        waitFor("readyok")
    }

    fun getBestMove(fen: String, depth: Int): String {
        if (process == null) return "e2e4"
        send("position fen $fen")
        send("go depth $depth")
        var line = ""
        while (true) {
            line = reader?.readLine() ?: break
            if (line.startsWith("bestmove")) break
        }
        return line.split(" ").getOrNull(1) ?: "e2e4"
    }

    private fun send(cmd: String) {
        writer?.write("$cmd\n")
        writer?.flush()
    }

    private fun waitFor(token: String) {
        while (true) {
            val line = reader?.readLine() ?: break
            if (line.contains(token)) break
        }
    }

    fun quit() {
        send("quit")
        process?.destroy()
    }
}
