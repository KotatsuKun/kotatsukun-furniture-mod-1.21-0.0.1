package net.kotatsu.kun.furnt.mod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
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
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class DoubleWindowsBlock extends Block {
    public static final DirectionProperty facing = Properties.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.createCuboidShape(0, 0, 7, 32, 1, 10),
            Block.createCuboidShape(0, 27, 7, 32, 28, 10),
            Block.createCuboidShape(0, 1, 7, 1, 27, 10),
            Block.createCuboidShape(15, 1, 7, 17, 27, 10),
            Block.createCuboidShape(31, 1, 7, 32, 27, 10),
            Block.createCuboidShape(1, 1, 8, 2, 27, 9),
            Block.createCuboidShape(14, 1, 8, 15, 27, 9),
            Block.createCuboidShape(2, 1, 8, 14, 2, 9),
            Block.createCuboidShape(2, 26, 8, 14, 27, 9),
            Block.createCuboidShape(17, 1, 8, 18, 27, 9),
            Block.createCuboidShape(30, 1, 8, 31, 27, 9),
            Block.createCuboidShape(18, 1, 8, 30, 2, 9),
            Block.createCuboidShape(18, 26, 8, 30, 27, 9),
            Block.createCuboidShape(2, 2, 8, 14, 26, 8),
            Block.createCuboidShape(18, 2, 8, 30, 26, 8)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.createCuboidShape(6.75, 0, -0.75, 9.75, 1, 31.25),
            Block.createCuboidShape(6.75, 27, -0.75, 9.75, 28, 31.25),
            Block.createCuboidShape(6.75, 1, 30.25, 9.75, 27, 31.25),
            Block.createCuboidShape(6.75, 1, 14.25, 9.75, 27, 16.25),
            Block.createCuboidShape(6.75, 1, -0.75, 9.75, 27, 0.25),
            Block.createCuboidShape(7.75, 1, 29.25, 8.75, 27, 30.25),
            Block.createCuboidShape(7.75, 1, 16.25, 8.75, 27, 17.25),
            Block.createCuboidShape(7.75, 1, 17.25, 8.75, 2, 29.25),
            Block.createCuboidShape(7.75, 26, 17.25, 8.75, 27, 29.25),
            Block.createCuboidShape(7.75, 1, 13.25, 8.75, 27, 14.25),
            Block.createCuboidShape(7.75, 1, 0.25, 8.75, 27, 1.25),
            Block.createCuboidShape(7.75, 1, 1.25, 8.75, 2, 13.25),
            Block.createCuboidShape(7.75, 26, 1.25, 8.75, 27, 13.25),
            Block.createCuboidShape(7.75, 2, 17.25, 7.75, 26, 29.25),
            Block.createCuboidShape(7.75, 2, 1.25, 7.75, 26, 13.25)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.createCuboidShape(0, 0, 6.5, 32, 1, 9.5),
            Block.createCuboidShape(0, 27, 6.5, 32, 28, 9.5),
            Block.createCuboidShape(31, 1, 6.5, 32, 27, 9.5),
            Block.createCuboidShape(15, 1, 6.5, 17, 27, 9.5),
            Block.createCuboidShape(0, 1, 6.5, 1, 27, 9.5),
            Block.createCuboidShape(30, 1, 7.5, 31, 27, 8.5),
            Block.createCuboidShape(17, 1, 7.5, 18, 27, 8.5),
            Block.createCuboidShape(18, 1, 7.5, 30, 2, 8.5),
            Block.createCuboidShape(18, 26, 7.5, 30, 27, 8.5),
            Block.createCuboidShape(14, 1, 7.5, 15, 27, 8.5),
            Block.createCuboidShape(1, 1, 7.5, 2, 27, 8.5),
            Block.createCuboidShape(2, 1, 7.5, 14, 2, 8.5),
            Block.createCuboidShape(2, 26, 7.5, 14, 27, 8.5),
            Block.createCuboidShape(18, 2, 8.5, 30, 26, 8.5),
            Block.createCuboidShape(2, 2, 8.5, 14, 26, 8.5)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.createCuboidShape(6.25, 0, -15.75, 9.25, 1, 16.25),
            Block.createCuboidShape(6.25, 27, -15.75, 9.25, 28, 16.25),
            Block.createCuboidShape(6.25, 1, -15.75, 9.25, 27, -14.75),
            Block.createCuboidShape(6.25, 1, -0.75, 9.25, 27, 1.25),
            Block.createCuboidShape(6.25, 1, 15.25, 9.25, 27, 16.25),
            Block.createCuboidShape(7.25, 1, -14.75, 8.25, 27, -13.75),
            Block.createCuboidShape(7.25, 1, -1.75, 8.25, 27, -0.75),
            Block.createCuboidShape(7.25, 1, -13.75, 8.25, 2, -1.75),
            Block.createCuboidShape(7.25, 26, -13.75, 8.25, 27, -1.75),
            Block.createCuboidShape(7.25, 1, 1.25, 8.25, 27, 2.25),
            Block.createCuboidShape(7.25, 1, 14.25, 8.25, 27, 15.25),
            Block.createCuboidShape(7.25, 1, 2.25, 8.25, 2, 14.25),
            Block.createCuboidShape(7.25, 26, 2.25, 8.25, 27, 14.25),
            Block.createCuboidShape(8.25, 2, -13.75, 8.25, 26, -1.75),
            Block.createCuboidShape(8.25, 2, 2.25, 8.25, 26, 14.25)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public DoubleWindowsBlock(Settings settings){
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

    @Nullable
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
