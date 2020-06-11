/*
* xxxxx
* xxxxx
* xxxxx
* */
package com.duode.lint.lintdemo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.ActionBar
import java.util.*

/**
 * xxxxx
 * xxxxx
 * */
class MainActivity : AppCompatActivity() {

    private val hello = "你好"
    val t: String = ""

    companion object {
        @JvmStatic
        val a: String = ""

        const val b: String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val m: String = ""

        Log.v("MainActivity", "xx")

        Log.wtf("MainActivity", "你好你好")
//
//        Log.println(Log.ASSERT, "MainActivity", "xx")


        System.err.print("System.err.print:$hello")

        System.err.println("System.err.println")

        System.out.printf("printf你好你好")



        System.out.format("System.out.format")


        print("System.err.println")


        System.out.flush()

        System.out.checkError()

        System.out.format("System.out.format")

        System.out.append("System.out.append")

        System.out.flush()


//        System.out.println("System.out.println")
//
        testA()
    }

    //    private fun print(message: Any?) {
//
//    }
    private fun println(message: Any?) {

    }

    fun testA() {
        testB()
    }

    fun testB(): ArrayList<String> {
        emptyArray<String>()
        return ArrayList<String>()
    }

    fun testC() {

        Toast.makeText(this, "xx", Toast.LENGTH_LONG).show()

        Toast.makeText(this, "你好", Toast.LENGTH_LONG).show()

    }

    fun AtestC() {
        if (false) 1 else 0

        if (true) {
            2
        }

    }

    override fun onBackPressed() {
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    interface ListenerC {

    }
}

interface Listener {

}