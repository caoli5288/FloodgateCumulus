package com.github.caoli5288.floodgate.cumulus.model;

import org.bukkit.entity.Player;
import org.geysermc.cumulus.form.Form;
import org.jetbrains.annotations.NotNull;

public class Model implements IModel {

    private static final ModalFormFactory MODAL_FACTORY = new ModalFormFactory();
    private static final SimpleFormFactory MODAL_FORM_FACTORY = new SimpleFormFactory();
    private final RawModel model;
    private final IModelFactory factory;

    public Model(RawModel model) {
        this.model = model;
        if (model.getType().equalsIgnoreCase("modal")) {
            factory = MODAL_FACTORY;
        } else {
            factory = MODAL_FORM_FACTORY;
        }
    }

    @Override
    public @NotNull Form toForm(@NotNull Player who) {
        return factory.toForm(who, model);
    }
}
