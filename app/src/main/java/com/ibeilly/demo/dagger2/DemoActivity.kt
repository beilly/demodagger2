package com.ibeilly.demo.dagger2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.snackbar.Snackbar
import com.ibeilly.demo.dagger2.bean.Car
import com.ibeilly.demo.dagger2.bean.Person
import com.ibeilly.demo.dagger2.dagger2.DaggerPersonComponent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import javax.inject.Inject

@Route(path = "/app/demo")
class DemoActivity : AppCompatActivity() {

    @Autowired(name = "car1")
    lateinit var carParam: Car

    @Inject
    lateinit var car2: Car

    @Autowired(name = "person1")
    lateinit var personParam: Person

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
        ARouter.getInstance().inject(this)

        tvLog.text = """
                carParam: ${carParam.print}
                car2: ${car2.print}
                personParam: ${personParam.print}
                person2: ${person2.print}
                format: ${format.print}
            """

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
