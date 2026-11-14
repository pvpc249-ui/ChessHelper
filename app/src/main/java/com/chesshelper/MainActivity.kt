package com.chesshelper

import android.app.Activity
import android.content.Intent
import android.media.projection.MediaProjectionManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val PROJECTION_REQUEST = 1001
    private lateinit var projectionManager: MediaProjectionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        projectionManager = getSystemService(MEDIA_PROJECTION_SERVICE) as MediaProjectionManager

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = android.view.Gravity.CENTER
            setBackgroundColor(0xFF1a1a2e.toInt())
            layoutParams = android.view.ViewGroup.LayoutParams(-1, -1)
        }

        val title = TextView(this).apply {
            text = "ChessHelper"
            textSize = 30f
            setTextColor(0xFFFFFFFF.toInt())
            gravity = android.view.Gravity.CENTER
            setPadding(0, 0, 0, 80)
        }

        val btn = Button(this).apply {
            text = "LIGAR"
            textSize = 24f
            setTextColor(0xFFFFFFFF.toInt())
            setBackgroundColor(0xFF2ecc71.toInt())
            setPadding(100, 50, 100, 50)
            setOnClickListener {
                if (!Settings.canDrawOverlays(this@MainActivity)) {
                    startActivity(Intent(
                        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:$packageName")
                    ))
                    Toast.makeText(this@MainActivity, "Permita 'Aparecer sobre outros apps' e toque LIGAR novamente", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                val intent = Intent(this@MainActivity, FloatingButtonService::class.java)
                startService(intent)
                Toast.makeText(this@MainActivity, "Botão flutuante ativado!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        layout.addView(title)
        layout.addView(btn)
        setContentView(layout)
    }
}
