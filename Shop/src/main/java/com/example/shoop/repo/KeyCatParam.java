package com.example.shoop.repo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class KeyCatParam implements Serializable {


    private Long category_id;
    private Long parameter_id;

    public KeyCatParam() {}
    public KeyCatParam(Long icon_id, Long icon_group_id) {
        this.category_id = icon_id;
        this.parameter_id = icon_group_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyCatParam that = (KeyCatParam) o;
        return Objects.equals(category_id, that.category_id) && Objects.equals(parameter_id, that.parameter_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category_id, parameter_id);
    }

    @Override
    public String toString() {
        return "IcoToiGroup{" +
                "icon_id=" + category_id +
                ", icon_group_id=" + parameter_id +
                '}';
    }
}






