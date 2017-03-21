package prog;

import gui.WriteFile;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;
import websphinx.Link;
import websphinx.Page;

public class Arg {
	private String pageLink;
	private String name;
	private Vector<String> value;
	private String rest;
	private int method;
	private boolean isVulnerable;
	private int vulnType;
	private boolean toTest;
	private FileWriter fw;
	// public static StringBuffer sb = new StringBuffer();
	private StringBuffer sb = new StringBuffer();
	WriteFile write = new WriteFile();

	public Arg(String name, String value, String rest, int method,
			String pageLink) {
		this.name = name;
		this.value = new Vector();
		this.value.addElement(value);
		this.rest = rest;
		this.method = method;
		this.pageLink = pageLink;
		this.isVulnerable = false;
		this.toTest = false;
		this.vulnType = 1;
	}

	public boolean getTest() {
		return this.toTest;
	}

	public void setTest(boolean t) {
		this.toTest = t;
	}

	public void addValue(String v) {
		this.value.addElement(v);
	}

	public int getVulnType() {
		return this.vulnType;
	}

	public boolean isVulnerable() {
		return this.isVulnerable;
	}

	public String getPageBase() {
		return this.pageLink.substring(0, this.pageLink.indexOf("?"));
	}

	public String postPage(String urlstring) {
		String ret = "";
		String query = "";
		String base = "";
		if (urlstring.indexOf('?') == urlstring.lastIndexOf('?')) {
			base = urlstring.substring(0, urlstring.indexOf('?'));
			query = urlstring.substring(urlstring.indexOf('?') + 1);
		} else {
			base = urlstring.substring(0, urlstring.lastIndexOf('?'));
			query = urlstring.substring(urlstring.lastIndexOf('?') + 1);
		}

		try {
			String data = "";
			String[] params = query.split("&");

			for (int i = 0; i < params.length; i++) {
				String name = params[i].substring(0, params[i].indexOf('='));
				if (!name.equals("")) {
					String value = params[i]
							.substring(params[i].indexOf('=') + 1);
					data = data + java.net.URLEncoder.encode(name, "UTF-8")
							+ "=" + java.net.URLEncoder.encode(value, "UTF-8");
					data = data + "&";
				}
			}

			URL url = new URL(base);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream());
			wr.write(data);
			wr.flush();

			BufferedReader rd = new BufferedReader(
					new java.io.InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				ret = ret + line;
			}
			wr.close();
			rd.close();
		} catch (Exception localException) {
		}
		return ret;
	}

	public void testSQL() {
		if (this.method == 0) {
			String base = this.pageLink;

			String args = this.pageLink
					.substring(this.pageLink.indexOf('?') + 1);
			if (args.length() > 0) {
				base = base.substring(0, base.indexOf('?'));
			}

//			String test1 = (String) this.value.firstElement() + " AND 1=1";
//			String test2 = (String) this.value.firstElement() + " AND 1=2";
			
			String test1 = (String) this.value.firstElement() + "'' OR 1=1";
			String test2 = (String) this.value.firstElement() + "'*/--'";

			String test3 = (String) this.value.firstElement() + " AND 1=1/*";
			String test4 = (String) this.value.firstElement() + " AND 1=2/*";

			String test5 = (String) this.value.firstElement() + "' AND '1'='1";
			String test6 = (String) this.value.firstElement() + "' AND '1'='2";

			String test7 = (String) this.value.firstElement() + "' AND 1=1/*";
			String test8 = (String) this.value.firstElement() + "' AND 1=2/*";
			try {
				Link linknormal = new Link((base + "?" + args).replaceAll(" ",
						"%20"));
				Link linktest1 = new Link(
						(base + "?" + args.replaceFirst(
								new StringBuilder(String.valueOf(this.name))
										.append("=")
										.append((String) this.value
												.firstElement()).toString(),
								new StringBuilder(String.valueOf(this.name))
										.append("=").append(test1).toString()))
								.replaceAll(" ", "%20"));
				Link linktest2 = new Link(
						(base + "?" + args.replaceFirst(
								new StringBuilder(String.valueOf(this.name))
										.append("=")
										.append((String) this.value
												.firstElement()).toString(),
								new StringBuilder(String.valueOf(this.name))
										.append("=").append(test2).toString()))
								.replaceAll(" ", "%20"));

				if (comparePages(linktest1, linktest2) > comparePages(
						linktest1, linknormal)) {
					System.out.println(this.pageLink + " is vulnerable (1)");
					this.isVulnerable = true;
					this.vulnType = 1;
					sb.append(this.pageLink).append("\n");
				}

				linknormal = new Link(
						(base + "?" + args).replaceAll(" ", "%20"));
				linktest1 = new Link(
						(base + "?" + args.replaceFirst(
								new StringBuilder(String.valueOf(this.name))
										.append("=")
										.append((String) this.value
												.firstElement()).toString(),
								new StringBuilder(String.valueOf(this.name))
										.append("=").append(test3).toString()))
								.replaceAll(" ", "%20"));
				linktest2 = new Link(
						(base + "?" + args.replaceFirst(
								new StringBuilder(String.valueOf(this.name))
										.append("=")
										.append((String) this.value
												.firstElement()).toString(),
								new StringBuilder(String.valueOf(this.name))
										.append("=").append(test4).toString()))
								.replaceAll(" ", "%20"));

				if (comparePages(linktest1, linktest2) > comparePages(
						linktest1, linknormal)) {
					System.out.println(this.pageLink + " is vulnerable (2)");
					this.isVulnerable = true;
					this.vulnType = 2;
					sb.append(this.pageLink).append("\n");
				}

				linknormal = new Link(
						(base + "?" + args).replaceAll(" ", "%20"));
				linktest1 = new Link(
						(base + "?" + args.replaceFirst(
								new StringBuilder(String.valueOf(this.name))
										.append("=")
										.append((String) this.value
												.firstElement()).toString(),
								new StringBuilder(String.valueOf(this.name))
										.append("=").append(test5).toString()))
								.replaceAll(" ", "%20"));
				linktest2 = new Link(
						(base + "?" + args.replaceFirst(
								new StringBuilder(String.valueOf(this.name))
										.append("=")
										.append((String) this.value
												.firstElement()).toString(),
								new StringBuilder(String.valueOf(this.name))
										.append("=").append(test6).toString()))
								.replaceAll(" ", "%20"));

				if (comparePages(linktest1, linktest2) > comparePages(
						linktest1, linknormal)) {
					System.out.println(this.pageLink + " is vulnerable (3)");
					this.isVulnerable = true;
					this.vulnType = 3;
					sb.append(this.pageLink).append("\n");
				}

				linknormal = new Link(
						(base + "?" + args).replaceAll(" ", "%20"));
				linktest1 = new Link(
						(base + "?" + args.replaceFirst(
								new StringBuilder(String.valueOf(this.name))
										.append("=")
										.append((String) this.value
												.firstElement()).toString(),
								new StringBuilder(String.valueOf(this.name))
										.append("=").append(test7).toString()))
								.replaceAll(" ", "%20"));
				linktest2 = new Link(
						(base + "?" + args.replaceFirst(
								new StringBuilder(String.valueOf(this.name))
										.append("=")
										.append((String) this.value
												.firstElement()).toString(),
								new StringBuilder(String.valueOf(this.name))
										.append("=").append(test8).toString()))
								.replaceAll(" ", "%20"));

				if (comparePages(linktest1, linktest2) <= comparePages(
						linktest1, linknormal))
					return;
				System.out.println(this.pageLink + " is vulnerable (4)");
				this.isVulnerable = true;
				this.vulnType = 4;
				sb.append(this.pageLink).append("\n");

				write.write(sb.toString());
				System.out.println("File Written Done...");
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			String base = this.pageLink;

			String args = this.pageLink
					.substring(this.pageLink.indexOf('?') + 1);

//			String test1 = (String) this.value.firstElement() + " AND 1=1";
//			String test2 = (String) this.value.firstElement() + " AND 1=2";
			
			String test1 = (String) this.value.firstElement() + "'' OR 1=1;";
			String test2 = (String) this.value.firstElement() + "'*/--'";

			String test3 = (String) this.value.firstElement() + " AND 1=1/*";
			String test4 = (String) this.value.firstElement() + " AND 1=2/*";

			String test5 = (String) this.value.firstElement() + "' AND '1'='1";
			String test6 = (String) this.value.firstElement() + "' AND '1'='2";

			String test7 = (String) this.value.firstElement() + "' AND 1=1/*";
			String test8 = (String) this.value.firstElement() + "' AND 1=2/*";
			try {
				String pagenormal = postPage(this.pageLink);
				String pagetest1 = postPage(this.pageLink.replaceFirst(
						this.name + "=" + (String) this.value.firstElement(),
						this.name + "=" + test1).replaceAll(" ", "%20"));
				String pagetest2 = postPage(this.pageLink.replaceFirst(
						this.name + "=" + (String) this.value.firstElement(),
						this.name + "=" + test2).replaceAll(" ", "%20"));
				if (comparePages(pagetest1, pagetest2) > comparePages(
						pagetest1, pagenormal)) {
					System.out.println(this.pageLink + " is vulnerable (1)");
					this.isVulnerable = true;
					this.vulnType = 1;
				}

				pagetest1 = postPage(
						this.pageLink.replaceFirst(this.name + "="
								+ (String) this.value.firstElement(), this.name
								+ "=" + test3)).replaceAll(" ", "%20");
				pagetest2 = postPage(
						this.pageLink.replaceFirst(this.name + "="
								+ (String) this.value.firstElement(), this.name
								+ "=" + test4)).replaceAll(" ", "%20");

				if (comparePages(pagetest1, pagetest2) > comparePages(
						pagetest1, pagenormal)) {
					System.out.println(this.pageLink + " is vulnerable (2)");
					this.isVulnerable = true;
					this.vulnType = 2;
				}

				pagetest1 = postPage(
						this.pageLink.replaceFirst(this.name + "="
								+ (String) this.value.firstElement(), this.name
								+ "=" + test5)).replaceAll(" ", "%20");
				pagetest2 = postPage(
						this.pageLink.replaceFirst(this.name + "="
								+ (String) this.value.firstElement(), this.name
								+ "=" + test6)).replaceAll(" ", "%20");

				if (comparePages(pagetest1, pagetest2) > comparePages(
						pagetest1, pagenormal)) {
					System.out.println(this.pageLink + " is vulnerable (3)");
					this.isVulnerable = true;
					this.vulnType = 3;
				}

				pagetest1 = postPage(
						this.pageLink.replaceFirst(this.name + "="
								+ (String) this.value.firstElement(), this.name
								+ "=" + test7)).replaceAll(" ", "%20");
				pagetest2 = postPage(
						this.pageLink.replaceFirst(this.name + "="
								+ (String) this.value.firstElement(), this.name
								+ "=" + test8)).replaceAll(" ", "%20");

				if (comparePages(pagetest1, pagetest2) > comparePages(
						pagetest1, pagenormal)) {
					System.out.println(this.pageLink + " is vulnerable (4)");
					this.isVulnerable = true;
					this.vulnType = 4;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public int comparePages(Link page1, Link page2) {
		String content1 = "";
		String content2 = "";
		try {
			content1 = new Page(page1).getContent();
			content2 = new Page(page2).getContent();
		} catch (java.io.IOException ioe) {
			ioe.printStackTrace();
		}
		String[] tmp1 = content1.split("<[^>]+>");
		String[] tmp2 = content2.split("<[^>]+>");
//		String[] tmp1 = content1.split("<[fname]+>");
//		String[] tmp2 = content2.split("<[fname]+>");
		int count1 = 0;
		int count2 = 0;
		for (int i = 0; i < tmp1.length; i++) {
			if (content2.indexOf(tmp1[i]) < 0) {
				count1++;
			}
		}
		for (int i = 0; i < tmp2.length; i++) {
			if (content1.indexOf(tmp2[i]) < 0) {
				count2++;
			}
		}
		return Math.max(count1, count2);
	}

	public int comparePages(String page1, String page2) {
		String[] tmp1 = page1.split("<[^>]+>");
		String[] tmp2 = page2.split("<[^>]+>");
//		String[] tmp1 = page1.split("<[fname]+>");
//		String[] tmp2 = page2.split("<[fname]+>");
		int count1 = 0;
		int count2 = 0;
		for (int i = 0; i < tmp1.length; i++) {
			if (page2.indexOf(tmp1[i]) < 0) {
				count1++;
			}
		}
		for (int i = 0; i < tmp2.length; i++) {
			if (page1.indexOf(tmp2[i]) < 0) {
				count2++;
			}
		}
		return Math.max(count1, count2);
	}

	public void setVulnerable(boolean vuln) {
		this.isVulnerable = vuln;
	}

	public String getName() {
		return this.name;
	}

	public String getValue() {
		return (String) this.value.firstElement();
	}

	public Vector<String> getValues() {
		return this.value;
	}

	public String getRest() {
		return this.rest;
	}

	public int getMethod() {
		return this.method;
	}

	public String getPageLink() {
		return this.pageLink;
	}

	public boolean equals(Object o) {
		if (((o instanceof Arg)) && (((Arg) o).getName().equals(this.name))
				&& (((Arg) o).getRest().equals(this.rest))
				&& (((Arg) o).getValue().equals(this.value))
				&& (((Arg) o).getMethod() == this.method)) {
			return true;
		}
		return false;
	}

	public int hashCode() {
		return this.name.hashCode() + this.value.hashCode()
				+ this.rest.hashCode() + this.method;
	}
}
