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

public class ChairBlock extends Block {
    public static final DirectionProperty facing = Properties.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.createCuboidShape(3, 8, 2, 13, 9, 14),
            Block.createCuboidShape(3, 9, 12, 13, 21, 13),
            Block.createCuboidShape(3, 0, 3, 4, 8, 4),
            Block.createCuboidShape(3, 0, 12, 4, 8, 13),
            Block.createCuboidShape(12, 0, 12, 13, 8, 13),
            Block.createCuboidShape(12, 0, 3, 13, 8, 4),
            Block.createCuboidShape(4, 5, 12, 12, 6, 13),
            Block.createCuboidShape(3, 5, 4, 4, 6, 12),
            Block.createCuboidShape(12, 5, 4, 13, 6, 12)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.createCuboidShape(2, 8, 3, 14, 9, 13),
            Block.createCuboidShape(12, 9, 3, 13, 21, 13),
            Block.createCuboidShape(3, 0, 12, 4, 8, 13),
            Block.createCuboidShape(12, 0, 12, 13, 8, 13),
            Block.createCuboidShape(12, 0, 3, 13, 8, 4),
            Block.createCuboidShape(3, 0, 3, 4, 8, 4),
            Block.createCuboidShape(12, 5, 4, 13, 6, 12),
            Block.createCuboidShape(4, 5, 12, 12, 6, 13),
            Block.createCuboidShape(4, 5, 3, 12, 6, 4)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();


    public static final VoxelShape SHAPE_S = Stream.of(
            Block.createCuboidShape(3, 8, 2, 13, 9, 14),
            Block.createCuboidShape(3, 9, 3, 13, 21, 4),
            Block.createCuboidShape(12, 0, 12, 13, 8, 13),
            Block.createCuboidShape(12, 0, 3, 13, 8, 4),
            Block.createCuboidShape(3, 0, 3, 4, 8, 4),
            Block.createCuboidShape(3, 0, 12, 4, 8, 13),
            Block.createCuboidShape(4, 5, 3, 12, 6, 4),
            Block.createCuboidShape(12, 5, 4, 13, 6, 12),
            Block.createCuboidShape(3, 5, 4, 4, 6, 12)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public static final VoxelShape SHAPE_E = Stream.of(
            Block.createCuboidShape(2, 8, 3, 14, 9, 13),
            Block.createCuboidShape(3, 9, 3, 4, 21, 13),
            Block.createCuboidShape(12, 0, 3, 13, 8, 4),
            Block.createCuboidShape(3, 0, 3, 4, 8, 4),
            Block.createCuboidShape(3, 0, 12, 4, 8, 13),
            Block.createCuboidShape(12, 0, 12, 13, 8, 13),
            Block.createCuboidShape(3, 5, 4, 4, 6, 12),
            Block.createCuboidShape(4, 5, 3, 12, 6, 4),
            Block.createCuboidShape(4, 5, 12, 12, 6, 13)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();


    public ChairBlock(Settings settings){
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
