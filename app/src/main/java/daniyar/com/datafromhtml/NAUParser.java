package daniyar.com.datafromhtml;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by yernar on 21/09/16.
 */

public class NAUParser {

    public static ArrayList<News> parseNews(String html, String baseUrl) {
        ArrayList<News> arrayList = new ArrayList<>();

        Document doc = Jsoup.parse(html, "http://www.na.edu/category/headlines/");
        Elements content = doc.getElementsByClass("fusion-post-large");

        for(Element post : content) {
            String title = post.getElementsByClass("entry-title").get(0).getElementsByTag("a").get(0).text();
            String description = post.getElementsByClass("reading-box-description").get(0).text();
            String imageSrc = post.getElementsByClass("attachment-blog-large").get(0).attr("src");

            arrayList.add(new News(title, description, imageSrc));
        }

        return arrayList;
    }

    public static ArrayList<News> parseNews(String html) {
        return parseNews(html, null);
    }

    public static ArrayList<News> parseNews(byte[] html, String baseUrl) {
        return parseNews(bytesToString(html), baseUrl);
    }

    public static ArrayList<News> parseNews(byte[] html) {
        return parseNews(bytesToString(html), null);
    }

    public static String bytesToString(byte[] bytes){
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e){
            return null;
        }
    }
}
