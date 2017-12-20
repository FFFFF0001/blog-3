<!DOCTYPE html>
<html>
<head>
    <title>

    <@layout.block name="title">
        ${preference.name}
    </@layout.block>

    </title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="user-scalable=no,width=device-width,initial-scale=1,maximum-scale=1">
    <meta name="msapplication-tap-highlight" content="no">
    <meta name="apple-mobile-web-app-capable" content="yes">

    <link rel="shortcut icon" type="image/x-icon" href="${preference.name!"/static/img/favicon.ico"}">

    <link href="/static/node_modules/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="/static/node_modules/toastr/build/toastr.min.css" rel="stylesheet" type="text/css">
    <link href="/static/css/main.css" rel="stylesheet" type="text/css">

    <script src="/static/node_modules/jquery/dist/jquery.min.js"></script>
    <script src="/static/node_modules/bootstrap/dist/js/bootstrap.min.js"></script>

    <script src="/static/node_modules/swiper/dist/js/swiper.jquery.min.js"></script>
    <script src="/static/node_modules/velocity-animate/velocity.js"></script>
    <script src="/static/node_modules/toastr/build/toastr.min.js"></script>

    <script src="/static/js/common/widget/FtlPager.js"></script>
    <script src="/static/js/common/widget/FtlTab.js"></script>
    <script src="/static/js/common/widget/FtlExpanding.js"></script>


    <script src="/static/js/init.js"></script>

<@layout.block name="head"></@layout.block>

</head>
<body>

<div class="nb-app">

    <div class="section-navigation">
        <div class="container">
            <div class="row">
                <div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1">
                    <a href="/">
                        <img src="${preference.logoUrl!"/static/img/logo.png"}" class="logo"/>
                        <span class="site-title">
                        ${preference.name}
                        </span>
                    </a>
                    <div class="menus hidden-xs">
                    <#if preference.menuName1??>
                        <a href="${preference.menuUrl1!""}"
                           target="${preference.menuUrl1?starts_with("http")?string('_blank','_self')}">${preference.menuName1}</a>
                    </#if>
                    <#if preference.menuName2??>
                        <a href="${preference.menuUrl2!""}"
                           target="${preference.menuUrl2?starts_with("http")?string('_blank','_self')}">${preference.menuName2}</a>
                    </#if>
                    <#if preference.menuName3??>
                        <a href="${preference.menuUrl3!""}"
                           target="${preference.menuUrl3?starts_with("http")?string('_blank','_self')}">${preference.menuName3}</a>
                    </#if>
                    <#if preference.menuName4??>
                        <a href="${preference.menuUrl4!""}"
                           target="${preference.menuUrl4?starts_with("http")?string('_blank','_self')}">${preference.menuName4}</a>
                    </#if>
                    <#if preference.menuName5??>
                        <a href="${preference.menuUrl5!""}"
                           target="${preference.menuUrl5?starts_with("http")?string('_blank','_self')}">${preference.menuName5}</a>
                    </#if>
                    </div>
                    <div class="visible-xs pull-right">
                        <em class="fa fa-navicon f17 ln60 cursor text-primary" data-expanding-target="menuSlide"></em>

                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container mb120">
        <div class="row">
            <div data-expanding-id="menuSlide" data-expanding-show="false"
                 class="menuExpand hidden-sm hidden-md hidden-lg">
                <ul>
                    <#if preference.menuName1??>
                    <li><a href="${preference.menuUrl1!""}" target="${preference.menuUrl1?starts_with("http")?string('_blank','_self')}">${preference.menuName1}</a></li>
                    </#if>
                    <#if preference.menuName2??>
                    <li><a href="${preference.menuUrl2!""}" target="${preference.menuUrl2?starts_with("http")?string('_blank','_self')}">${preference.menuName2}</a></li>
                    </#if>
                    <#if preference.menuName3??>
                    <li><a href="${preference.menuUrl3!""}" target="${preference.menuUrl3?starts_with("http")?string('_blank','_self')}">${preference.menuName3}</a></li>
                    </#if>
                    <#if preference.menuName4??>
                    <li><a href="${preference.menuUrl4!""}" target="${preference.menuUrl4?starts_with("http")?string('_blank','_self')}">${preference.menuName4}</a></li>
                    </#if>
                    <#if preference.menuName5??>
                    <li><a href="${preference.menuUrl5!""}" target="${preference.menuUrl5?starts_with("http")?string('_blank','_self')}">${preference.menuName5}</a></li>
                    </#if>
                </ul>
            </div>
            <div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1">
                <@layout.block name="content"></@layout.block>
            </div>
        </div>

    </div>


    <div class="section-footer">

        <div class="container">
            <div class="row">
                <div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1">
                    <div>
                        CopyRight 2017©蓝眼博客 版权所有
                    </div>
                    <div>
                        沪ICP备 05049
                    </div>
                    <div>
                        Proudly powered by <a href="#"><img class="w30" src="/static/img/eyeblue.png"/> 蓝眼博客</a>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>

</body>
</html>
