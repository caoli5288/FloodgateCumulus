package com.github.caoli5288.floodgate.cumulus.model;

import org.bukkit.entity.Player;
import org.geysermc.cumulus.form.Form;
import org.jetbrains.annotations.NotNull;

public interface IModel {

    @NotNull
    Form toForm(@NotNull Player who);
}
