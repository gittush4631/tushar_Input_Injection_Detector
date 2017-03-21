package prog;

import gui.FindPanel;
import gui.MainFrame;

public class TestingThread extends Thread {
	java.util.Vector<Arg> args;
	boolean run;
	MainFrame parent;
	public static long test_end_time;
	public static long test_total_time;

	public TestingThread(java.util.Vector<Arg> args, MainFrame parent) {
		this.args = args;
		this.parent = parent;
	}

	public void run() {
		this.run = true;
		for (Arg arg : this.args) {
			if ((this.run) && (arg.getTest())) {
				arg.testSQL();
				this.parent.setStatus("Testing page " + arg.getPageLink()
						+ "...");
				this.parent.repaint();
			}
		}

		this.parent.setStatus("Finished Testing");
		test_end_time = System.currentTimeMillis();
		test_total_time = test_end_time - FindPanel.test_start_time;
		System.out.println("test_total_time: " + test_total_time);
		
		// WriteFile write = new WriteFile();
		// write.write(Arg.sb.toString());

	}

	public void stopit() {
		this.run = false;
	}
}
