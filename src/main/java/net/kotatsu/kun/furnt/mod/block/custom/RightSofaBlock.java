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

public class RightSofaBlock extends Block {
    public static final DirectionProperty facing = Properties.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.createCuboidShape(4, 0, 2, 16, 5, 14),
            Block.createCuboidShape(4, 5, 1, 16, 7, 14),
            Block.createCuboidShape(4, 7, 12, 16, 18, 14),
            Block.createCuboidShape(4.1, 5.25, 7.74, 15.9, 14.25, 12.74),
            Block.createCuboidShape(2, 0, 1, 4, 12, 16),
            Block.createCuboidShape(0, 8, 1, 2, 12, 16),
            Block.createCuboidShape(4, 0, 14, 16, 20, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.createCuboidShape(2.25, 0, 0, 14.25, 5, 11.75),
            Block.createCuboidShape(1.25, 5, 0, 14.25, 7, 11.75),
            Block.createCuboidShape(12.25, 7, 0, 14.25, 18, 11.75),
            Block.createCuboidShape(8, 5.25, 0, 13, 14.25, 11.65),
            Block.createCuboidShape(1.25, 0, 11.75, 16.25, 12, 13.75),
            Block.createCuboidShape(1.25, 8, 13.75, 16.25, 12, 15.75),
            Block.createCuboidShape(14.25, 0, -0.25, 16.25, 20, 11.75)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.createCuboidShape(1, 0, 2.5, 13, 5, 14.5),
            Block.createCuboidShape(1, 5, 2.5, 13, 7, 15.5),
            Block.createCuboidShape(1, 7, 2.5, 13, 18, 4.5),
            Block.createCuboidShape(1.1, 5.25, 3.75, 12.9, 14.25, 8.75),
            Block.createCuboidShape(13, 0, 0.5, 15, 12, 15.5),
            Block.createCuboidShape(15, 8, 0.5, 17, 12, 15.5),
            Block.createCuboidShape(1, 0, 0.5, 13, 20, 2.5)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.createCuboidShape(1.75, 0, 3.75, 13.75, 5, 15.75),
            Block.createCuboidShape(1.75, 5, 3.75, 14.75, 7, 15.75),
            Block.createCuboidShape(1.75, 7, 3.75, 3.75, 18, 15.75),
            Block.createCuboidShape(3, 5.25, 3.8, 8, 14.25, 15.65),
            Block.createCuboidShape(0, 0, 1.75, 14.75, 12, 3.75),
            Block.createCuboidShape(0, 8, 0, 14.75, 12, 1.75),
            Block.createCuboidShape(0, 0, 3.75, 1.75, 20, 15.75)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public RightSofaBlock(Settings settings){
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
