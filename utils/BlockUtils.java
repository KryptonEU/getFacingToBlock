/* Copyright (c) 2020 Kryštof Špírek

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE. */

package me.krypton.utils

import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;

//If your MC version is higher than 1.8.9, change "thePlayer" to "player" and "theWorld" to "world".

public class BlockUtils {
    
    //If this metod returns null, that means that there isn't side of the block available.
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
            if(Minecraft.getMinecraft().theWorld.getBlockState(blockPos).getBlock() instanceof BlockAir ||
               Minecraft.getMinecraft().theWorld.getBlockState(blockPos).getBlock() instanceof BlockLiquid) {
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
