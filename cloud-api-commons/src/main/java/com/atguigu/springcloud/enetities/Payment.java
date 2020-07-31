package com.atguigu.springcloud.enetities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @auther River
 * @date 2020/7/28 11:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Payment {
    private Long id;
    private String serial;
}
