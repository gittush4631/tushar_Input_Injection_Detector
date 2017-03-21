 package prog;

 import java.io.BufferedReader;
 import java.io.OutputStreamWriter;
 import java.net.URL;
 import java.net.URLConnection;
 import java.net.URLEncoder;
 import java.util.Collection;
 import java.util.Vector;
 import websphinx.Link;
 import websphinx.Page;

 public class WebPage
 {
   private Link link;
   private Page p;
   private String content;

   public WebPage(Page p)
   {
     this.p = p;
     this.link = p.getOrigin();
     this.content = p.getContent();
   }

   public String getContent() {
     return this.content;
   }

   public Page getPage() {
     return this.p;
   }

   public Link getLink() {
     return this.link;
   }

   public String postPage(String urlstring) {
     String ret = "";
     String query = "";
     String base = "";
     if (urlstring.indexOf('?') == urlstring.lastIndexOf('?'))
     {
       base = urlstring.substring(0, urlstring.indexOf('?'));
       query = urlstring.substring(urlstring.indexOf('?') + 1);
     }
     else {
       base = urlstring.substring(0, urlstring.lastIndexOf('?'));
       query = urlstring.substring(urlstring.lastIndexOf('?') + 1);
     }

     try
     {
       String data = "";
       String[] params = query.split("&");

       for (int i = 0; i < params.length; i++) {
         String[] temp = params[i].split("=");
         String name = temp[0];
         String value = temp[1];
         data = data + URLEncoder.encode(name, "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8");
       }



       URL url = new URL(base);
       URLConnection conn = url.openConnection();
       conn.setDoOutput(true);
       OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
       wr.write(data);
       wr.flush();


       BufferedReader rd = new BufferedReader(new java.io.InputStreamReader(conn.getInputStream()));
       String line;
       while ((line = rd.readLine()) != null) {
         ret = ret + line;
       }
       wr.close();
       rd.close();
     }
     catch (Exception localException) {}
     return ret;
   }

   public Vector<Arg> getParams() {
     Vector<Arg> ret = new Vector();
     String base = "";
     String args = "";
     if (this.link.getMethod() == 0) {
       base = this.link.toURL();
       args = this.link.getQuery();
       if (base.indexOf('?') > 0) {
         args = this.link.toURL().substring(this.link.toURL().indexOf('?') + 1);
       }
       if (args.length() > 0) {
         base = base.substring(0, this.link.toURL().indexOf('?'));
       }
     }
     else {
       base = this.link.toURL();
       args = this.link.getQuery();

       if (base.indexOf('?') == base.lastIndexOf('?'))
       {
         base = this.link.toURL();
         args = this.link.getQuery();
         if (base.indexOf('?') > 0)
           args = this.link.toURL().substring(this.link.toURL().indexOf('?') + 1);
         if (args.length() > 0) {
           base = base.substring(0, this.link.toURL().indexOf('?'));
         }
       }
       else {
         base = this.link.toURL();
         args = this.link.getQuery().substring(this.link.getQuery().lastIndexOf('?') + 1);
         if (args.length() > 0) {
           base = base.substring(0, this.link.toURL().lastIndexOf('?'));
         }
       }
     }

     if (args.length() > 0) {
       Vector<String> arguments = new Vector((Collection)java.util.Arrays.asList(args.split("&")));
       for (String arg : arguments) {
         String[] tmp = arg.split("=");
         String name = tmp[0];
         String value; if (tmp.length > 1) {
           value = tmp[1];
         } else
           value = "";
         ret.add(new Arg(name, value, args.replaceFirst(name + "=" + value, ""), this.link.getMethod(), this.link.toURL()));
       }
     }

     return ret;
   }
 }
