package com.example.aws_application

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.androidgettingstarted.NoteRecyclerViewAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.hide()



        // prepare our List view and RecyclerView (cells)
        setupRecyclerView(item_list)
        setupAuthButton(UserData)

        UserData.isSignedIn.observe(this, Observer<Boolean> { isSignedUp ->
            // update UI
            Log.i(TAG, "isSignedIn changed : $isSignedUp")

            //animation inspired by https://www.11zon.com/zon/android/multiple-floating-action-button-android.php
            if (isSignedUp) {
                fabAuth.setImageResource(R.drawable.unlock)
                Log.d(TAG, "Showing fabADD")
                fabAdd.show()
                fabAdd.animate().translationY(0.0F - 1.1F * fabAuth.customSize)
            } else {
                fabAuth.setImageResource(R.drawable.app_lock)
                Log.d(TAG, "Hiding fabADD")
                fabAdd.hide()
                fabAdd.animate().translationY(0.0F)
            }
        })
        // register a click listener
        fabAdd.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }

    }

    // MainActivity.kt
// receive the web redirect after authentication
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Backend.handleWebUISignInResponse(requestCode, resultCode, data)
    }


    // recycler view is the list of cells
    private fun setupRecyclerView(recyclerView: RecyclerView) {

        // update individual cell when the Note data are modified
        UserData.notes().observe(this, Observer<MutableList<UserData.Note>> { notes ->
            Log.d(TAG, "Note observer received ${notes.size} notes")

            // let's create a RecyclerViewAdapter that manages the individual cells
            recyclerView.adapter = NoteRecyclerViewAdapter(notes)

            // add a touch gesture handler to manager the swipe to delete gesture
            val itemTouchHelper = ItemTouchHelper(SwipeCallback(this))
            itemTouchHelper.attachToRecyclerView(recyclerView)
        })
    }
    // anywhere in the MainActivity class
    private fun setupAuthButton(userData: UserData) {

        // register a click listener
        fabAuth.setOnClickListener { view ->

            val authButton = view as FloatingActionButton

            if (userData.isSignedIn.value!!) {
                authButton.setImageResource(R.drawable.app_lock)
                Backend.signOut()
            } else {
                authButton.setImageResource(R.drawable.unlock)
                Backend.signIn(this)
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}


