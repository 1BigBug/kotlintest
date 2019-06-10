package com.example.kotlintest.Model

import com.example.kotlintest.Beens.CoinInfoBeens
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlintest.Beens.BaseBeens
import com.example.kotlintest.http.Api
import com.example.kotlintest.http.ApiErrorModel
import com.example.kotlintest.http.ApiResponse
import com.example.kotlintest.http.ApiSubscribe



class CointypeModel : ViewModel() {
    val coinInfo = MutableLiveData<BaseBeens<CoinInfoBeens>>()
    fun getdata(access_token: String,context: Context){
        Api.instance.init()
        Api.instance.service.GetCoinInfo(access_token)
            .compose(ApiSubscribe.compose())
            .subscribe(object : ApiResponse<BaseBeens<CoinInfoBeens>>(context){
                override fun success(data: BaseBeens<CoinInfoBeens>) {
                    coinInfo.value=data
                }

                override fun failure(statusCode: Int, apiErrorModel: ApiErrorModel) {
                    Toast.makeText(context, apiErrorModel.message, Toast.LENGTH_SHORT).show()
                }

            })
    }
}