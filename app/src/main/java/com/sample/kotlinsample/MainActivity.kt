package com.sample.kotlinsample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.kotlinsample.adapter.MyAdapter
import com.sample.kotlinsample.model.Data
import com.sample.kotlinsample.model.EmployeeResponse
import com.sample.kotlinsample.network.ApiClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var myCustomAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        rvEmployees.layoutManager = LinearLayoutManager(this)
        rvEmployees.setHasFixedSize(true)
        getEmployeesApi();
    }

    private fun getEmployeesApi() {

        ApiClient.create().getEmployees()
            .enqueue(object : Callback<EmployeeResponse> {
                override fun onFailure(call: Call<EmployeeResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<EmployeeResponse>,
                    response: Response<EmployeeResponse>
                ) {
                    if (response.code() == 200) {
                        println(response.body())
                        var listOfEmployes: List<Data> = response.body()?.data!!
                        myCustomAdapter = MyAdapter(applicationContext, listOfEmployes)
                        rvEmployees.setAdapter(myCustomAdapter)
                    }

                }
            })
    }
}
