package co.tiagoaguiar.tutorial.jokerappdev

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

  private lateinit var appBarConf: AppBarConfiguration

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setSupportActionBar(findViewById(R.id.toolbar))

    val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
    val navView: NavigationView = findViewById(R.id.nav_view)

    appBarConf = AppBarConfiguration(
      setOf(R.id.nav_home, R.id.nav_joke_day, R.id.nav_about),
      drawerLayout)

    val navController = findNavController(R.id.nav_host_fragment_content_main)

    setupActionBarWithNavController(navController, appBarConf)
    navView.setupWithNavController(navController)
  }

  override fun onSupportNavigateUp(): Boolean {
    val navController = findNavController(R.id.nav_host_fragment_content_main)
    return navController.navigateUp(appBarConf) || super.onSupportNavigateUp()
  }

}