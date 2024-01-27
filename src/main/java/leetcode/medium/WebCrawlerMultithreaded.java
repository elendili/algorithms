package leetcode.medium;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/web-crawler-multithreaded/
 * <p>
 * Given a URL startUrl and an interface HtmlParser, implement a Multi-threaded web crawler to crawl all links that are under the same hostname as startUrl.
 * <p>
 * Return all URLs obtained by your web crawler in any order.
 * <p>
 * Your crawler should:
 * Start from the page: startUrl
 * Call HtmlParser.getUrls(url) to get all URLs from a webpage of a given URL.
 * Do not crawl the same link twice.
 * Explore only the links that are under the same hostname as startUrl.
 */
public class WebCrawlerMultithreaded {

    class Crawler {
        private final HtmlParser htmlParser;
        private final CompletionService<List<String>> completionService;
        private final String startUrl;
        private final String startHostName;

        private final Set<String> outUrls;
        private final AtomicInteger submitted;

        Crawler(HtmlParser htmlParser, String startUrl) {
            this.htmlParser = htmlParser;
            this.startUrl = startUrl;
            this.startHostName = getHostName(startUrl);
            ExecutorService threadPool = Executors.newWorkStealingPool(4);
            this.completionService =
                    new ExecutorCompletionService<>(threadPool);
            this.outUrls =  Collections.newSetFromMap(new ConcurrentHashMap<>());
            this.submitted = new AtomicInteger();
        }

        private Callable<List<String>> createTask(String startUrl) {
            return () -> {

                long startTime = System.currentTimeMillis();
                List<String> list = htmlParser.getUrls(startUrl);
                List<String> out = new ArrayList<>();
                long duration = System.currentTimeMillis() - startTime;
                System.out.println("Crawl " + startUrl + " finished in " + duration + " ms with " + list);
                for (String url : list) {
                    if (startHostName.equals(getHostName(url)) && outUrls.add(url)) {
                        submit(url);
                    }
                }
                return out;
            };
        }

        void submit(String url){
            outUrls.add(url);
            submitted.incrementAndGet();
            completionService.submit(createTask(url));
        }

        public Set<String> crawl() {
            submit(startUrl);
            while (submitted.get() > 0) {
                completionServiceTake();
            }
            return outUrls;
        }

        List<String> completionServiceTake() {
            try {
                Future<List<String>> f = completionService.take();
                submitted.decrementAndGet();
                return f.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        <T> T futureBlockingGet(Future<T> future) {
            try {
                return future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        private String getHostName(String url) {
            String noProtocol = url.substring(7);      // without http://
            int slashIndex = noProtocol.indexOf('/');
            if (slashIndex>-1){
                return noProtocol.substring(0,slashIndex);
            }
            return noProtocol;
        }
    }

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        return new Crawler(htmlParser, startUrl).crawl().stream().sorted().toList();
    }

    // ----------------------------------------------------------------
    @Test
    public void test1() {
        List<String> urls = Arrays.asList(
                "http://news.yahoo.com",
                "http://news.yahoo.com/news",
                "http://news.yahoo.com/news/topics/",
                "http://news.google.com",
                "http://news.yahoo.com/us"
        );
        int[][] edges = new int[][]{{2, 0}, {2, 1}, {3, 2}, {3, 1}, {0, 4}};
        HtmlParser htmlParser = new HtmlParserMock(urls, edges);

        List<String> actual = crawl("http://news.yahoo.com/news/topics/", htmlParser);
        List<String> expected = Arrays.asList(
                "http://news.yahoo.com",
                "http://news.yahoo.com/news",
                "http://news.yahoo.com/news/topics/",
                "http://news.yahoo.com/us");

        assertEquals(expected, actual);
    }

    @Test
    public void test2() {
        List<String> urls = Arrays.asList(
                "http://news.yahoo.com",
                "http://news.yahoo.com/news",
                "http://news.yahoo.com/news/topics/",
                "http://news.google.com"
        );
        int[][] edges = new int[][]{{0, 2}, {2, 1}, {3, 2}, {3, 1}, {3, 0}};
        HtmlParser htmlParser = new HtmlParserMock(urls, edges);

        List<String> actual = crawl("http://news.google.com", htmlParser);
        List<String> expected = Arrays.asList("http://news.google.com");

        assertEquals(expected, actual);
    }

    interface HtmlParser {
        List<String> getUrls(String url);
    }

    static class HtmlParserMock implements HtmlParser {
        List<String> urls;
        int[][] edges;

        HtmlParserMock(List<String> urls, int[][] edges) {
            this.urls = urls;
            this.edges = edges;
        }

        @Override
        public List<String> getUrls(String url) {
            int urlIndex = urls.indexOf(url);
            List<String> out = new ArrayList<>();
            for (int[] edge : edges) {
                if (edge[0] == urlIndex) {
                    int childUrlIndex = edge[1];
                    String childUrl = urls.get(childUrlIndex);
                    out.add(childUrl);
                }
            }
            return out;
        }
    }
}

