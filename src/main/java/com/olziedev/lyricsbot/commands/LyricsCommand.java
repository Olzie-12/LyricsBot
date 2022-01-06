package com.olziedev.lyricsbot.commands;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.dv8tion.jda.api.EmbedBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import com.olziedev.lyricsbot.utils.Info;
import com.olziedev.olziecommand.framework.CommandExecutor;
import com.olziedev.olziecommand.framework.api.FrameworkCommand;

import java.awt.*;

public class LyricsCommand extends FrameworkCommand {

    public LyricsCommand() {
        super("lyrics");
        this.setDescription("Show the lyrics of a song.");
        this.setSyntax("**%label% <song>**: %description%");
    }

    @Override
    public void onExecute(CommandExecutor cmd) {
        new Thread(() -> {
            String[] args = cmd.getArguments();
            if (args.length == 0) {
                cmd.getChannel().sendMessage(this.getSyntaxEmbedBuilder(cmd).build()).queue();
                return;
            }
            String songName = String.join(" ", args);
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://api.ksoft.si/lyrics/search?q=" + songName)
                        .get()
                        .addHeader("Authorization", "Bearer " + Info.getInformation("KSOFT_TOKEN"))
                        .build();

                JsonElement json = JsonParser.parseString(client.newCall(request).execute().body().string());
                JsonObject data = json.getAsJsonObject().getAsJsonArray("data").get(0).getAsJsonObject();
                String lyrics = data.get("lyrics").getAsString();
                if (lyrics.length() > 2048) lyrics = lyrics.substring(0, 2048);

                cmd.getChannel().sendMessage(new EmbedBuilder()
                        .setDescription(lyrics)
                        .setFooter("Lyrics provided by KSoft.API").setColor(Color.GREEN).build()).queue();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
    }
}
