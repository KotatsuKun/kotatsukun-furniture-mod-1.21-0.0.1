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

public class MiddleSofaBlock extends Block {
    public static final DirectionProperty facing = Properties.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.createCuboidShape(0, 0, 2, 16, 5, 14),
            Block.createCuboidShape(0, 5, 1, 16, 7, 14),
            Block.createCuboidShape(0, 7, 12, 16, 18, 14),
            Block.createCuboidShape(0.1, 6, 7, 15.9, 15, 12),
            Block.createCuboidShape(0, 0, 14, 16, 20, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.createCuboidShape(1.75, 0, 0.25, 13.75, 5, 16.25),
            Block.createCuboidShape(0.75, 5, 0.25, 13.75, 7, 16.25),
            Block.createCuboidShape(11.75, 7, 0.25, 13.75, 18, 16.25),
            Block.createCuboidShape(7.5, 5.25, 0.35, 12.5, 14.25, 16.15),
            Block.createCuboidShape(13.75, 0, 0.25, 15.75, 20, 16.25)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.createCuboidShape(0, 0, 2.5, 16, 5, 14.5),
            Block.createCuboidShape(0, 5, 2.5, 16, 7, 15.5),
            Block.createCuboidShape(0, 7, 2.5, 16, 18, 4.5),
            Block.createCuboidShape(0.1, 5.25, 3.75, 15.9, 14.25, 8.75),
            Block.createCuboidShape(0, 0, 0.5, 16, 20, 2.5)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.createCuboidShape(2.5, 0, 0, 14.5, 5, 16),
            Block.createCuboidShape(2.5, 5, 0, 15.5, 7, 16),
            Block.createCuboidShape(2.5, 7, 0, 4.5, 18, 16),
            Block.createCuboidShape(3.75, 5.25, 0.1, 8.75, 14.25, 15.9),
            Block.createCuboidShape(0.5, 0, 0, 2.5, 20, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public MiddleSofaBlock(Settings settings){
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
