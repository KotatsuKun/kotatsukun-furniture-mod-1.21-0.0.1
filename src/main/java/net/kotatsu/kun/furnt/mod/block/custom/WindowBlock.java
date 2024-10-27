package net.kotatsu.kun.furnt.mod.block.custom;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import java.util.stream.Stream;

public class WindowBlock extends Block{

    public WindowBlock(AbstractBlock.Settings settings){
        super(settings);

    }

    private static final VoxelShape SHAPE = Stream.of(
            Block.createCuboidShape(0, 0, 6.5, 16, 1, 9.5),
            Block.createCuboidShape(0, 31, 6.5, 16, 32, 9.5),
            Block.createCuboidShape(15, 1, 6.5, 16, 31, 9.5),
            Block.createCuboidShape(0, 1, 6.5, 1, 31, 9.5),
            Block.createCuboidShape(14, 1, 7.5, 15, 31, 8.5),
            Block.createCuboidShape(1, 1, 7.5, 2, 31, 8.5),
            Block.createCuboidShape(2, 1, 7.5, 14, 2, 8.5),
            Block.createCuboidShape(2, 30, 7.5, 14, 31, 8.5),
            Block.createCuboidShape(2, 2, 7.5, 14, 30, 8.5)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();


    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

}
