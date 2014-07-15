package me.raino.commons.utility;

import java.util.Map;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;

import com.google.common.collect.Maps;

public class BlockUtility {

    public static final BlockFace[] BLOCK_FACES = new BlockFace[] { BlockFace.UP, BlockFace.DOWN, BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST };
    public static final BlockFace[] SIGN_FACES = new BlockFace[] { BlockFace.UP, BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST };

    public static Map<BlockFace, Block> getRelativeBlocks(Block block) {
        Map<BlockFace, Block> blocks = Maps.newHashMap();
        for (BlockFace face : BLOCK_FACES) {
            Block relative = block.getRelative(face);
            if (relative == null)
                continue;
            blocks.put(face, relative);
        }
        return blocks;
    }

    public static Map<BlockFace, Sign> getAttachedSigns(Block block) {
        Map<BlockFace, Sign> signs = Maps.newHashMap();
        for (BlockFace face : SIGN_FACES) {
            Sign sign = toSign(block.getRelative(face));
            if (sign == null)
                continue;
            signs.put(face, sign);
        }
        return signs;
    }

    public static boolean isSign(Block block) {
        switch (block.getType()) {
        case WALL_SIGN:
        case SIGN_POST:
        case SIGN:
            return true;
        default:
            return false;
        }
    }

    public static Sign toSign(Block block) {
        return isSign(block) ? (Sign) block.getState() : null;
    }

}
