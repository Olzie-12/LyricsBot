package com.olziedev.lyricsbot.commands;

import com.olziedev.olziecommand.framework.api.FrameworkCommand;
import com.olziedev.olziecommand.framework.api.slash.SlashCommand;
import com.olziedev.olziecommand.framework.executor.SlashExecutor;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.time.Instant;

public class HelpCommand extends SlashCommand {

    public HelpCommand() {
        super("help");
        this.setDescription("Help message");
        this.setSyntax("**%label%**: %description%");
    }

    @Override
    public void onExecute(SlashExecutor cmd) {
        EmbedBuilder eb = new EmbedBuilder()
                .setTitle("General Commands")
                .setColor(new Color((int) (Math.random() * 0x1000000)))
                .setThumbnail(cmd.getMember().getUser().getEffectiveAvatarUrl())
                .setTimestamp(Instant.now());
        for (FrameworkCommand cmds : olzieCommand.getCommands()) {
            eb.addField(olzieCommand.getPrefix() + cmds.getName(), cmds.getDescription(), true);
        }
        cmd.replyEmbeds(eb.build()).queue();
    }
}
