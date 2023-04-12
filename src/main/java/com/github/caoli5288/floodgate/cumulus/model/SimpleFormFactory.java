package com.github.caoli5288.floodgate.cumulus.model;

import com.github.caoli5288.floodgate.cumulus.util.CommandContext;
import com.github.caoli5288.floodgate.cumulus.util.Utils;
import com.google.common.collect.Lists;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.geysermc.cumulus.form.Form;
import org.geysermc.cumulus.form.SimpleForm;
import org.geysermc.cumulus.util.FormImage;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SimpleFormFactory implements IModelFactory {

    @Override
    public @NotNull Form toForm(@NotNull Player who, @NotNull RawModel model) {
        List<RawModelButton> list = Lists.newArrayList();
        if (!Utils.isNullOrEmpty(model.getButtons())) {
            for (RawModelButton button : model.getButtons()) {
                if (button.isVisible(who)) {
                    list.add(button);
                }
            }
        }
        SimpleForm.Builder b = SimpleForm.builder()
                .translator((it, __) -> PlaceholderAPI.setPlaceholders(who, it))
                .title(model.getTitle())
                .content(model.getContent())
                .validResultHandler((__, it) -> {
                    RawModelButton cli = list.get(it.clickedButtonId());
                    CommandContext.execute(who, cli.getCommands());
                });
        for (RawModelButton it : list) {
            if (Utils.isNullOrEmpty(it.getImage())) {
                b.button(it.getText());
            } else {
                String image = it.getImage();
                b.button(it.getText(), ofType(image), image);
            }
        }
        return b.build();
    }

    static FormImage.Type ofType(String image) {
        return image.startsWith("http") ? FormImage.Type.URL : FormImage.Type.PATH;
    }
}
