package hackerrank.natera;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetArticleTitles {

    public static List<String> getArticleTitles(String author) {
        List<String> out = new ArrayList<>();
        int totalPages = Integer.MAX_VALUE;
        int page = 0;
        while (page < totalPages) {
            Map<String, Object> res = makeRequestReturnResponseAsMap(author, ++page);
            totalPages = ((Number) res.get("total_pages")).intValue();
            page = ((Number) res.get("page")).intValue();
            processData(res, out);
        }
        return out;
    }

    static void processData(Map<String, Object> res, List<String> toAdd) {
        List<Map<String, Object>> dataO = (List<Map<String, Object>>) res.get("data");
        dataO.forEach(
                map -> {
                    if (map.get("title") != null) {
                        toAdd.add(String.valueOf(map.get("title")));
                    } else if (map.get("story_title") != null) {
                        toAdd.add(String.valueOf(map.get("story_title")));
                    }
                });
    }

    private static Map<String, Object> makeRequestReturnResponseAsMap(String author, int page) {
        try {
            URL url =
                    new URL(
                            "https://jsonmock.hackerrank.com/api/articles?author=" + author + "&page=" + page);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            String text = new BufferedReader(new InputStreamReader(connection.getInputStream()))
                    .lines().collect(Collectors.joining("\n"));
            Type mapType = new TypeToken<Map<String, Object>>() {
            }.getType();
            return new Gson().fromJson(text, mapType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test() {
        Assertions.assertEquals("[Steve Jobs has passed away.]", getArticleTitles("patricktomas").toString());
        Assertions.assertEquals("[A Message to Our Customers, “Was isolated from 1999 to 2006 with a 486. Built my own late 80s OS”, Apple’s declining software quality]",
                getArticleTitles("epaga").toString());
        Assertions.assertEquals("[]", getArticleTitles("lol").toString());
    }
}
