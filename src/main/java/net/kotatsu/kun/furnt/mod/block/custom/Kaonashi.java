package net.kotatsu.kun.furnt.mod.block.custom;

import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;


import java.util.stream.Stream;

public class Kaonashi extends Block {

    public static final DirectionProperty facing = Properties.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.createCuboidShape(3, 16, 3, 13, 31, 12),
            Block.createCuboidShape(4, 31, 3, 12, 32, 11),
            Block.createCuboidShape(2, 0, 3, 14, 16, 13),
            Block.createCuboidShape(4, 23, 2, 12, 30, 3),
            Block.createCuboidShape(5, 22, 2, 11, 23, 3),
            Block.createCuboidShape(5, 30, 2, 11, 31, 3)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.createCuboidShape(2.75, 16, 3.25, 11.75, 31, 13.25),
            Block.createCuboidShape(2.75, 31, 4.25, 10.75, 32, 12.25),
            Block.createCuboidShape(2.75, 0, 2.25, 12.75, 16, 14.25),
            Block.createCuboidShape(1.75, 23, 4.25, 2.75, 30, 12.25),
            Block.createCuboidShape(1.75, 22, 5.25, 2.75, 23, 11.25),
            Block.createCuboidShape(1.75, 30, 5.25, 2.75, 31, 11.25)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public static final VoxelShape SHAPE_S = Stream.of(
            Block.createCuboidShape(3, 16, 4.5, 13, 31, 13.5),
            Block.createCuboidShape(4, 31, 5.5, 12, 32, 13.5),
            Block.createCuboidShape(2, 0, 3.5, 14, 16, 13.5),
            Block.createCuboidShape(4, 23, 13.5, 12, 30, 14.5),
            Block.createCuboidShape(5, 22, 13.5, 11, 23, 14.5),
            Block.createCuboidShape(5, 30, 13.5, 11, 31, 14.5)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public static final VoxelShape SHAPE_E = Stream.of(
            Block.createCuboidShape(4.25, 16, 3.25, 13.25, 31, 13.25),
            Block.createCuboidShape(5.25, 31, 4.25, 13.25, 32, 12.25),
            Block.createCuboidShape(3.25, 0, 2.25, 13.25, 16, 14.25),
            Block.createCuboidShape(13.25, 23, 4.25, 14.25, 30, 12.25),
            Block.createCuboidShape(13.25, 22, 5.25, 14.25, 23, 11.25),
            Block.createCuboidShape(13.25, 30, 5.25, 14.25, 31, 11.25)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public Kaonashi(AbstractBlock.Settings settings){
        super(settings);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(facing)){
            case NORTH:
                return SHAPE_N;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            case EAST:
                return SHAPE_E;
            default:
                return SHAPE_N;
        }
    }


    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(facing, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(facing, rotation.rotate(state.get(facing)));
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(facing)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(facing);
    }
}


