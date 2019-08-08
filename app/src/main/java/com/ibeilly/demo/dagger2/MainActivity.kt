package com.ibeilly.demo.dagger2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.snackbar.Snackbar
import com.ibeilly.demo.dagger2.bean.Dog
import dagger.Lazy
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.text.DateFormat
import javax.inject.Inject
import kotlin.random.Random

@Route(path = "/app/main")
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var dog1: Dog

    @Inject
    lateinit var dog2: Dog

    @Inject
    lateinit
    var format: DateFormat

    @Inject
    lateinit var toast: Lazy<Toast>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        app.appComponent.mainComponent()
            .dogName("哈士奇-${Random.nextInt(100)}")
            .build()
            .injectMain(this)

        tvLog.text = """
                ${pretty("car1")}
                ${pretty("car2")}
                ${pretty("person1")}
                ${pretty("person2")}
                ${pretty("dog1")}
                ${pretty("dog2")}
                ${pretty("format")}
                ${pretty("toast")}
            """
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action") {
                    toast.get().show("Aciton ${Random.nextInt(100)}")
                }.show()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                ARouter.getInstance().build("/app/demo")
                    .navigation()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

