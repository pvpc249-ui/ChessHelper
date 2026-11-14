package com.chesshelper

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                    val i = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
                    startActivity(i)
                    Toast.makeText(this@MainActivity, "Permita overlay e toque LIGAR novame
olliE: erro de compilação no MainActivity.kt — `Intent` e `FloatingButtonService` com referência errada. corrige no Termux:

```bash
cd ~/ChessHelper
cat > app/src/main/java/com/chesshelper/MainActivity.kt << 'EOF'
package com.chesshelper

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                    val i = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
                    startActivity(i)
                    Toast.makeText(this@MainActivity, "Permita overlay e toque LIGAR novamente", Toast.LENGTH_LONG).show()
                } else {
                    val i = Intent(this@MainActivity, FloatingButtonService::class.java)
                    startService(i)
                    Toast.makeText(this@MainActivity, "Botão flutuante ativado!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }

        layout.addView(title)
        layout.addView(btn)
        setContentView(layout)
    }
}
