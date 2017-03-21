package prog;

import gui.CrawlerPanel;
import gui.MainFrame;

public class CrawlingThread extends Thread {
	CustomCrawler c;
	MainFrame parent;
	public static long crawl_end_time;
	public static long crawl_total_time;
	
	public CrawlingThread(MainFrame parent, CustomCrawler c) {
		this.parent = parent;
		this.c = c;
	}

	public void run() {
		this.c.run();
		System.out.println("Done...");
		crawl_end_time = System.currentTimeMillis();
		crawl_total_time = crawl_end_time - CrawlerPanel.crawl_start_time;
		System.out.println("crawl_total_time: " + crawl_total_time);
		this.parent.setStatus("Finished crawling");
		this.parent.getCPanel().setNbPagesLeft(0);
	}

	public void stopit() {
		this.c.stop();
	}
}
