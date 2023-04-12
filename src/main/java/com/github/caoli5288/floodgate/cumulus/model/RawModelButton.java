package com.github.caoli5288.floodgate.cumulus.model;

import com.github.caoli5288.floodgate.cumulus.util.CommandContext;
import com.github.caoli5288.floodgate.cumulus.util.JsContext;
import com.github.caoli5288.floodgate.cumulus.util.Utils;
import lombok.Data;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.Consumer;

@Data
public class RawModelButton {

    private String text;
    private String image;
    private String visible;
    private List<String> commands;
    private Consumer<Player> callback;

    public void invoke(Player who) {
        if (callback == null) {
            callback = (it) -> CommandContext.execute(it, commands);
        }
        callback.accept(who);
    }

    public boolean isVisible(Player who) {
        if (Utils.isNullOrEmpty(visible)) {
            return true;
        }
        String exp = PlaceholderAPI.setPlaceholders(who, visible);
        if (Utils.isNullOrEmpty(exp)) {
            return false;
        }
        Object eval = JsContext.eval(exp);
        if (eval == null) {
            return false;
        }
        if (eval instanceof Boolean) {
            return (boolean) eval;
        } else if (eval instanceof Number) {
            return ((Number) eval).doubleValue() != 0;
        }
        return false;
    }
}
