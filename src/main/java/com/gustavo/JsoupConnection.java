package com.gustavo;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

@Slf4j
public class JsoupConnection {
    private JsoupConnection() {
    }

//    deviantArt API get infos
    public static Document connectionDeviantArt(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            log.error("Jsoup connection error.");
            e.printStackTrace();
            return null;
        }
    }
}
