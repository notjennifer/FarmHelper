package com.github.may2beez.farmhelperv2.remote.command.commands.impl;

import com.github.may2beez.farmhelperv2.feature.impl.ProfitCalculator;
import com.github.may2beez.farmhelperv2.handler.MacroHandler;
import com.github.may2beez.farmhelperv2.remote.command.commands.ClientCommand;
import com.github.may2beez.farmhelperv2.remote.command.commands.Command;
import com.github.may2beez.farmhelperv2.remote.struct.RemoteMessage;
import com.github.may2beez.farmhelperv2.util.LogUtils;
import com.google.gson.JsonObject;

@Command(label = "info")
public class InfoCommand extends ClientCommand {
    @Override
    public void execute(RemoteMessage message) {
        JsonObject data = new JsonObject();

        data.addProperty("username", mc.getSession().getUsername());
        data.addProperty("runtime", LogUtils.getRuntimeFormat());
        data.addProperty("totalProfit", ProfitCalculator.getInstance().getRealProfitString());
        data.addProperty("profitPerHour", ProfitCalculator.getInstance().getProfitPerHourString());
        data.addProperty("cropType", String.valueOf(MacroHandler.getInstance().getCrop() == null ? "None" : MacroHandler.getInstance().getCrop()));
        data.addProperty("currentState", String.valueOf(!MacroHandler.getInstance().getCurrentMacro().isPresent() ? "Macro is not running" : MacroHandler.getInstance().getCurrentMacro().get().getCurrentState()));
        data.addProperty("uuid", mc.getSession().getPlayerID());
        data.addProperty("image", getScreenshot());

        RemoteMessage response = new RemoteMessage(label, data);
        send(response);
    }
}
