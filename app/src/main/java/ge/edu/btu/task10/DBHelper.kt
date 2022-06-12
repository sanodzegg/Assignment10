package ge.edu.btu.task10

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import java.text.SimpleDateFormat
import java.util.*

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, "events_database", null, 1) {

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(
            "CREATE TABLE events(" +
                    "id integer PRIMARY KEY," +
                    "event_type text," +
                    "create_date date)"
        )
    }

    override fun onUpgrade(database: SQLiteDatabase?, p1: Int, p2: Int) {
        database!!.execSQL("DROP TABLE IF EXISTS events")
        onCreate(database)
    }

    fun save(actionType: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CANADA)
        val strDate: String = sdf.format(Date())
        contentValues.put("event_type", actionType)
        contentValues.put("create_date", strDate)
        val success = db.insert("events", null, contentValues)
        db.close()
        return success
    }

    @SuppressLint("Range", "Recycle")
    fun getEvents(): ArrayList<Fire> {
        val list = ArrayList<Fire>()
        val selectQuery = "SELECT  * FROM events"
        val db = this.readableDatabase
        val cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                list.add(
                    Fire(
                        id = cursor.getInt(0),
                        name = cursor.getString(1).toString(),
                        date = cursor.getString(2).toString()
                    )
                )
            }
        }
        db.close()

        return list
    }

}
