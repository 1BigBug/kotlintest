package com.example.kotlintest.Beens



data class CoinInfoBeens(var datas:List<CoinInfo>) {

    data class CoinInfo(var balance: String,var dailyEarning: String,var caculTotal: String,var balancePaid: Int,var onlineMiner: Int,
                        var hashrate: Long,var coin: String,var chartXtime: List<Long>,var chartpoints: List<Long>){

    }
}
