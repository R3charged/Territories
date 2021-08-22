package io.github.R3charged;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.jorel.commandapi.CommandAPIHandler;
import dev.jorel.commandapi.arguments.CommandAPIArgumentType;
import dev.jorel.commandapi.arguments.LocationType;
import dev.jorel.commandapi.arguments.SafeOverrideableArgument;
import dev.jorel.commandapi.nms.NMS;
import dev.jorel.commandapi.wrappers.Location2D;
import io.github.R3charged.utility.Loc;

import java.util.function.Function;

public class LocArgument extends SafeOverrideableArgument<Location2D> {
    public LocArgument(String nodeName) {
        super(nodeName, CommandAPIHandler.getInstance().getNMS()._ArgumentPosition2D()
                        , (Location2D l) -> l.getChunk().getX() + "d " + l.getChunk().getZ());
    }

    @Override
    public Class<?> getPrimitiveType() {
        return Loc.class;
    }

    @Override
    public CommandAPIArgumentType getArgumentType() {
        return CommandAPIArgumentType.LOCATION_2D;
    }

    @Override
    public <CommandSourceStack> Object parseArgument(NMS<CommandSourceStack> nms, CommandContext<CommandSourceStack> cmdCtx, String key) throws CommandSyntaxException {
        return nms.getLocation2DBlock(cmdCtx, key);
    }
}
