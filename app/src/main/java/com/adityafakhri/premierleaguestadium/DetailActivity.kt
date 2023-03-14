package com.adityafakhri.premierleaguestadium

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
class DetailActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_STADIUM = "extra_stadium"
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.title = "Stadium Details"

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val btnDialPhone: Button = findViewById(R.id.btn_share)
        btnDialPhone.setOnClickListener(this)

        val tvStadiumName: TextView = findViewById(R.id.tv_stadium_name)
        val imgStadium: ImageView = findViewById(R.id.img_stadium)
        val tvDescription: TextView = findViewById(R.id.tv_item_description)
        val tvTeam: TextView = findViewById(R.id.tv_team)
        val tvCapacity: TextView = findViewById(R.id.tv_capacity)

        val dataStadium = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("key_stadium", Stadium::class.java) as Stadium
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("key_stadium")
        }

        if (dataStadium != null) {
            tvStadiumName.text = dataStadium.name
            imgStadium.setImageResource(dataStadium.photo)
            tvDescription.text = dataStadium.description
            tvTeam.text = dataStadium.team
            tvCapacity.text = dataStadium.capacity.toString()
        }
    }

    override fun onClick(v: View?) {
        val dataStadium = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("key_stadium", Stadium::class.java) as Stadium
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("key_stadium")
        }

        val name = dataStadium?.name
        val team = dataStadium?.team
        val capacity = dataStadium?.capacity

        val stringValue = """
            Stadium Name: $name
            Team: $team
            Capacity: $capacity
        """.trimIndent()

        when (v?.id) {
            R.id.btn_share -> {
                val sharingIntent = Intent()
                sharingIntent.action = Intent.ACTION_SEND
                sharingIntent.putExtra(Intent.EXTRA_TEXT, stringValue)
                sharingIntent.type="text/plain"
                startActivity(Intent.createChooser(sharingIntent,"Share to:"))
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        finish()
        return true
    }


}