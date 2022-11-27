package ru.vsibi.lastmin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.TintContextWrapper.wrap
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NavUtils
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import ru.vsibi.presentation.helpers.ContextWrapper
import ru.vsibi.presentation.helpers.Router
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val router: Router by lazy { Router.instance }

    override fun attachBaseContext(newBase: Context?) {
        val currentLang = "en"
        val newLocale = Locale(currentLang)
        val context: Context = ContextWrapper.wrap(newBase, newLocale)
        super.attachBaseContext(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        router.parentNavHostFragment = navHostFragment
    }

    override fun onBackPressed() {
        if (router.childNavController?.popBackStack() == false) {
            if (router.parentNavController?.popBackStack() == false) {
                finish()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}