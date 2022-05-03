package main;

import mapinteraction.MapInteractionManager;

public class GameLoop extends Thread{
    private final int FPS = 60;//so khung hinh (frames) tren giay
	private MapInteractionManager mapInteractionManager;
    public GameLoop(MapInteractionManager mapInteractionManager){
		this.mapInteractionManager = mapInteractionManager;
    }

	private void updateAndRender(){
		mapInteractionManager.update();
		mapInteractionManager.render();
    }
    @Override
	public void run() {

		double timePerFrame = 1000000000.0 / FPS;

		long previousTime = System.nanoTime();

		int frames = 0;
		long lastCheck = System.currentTimeMillis();

		double deltaF = 0;

		while (Thread.interrupted()==false) {
			long currentTime = System.nanoTime();

			// deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;


			if (deltaF >= 1) {// khi du 1/120 giay thi render va xu li va cham 
                updateAndRender();
				frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {// bo dem FPS
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}

	}
}
