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

public class SinkBlock extends Block {
    public static final DirectionProperty facing = Properties.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.createCuboidShape(5, 0, 10, 10, 14, 13),
            Block.createCuboidShape(7, 0, 13, 8, 14, 14),
            Block.createCuboidShape(7, 11, 11, 8, 11, 12),
            Block.createCuboidShape(3, 14, 8, 12, 15, 14),
            Block.createCuboidShape(3, 15, 8, 4, 16, 12),
            Block.createCuboidShape(11, 15, 8, 12, 16, 12),
            Block.createCuboidShape(4, 15, 8, 11, 16, 9),
            Block.createCuboidShape(3, 15, 12, 12, 16, 14),
            Block.createCuboidShape(7.1, 16, 13, 7.9, 19, 14),
            Block.createCuboidShape(7, 18.9, 8.5, 8, 19.9, 11.5),
            Block.createCuboidShape(6, 16, 13, 7, 17, 14),
            Block.createCuboidShape(8, 16, 13, 9, 17, 14)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.createCuboidShape(12, 0, 5, 15, 14, 10),
            Block.createCuboidShape(15, 0, 7, 16, 14, 8),
            Block.createCuboidShape(13, 11, 7, 14, 11, 8),
            Block.createCuboidShape(10, 14, 3, 16, 15, 12),
            Block.createCuboidShape(10, 15, 11, 14, 16, 12),
            Block.createCuboidShape(10, 15, 3, 14, 16, 4),
            Block.createCuboidShape(10, 15, 4, 11, 16, 11),
            Block.createCuboidShape(14, 15, 3, 16, 16, 12),
            Block.createCuboidShape(15, 16, 7.1, 16, 19, 7.9),
            Block.createCuboidShape(6.58, 24.98, 7, 9.58, 25.98, 8),
            Block.createCuboidShape(15, 16, 8, 16, 17, 9),
            Block.createCuboidShape(15, 16, 6, 16, 17, 7)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.createCuboidShape(5, 0, 1, 10, 14, 4),
            Block.createCuboidShape(7, 0, 0, 8, 14, 1),
            Block.createCuboidShape(7, 11, 2, 8, 11, 3),
            Block.createCuboidShape(3, 14, 0, 12, 15, 6),
            Block.createCuboidShape(11, 15, 2, 12, 16, 6),
            Block.createCuboidShape(3, 15, 2, 4, 16, 6),
            Block.createCuboidShape(4, 15, 5, 11, 16, 6),
            Block.createCuboidShape(3, 15, 0, 12, 16, 2),
            Block.createCuboidShape(7.1, 16, 0, 7.89, 19, 1),
            Block.createCuboidShape(7, 24.98, 6.41, 8, 25.98, 9.41),
            Block.createCuboidShape(8, 16, 0, 9, 17, 1),
            Block.createCuboidShape(6, 16, 0, 7, 17, 1)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.createCuboidShape(0, 0, 5, 3, 14, 10),
            Block.createCuboidShape(-1, 0, 7, 0, 14, 8),
            Block.createCuboidShape(1, 11, 7, 2, 11, 8),
            Block.createCuboidShape(-1, 14, 3, 5, 15, 12),
            Block.createCuboidShape(1, 15, 3, 5, 16, 4),
            Block.createCuboidShape(1, 15, 11, 5, 16, 12),
            Block.createCuboidShape(4, 15, 4, 5, 16, 11),
            Block.createCuboidShape(-1, 15, 3, 1, 16, 12),
            Block.createCuboidShape(-1, 16, 7.1, 0, 19, 7.89),
            Block.createCuboidShape(5.41, 24.98, 7, 8.41, 25.98, 8),
            Block.createCuboidShape(-1, 16, 6, 0, 17, 7),
            Block.createCuboidShape(-1, 16, 8, 0, 17, 9)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public SinkBlock(Settings settings){
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



