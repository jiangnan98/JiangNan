package com.bing.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;



import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jiang
 * @since 2019-04-18
 */
@Data
@Accessors(chain = true)
@TableName("base_user")
public class BaseUser extends Model<BaseUser> {

    private static final long serialVersionUID = 1L;

    @TableId("user_id")
    private String userId;

    /**
     * 签名
     */
    @TableField("user_sig")
    private String userSig;

    /**
     * 账号
     */
    @TableField("user_code")
    private String userCode;

    /**
     * 密码
     */
    @TableField("user_pwd")
    private String userPwd;

    /**
     * 身份证号码
     */
    @TableField("user_card")
    private String userCard;

    /**
     * 头像
     */
    @TableField("user_head")
    private String userHead;

    /**
     * 1:管理员 2:用户
     */
    @TableField("user_type")
    private String userType;

    /**
     * 1:已实名 2:实名审核中 3：未实名
     */
    @TableField("user_verified")
    private String userVerified;

    @TableField("user_valid")
    private String userValid;

    /**
     * 出生年月
     */
    @TableField("user_birthday")
    private String userBirthday;

    /**
     * 1男 2女
     */
    @TableField("user_sex")
    private String userSex;

    /**
     * 手机号
     */
    @TableField("user_phone")
    private String userPhone;

    @TableField("user_no")
    private String userNo;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
