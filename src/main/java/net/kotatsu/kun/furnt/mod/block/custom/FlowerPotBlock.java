package net.kotatsu.kun.furnt.mod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import java.util.stream.Stream;

public class FlowerPotBlock extends Block {

    public FlowerPotBlock(Settings settings){
        super(settings);
    }

    private static final VoxelShape SHAPE = Stream.of(
            Block.createCuboidShape(5, 0, 5, 11, 1, 11),
            Block.createCuboidShape(4, 1, 5, 5, 6, 11),
            Block.createCuboidShape(11, 1, 5, 12, 6, 11),
            Block.createCuboidShape(5, 1, 4, 11, 6, 5),
            Block.createCuboidShape(5, 1, 11, 11, 6, 12),
            Block.createCuboidShape(8, 5, 4, 8, 13, 12),
            Block.createCuboidShape(4, 5, 8, 12, 13, 8),
            Block.createCuboidShape(5, 5, 5, 11, 5, 11)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
