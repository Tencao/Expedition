package wtf.worldgen;

import net.minecraft.world.World;
import wtf.Core;
import wtf.utilities.UBC.UBCGenMethods;
import wtf.utilities.wrappers.ChunkCoords;
import wtf.utilities.wrappers.ChunkScan;

import java.util.concurrent.CountDownLatch;

public class MultiThreadScanner implements Runnable{

	private final World world;
	public final ChunkCoords coords;
	
	private volatile CountDownLatch latch = new CountDownLatch(1);
	
	
	public MultiThreadScanner(World world, ChunkCoords coords){
		this.world = world;
		this.coords = coords;
		
	}

	public void latchOn() throws InterruptedException{
		latch.await();
	}
	

	@Override
	public void run() {
		WorldScanner scanner;
		switch (world.provider.getDimensionType()) {
			case OVERWORLD:
				scanner = new WorldScanner();
				break;
			case NETHER:
				return;
				//scanner = new NetherScanner();
				//break;
			case THE_END:
				return;
			default:
				return;
		}

		GeneratorMethods gen = Core.UBC ? new UBCGenMethods(world, coords, world.rand) : new GeneratorMethods(world, coords, world.rand);
		ChunkScan scan = scanner.getChunkScan(world, coords, gen);
		CoreWorldGenListener.storeScan(world, coords, scan);

		CoreWorldGenListener.deRegScanner(world, coords);
		latch.countDown();

	}

	
}