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

public class Totoro extends Block {

    public Totoro(Settings settings){
        super(settings);
    }

    private static final VoxelShape SHAPE = Stream.of(
            Block.createCuboidShape(3, 1, 2, 13, 10, 11),
            Block.createCuboidShape(13, 2.864708086973228, 2.3198416226752547, 16, 9.864708086973227, 5.319841622675256),
            Block.createCuboidShape(0, 2.864708086973228, 2.3198416226752547, 2.999999999999999, 9.864708086973227, 5.319841622675256),
            Block.createCuboidShape(4, 2, 11, 12, 9, 12),
            Block.createCuboidShape(4, 2, 1, 12, 9, 2),
            Block.createCuboidShape(5, 1, 10, 11, 3, 15),
            Block.createCuboidShape(4, 9, 3, 12, 16, 10),
            Block.createCuboidShape(6, 11, 2, 10, 13, 3),
            Block.createCuboidShape(9, 13, 2.95, 11, 15, 2.95),
            Block.createCuboidShape(9, 16, 5, 11, 19, 7),
            Block.createCuboidShape(5, 16, 5, 7, 19, 7),
            Block.createCuboidShape(5, 13, 2.95, 7, 15, 2.95),
            Block.createCuboidShape(4, 0, 4, 7, 2, 7),
            Block.createCuboidShape(9, 0, 4, 12, 2, 7),
            Block.createCuboidShape(3, 11, 2.5, 7, 14, 2.5),
            Block.createCuboidShape(10, 11, 2.5, 14, 14, 2.5)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
