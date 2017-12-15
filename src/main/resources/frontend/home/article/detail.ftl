<@layout.extends name="/common/base.ftl">
    <@layout.put block="title" type="replace">
        ${article.title}
    </@layout.put>
    <@layout.put block="head" type="append">

    </@layout.put>

    <@layout.put block="content" type="replace">


    <div class="row mt40 mb100">
        <div class="col-lg-10 col-lg-offset-1 col-lg-10 col-lg-offset-1">

            <div class="article-content">
                ${article.html}
            </div>

            <div class="row mt20">
                <div class="col-xs-12">
                    <h2>写下你的评论</h2>
                </div>
            </div>

        </div>
    </div>




    </@layout.put>

</@layout.extends>