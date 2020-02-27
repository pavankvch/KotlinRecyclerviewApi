package com.example.mvvmkotlincoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmkotlincoroutines.model.Data
import com.example.mvvmkotlincoroutines.model.EmployeeResponse
import com.example.mvvmkotlincoroutines.network.APIClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class EmployeeActivity : AppCompatActivity() {

    var myAdapter: MyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {

        rvEmployees.layoutManager = LinearLayoutManager(this)
        rvEmployees.setHasFixedSize(true)

        getEmployeeApi()
    }

    private fun getEmployeeApi() {

        val service = APIClient.getInstance()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getEmployeesList()
            try {
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
//                        Toast.makeText(applicationContext, "${response.body()}", Toast.LENGTH_SHORT).show()
                        response.body()?.let { initRecyclerView(it) }
                    } else {
                        Toast.makeText(applicationContext, "Error network operation failed with ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: HttpException) {
                Log.e("REQUEST", "Exception ${e.message}")
            } catch (e: Throwable) {
                Log.e("REQUEST", "Ooops: Something else went wrong")
            }
        }

    }

    private fun initRecyclerView(it: EmployeeResponse) {

        val listOfEmployees: List<Data> = it.data

        myAdapter = MyAdapter(applicationContext, listOfEmployees)
        rvEmployees?.adapter = myAdapter
    }
}
