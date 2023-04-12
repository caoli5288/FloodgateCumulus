package com.github.caoli5288.floodgate.cumulus.model;

import lombok.Data;

import java.util.List;

@Data
public class RawModel {

    private String type;
    private String title;
    private String content;
    private List<RawModelButton> buttons;
    private RawModelButton button1;
    private RawModelButton button2;
}
