package apps.paprika.aplicacionrick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import apps.paprika.aplicacionrick.network.ApiClient
import apps.paprika.aplicacionrick.network.Character
import apps.paprika.aplicacionrick.network.CharacterResponse
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val client = ApiClient.apiService.fetchCharacters("1")
        client.enqueue(object : retrofit2.Callback<CharacterResponse>{

            override fun  onResponse(
                call: Call<CharacterResponse>,
                response:Response<CharacterResponse>
            ){

                if (response.isSuccessful){



                    Log.d("characters", ""+response.body())

                    val result = response.body()?.result
                    result?.let {
                        val adapter = MainAdapter(result)
                        val recyclerView= findViewById<RecyclerView>(R.id.characterRv)
                        recyclerView?.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                       recyclerView?.adapter=adapter

                        adapter.setOnItemClickListener(object :MainAdapter.onItemClickListener{
                            override fun onItemClick(position: Int) {
                                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                                intent.putExtra("name", result[position].name)
                                intent.putExtra("image", result[position].image)
                                startActivity(intent)
                            }

                        })
                    }
                }
            }
           override fun onFailure(call: Call<CharacterResponse>, t:Throwable){
               Log.e("failed", ""+t.message)
           }

        })

    }
}