package com.chesshelper

import android.app.*
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.WindowManager
import android.widget.TextView

class OverlayService : Service() {

    private lateinit var windowManager: WindowManager
    private lateinit var overlayView: TextView

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val move = intent?.getStringExtra("move") ?: "..."

        createNotificationChannel()
        startForeground(1, buildNotification())

        if (!::overlayView.isInitialized) {
            windowManager = getSystemService(WINDOW_SERVICE) as WindowManager

            overlayView = TextView(this).apply {
                text = "▶ $move"
                textSize = 22f
                setTextColor(Color.GREEN)
                setBackgroundColor(0xCC000000.toInt())
                setPadding(16, 8, 16, 8)
            }

            val params = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
            ).apply {
                gravity = Gravity.TOP or Gravity.START
                x = 20; y = 100
            }

            windowManager.addView(overlayView, params)
        } else {
            overlayView.text = "▶ $move"
        }

        return START_STICKY
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            "chess_overlay", "Chess Overlay", NotificationManager.IMPORTANCE_LOW
        )
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
    }

    private fun buildNotification() = Notification.Builder(this, "chess_overlay")
        .setContentTitle("ChessHelper ativo")
        .setSmallIcon(android.R.drawable.ic_menu_view)
        .build()

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        if (::overlayView.isInitialized) windowManager.removeView(overlayView)
    }
}
