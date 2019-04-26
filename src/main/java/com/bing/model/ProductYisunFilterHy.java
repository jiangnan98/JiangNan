package com.bing.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jiang
 * @since 2019-04-20
 */
@Data
@Accessors(chain = true)
@TableName("t_product_yisun_filter")
public class ProductYisunFilterHy extends Model<ProductYisunFilterHy> {

    private static final long serialVersionUID = 1L;

    /**
     * 商品pk
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

    /**
     * 规格：长
     */
    @TableField("A")
    private String a;

    /**
     * 规格：宽
     */
    @TableField("B")
    private String b;

    /**
     * 规格：高
     */
    @TableField("C")
    private String c;

    /**
     * 规格：内径
     */
    @TableField("G")
    private String g;

    /**
     * 规格：外径
     */
    @TableField("H")
    private String h;


    @Override
    protected Serializable pkVal() {
        return this.productId;
    }

}
