package cn.zicla.blog.rest.base;

import cn.zicla.blog.config.AppContextManager;
import cn.zicla.blog.rest.user.User;
import cn.zicla.blog.util.JsonUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public abstract class BaseEntityController<E extends BaseEntity, F extends BaseEntityForm<E>> extends BaseController {

    private Class<E> clazz;

    public BaseEntityController(Class<E> clazz) {
        this.clazz = clazz;
    }

    protected BaseEntityDao<E> getDao() {
        return AppContextManager.getBaseEntityDao(clazz);
    }


    protected BaseEntityService<E> getService() {
        return AppContextManager.getBaseEntityService(clazz);
    }


    /**
     * 返回一个分页，手动指定一个map规则
     */
    protected WebResult success(Specification<E> specification, Pageable pageable, Function<E, ? extends Map<String, Object>> mapper) {

        Page<E> pageData = getDao().findAll(specification, pageable);

        int page = pageData.getNumber();
        int pageSize = pageData.getSize();
        long totalItems = pageData.getTotalElements();
        List<E> list = pageData.getContent();

        if (mapper != null) {
            List<Map<String, Object>> collect = list.stream().map(mapper).collect(Collectors.toList());
            Pager<Map<String, Object>> pager = new Pager<>(page, pageSize, totalItems, collect);

            return new WebResult(JsonUtil.toMap(pager));

        } else {
            Pager<E> pager = new Pager<E>(page, pageSize, totalItems, list);

            return new WebResult(JsonUtil.toMap(pager));
        }


    }


    /**
     * 添加一个实例
     */
    @RequestMapping("/create")
    public WebResult create(@Valid F form) {

        E entity = getService().create(form, checkUser());

        return success(entity);

    }


    /**
     * 删除一个实例
     */
    @RequestMapping("/del/{uuid}")
    public WebResult del(@PathVariable String uuid) {

        E entity = this.check(uuid);
        getService().del(entity, checkUser());
        return success();
    }


    /**
     * 编辑一个实例
     */
    @RequestMapping("/edit")
    public WebResult edit(@Valid F form) {

        E entity = getService().edit(form, checkUser());

        return success(entity);
    }


    /**
     * 获取详情
     */
    @RequestMapping("/detail/{uuid}")
    public WebResult detail(@PathVariable String uuid) {


        E entity = this.check(uuid);

        return success(entity);

    }

    /**
     * 改变排序位置,需要手动指定放置的位置。
     * sort是用时间戳来表示的。比如是System.currentTimeMillis()
     * 子类中如果要覆盖该方法，则不用再指定 @RequestMapping("/sort") 加上 @Override即可
     */
    @RequestMapping("/sort")
    public WebResult sort(@RequestParam String uuid1, @RequestParam Long sort1, @RequestParam String uuid2, @RequestParam Long sort2) {

        E entity1 = this.check(uuid1);
        entity1.setSort(sort1);
        getDao().save(entity1);

        E entity2 = this.check(uuid2);
        entity2.setSort(sort2);
        getDao().save(entity2);

        return success();

    }


    /**
     * 从数据库中检出一个当前泛型的实例。
     * 找不到抛异常。
     */
    public E check(String uuid) {

        return AppContextManager.check(this.clazz, uuid);
    }

    /**
     * 从数据库中找出一个当前泛型的实例。
     * 找不到返回null
     */
    public E find(String uuid) {
        return AppContextManager.find(this.clazz, uuid);
    }

    /**
     * 获取当前的登录的这个user. 没有就抛异常
     *
     * @return User.
     */
    protected User checkUser() {

        return null;

    }

}
