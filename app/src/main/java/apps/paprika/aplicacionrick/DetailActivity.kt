package apps.paprika.aplicacionrick
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setValuesToView()
    }
    private fun setValuesToView(){
        detailActivityTv.text = intent.getStringExtra("name")
        val urlImage = intent.getStringExtra("image")
      Picasso.get().load(urlImage).into(detailActivityIm)
    }
}