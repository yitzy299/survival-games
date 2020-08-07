package supercoder79.survivalgames.game.map.gen.biome;

import java.util.Random;

import kdotjpg.opensimplex.OpenSimplexNoise;
import supercoder79.survivalgames.game.map.gen.feature.GrassGen;
import supercoder79.survivalgames.game.map.gen.feature.MapGen;
import supercoder79.survivalgames.game.map.gen.feature.NoGen;
import supercoder79.survivalgames.game.map.gen.feature.tree.SpruceTreeGen;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;

public class MountainsGen implements BiomeGen {
	private OpenSimplexNoise rockNoise;

	@Override
	public void setupSeed(Random random) {
		rockNoise = new OpenSimplexNoise(random.nextLong());
	}

	@Override
	public double baseHeightFactorHigh() {
		return 40;
	}

	@Override
	public double baseHeightFactorLow() {
		return 20;
	}

	@Override
	public double lowerHeightFactorHigh() {
		return 24;
	}

	@Override
	public double lowerHeightFactorLow() {
		return 4;
	}

	@Override
	public double upperHeightFactorHigh() {
		return 16;
	}

	@Override
	public double upperHeightFactorLow() {
		return 4;
	}

	@Override
	public BlockState underState(int x, int z, Random random) {
		return rockNoise.eval(x / 32.0, z / 32.0) > 0.0 ? Blocks.STONE.getDefaultState() : Blocks.DIRT.getDefaultState();
	}

	@Override
	public BlockState topState(int x, int z, Random random) {
		return rockNoise.eval(x / 32.0, z / 32.0) > 0.0 ? Blocks.STONE.getDefaultState() : Blocks.GRASS_BLOCK.getDefaultState();
	}

	@Override
	public BlockState underwaterState(int x, int z, Random random) {
		return rockNoise.eval(x / 32.0, z / 32.0) > 0.0 ? Blocks.STONE.getDefaultState() : Blocks.DIRT.getDefaultState();
	}

	@Override
	public MapGen treeAt(BlockPos pos, Random random) {
		return random.nextInt(800) == 0 ? new SpruceTreeGen(pos) : new NoGen();
	}

	@Override
	public MapGen grassAt(BlockPos pos, Random random) {
		return random.nextInt(16) == 0 ? new GrassGen(pos) : new NoGen();
	}
}