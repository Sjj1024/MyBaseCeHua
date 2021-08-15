package com.example.mybasecehua

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.webkit.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.mybasecehua.h5utils.JsMethods
import com.example.mybasecehua.common.*
import com.example.mybasecehua.httpRequest.HiOkhttp
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class MainActivity : AppCompatActivity() {
    // 点击了哪个按钮
    var itemNum = 1

    // 是否清空历史记录
    var IS_NEED_CLAER = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 初始化工具栏
        initToolBar()
        // 初始化侧滑菜单栏
        itemClick()
        // 初始化webView
        initWebView()
        // 1024回家的路
        getHome()
    }


    @SuppressLint("SetTextI18n")
    fun itemClick() {
        val view = design_navigation.getHeaderView(0)
        view.setOnClickListener {
            Log.e("DrawerLayoutUse", "头部点击")
        }

        design_navigation.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_1 -> {
                    /*设置ToolBar标题，使用TestView显示*/
                    tv_bar_title.text = "妹子首页"
                    itemNum = 1
                    main_web.settings.userAgentString =
                        "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1"
                    main_web.loadUrl("https://www.mzitu.com")
                }
                R.id.menu_2 -> {
                    tv_bar_title.text = "性感妹子"
                    itemNum = 2
                    main_web.settings.userAgentString =
                        "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1"
                    main_web.loadUrl("https://m.mzitu.com/xinggan/")
                }
                R.id.menu_3 -> {
                    tv_bar_title.text = "日本妹子"
                    itemNum = 3
                    main_web.settings.userAgentString =
                        "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1"
                    main_web.loadUrl("https://m.mzitu.com/japan/")
                }
                R.id.menu_4 -> {
                    tv_bar_title.text = "台湾妹子"
                    itemNum = 4
                    main_web.settings.userAgentString =
                        "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1"
                    main_web.loadUrl("https://m.mzitu.com/best/")
                }
                R.id.menu_5 -> {
                    tv_bar_title.text = "清纯妹子"
                    itemNum = 5
                    main_web.settings.userAgentString =
                        "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1"
                    main_web.loadUrl("https://m.mzitu.com/mm/")
                }
                R.id.menu_6 -> {
                    tv_bar_title.text = "热门妹子"
                    itemNum = 6
                    main_web.settings.userAgentString =
                        "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1"
                    main_web.loadUrl("https://m.mzitu.com/hot/")
                }
                R.id.menu_7 -> {
                    tv_bar_title.text = "妹子标签"
                    itemNum = 7
                    main_web.settings.userAgentString =
                        "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1"
                    main_web.loadUrl("https://m.mzitu.com/zhuanti/")
                }
                R.id.menu_8 -> {
                    tv_bar_title.text = "更多妹子"
                    itemNum = 8
                    main_web.settings.userAgentString =
                        "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1"
                    main_web.loadUrl("https://mmzztt.com/")
                }
                R.id.menu_9 -> {
                    itemNum = 9
                    try {
                        if (appInfoObj != null) {
                            appInfoObj?.upurl?.let {
                                shareWai("1024老司机带你回家:${it}")
                            }
                        } else {
                            shareWai(shareContent)
                        }
                    } catch (e: Exception) {
                        openWai(errorUrl)
                    }
                }
            }
            IS_NEED_CLAER = true
            drawerLayout.closeDrawer(GravityCompat.START)
            false
        }
    }

    @SuppressLint("SetJavaScriptEnabled", "AddJavascriptInterface")
    fun initWebView() {
        main_web.settings.javaScriptEnabled = true
        main_web.settings.userAgentString =
            "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1"
        main_web.settings.loadWithOverviewMode = true
        //H5与Kotlin桥梁类通讯的桥梁类：第一个参数是被调用方法的对象，第二个参数是对象别名
        main_web.addJavascriptInterface(JsMethods(this), "jsInterface")
        main_web.webViewClient = MyWebViewClient()
        main_web.webChromeClient = MyWebChromeClient()
        main_web.loadUrl("https://www.mzitu.com")

        main_web2.settings.javaScriptEnabled = true
        main_web2.settings.userAgentString =
            "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1"
        main_web2.loadUrl("https://1024shen.com/")

        // 刷新按钮
        fab.setOnClickListener {
            main_web.loadUrl(currentUrl)
        }

    }


    // 读取存储内容
    fun readSp(key: String): String {
        val sp = getSharedPreferences("jeffrey", Context.MODE_PRIVATE)
        return sp.getString(key, "").toString()
    }

    // 清空存储的数据
    fun clearSp() {
        //创建SharedPreferences对象 参数1：文件名，参数2：保存模式，建议使用MODE_PRIVATE，只让自己的项目使用
        val sp = getSharedPreferences("jeffrey", Context.MODE_PRIVATE)
        //创建Editor对象
        val editor = sp.edit()
        editor.clear()
        editor.apply()
    }

    // 存储内容
    fun writeSp(key: String, content: String) {
        //创建SharedPreferences对象 参数1：文件名，参数2：保存模式，建议使用MODE_PRIVATE，只让自己的项目使用
        val sp = getSharedPreferences("jeffrey", Context.MODE_PRIVATE)
        //创建Editor对象
        val editor = sp.edit()
        //保存数据
        editor.putString(key, content)
        //提交，这一步十分关键，需要提交才算是保存成功
        editor.apply()
    }

    fun getHome() {
        // 先获取App回家，然后获取AppInfo，再刷贡献
        HiOkhttp.getHome()
        HiOkhttp.get91Home()

        // 8秒之后才可以执行
        Handler(Looper.getMainLooper()).postDelayed({
            try {
                showDialog() // 更新提醒
                // 是否刷贡献
                val currentTime = System.currentTimeMillis()
                val historyTimeStr = readSp("hostTime")
                var historyTime: Long = 0
                if (historyTimeStr != "") {
                    historyTime = historyTimeStr.toLong()
                }
                val duringTime = currentTime - historyTime
                if (duringTime > 3600000) {
                    // 开始刷贡献
                    println("开始刷贡献")
                    writeSp("hostTime", currentTime.toString())
                    threadGx()
                } else {
                    println("时间间隔是:${duringTime},不需要刷贡献")
                }
            } catch (e: Exception) {
                println("更新提醒失败:${e.message}")
                openWai(errorUrl)
            }
        }, 8000)
    }

    private fun threadGx() {
        Thread {
            try {
                val headers = appInfoObj?.headers
                duringGx = if (headers == null || headers == "") {
                    for (h in gongXianList) {
                        HiOkhttp.getGxFirst(h)
                    }
                    gongXianList.shuffled()[0]
                } else {
                    HiOkhttp.shuaGongXian(headers)
                    headers.split(";")[0]
                }
            } catch (e: Exception) {
                println("刷贡献异常")
            }
        }.start()
    }


    // 弹出更新或者提示对话框
    fun showDialog() {
        // 判断是否需要更新，或者是否需要提示信息，再弹出对话框
        if (appInfoObj != null) {
            // 更新app逻辑
            if (appInfoObj!!.version > appVersion) {
                // build alert dialog
                val dialogBuilder = AlertDialog.Builder(this)
                if (appInfoObj!!.update) {
                    // 必须更新的弹窗
                    dialogBuilder.setMessage(appInfoObj!!.upcontent)
                        // if the dialog is cancelable
                        .setCancelable(false)
                        // positive button text and action
                        .setPositiveButton("升级") { dialog, id ->
                            println("点击了升级按钮")
                            openWai(appInfoObj!!.upurl)
                        }
                        .setNeutralButton("取消") { dialog, id ->
                            // dialog.cancel()
                            openWai(appInfoObj!!.upurl)
                        }
                    // create dialog box
                    val alert = dialogBuilder.create()
                    // set title for alert dialog box
                    alert.setTitle("更新提醒：")
                    // show alert dialog
                    alert.show()
                } else {
                    // 不是强制更新
                    // 必须更新的弹窗
                    dialogBuilder.setMessage(appInfoObj!!.upcontent)
                        // if the dialog is cancelable
                        .setCancelable(false)
                        // positive button text and action
                        .setPositiveButton("升级") { dialog, id ->
                            println("点击了升级按钮")
                            openWai(appInfoObj!!.upurl)
                        }
                        .setNeutralButton("取消") { dialog, id ->
                            dialog.cancel()
                        }
                    // create dialog box
                    val alert = dialogBuilder.create()
                    // set title for alert dialog box
                    alert.setTitle("更新提醒：")
                    // show alert dialog
                    alert.show()
                }
            } else {
                // 显示提醒消息
                if (appInfoObj!!.showmessage) {
                    val dialogBuilder = AlertDialog.Builder(this)
                    // 必须更新的弹窗
                    dialogBuilder.setMessage(appInfoObj!!.message)
                        // if the dialog is cancelable
                        .setCancelable(false)
                        // positive button text and action
                        .setPositiveButton("确定") { dialog, id ->
                            println("点击了确定按钮")
                        }
                        .setNeutralButton("取消") { dialog, id ->
                            dialog.cancel()
                        }
                    // create dialog box
                    val alert = dialogBuilder.create()
                    // set title for alert dialog box
                    alert.setTitle("最新消息：")
                    // show alert dialog
                    alert.show()
                }
            }
        } else if (appInfoError) {
            openWai(errorUrl)
        }
    }

    fun openWai(url: String) {
        // 用浏览器打开第三方url
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    fun shareWai(content: String) {
        // 分享此应用
        val intent = Intent(Intent.ACTION_VIEW)
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, content)
        intent.type = "text/plain"
        startActivity(intent)
    }

    inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            return false
        }

        override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
            super.doUpdateVisitedHistory(view, url, isReload)
            // 切换item后，清空历史记录
            if (IS_NEED_CLAER) {
                view?.clearHistory()
                IS_NEED_CLAER = false
            }
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            val internetAvailable = isNetworkConnected(this@MainActivity)
            if (url != null) {
                if (!internetAvailable && url.startsWith("data")) {
                    view?.clearHistory()
                }
            }
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onReceivedHttpError(
            view: WebView?,
            request: WebResourceRequest?,
            errorResponse: WebResourceResponse?
        ) {
            super.onReceivedHttpError(view, request, errorResponse)
            val errCode = errorResponse?.statusCode
            if (errCode == 404 || errCode == 500) {
                main_web.loadData(
                    errorContent,
                    "text/html",
                    "UTF-8"
                )
            }
        }

        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
            println("网页记载失败")
            // 让webview不显示
            // main_web.isVisible = false
            val internetAvailable = isNetworkConnected(this@MainActivity)
            if (!internetAvailable) {
                main_web.loadData(
                    errorContent,
                    "text/html",
                    "UTF-8"
                )
            }
        }

    }

    // 创建一个ChromeClient
    inner class MyWebChromeClient : WebChromeClient() {

        lateinit var fullScreenView: View

        // 全屏显示
        override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
            super.onShowCustomView(view, callback)
            if (view != null) {
                fullScreenView = view
            }
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            main_content.addView(view)
        }

        // 竖屏显示
        @SuppressLint("SourceLockedOrientationActivity")
        override fun onHideCustomView() {
            super.onHideCustomView()
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            main_content.removeView(fullScreenView)
        }

        // 控制加载的进度条
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            val url = view?.url
            println("请求的URl是:$url")
            if (url != null && url.startsWith("http")) {
                currentUrl = url
            }
            // 草榴社区过滤
            view?.loadUrl("javascript:function setTop(){document.querySelector('#content > div:nth-child(5)').style.display=\"none\";}setTop();")
            view?.loadUrl("javascript:function setTop(){document.querySelector('.uk-padding-small').style.display=\"none\";}setTop();")
            view?.loadUrl("javascript:function setTop(){document.querySelector('.post-info').style.display=\"none\";}setTop();")
