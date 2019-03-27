package com.fast.cloud.ordinary.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by pangshaofei on 2018-12-28 16:22
 */
@Document(collection = "es_app_goods")
@Data
@ToString
public class AppGoods {

    @Indexed
    @Id
    private String id;

    private String goodsId;

    private List<String> catalog;

    private Long marketPrice;

    private List<String> tagsName;

    private List<String> labelName;

    private Long price;

    private String headImage;

    private String name;

    private String sn;

    private String imageType;

    private String tailImage;

    private Boolean isMarketable;
    /**
     * 预览
     */
    private Boolean isPreView;
    /**
     * 是否列出
     */
    private Boolean isList;


    private Date couldPayTimeBegin;

    /**
     * 可支付时间，针对的是员工内卖/预售
     */
    private Date couldPayTimeEnd;

    private BigDecimal depositPrice;

    private String imageSpecification;

    private String searchKeyWords;
}
