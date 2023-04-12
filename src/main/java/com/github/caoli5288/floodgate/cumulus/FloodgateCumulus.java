package com.github.caoli5288.floodgate.cumulus;

import com.github.caoli5288.floodgate.cumulus.model.IModel;
import com.github.caoli5288.floodgate.cumulus.model.JsModel;
import com.github.caoli5288.floodgate.cumulus.model.Model;
import com.github.caoli5288.floodgate.cumulus.model.RawModel;
import com.github.caoli5288.floodgate.cumulus.util.Utils;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.geysermc.floodgate.api.FloodgateApi;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

public class FloodgateCumulus extends JavaPlugin {

    private static Map<String, IModel> modalMap;

    @Override
    public void onLoad() {
        saveDefaultConfig();
        reloadConfig();
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();
        File file = new File(getDataFolder(), "cumulus");
        Preconditions.checkState(file.isDirectory() || file.mkdirs());
        Yaml yaml= new Yaml();
        modalMap = Maps.newHashMap();
        for (File f : Objects.requireNonNull(file.listFiles())) {
            String fullName = f.getName();
            String extension = Files.getFileExtension(fullName);
            IModel model = null;
            if (extension.equalsIgnoreCase("yml")) {
                try {
                    Map<String, Object> load = yaml.load(Files.newReader(f, StandardCharsets.UTF_8));
                    model = new Model(Utils.asObject(load, RawModel.class));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (extension.equalsIgnoreCase("js")) {
                try {
                    String js = new String(Files.toByteArray(f), StandardCharsets.UTF_8);
                    model = new JsModel(js);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (model != null) {
                modalMap.put(fullName, model);
                modalMap.put(Files.getNameWithoutExtension(fullName), model);
                getLogger().info("load0 " + fullName);
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            return false;
        }
        if (args[0].equalsIgnoreCase("form")) {
            Player who = Bukkit.getPlayerExact(args[1]);
            Objects.requireNonNull(who);
            open(who, args[2]);
            return true;
        }
        return false;
    }

    public static void open(Player who, String modalName) {
        open(who, modalMap.get(modalName));
    }

    public static void open(Player who, IModel modal) {
        FloodgateApi.getInstance().sendForm(who.getUniqueId(), modal.toForm(who));
    }
}
