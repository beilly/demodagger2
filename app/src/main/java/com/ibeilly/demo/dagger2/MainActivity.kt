package com.ibeilly.demo.dagger2

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ibeilly.demo.dagger2.bean.Car
import com.ibeilly.demo.dagger2.bean.Person
import com.ibeilly.demo.dagger2.dagger2.DaggerPersonComponent
import com.ibeilly.demo.dagger2.dagger2.PersonModule

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import javax.inject.Inject

@Route(path = "/app/main")
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var car1: Car

    @Inject
    lateinit var car2: Car

    @Inject
    lateinit var person1: Person

    @Inject
    lateinit var person2: Person

//    @Inject lateinit
    var format: DateFormat = SimpleDateFormat()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        DaggerPersonComponent.builder()
            .appComponent(app.appComponent)
            .build()
            .inject(this)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            tvLog.text = """
                car1: ${car1.print}
                car2: ${car2.print}
                person1: ${person1.print}
                person2: ${person2.print}
                format: ${format.print}
            """
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
                    .withObject("car1", car1)
                    .withObject("person1", person1)
                    .navigation()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

