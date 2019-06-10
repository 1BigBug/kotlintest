package com.example.kotlintest.UI.Base

import android.app.Activity
import android.app.Application

class MApplication : Application() {
    object list {
        //用object修饰，相当于Java中的static，用object修饰一个变量，可以实现全局变量的效果
        var list: ArrayList<Activity>? = null
    }

    override fun onCreate() {
        super.onCreate()
    }

    companion object {//用companion object包裹方法，实现java中static的效果
        /**
         * 添加Activity到全局集合中，方便一键退出
         */
        fun registerActivity(activity: Activity) {
            list.list?.add(activity)
        }
        /**
         * 退出activity
         */
        fun exitActivity() {
            list.list?.clear()//清空集合
            System.exit(0)//退出虚拟机
        }
    }

}