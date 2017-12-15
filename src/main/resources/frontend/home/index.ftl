<@layout.extends name="/common/base.ftl">
    <@layout.put block="title" type="replace">
    个人博客 | 博客首页
    </@layout.put>
    <@layout.put block="head" type="append">

    </@layout.put>

    <@layout.put block="content" type="replace">

        <#import "../common/widget/NbPager.ftl" as NbPager>

<div class="row">
    <div class="col-sm-8 mt40">

            <#list articlePager.data as article>
                <div class="border-bottom mt20">
                    <div>
                        <h3>
                            <a href="/home/article/${article.uuid}">
                                ${article.title}
                            </a>
                        </h3>
                    </div>
                    <div>
                        <p>${article.createTime?string("yyyy-MM-dd HH:mm")}</p>
                    </div>
                </div>
            </#list>
    </div>
    <div class="col-sm-4 mt60">

            <#list tagPager.data as tag>

                <a class="tag-collection" target="_blank" href="#">
                    <img src="${tag.logoUrl!""}?imageProcess=resize&imageResizeM=fill&imageResizeW=64&imageResizeH=64"
                         alt="64">
                    <span class="name">${tag.name}</span>
                </a>

            </#list>

    </div>
</div>

    <div>
        <@NbPager.NbPager pager=articlePager/>
    </div>

    </@layout.put>

</@layout.extends>