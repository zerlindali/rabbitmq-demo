package org.lic.mq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ZerlindaLi create at 2020/10/27 13:48
 * @version 1.0.0
 * @description Merchant
 */
@Data
@AllArgsConstructor
public class Merchant {

    private Integer id;

    private String name;

    private String message;

    private String address;

    public Merchant() {
    }

    public Merchant(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
