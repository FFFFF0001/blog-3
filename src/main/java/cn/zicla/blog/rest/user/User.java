package cn.zicla.blog.rest.user;

import cn.zicla.blog.rest.base.BaseEntity;
import cn.zicla.blog.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class User extends BaseEntity {

    private String username;

    @JsonIgnore
    private String password;

    private String email;

    private String phone;

    //用户角色
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.UNKNOWN;

    private String city;

    private String description;

    private String avatarTankUuid;

    private String avatarUrl;

    //上次登录IP
    @JsonIgnore
    private String lastIp;

    //上次登录时间
    @JsonIgnore
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT)
    private Date lastTime;

    //性别
    public enum Gender {
        UNKNOWN,
        MALE,
        FEMALE
    }

}