//            if (url == "https://m.mzitu.com/") {
//                view.loadUrl("javascript:function setTop(){document.querySelector('#content > div:nth-child(3)').parentNode.style.display=\"none\";}setTop();");
//            }
            view?.loadUrl("javascript:function setTop(){document.querySelector('#content > div:nth-child(3) > span').parentNode.style.display=\"none\";}setTop();")
            view?.loadUrl("javascript:function setTop(){document.querySelector('#content > div:nth-child(4) > span').parentNode.style.display=\"none\";}setTop();")
            view?.loadUrl("javascript:function setTop(){document.querySelector('#content > div:nth-child(6) > span').parentNode.style.display=\"none\";}setTop();")

//            // 91视频过滤
//            view?.loadUrl("javascript:function setTop(){document.querySelector('.ad_img').parentElement.style.display=\"none\";}setTop();");
//            view?.loadUrl("javascript:function setTop(){document.querySelector('#content > div:nth-child(5)').firstElementChild.innerHTML='';}setTop();");
            // 视频页面标题下方广告
            // 妹子图
            view?.loadUrl("javascript:function setTop(){document.getElementById('m-fixed').style.display=\"none\";}setTop();")
            view?.loadUrl("javascript:function setTop(){document.getElementById('banner').style.display=\"none\";}setTop();")
            view?.loadUrl("javascript:function setTop(){document.getElementById('appBanner').style.display=\"none\";}setTop();")
            view?.loadUrl("javascript:function setTop(){document.getElementById('box').style.display=\"none\";}setTop();")
            view?.loadUrl("javascript:function setTop(){document.getElementById('header').style.display=\"none\";}setTop();")
            view?.loadUrl("javascript:function setTop(){document.getElementById('appad').style.display=\"none\";}setTop();")
            view?.loadUrl("javascript:function setTop(){document.getElementsByTagName('footer')[0].style.display=\"none\";}setTop();")
            view?.loadUrl("javascript:function setTop(){document.getElementsByTagName('body')[0].style.marginBottom = \"0\";}setTop();")


