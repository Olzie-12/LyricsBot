package com.olziedev.lyricsbot;

import com.olziedev.lyricsbot.utils.Info;
import com.olziedev.olziecommand.OlzieCommand;
import com.olziedev.olziecommand.framework.action.CommandActionType;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.internal.utils.JDALogger;

import java.awt.*;
import java.io.File;
import java.time.Instant;
import java.util.Arrays;

public class LyricsBot extends ListenerAdapter {

    @Override
    public void onReady(ReadyEvent event) {
        new OlzieCommand(event.getJDA(), LyricsBot.class, null, JDALogger.getLog("LyricsBot")).getActionRegister()
                .registerAction(CommandActionType.SYNTAX, cmd -> new EmbedBuilder()
                        .addField("**Syntax Error**", "%syntax%", false)
                        .setColor(Color.RED)
                        .setTimestamp(Instant.now())).buildActions()
                .registerCommands();
    }

    public static void main(String[] args) throws Exception {
        JDABuilder builder = JDABuilder.create(Info.getInformation("DISCORD_TOKEN"), Arrays.asList(GatewayIntent.values()))
                .addEventListeners(new LyricsBot());
        builder.build();
    }

    public static File getDataFile() { return new File("Lyrics Bot"); }
}
