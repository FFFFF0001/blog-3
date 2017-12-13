package cn.zicla.blog.rest.tag;

import cn.zicla.blog.rest.base.BaseEntityForm;
import cn.zicla.blog.rest.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Size;


@EqualsAndHashCode(callSuper = false)
@Data
public class TagForm extends BaseEntityForm<Tag> {

    //标题
    @Size(min = 1, max = 45, message = "名称必填并且最长45字")
    private String name;


    //图片
    private String logoTankUuid;

    //Url
    private String logoUrl;


    public TagForm() {
        super(Tag.class);
    }

    @Override
    protected void update(Tag tag, User operator) {
        tag.setName(name);
        tag.setLogoTankUuid(logoTankUuid);
        tag.setLogoUrl(logoUrl);
    }

    public Tag create(User operator) {

        Tag entity = new Tag();
        this.update(entity, operator);
        entity.setUserUuid(operator.getUuid());
        return entity;
    }

}

