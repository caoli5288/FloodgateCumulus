package com.github.caoli5288.floodgate.cumulus.model;

import com.github.caoli5288.floodgate.cumulus.util.JsContext;
import com.google.common.io.Files;
import lombok.RequiredArgsConstructor;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.geysermc.cumulus.form.Form;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class JsModel implements IModel {

    private final String js;

    @Override
    public @NotNull Form toForm(@NotNull Player who) {
        RawModelDsl dsl = new RawModelDsl();
        try {
            String exp = PlaceholderAPI.setPlaceholders(who, js);
            JsContext.evalContext(dsl, exp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Model(dsl.getModel()).toForm(who);
    }
}
