package com.bing.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jiang
 * @since 2019-04-30
 */
@Data
@Accessors(chain = true)
@TableName("t_product_yisun_brak_copy1")
public class ProductYisunBrakCopy1 extends Model<ProductYisunBrakCopy1> {

    private static final long serialVersionUID = 1L;

    /**
     * 制动盘厚度【特殊属性】
     */
    @TableId("product_id")
    private String productId;

    /**
     * 供应商id
     */
    @TableField("shop_id")
    private String shopId;

    /**
     * 分类
     */
    @TableField("classify_id")
    private String classifyId;

    /**
     * 品牌
     */
    @TableField("spec_name")
    private String specName;

    private String oem;

    /**
     * 标准名称
     */
    @TableField("stand_name")
    private String standName;

    /**
     * 俗名
     */
    @TableField("another_name")
    private String anotherName;

    /**
     * 包装规格
     */
    @TableField("pac_spec")
    private String pacSpec;

    /**
     * 单位
     */
    private String unit;

    /**
     * 规格型号
     */
    @TableField("spec_m")
    private String specM;

    /**
     * 工厂编号（工厂的商品编号）
     */
    @TableField("factory_num")
    private String factoryNum;

    /**
     * 保质期
     */
    @TableField("exp_date")
    private String expDate;

    /**
     * 批发价【分】
     */
    @TableField("ourc_price")
    private Integer ourcPrice;

    /**
     * 零售价【分】
     */
    private Integer price;

    /**
     * 商品轮播图
     */
    private String carousel;

    /**
     * 商品详情图
     */
    private String detail;

    /**
     * 商品描述
     */
    private String describe;

    /**
     * 商家类型 ：1 自营 2 认证 3 合作
     */
    @TableField("business_type")
    private Integer businessType;

    /**
     * 品质级别
     */
    private String quality;

    /**
     * 销量
     */
    @TableField("buy_count")
    private Integer buyCount;

    /**
     * 库存
     */
    @TableField("stock_max")
    private Integer stockMax;

    /**
     * 是否上架 1上架，0下架
     */
    @TableField("market_enable")
    private Integer marketEnable;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 评论次数
     */
    @TableField("comment_num")
    private Integer commentNum;

    /**
     * 好评率
     */
    @TableField("comment_score")
    private String commentScore;

    /**
     * 1:专车专用 2：通用件
     */
    @TableField("expert_type")
    private Integer expertType;

    /**
     * 品牌【专车专用】
     */
    private String brand;

    /**
     * 车型【专车专用】
     */
    private String model;

    /**
     * 年款【专车专用】
     */
    private String year;

    /**
     * 排量【专车专用】
     */
    private String dis;

    /**
     * 制动盘类型【特殊属性】
     */
    @TableField("disc_type")
    private String discType;

    /**
     * 总高【特殊属性】
     */
    private String height;

    /**
     * 制动盘厚度【特殊属性】
     */
    @TableField("disc_thickness")
    private String discThickness;

    /**
     * 节圆直径【特殊属性】
     */
    @TableField("Pitch_diameter")
    private String pitchDiameter;

    /**
     * 最小厚度【特殊属性】
     */
    @TableField("Minimum_thickness")
    private String minimumThickness;

    /**
     * 直径【特殊属性】
     */
    private String diameter;

    /**
     * 中心孔直径【特殊属性】
     */
    @TableField("Center_hole_diameter")
    private String centerHoleDiameter;

    /**
     * 厂商【专车专用】
     */
    private String factory;

    /**
     * 适用车型
     */
    @TableField("apply_model")
    private String applyModel;

    private String remark;

    /**
     * 容量
     */
    private String capacity;

    /**
     * 最小订货量
     */
    @TableField("min_order_amount")
    private Integer minOrderAmount;

    /**
     * 包装费
     */
    @TableField("packing_fee")
    private Integer packingFee;


    @Override
    protected Serializable pkVal() {
        return this.productId;
    }

}
