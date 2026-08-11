package com.backend.blogfrontbackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Orders {
    private Integer id;
    private Integer goodsId;
    private String name;
    private String orderId;
    private String alipayNo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date payTime;
    private String state;
    private BigDecimal total;
}
