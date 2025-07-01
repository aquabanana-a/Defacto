package com.banana.defacto.view.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import com.banana.defacto.R
import com.banana.defacto.di.AppGraph
import javax.inject.Inject
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.banana.defacto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainContract.View {
    @Inject
    lateinit var presenter: MainPresenterImpl

    private lateinit var navigationController: NavController
    private lateinit var vb: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AppGraph.appComponent.inject(this)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(ActivityMainBinding.inflate(layoutInflater)
            .apply { vb = this }
            .root)
        presenter.attachView(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupNavigation()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    private fun setupNavigation() {
        setSupportActionBar(vb.toolbar)

        navigationController = findNavController(R.id.navigationHost)
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.factListFragment)
        )

        setupActionBarWithNavController(navigationController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navigationController.navigateUp() || super.onSupportNavigateUp()
    }
}