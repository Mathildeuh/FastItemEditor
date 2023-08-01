package fr.mathilde.commands.FastItemEditorCommand;

import org.bukkit.entity.Player;

public abstract class SubCommands {

    public abstract String getName();

    public abstract String getSyntax();

    public abstract void run(Player player, String[] args);
}
