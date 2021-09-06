package io.github.R3charged.commands;

import dev.jorel.commandapi.Brigadier;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.executors.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class TerritoryCommand extends CommandAPICommand{

    private HashMap<String, Object> defaultValues = new HashMap<>();

    protected Player sender;

    public TerritoryCommand(String commandName) {
        super(commandName);
        executes((sender, args) -> {
            if(!(sender instanceof Player)) { //TODO maybe change later
                sender.sendMessage("Only players can use this command.");
                return;
            }
            this.sender = (Player) sender;

            castArgs(getMap(args));
            exeCmd();
        });

    }
    /*

    public TerritoryCommand(String cmdName) {
        cmd = new CommandAPICommand(cmdName);
        cmd.executes((sender, args) -> {

            parseArgs(args);
            exeCmd();
        });
    }*/

    protected CommandAPICommand prepend(Argument arg) {
        getArguments().add(0, arg);
        return this;
    }

    protected void addDefault(String nodeName, Object obj) {
        defaultValues.put(nodeName,obj);
    }

    protected HashMap<String, Object> getMap(Object[] args) {
        HashMap<String,Object> map = (HashMap<String, Object>) defaultValues.clone();
        try {
            for (int i = 0; i < getArguments().size(); i++) {
                map.put(getArguments().get(i).getNodeName(), args[i]);
            }
        } catch (IndexOutOfBoundsException e) {
            return map;
        }
        return map;
    }

    public Object duplicate() {
        try {
            ByteArrayOutputStream outByte = new ByteArrayOutputStream();
            ObjectOutputStream outObj = new ObjectOutputStream(outByte);
            ByteArrayInputStream inByte;
            ObjectInputStream inObject;
            outObj.writeObject(this);
            outObj.close();
            byte[] buffer = outByte.toByteArray();
            inByte = new ByteArrayInputStream(buffer);
            inObject = new ObjectInputStream(inByte);
            @SuppressWarnings("unchecked")
            Object deepcopy =  inObject.readObject();
            inObject.close();
            return (TerritoryCommand) deepcopy;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    protected abstract void castArgs(HashMap<String, Object> map);
    protected abstract boolean exeCmd();

}
