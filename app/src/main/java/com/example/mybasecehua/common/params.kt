package com.example.mybasecehua.common


//app版本
val appVersion = 1.4
// 分享应用
var shareContent = "1024老司机带你回家了：https://docs.qq.com/doc/DVGhIR05ZR3lTWGRa"
// 间隔刷贡献的贡献头
var duringGx = ""

// app信息对象
data class AppInfo(
    var update: Boolean,
    var version: Float,
    var upurl: String,
    var upcontent: String,
    var showmessage: Boolean,
    var message: String,
    var headers: String,
    var weixinxin: String,
    var weiphoto: String,
    var about: String,
    var mazinote: String = "需要邀请码才可以注册哦"
) {
    override fun toString(): String {
        return "AppInfo(update=$update, version=$version, upurl='$upurl', upcontent='$upcontent', showmessage=$showmessage, message='$message', headers='$headers', weixinxin='$weixinxin', weiphoto='$weiphoto', about='$about', mazinote='$mazinote')"
    }
}

// 存储appInfo
var appInfoObj: AppInfo? = null
var appInfoError = false

// 如果升级失败，就打开此链接
var errorUrl = "https://docs.qq.com/doc/DVEZ3Y0RWdUhFVmpC"
const val errorContent = "网络可能有问题，请保持网络通畅，或更换网络后再试。因为有的移动运营商可能把网站屏蔽了"

var currentUrl = "https://www.baidu.com"
var caoHome0 = "https://www.t66y.com"
var caoHome1 = "https://cl.291x.xyz"
var caoHome2 = "https://cl.291y.xyz"
var caoHome3 = "https://cl.291z.xyz"
var caoHomeApp = "https://private70.ghuws.win/index.php"
var caoHome5 = "https://www.baidu.com"
var porn91VideoApp = "https://its.better2021app.com"
var porn91VideoWeb1 = "https://up.91p22.net//index.php"
var porn91VideoWeb2 = "http://0728.91p50.com/index.php"
var porn91VideoWeb3 = "https://f0601.workgreat11.live/index.php"
var porn91PhotoWeb1 = "https://f1113.workarea1.live/index.php"
var porn91PhotoWeb2 = "https://f.wonderfulday28.live/index.php"
var porn91PhotoWeb3 = "https://f.wonderfulday28.live/index.php"
var porn91SourceVideo = "https://91porn.com/index.php"
var heiLiaoApp = "https://www.jusebao.biz/"
var heiLiaoWeb1 = "https://zztt10.com/"
var heiLiaoWeb2 = "https://zztt11.com/"
var heiLiaoWeb3 = "https://zztt12.com/"
var heiliaoSOurce = "https://668.su/"
// 贡献头
var gongXianList = mutableListOf<String>(
    "/index.php?u=502421&ext=363a3",
    "/index.php?u=551567&ext=76e8e",
    "/index.php?u=555824&ext=3f7c8",
    "/index.php?u=558228&ext=345c8",
    "/index.php?u=558551&ext=25120",
    "/index.php?u=552657&ext=e0cd1",
    "/index.php?u=565094&ext=71a49",
    "/index.php?u=566750&ext=06776",
    "/index.php?u=498434&ext=8aa5e",
    "/index.php?u=567982&ext=b813c")

// app信息获取的url
var appInfoUrl = mutableListOf<String>(
    "https://www.cnblogs.com/sdfasdf/p/15019781.html",
    "https://blog.csdn.net/weixin_44786530/article/details/119567136",
    "https://1024shen.com/archives/5304")


data class CaoLiuHome(
    var url1: String,
    var url2: String,
    var url3: String,
    var app: String,
    var update: String,
    var note: String,
    var appVer: String
) {
    override fun toString(): String {
        return "CaoLiuHome(url1='$url1', url2='$url2', url3='$url3', app='$app', update='$update', note='$note', appVer='$appVer')"
    }
}