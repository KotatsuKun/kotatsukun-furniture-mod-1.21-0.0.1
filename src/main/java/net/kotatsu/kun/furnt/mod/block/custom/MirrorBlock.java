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

public class MirrorBlock extends Block {
    public static final DirectionProperty facing = Properties.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.createCuboidShape(3, 0, 14, 13, 1, 15),
            Block.createCuboidShape(3, 16, 14, 13, 17, 15),
            Block.createCuboidShape(3, 1, 14, 4, 16, 15),
            Block.createCuboidShape(12, 1, 14, 13, 16, 15),
            Block.createCuboidShape(4, 1, 14.5, 12, 16, 15)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.createCuboidShape(15, 0, 2.625, 16, 1, 12.625),
            Block.createCuboidShape(15, 16, 2.625, 16, 17, 12.625),
            Block.createCuboidShape(15, 1, 11.625, 16, 16, 12.625),
            Block.createCuboidShape(15, 1, 2.625, 16, 16, 3.625),
            Block.createCuboidShape(15.5, 1, 3.625, 16, 16, 11.625)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.createCuboidShape(3, 0, 0, 13, 1, 1),
            Block.createCuboidShape(3, 16, 0, 13, 17, 1),
            Block.createCuboidShape(12, 1, 0, 13, 16, 1),
            Block.createCuboidShape(3, 1, 0, 4, 16, 1),
            Block.createCuboidShape(4, 1, 0, 12, 16, 0.5)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.createCuboidShape(0, 0, 3, 1, 1, 13),
            Block.createCuboidShape(0, 16, 3, 1, 17, 13),
            Block.createCuboidShape(0, 1, 3, 1, 16, 4),
            Block.createCuboidShape(0, 1, 12, 1, 16, 13),
            Block.createCuboidShape(0, 1, 4, 0.5, 16, 12)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public MirrorBlock(Settings settings){
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
