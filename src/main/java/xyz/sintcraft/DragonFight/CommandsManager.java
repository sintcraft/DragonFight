package xyz.sintcraft.DragonFight;

public class CommandsManager {
    private DragonFight dragonFight;

    public CommandsManager(DragonFight p) {
        this.dragonFight = p;
        loadCommands();
    }

    private void loadCommands() {
        xyz.sintcraft.DragonFight.commands.DragonFight dragonFightCommand = new xyz.sintcraft.DragonFight.commands.DragonFight(dragonFight);
        dragonFight.getCommand("dragonfight").setExecutor(dragonFightCommand);
        dragonFight.getCommand("dragonfight").setTabCompleter(dragonFightCommand);
    }
}
