package com.example.shoop.repo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class KeyProdParam implements Serializable {

    private Long product_id;
    private Long param_id;

    public KeyProdParam() {}
    public KeyProdParam(Long product_id, Long param_id) {
        this.product_id = product_id;
        this.param_id = param_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyProdParam that = (KeyProdParam) o;
        return Objects.equals(product_id, that.product_id) && Objects.equals(param_id, that.param_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_id, param_id);
    }

    @Override
    public String toString() {
        return "IcoToiGroup{" +
                "icon_id=" + product_id +
                ", icon_group_id=" + param_id +
                '}';
    }
}






