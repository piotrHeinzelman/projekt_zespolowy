package com.example.shoop.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class LongLong implements Serializable {

    private Long icon_id;
    private Long icon_group_id;

    public LongLong() {}
    public LongLong(Long icon_id, Long icon_group_id) {
        this.icon_id = icon_id;
        this.icon_group_id = icon_group_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LongLong that = (LongLong) o;
        return Objects.equals(icon_id, that.icon_id) && Objects.equals(icon_group_id, that.icon_group_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(icon_id, icon_group_id);
    }

    @Override
    public String toString() {
        return "IcoToiGroup{" +
                "icon_id=" + icon_id +
                ", icon_group_id=" + icon_group_id +
                '}';
    }
}






