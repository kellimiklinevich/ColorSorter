
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Display extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	static final int SCALE = 100;
	public static final int WIDTH = 12 * SCALE;
	public static final int HEIGHT = 10 * SCALE;
	public static final String TITLE = "Color Sorter";

	private Thread thread;

	private BufferedImage img;
	private boolean running = false;

	public Display() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		sortColors();
	}

	private void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	private void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void run() {
		int frames = 0;
		double unprocessedSeconds = 0;
		long previousTime = System.nanoTime();
		double secondsPerTick = 1 / 60.0;
		int tickCount = 0;
		boolean ticked = false;

		while (running) {
			long currentTime = System.nanoTime();
			long passedTime = currentTime - previousTime;
			previousTime = currentTime;
			unprocessedSeconds += passedTime / 1000000000.0;
			requestFocus();

			while (unprocessedSeconds > secondsPerTick) {
				tick();
				unprocessedSeconds -= secondsPerTick;
				ticked = true;
				tickCount++;
				if (tickCount % 60 == 0) {
					System.out.println(frames);
					previousTime += 1000;
					frames = 0;
				}

			}
			if (ticked) {
				render();
				frames++;
			}
			render();
			frames++;
		}
	}

	private void tick() {
	}

	private int a = 30;
	private double v = 0.5;
	private double x = 0;

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		
		x -= v;
		if (x <= -3000) {
			x = 0;
		}
		
		for (int i = 0; i < info.size(); i++) {
			if (info.get(i).getColor().equals("Color:blue")) {
				g.setColor(Color.BLUE);
			}
			if (info.get(i).getColor().equals("Color:green")) {
				g.setColor(Color.GREEN);
			}
			if (info.get(i).getColor().equals("Color:indigo")) {
				g.setColor(new Color(75, 0, 130));
			}
			if (info.get(i).getColor().equals("Color:orange")) {
				g.setColor(Color.ORANGE);
			}
			if (info.get(i).getColor().equals("Color:red")) {
				g.setColor(Color.RED);
			}
			if (info.get(i).getColor().equals("Color:violet")) {
				g.setColor(new Color(128, 0, 128));
			}
			if (info.get(i).getColor().equals("Color:yellow")) {
				g.setColor(Color.YELLOW);
			}
			g.fillRect(0, i * a+(int)x, 1200, a);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", 0, 18));
			g.drawString(info.get(i).toString(), 25, 25 + a * i+(int)x);
		}

		g.dispose();
		bs.show();
	}

	ArrayList<Information> info = new ArrayList<Information>();

	public void sortColors() {
		LoadFile l1 = new LoadFile("color.txt");
		ArrayList<String> newFile = l1.getFileText();

		for (int i = 0; i < newFile.size(); i += 4) {
			info.add(new Information(newFile.get(i), newFile.get(i + 1), newFile.get(i + 2), newFile.get(i + 3)));
		}
		for (int i = 0; i < info.size(); i++) {
			for (int j = 0; j < info.size(); j++) {
				if (info.get(i).getColor().charAt(6) < info.get(j).getColor().charAt(6)) {
					Information temp = info.get(j);
					info.set(j, info.get(i));
					info.set(i, temp);
				}
			}
		}
		for (Information x : info) {
			System.out.println(x.toString());
		}

	}

	public static void main(String[] args) {
		Display task = new Display();
		JFrame frame = new JFrame(TITLE);
		frame.add(task);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		task.start();

	}
}
