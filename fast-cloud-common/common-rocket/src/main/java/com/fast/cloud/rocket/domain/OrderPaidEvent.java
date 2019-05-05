package com.fast.cloud.rocket.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-05-05 10:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPaidEvent implements Serializable {
    private String orderId;

    private BigDecimal paidMoney;
}
