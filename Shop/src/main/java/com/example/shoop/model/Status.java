package com.example.shoop.model;

import java.util.ArrayList;
import java.util.List;


public enum Status { DESIGNED, ACTIVE, INACTIVE, DISCONTINUED;

    private static ComboRow E = new ComboRow(0L, "projektowany");
    private static ComboRow A = new ComboRow(1L, "aktywny");
    private static ComboRow I = new ComboRow(2L, "nieaktywny");
    private static ComboRow D = new ComboRow(3L, "wycofany");


    public static List<ComboRow> getAsComboList(){
        return List.of( E,A,I,D );
    }

    public static Long getAsLong( Status status ){
        switch ( status ){
            case DESIGNED -> { return 0L; }
            case ACTIVE -> { return 1L; }
            case INACTIVE -> { return 2L; }
            case DISCONTINUED -> { return 3L; }
        }
        return 0L;
    }
}



