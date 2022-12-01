package com.indocyber.jasindo.dto.Utility;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Dropdown {
    private String stringValue;
    private Long longValue;
    private String text;

    public Dropdown(String stringValue, String text) {
        this.stringValue = stringValue;
        this.text = text;
    }

    public Dropdown(Long longValue, String text) {
        this.longValue = longValue;
        this.text = text;
    }
}
