package com.example.shoop.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComboRow {
    Long id;
    String name;

    public ComboRow( Long id, String name ) {
        this.id = id;
        this.name = name;
    }
}



