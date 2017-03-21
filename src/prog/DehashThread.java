 package prog;

 import websphinx.Form;
 import websphinx.Link;
 import websphinx.Page;

 public class DehashThread extends Thread
 {
   private String md5;

   public DehashThread(String md5)
   {
     this.md5 = md5;
   }

   public void run() {
     try {
       Page p = new Page(new Link("http://www.md5search.org/index.php"));

       websphinx.Tag[] tags = p.getTags();
       for (int i = 0; i < tags.length; i++) {}



       Form f = new Form(tags[79], tags[0], p.getURL());
       System.out.println(f.getMethod());
       Page res = new Page(new Link(f.makeQuery()));
       System.out.println(res.getContent());
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
   }

   public static void main(String[] args) { new DehashThread("a002825aa880d2fd493ef684588d18c0").start(); }
 }
