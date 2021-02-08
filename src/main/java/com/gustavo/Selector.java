package com.gustavo;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Selector {
    public static RestTemplate restTemplate = new RestTemplate();


    public static String getImageLink() throws IOException {
        String url = "https://www.deviantart.com/_napi/da-user-profile/api/gallery/contents?username=AiKawaiiChan" +
                "&offset=0&limit=24&folderid=61285342";
        List<String> urlImgSelectList = new ArrayList<>();
        String jsonGetDeviantArt = restTemplate.getForEntity(url, String.class).getBody();

        JSONObject jsonObj = new JSONObject(jsonGetDeviantArt);
        System.out.println(jsonObj.getBoolean("hasMore"));
        JSONArray jsonResults = jsonObj.getJSONArray("results");
        for(int i = 0; i < jsonResults.length(); i++) {
            urlImgSelectList.add(jsonResults.getJSONObject(i).getJSONObject("deviation").getString("url"));
        }

        List<String> urlImgs = new ArrayList<>();
        for(String urlImage: urlImgSelectList) {
            Document doc = JsoupConnection.connectionDeviantArt(urlImage);
            Elements imgElement = doc.select("._1izoQ");
            urlImgs.add(imgElement.attr("src"));
        }

        int nameNumber = 0;
        for(String urlImg : urlImgs) {
            try(InputStream in = new URL(urlImg).openStream()){
                Files.copy(in, Paths.get("C:/images/image" + nameNumber + ".png"));
                nameNumber++;
            }
        }

        return null;
    }

}
