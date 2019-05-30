package com.bing.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 公司表。
通过（t_com_user)和用户进行关联表
 * </p>
 *
 * @author Jiang
 * @since 2019-05-30
 */
@Data
@Accessors(chain = true)
@TableName("t_company")
public class Company extends Model<Company> {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 门店id（展示在页面上的一个编号8位数不能重复）
     */
    @TableField("com_code")
    private String comCode;

    /**
     * 公司名称
     */
    @TableField("com_name")
    private String comName;

    /**
     * 公司电话
     */
    @TableField("com_phone")
    private String comPhone;

    /**
     * 汽配城id
     */
    @TableField("partcity_id")
    private String partcityId;

    /**
     * 省序列
     */
    @TableField("province_id")
    private String provinceId;

    /**
     * 市序列
     */
    @TableField("city_id")
    private String cityId;

    /**
     * 县序号
     */
    @TableField("region_id")
    private String regionId;

    /**
     * 公司地址
     */
    @TableField("com_address")
    private String comAddress;

    /**
     * 营业执照照片路径
     */
    @TableField("license_img")
    private String licenseImg;

    /**
     * 门头照片，支持多张，用英文逗号隔开
     */
    @TableField("shop_img")
    private String shopImg;

    /**
     * 法人手持身份证照片
     */
    @TableField("idcard_person_img")
    private String idcardPersonImg;

    /**
     * 信用代码
     */
    @TableField("reg_num")
    private String regNum;

    /**
     * 邀请成员码（用户搜索加入商户的时候用到）
     */
    @TableField("invite_code")
    private String inviteCode;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 是否删除    0否   1是  2已撤销申请
     */
    @TableField("is_del")
    private Integer isDel;

    /**
     * 审核状态 0：代付款  1：待完善(汽配城，全，易)  2：待审核  3：审核通过 4：审核未通过
     */
    @TableField("com_status")
    private Integer comStatus;

    /**
     * 审核时间  
     */
    @TableField("check_time")
    private Date checkTime;

    /**
     * 审核人（壁虎运营人员），0代表暂未审核
     */
    @TableField("check_user")
    private String checkUser;

    /**
     * 会员等级  t_data_vip
     */
    @TableField("vip_type")
    private String vipType;

    /**
     * 供代权限（0.修理厂1.全车2.易损3.全部）修理厂不卖东西，是0
     */
    @TableField("com_type")
    private Integer comType;

    /**
     * （默认店铺能最多有多少账号，默认3个）
     */
    @TableField("full_size")
    private Integer fullSize;

    /**
     * 真实人数（当前所有账号）
     */
    @TableField("real_size")
    private Integer realSize;

    /**
     * 会员到期期限（当续交的时候，累加；）
     */
    @TableField("vip_days")
    private Date vipDays;

    /**
     * 当前会员是否有效（0-终身会员1-从未成为会员，2-有效会员，3-到期会员）默认1
     */
    @TableField("vip_status")
    private Integer vipStatus;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 是否被删除(0正常 1-删除)
     */
    @TableField("del_flag")
    private Integer delFlag;

    /**
     * 店铺创建
     */
    @TableField("login_id")
    private String loginId;

    /**
     * vip是否被冻结（0-正常，1-冻结）
     */
    @TableField("vip_freeze")
    private Integer vipFreeze;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
