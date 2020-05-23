package me.krypton.utils

import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;

public class BlockUtils {
    public static EnumFacing getFacingToBlock(BlockPos blockPosition) {
        ArrayList<BlockPos> possiblePos = new ArrayList<>();
        ArrayList<BlockPos> blockPosList = new ArrayList<>();

        possiblePos.add(blockPosition.add(-1,0,0));
        possiblePos.add(blockPosition.add(1,0,0));
        possiblePos.add(blockPosition.add(0,-1,0));
        possiblePos.add(blockPosition.add(0,1,0));
        possiblePos.add(blockPosition.add(0,0,-1));
        possiblePos.add(blockPosition.add(0,0,1));

        for(BlockPos blockPos : possiblePos) {
            if(Minecraft.getMinecraft().theWorld.getBlockState(blockPos).getBlock() instanceof BlockAir || Minecraft.getMinecraft().theWorld.getBlockState(blockPos).getBlock() instanceof BlockLiquid) {
                blockPosList.add(blockPos);
            }
        }

        double distance = -1;
        BlockPos bestPos = null;
        for(BlockPos blockPos : blockPosList) {
            double forDistance = Minecraft.getMinecraft().thePlayer.getDistance(blockPos.getX(), blockPos.getY() - 1, blockPos.getZ());
            if(forDistance < distance || bestPos == null || distance == -1) {
                distance = forDistance;
                bestPos = blockPos;
            }
        }

        bestPos = new BlockPos(bestPos.getX() - blockPosition.getX(),
                bestPos.getY() - blockPosition.getY(),
                bestPos.getZ() - blockPosition.getZ());

        if(bestPos.getX() == -1) {
            return EnumFacing.WEST;
        }
        if(bestPos.getX() == 1) {
            return EnumFacing.EAST;
        }
        if(bestPos.getY() == -1) {
            return EnumFacing.DOWN;
        }
        if(bestPos.getY() == 1) {
            return EnumFacing.UP;
        }
        if(bestPos.getZ() == -1) {
            return EnumFacing.NORTH;
        }
        if(bestPos.getZ() == 1) {
            return EnumFacing.SOUTH;
        }
        return null;
    }
}