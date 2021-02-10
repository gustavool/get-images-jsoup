package com.gustavo;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.HttpStatusException;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DeviantArtSelector {
    private DeviantArtSelector() {
    }

    static final RestTemplate restTemplate = new RestTemplate();

    public static void getAllImages() throws IOException, InterruptedException {
        String username = "maxiuchiha22";
        int folderId = 72762201;

        int offsetNum = 0;
        int nameNumber = 0;
        boolean hasMore = true;

        try {
            while (hasMore) {
                log.info("Connection URL with parameters");
                String url =
                        "https://www.deviantart.com/_napi/da-user-profile/api/gallery/contents?username=" + username +
                                "&offset=" + offsetNum + "&limit=24&folderid=" + folderId;

                //added delay due to error 403 Forbidden
                if (nameNumber <= 450) {
                    log.info("Total <= 450 - Delay of 20 seconds - get json with new offset number");
                    Thread.sleep(20000L);
                } else {
                    log.info("Total > 450 - Delay of 40 seconds - get json with new offset number");
                    Thread.sleep(40000L);
                }

                String jsonGetDeviantArt = restTemplate.getForEntity(url, String.class).getBody();

                JSONObject jsonObj = new JSONObject(jsonGetDeviantArt);
                hasMore = jsonObj.getBoolean("hasMore");
                JSONArray jsonResults = jsonObj.getJSONArray("results");

                //get urls from json diviantart
                List<String> urlImgSelectList = new ArrayList<>();
                for (int i = 0; i < jsonResults.length(); i++) {
                    urlImgSelectList.add(jsonResults.getJSONObject(i).getJSONObject("deviation").getString("url"));
                }
                log.info("get url links from json, size: {}", urlImgSelectList.size());

                //get specific url images with Jsoup
                List<String> urlImgs = new ArrayList<>();
                for (String urlImage : urlImgSelectList) {
                    log.info("Delay of 2 seconds - get specific url");
                    Thread.sleep(2000L);
                    Document doc = JsoupConnection.connectionDeviantArt(urlImage);
                    Elements imgElement = doc.select("._1izoQ");
                    urlImgs.add(imgElement.attr("src"));
                }
                log.info("get specific url from website, total: {}", urlImgSelectList.size());

                //save images .png
                for (String urlImg : urlImgs) {
                    log.info("Delay of 2 seconds - saving images");
                    Thread.sleep(2000L);
                    try (InputStream in = new URL(urlImg).openStream()) {
                        Files.copy(in, Paths.get("C:/images/image" + nameNumber + ".png"));
                        nameNumber++;
                    }
                }
                log.info("Total images: {}", nameNumber);
                offsetNum += 24;
                urlImgSelectList.clear();
                urlImgs.clear();
            }
        } catch (HttpStatusException e) {
            e.printStackTrace();
        }
    }

}
