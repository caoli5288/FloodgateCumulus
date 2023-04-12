package com.github.caoli5288.floodgate.cumulus.model;

import com.github.caoli5288.floodgate.cumulus.util.Utils;
import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.List;
import java.util.Map;

public class RawModelDsl {

    @Getter
    private final RawModel model = new RawModel();

    public void setType(String type) {
        model.setType(type);
    }

    public void type(String type) {
        setType(type);
    }

    public void setTitle(String title) {
        model.setTitle(title);
    }

    public void title(String title) {
        setTitle(title);
    }

    public void setContent(String content) {
        model.setContent(content);
    }

    public void content(String content) {
        setContent(content);
    }

    public RawModelButton button(Map<String, Object> dsl) {
        List<RawModelButton> list = model.getButtons();
        if (list == null) {
            model.setButtons(list = Lists.newArrayList());
        }
        RawModelButton button = Utils.asObject(dsl, RawModelButton.class);
        list.add(button);
        return button;
    }

    public RawModelButton setButton1(Map<String, Object> dsl) {
        RawModelButton button = Utils.asObject(dsl, RawModelButton.class);
        model.setButton1(button);
        return button;
    }

    public RawModelButton button1(Map<String, Object> dsl) {
        return setButton1(dsl);
    }

    public RawModelButton setButton2(Map<String, Object> dsl) {
        RawModelButton button = Utils.asObject(dsl, RawModelButton.class);
        model.setButton2(button);
        return button;
    }

    public RawModelButton button2(Map<String, Object> dsl) {
        return setButton2(dsl);
    }
}
