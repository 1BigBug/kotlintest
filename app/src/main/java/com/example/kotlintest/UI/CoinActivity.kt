package com.example.kotlintest.UI
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintest.Beens.BaseBeens
import com.example.kotlintest.Beens.CoinInfoBeens
import com.example.kotlintest.Model.CointypeModel
import com.example.kotlintest.R
import com.example.kotlintest.UI.Adapter.ForceAdapter
import com.example.kotlintest.UI.Adapter.TypeAdapter
import com.trello.rxlifecycle3.android.ActivityEvent
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import com.trello.rxlifecycle3.kotlin.bindUntilEvent
import io.reactivex.Observable
import kotlinx.android.synthetic.main.coinactivity.*
import java.util.*
import java.util.concurrent.TimeUnit


class CoinActivity : RxAppCompatActivity() {
    private lateinit var cointypeModel: CointypeModel
    companion object {
        private val TAG = "RxLifecycle-Kotlin"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coinactivity)

        cointypeModel=CointypeModel()
        cointypeModel.coinInfo.observe(this, Observer<BaseBeens<CoinInfoBeens>>{
            Log.e("info", it.content.toString())
            loadui(it.content.datas)
            changeui(it.content.datas[0])
        })

        cointypeModel.getdata(intent.getStringExtra("access_token"),this)

        Observable.interval(1, TimeUnit.SECONDS)
            .doOnDispose {
                Log.i(TAG, "离线") }
            .bindUntilEvent(this, ActivityEvent.DESTROY)
            .subscribe { num -> Log.i(TAG, "在线" + num) }
            .dispose()
    }

    fun loadui(coinDatas:List<CoinInfoBeens.CoinInfo>){
        val HlayoutManager2 = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        Collections.reverse(coinDatas)
        val typeAdapter = TypeAdapter(coinDatas,itemClickListener = { coinInfo: CoinInfoBeens.CoinInfo ->
            changeui(coinInfo)
        })
        rcv_type.layoutManager = HlayoutManager2
        rcv_type.adapter = typeAdapter
        typeAdapter.notifyDataSetChanged()
    }
    fun changeui(coininfo: CoinInfoBeens.CoinInfo){
        val force_data = arrayOf<String>(
            coininfo.dailyEarning,
            coininfo.onlineMiner.toString(),
            coininfo.hashrate.toString(),
            coininfo.caculTotal
        )
        val title = arrayOf("每天收益", "在线矿工", "矿池算力", "全网算力")
        val forceAdapter = ForceAdapter(force_data,title)
        val hlayoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rcv_slinfo.setLayoutManager(hlayoutManager)
        rcv_slinfo.setAdapter(forceAdapter)
        //绘制折线图
//        chat_coin.setText(coinDatas.get(0).coin.toUpperCase())
//        chat_img.setBackgroundResource(TotalUtils.getimage(coinDatas.get(0).coin))
//        mpLinechartsUtils = MpLinechartsUtils(
//            getContext(),
//            lineChart,
//            coinDatas.get(i).getChartXtime(),
//            coinDatas.get(i).getChartpoints(),
//            ArrayList<Double>(),
//            "算力",
//            null,
//            true,
//            "#F5B300",
//            R.drawable.chartscoin
//        )
//        mpLinechartsUtils.initLineChart()
    }


}