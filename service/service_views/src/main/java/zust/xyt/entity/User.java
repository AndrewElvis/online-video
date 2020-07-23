package zust.xyt.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author EricWang
 * @since 2020-06-17
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String openid;

    private String name;

    private String password;

    private String avatar;

    private String nickname;

    private Integer sex;

    private String phone;

    private Date birthday;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean isDeleted;

    private Boolean isDisabled;

    private int fansNum;

    private int subscribeNum;

    private Integer isAdmin;
}
