package leetcode.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/web-crawler/description/?envType=study-plan-v2&envId=premium-algo-100
 */
public class WebCrawler_1236 {
    interface HtmlParser {
        List<String> getUrls(String url);
    }

    Set<String> visited;
    String prefix;
    HtmlParser htmlParser;
    List<String> crawled;

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        visited = new HashSet<>();
        prefix = getPrefix(startUrl);
        this.htmlParser = htmlParser;
        crawled = new ArrayList<>();
        dfs(startUrl);
        return crawled;
    }

    void dfs(String url) {
        if (!url.startsWith(prefix) || visited.contains(url)) {
            return;
        }
        visited.add(url);
        crawled.add(url);
        List<String> list = htmlParser.getUrls(url);
        if (list == null) {
            return;
        }
        for (String nextUrl : list) {
            dfs(nextUrl);
        }
    }

    String getPrefix(String url) {
        int slashIndex = url.indexOf('/', 7);
        if (slashIndex<0) {
            return url;
        }
        return url.substring(0, slashIndex);
    }

    @org.junit.jupiter.api.Test
    public void test() {
        /**
         Input:
         urls = [
         "http://news.yahoo.com",
         "http://news.yahoo.com/news",
         "http://news.yahoo.com/news/topics/",
         "http://news.google.com",
         "http://news.yahoo.com/us"
         ]
         edges = [[2,0],[2,1],[3,2],[3,1],[0,4]]
         startUrl = "http://news.yahoo.com/news/topics/"
         Output: [
         "http://news.yahoo.com",
         "http://news.yahoo.com/news",
         "http://news.yahoo.com/news/topics/",
         "http://news.yahoo.com/us"
         ]
         */
//        assertEquals(expected, actual);
    }
}
