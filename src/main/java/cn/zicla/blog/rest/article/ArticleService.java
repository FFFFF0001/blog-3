package cn.zicla.blog.rest.article;

import cn.zicla.blog.config.exception.UtilException;
import cn.zicla.blog.rest.agree.History;
import cn.zicla.blog.rest.agree.HistoryDao;
import cn.zicla.blog.rest.base.BaseEntityService;
import cn.zicla.blog.rest.base.Pager;
import cn.zicla.blog.rest.comment.CommentDao;
import cn.zicla.blog.rest.comment.CommentService;
import cn.zicla.blog.rest.support.session.SupportSessionDao;
import cn.zicla.blog.rest.tank.TankService;
import cn.zicla.blog.rest.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.Predicate;
import java.util.List;

@Slf4j
@Service
public class ArticleService extends BaseEntityService<Article> {

    @Autowired
    ArticleDao articleDao;

    @Autowired
    HistoryDao historyDao;

    @Autowired
    UserService userService;

    @Autowired
    TankService tankService;

    @Autowired
    CommentDao commentDao;

    @Autowired
    SupportSessionDao supportSessionDao;

    public ArticleService() {
        super(Article.class);
    }

    public Pager<Article> page(
            Integer page,
            Integer pageSize,
            Sort.Direction orderSort,
            Sort.Direction orderTop,
            Sort.Direction orderHit,
            Sort.Direction orderPrivacy,
            Sort.Direction orderReleaseTime,
            String userUuid,
            String title,
            String tag,
            String keyword
    ) {

        Sort sort = new Sort(Sort.Direction.ASC, Article_.deleted.getName());

        if (orderTop != null) {
            sort = sort.and(new Sort(orderTop, Article_.top.getName()));
        }

        if (orderSort != null) {
            sort = sort.and(new Sort(orderSort, Article_.sort.getName()));
        }


        if (orderHit != null) {
            sort = sort.and(new Sort(orderHit, Article_.hit.getName()));
        }

        if (orderPrivacy != null) {
            sort = sort.and(new Sort(orderPrivacy, Article_.privacy.getName()));
        }

        if (orderReleaseTime != null) {
            sort = sort.and(new Sort(orderReleaseTime, Article_.releaseTime.getName()));
        }

        Pageable pageable = getPageRequest(page, pageSize, sort);

        Page<Article> pageData = getDao().findAll(((root, query, cb) -> {
            Predicate predicate = cb.equal(root.get(Article_.deleted), false);

            if (userUuid != null) {
                predicate = cb.and(predicate, cb.equal(root.get(Article_.userUuid), userUuid));
            }
            if (title != null) {
                predicate = cb.and(predicate, cb.like(root.get(Article_.title), "%" + title + "%"));
            }
            if (tag != null) {
                predicate = cb.and(predicate, cb.like(root.get(Article_.tags), "%" + tag + "%"));
            }

            if (keyword != null) {

                Predicate predicate1 = cb.like(root.get(Article_.title), "%" + keyword + "%");
                Predicate predicate2 = cb.like(root.get(Article_.html), "%" + keyword + "%");

                predicate = cb.and(predicate, cb.or(predicate1, predicate2));
            }
            return predicate;

        }), pageable);

        long totalItems = pageData.getTotalElements();
        List<Article> list = pageData.getContent();

        list.forEach(article -> article.setUser(userService.find(article.getUserUuid())));

        return new Pager<>(page, pageSize, totalItems, list);
    }


    //获取一篇文章的详情。
    public Article detail(String uuid, String ip) {


        Article article = this.check(uuid);

        article.setPosterTank(tankService.find(article.getPosterTankUuid()));
        article.setUser(userService.find(article.getUserUuid()));

        //评论数量
        article.setCommentNum(commentDao.countByArticleUuidAndDeletedFalse(uuid));


        //统计文章点击数量。
        this.analysisHit(article, ip);

        //查看当前用户的点赞与否情况。
        History history = historyDao.findTopByTypeAndEntityUuidAndIp(History.Type.AGREE_ARTICLE, uuid, ip);
        if (history != null) {
            article.setAgreed(true);
        }
        return article;
    }


    //统计访问数量。
    @Async
    public void analysisHit(Article article, String ip) {


        int count = historyDao.countByTypeAndEntityUuidAndIp(History.Type.VISIT_ARTICLE, article.getUuid(), ip);
        if (count > 0) {
            //之前就已经访问过了，忽略。
            return;
        }

        History history = new History();
        history.setType(History.Type.VISIT_ARTICLE);
        history.setEntityUuid(article.getUuid());
        history.setIp(ip);
        historyDao.save(history);


        article.setHit(article.getHit() + 1);
        articleDao.save(article);

    }


}
