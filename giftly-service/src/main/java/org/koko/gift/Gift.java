package org.koko.gift;

import org.springframework.data.annotation.Id;

/**
 * Gift
 */
public class Gift {

    @Id
    private Long id;
    private String name;

    public Gift() {
        this.id = null;
        this.name = "";
    }

    public Gift(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
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
