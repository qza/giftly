package org.koko.gift;

import org.springframework.data.annotation.Id;

import java.math.BigInteger;

/**
 * Gift
 */
public class Gift {

    @Id
    private BigInteger id;
    private String name;

    public Gift() {
        this.id = null;
        this.name = "";
    }

    public Gift(BigInteger id, String name) {
        this.id = id;
        this.name = name;
    }

    public Gift(String name) {
        this.id = null;
        this.name = name;
    }

    public BigInteger getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Gift{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
