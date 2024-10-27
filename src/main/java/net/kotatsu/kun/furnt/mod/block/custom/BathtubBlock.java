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

public class BathtubBlock extends Block {
    public static final DirectionProperty facing = Properties.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.createCuboidShape(0, 0, 0, 2, 14, 32),
            Block.createCuboidShape(14, 0, 0, 16, 14, 32),
            Block.createCuboidShape(2, 0, 0, 14, 14, 2),
            Block.createCuboidShape(2, 0, 28, 14, 14, 32),
            Block.createCuboidShape(2, 0, 2, 14, 2, 30),
            Block.createCuboidShape(2, 10, 2, 14, 10, 30),
            Block.createCuboidShape(7, 14, 29, 9, 17, 31),
            Block.createCuboidShape(5, 14, 29, 7, 16, 31),
            Block.createCuboidShape(9, 14, 29, 11, 16, 31),
            Block.createCuboidShape(7, 17, 25, 9, 19, 31)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.createCuboidShape(0, 0, 14, 32, 14, 16),
            Block.createCuboidShape(0, 0, 0, 32, 14, 2),
            Block.createCuboidShape(0, 0, 2, 2, 14, 14),
            Block.createCuboidShape(28, 0, 2, 32, 14, 14),
            Block.createCuboidShape(2.5, 0, 1.5, 30.5, 2, 13.5),
            Block.createCuboidShape(2, 10, 2, 30, 10, 14),
            Block.createCuboidShape(29.5, 14, 7, 31.5, 17, 9),
            Block.createCuboidShape(29.5, 14, 9, 31.5, 16, 11),
            Block.createCuboidShape(29.5, 14, 5, 31.5, 16, 7),
            Block.createCuboidShape(25.5, 17, 7, 31.5, 19, 9)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.createCuboidShape(14.25, 0, -15.75, 16.25, 14, 16.25),
            Block.createCuboidShape(0.25, 0, -15.75, 2.25, 14, 16.25),
            Block.createCuboidShape(2.25, 0, 14.25, 14.25, 14, 16.25),
            Block.createCuboidShape(2.25, 0, -15.75, 14.25, 14, -11.75),
            Block.createCuboidShape(1.75, 0, -14.25, 13.75, 2, 13.75),
            Block.createCuboidShape(2.25, 10, -13.75, 14.25, 10, 14.25),
            Block.createCuboidShape(7.25, 14, -15.25, 9.25, 17, -13.25),
            Block.createCuboidShape(9.25, 14, -15.25, 11.25, 16, -13.25),
            Block.createCuboidShape(5.25, 14, -15.25, 7.25, 16, -13.25),
            Block.createCuboidShape(7.25, 17, -15.25, 9.25, 19, -9.25)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.createCuboidShape(-16, 0, 0, 16, 14, 2),
            Block.createCuboidShape(-16, 0, 14, 16, 14, 16),
            Block.createCuboidShape(14, 0, 2, 16, 14, 14),
            Block.createCuboidShape(-16, 0, 2, -12, 14, 14),
            Block.createCuboidShape(-14, 0, 2, 14, 2, 14),
            Block.createCuboidShape(-13.5, 10, 2, 14.5, 10, 14),
            Block.createCuboidShape(-15, 14, 7, -13, 17, 9),
            Block.createCuboidShape(-15, 14, 5, -13, 16, 7),
            Block.createCuboidShape(-15, 14, 9, -13, 16, 11),
            Block.createCuboidShape(-15, 17, 7, -9, 19, 9)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();


    public BathtubBlock(Settings settings){
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
