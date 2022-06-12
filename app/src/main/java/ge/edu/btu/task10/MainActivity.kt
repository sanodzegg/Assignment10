package ge.edu.btu.task10

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var list: RecyclerView
    lateinit var adapter: RecyclerAdapter
    lateinit var database: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = DBHelper(this)
        adapter = RecyclerAdapter(database.getEvents())
        list = findViewById(R.id.list)

        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter

        registerReceiver(Receive(), IntentFilter().apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
            addAction(Intent.ACTION_HEADSET_PLUG)
        })
    }
}
