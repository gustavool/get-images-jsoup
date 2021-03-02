# DeviantArt Images Jsoup

## Description
Simple project developed to download all images from a [DeviantArt](https://www.deviantart.com) gallery using Rest Template and 
[JSOUP](https://jsoup.org).

## Technologies used
* Java
* Rest Template  
* JSOUP

## How to use
* Get **gallery** name and **folder id** that are in url: 
  * https://www.deviantart.com/maxiuchiha22/gallery/76514340/majin-buu-saga

* Add in their respective variables in the code(**DeviantArtSelector.java** class). Example below:
```
String username = "maxiuchiha22"; //gallery name
int folderId = 76514340;  //folder id
```
* Obs.:
  * Directory default: **C:/images**
  * If there are a lot of images it is necessary to add delay to not make too many requests in a short period of time 
  and return **error 403 Forbidden**.
