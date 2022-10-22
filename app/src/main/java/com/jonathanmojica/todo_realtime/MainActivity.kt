package com.jonathanmojica.todo_realtime

import android.app.Activity
import android.app.AlertDialog
import android.app.PendingIntent.getActivity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import com.google.firebase.database.FirebaseDatabase
import com.jonathanmojica.todo_realtime.databinding.ActivityMainBinding
import com.jonathanmojica.todo_realtime.model.Postick
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val marksRef = FirebaseDatabase.getInstance().getReference("todos")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.fab.setOnClickListener{
            showDialog(this)
            //save()
        }
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
        val win = activity.window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    private fun showDialog(context: Context) {
        val alertDialog: AlertDialog?
        val dialogBuilder = AlertDialog.Builder(context)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView: View = inflater.inflate(R.layout.alert_dialog_add, null)
        dialogBuilder.setView(dialogView)
        val txtTitulo = dialogView.findViewById<View>(R.id.txtTitulo) as EditText
        val txtColor = dialogView.findViewById<View>(R.id.txtColor) as EditText
        val btn = dialogView.findViewById<View>(R.id.btnEnviar) as Button

        alertDialog = dialogBuilder.create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()
        alertDialog.setCancelable(false)
        btn.setOnClickListener {
            alertDialog.cancel()
            save(txtTitulo.text.toString(), txtColor.text.toString())
        }


    }

    private fun save(titulo:String,color:String) {
        val mark = Postick(
            UUID.randomUUID().toString(),
            Color.parseColor("#$color"),
            titulo,
            arrayListOf(),
            arrayListOf()
        )
        marksRef.push().setValue(mark)
    }

}