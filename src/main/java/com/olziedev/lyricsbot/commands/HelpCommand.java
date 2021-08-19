package com.olziedev.lyricsbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import com.olziedev.olziecommand.OlzieCommand;
import com.olziedev.olziecommand.framework.CommandExecutor;
import com.olziedev.olziecommand.framework.FrameworkCommand;

import java.awt.*;
import java.time.Instant;

public class HelpCommand extends FrameworkCommand {

    public HelpCommand() {
        super("help");
        this.setDescription("Help message");
        this.setSyntax("**%label%**: %description%");
    }

    @Override
    public void onExecute(CommandExecutor cmd) {
        OlzieCommand olzieCommand = OlzieCommand.getInstance();
        EmbedBuilder eb = new EmbedBuilder()
                .setTitle("General Commands")
                .setColor(new Color((int) (Math.random() * 0x1000000)))
                .setThumbnail(cmd.getMember().getUser().getEffectiveAvatarUrl())
                .setTimestamp(Instant.now());
        for (FrameworkCommand cmds : olzieCommand.getCommands()) {
            eb.addField(olzieCommand.getPrefix() + cmds.getName(), cmds.getDescription(), true);
        }
        cmd.getChannel().sendMessage(eb.build()).queue();
    }
}
