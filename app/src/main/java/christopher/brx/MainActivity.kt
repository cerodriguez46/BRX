package christopher.brx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

//class MainActivity inherits from AppCompatActivity class
class MainActivity : AppCompatActivity() {

    //this function creates the class and takes a bundle object as savedInstanceState
    // ? refers to safe call, will say if it is null, so if bundle is null will show null
    //!! allows null pointer exceptions to be thrown if it is null
    override fun onCreate(savedInstanceState: Bundle?) {
        //calls onCreate from super class and takes in parameter savedInstanceState bundle
        super.onCreate(savedInstanceState)
        //sets the layout file for the class which is activity_main
        setContentView(R.layout.activity_main)
    }
}