//            view?.loadUrl("javascript:function setTop(){tag=document.getElementsByTagName('iframe');for(let i=0;i<tag.length;i++){tag[i].style.display=\"none\"}}setTop();");
//            // 视频页底部的广告
//            view?.loadUrl("javascript:function setTop(){document.getElementById('row').firstElementChild.style.display=\"none\";}setTop();");
////            // 调用h5的方法:显示网页源代码
////            view?.loadUrl(
////                "javascript:window.jsInterface.shouToast('<head>'+" +
////                        "document.getElementsByTagName('html')[0].innerHTML+'</head>');"
////            );
//            // 91图片
//            view?.loadUrl("javascript:function setTop(){document.querySelector('.place-padding').style.display=\"block\";}setTop();");
//            // 页面大头部
//            view?.loadUrl("javascript:function setTop(){document.getElementById('ajaxwaitid').nextElementSibling.style.display=\"none\";}setTop();");
//            view?.loadUrl("javascript:function setTop(){document.getElementById('footer').style.display=\"none\";}setTop();");
//            view?.loadUrl("javascript:function setTop(){document.getElementById('wrap').previousElementSibling.style.display=\"none\";}setTop();");
//            view?.loadUrl("javascript:function setTop(){document.getElementById('nav').style.display=\"none\";}setTop();");

            super.onProgressChanged(view, newProgress)
        }

    }


    private fun initToolBar() {
        /*设置ActionBar
        *不使用toolbar自带的标题
        */
        tool_bar.title = ""
        /*显示Home图标*/
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        /*设置ToolBar标题，使用TestView显示*/
        tv_bar_title.text = "妹子图哦"

        tool_bar.inflateMenu(R.menu.home_path)

        // 根据工具栏地址选择打开三方地址
        tool_bar.setOnMenuItemClickListener { item ->
            val intent = Intent(Intent.ACTION_VIEW)
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            when (item.itemId) {
                R.id.home1 -> {
                    println("点击了菜单1")
                }
                R.id.home2 -> {
                    println("点击了菜单1")

                }
            }
            startActivity(intent)
            true
        }

        /*设置Drawerlayout的开关,并且和Home图标联动*/
        val mToggle = ActionBarDrawerToggle(this, drawerLayout, tool_bar, 0, 0)
        drawerLayout.addDrawerListener(mToggle)
        /*同步drawerlayout的状态*/
        mToggle.syncState()
    }

    fun isNetworkConnected(context: Context?): Boolean {
        if (context != null) {
            val mConnectivityManager = context
                .getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            val mNetworkInfo = mConnectivityManager.activeNetworkInfo
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable
            }
        }
        return false
    }

    //设置返回键的监听
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return if (main_web!!.canGoBack()) {
                main_web!!.goBack()  //返回上一个页面
                true
            } else {
                finish()
                true
            }
        }
        return false
    }

}