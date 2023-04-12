package com.github.caoli5288.floodgate.cumulus.model;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.geysermc.cumulus.form.Form;
import org.geysermc.cumulus.form.ModalForm;
import org.jetbrains.annotations.NotNull;

public class ModalFormFactory implements IModelFactory {

    @Override
    public @NotNull Form toForm(@NotNull Player who, @NotNull RawModel modal) {
        return ModalForm.builder()
                .translator((it, __) -> PlaceholderAPI.setPlaceholders(who, it))
                .title(modal.getTitle())
                .content(modal.getContent())
                .button1(modal.getButton1().getText())
                .button2(modal.getButton2().getText())
                .validResultHandler((__, it) -> {
                    if (it.clickedFirst()) {
                        modal.getButton1().invoke(who);
                    } else {
                        modal.getButton2().invoke(who);
                    }
                })
                .closedOrInvalidResultHandler(() -> modal.getButton2().invoke(who))
                .build();
    }
}
