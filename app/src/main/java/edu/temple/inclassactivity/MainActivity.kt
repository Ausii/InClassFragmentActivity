package edu.temple.inclassactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Fetch images into IntArray called imageArray
        val typedArray = resources.obtainTypedArray(R.array.image_ids)
        val imageArray = IntArray(typedArray.length()) {typedArray.getResourceId(it, 0)}
        typedArray.recycle()

        // Attach an instance of ImageDisplayFragment using factory method
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            (supportFragmentManager.findFragmentById(R.id.imageDisplayFragmentContainer) as ImageDisplayFragment).setImages(imageArray)
        }

        val imageFragment = ImageDisplayFragment.newInstance(imageArray)

        if (supportFragmentManager.findFragmentById(R.id.imageDisplayFragmentContainer) !is ImageDisplayFragment)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.imageDisplayFragmentContainer, imageFragment)
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .commit()
    }
}