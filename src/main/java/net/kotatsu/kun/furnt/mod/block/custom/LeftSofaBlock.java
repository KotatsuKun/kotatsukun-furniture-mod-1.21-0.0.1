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

public class LeftSofaBlock extends Block {
    public static final DirectionProperty facing = Properties.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.createCuboidShape(0, 0, 2, 12, 5, 14),
            Block.createCuboidShape(0, 5, 1, 12, 7, 14),
            Block.createCuboidShape(0, 7, 12, 12, 18, 14),
            Block.createCuboidShape(0, 6.3, 7, 12, 15.3, 9),
            Block.createCuboidShape(12, 0, 1, 14, 12, 16),
            Block.createCuboidShape(14, 8, 1, 16, 12, 16),
            Block.createCuboidShape(0, 0, 14, 12, 20, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.createCuboidShape(1.25, 0, 3.75, 13.25, 5, 15.75),
            Block.createCuboidShape(0.25, 5, 3.75, 13.25, 7, 15.75),
            Block.createCuboidShape(11.25, 7, 3.75, 13.25, 18, 15.75),
            Block.createCuboidShape(6.9, 5.5, 3.75, 8.9, 14.5, 15.75),
            Block.createCuboidShape(0.25, 0, 1.75, 15.25, 12, 3.75),
            Block.createCuboidShape(0.25, 8, -0.25, 15.25, 12, 1.75),
            Block.createCuboidShape(13.25, 0, 3.75, 15.25, 20, 15.75)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.createCuboidShape(4, 0, 2.5, 16, 5, 14.5),
            Block.createCuboidShape(4, 5, 2.5, 16, 7, 15.5),
            Block.createCuboidShape(4, 7, 2.5, 16, 18, 4.5),
            Block.createCuboidShape(4, 5.5, 6.75, 16, 14.5, 8.75),
            Block.createCuboidShape(2, 0, 0.5, 4, 12, 15.5),
            Block.createCuboidShape(0, 8, 0.5, 2, 12, 15.5),
            Block.createCuboidShape(4, 0, 0.5, 16, 20, 2.5)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.createCuboidShape(2.75, 0, -0.25, 14.75, 5, 11.75),
            Block.createCuboidShape(2.75, 5, -0.25, 15.75, 7, 11.75),
            Block.createCuboidShape(2.75, 7, -0.25, 4.75, 18, 11.75),
            Block.createCuboidShape(7, 5.5, -0.25, 9, 14.5, 11.75),
            Block.createCuboidShape(0.75, 0, 11.75, 15.75, 12, 13.75),
            Block.createCuboidShape(0.75, 8, 13.75, 15.75, 12, 15.75),
            Block.createCuboidShape(0.75, 0, -0.25, 2.75, 20, 11.75)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

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

    public LeftSofaBlock(Settings settings){
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
       builder.add(facing);
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(facing)));
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(facing, rotation.rotate(state.get(facing)));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(facing, ctx.getHorizontalPlayerFacing().getOpposite());
    }
}
