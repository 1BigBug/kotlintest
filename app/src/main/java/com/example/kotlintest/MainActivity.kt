package com.example.kotlintest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlintest.Beens.Access_TokenBeens
import com.example.kotlintest.http.Api
import com.example.kotlintest.http.ApiErrorModel
import com.example.kotlintest.http.ApiResponse
import com.example.kotlintest.http.ApiSubscribe
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_load.setOnClickListener { getaccess_token() }
    }

    fun  getaccess_token(){
        Api.instance.init()
        Api.instance.service.getAccessToken("url",et_username.text.toString(),et_password.text.toString(),"password")
            .compose(ApiSubscribe.compose())                      //线程切换处理 //生命周期管理
            .subscribe(object : ApiResponse<Access_TokenBeens>(this) {       //对象表达式约等于Java中的匿名内部类
                override fun success(data: Access_TokenBeens) {
                    //请求成功，此处显示一些返回的数据
                }
                override fun failure(statusCode: Int, apiErrorModel: ApiErrorModel) { //请求失败，此处直接显示Toast
                    Toast.makeText(this@MainActivity, apiErrorModel.message, Toast.LENGTH_SHORT).show()
                }
            })

    }
}
