package com.adityafakhri.premierleaguestadium

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvStadium: RecyclerView
    private val list = ArrayList<Stadium>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Premier League Stadiums"

        rvStadium = findViewById(R.id.rv_stadium)
        rvStadium.setHasFixedSize(true)

        list.addAll(getListStadium())
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvStadium.layoutManager = LinearLayoutManager(this)
        val listStadiumAdapter = ListStadiumAdapter(list)
        rvStadium.adapter = listStadiumAdapter

        listStadiumAdapter.setOnItemClickCallback(object : ListStadiumAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Stadium) {
                val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
                intentToDetail.putExtra(DetailActivity.EXTRA_STADIUM, data)
                startActivity(intentToDetail)
            }
        })
    }

    @SuppressLint("Recycle")
    private fun getListStadium(): ArrayList<Stadium> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataTeam = resources.getStringArray(R.array.data_team)
        val dataCapacity = resources.obtainTypedArray(R.array.data_capacity)
        val listStadium = ArrayList<Stadium>()
        for (i in dataName.indices) {
            val stadium = Stadium(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataTeam[i], dataCapacity.getInt(i, -1))
            listStadium.add(stadium)
        }
        return listStadium
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val aboutPage = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(aboutPage)
            }
        }
        return super.onOptionsItemSelected(item)
    }






}