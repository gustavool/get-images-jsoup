# DeviantArt Images Jsoup

## Description
Project developed to download all images from a [DeviantArt](deviantart.com) gallery using Rest Template and 
[JSOUP](https://jsoup.org).

## Technologies used
* Java
* Rest Template  
* JSOUP

## How to use
* Get **gallery** name and **folder id** that are in url: 
  * https://www.deviantart.com/maxiuchiha22/gallery/76514340/majin-buu-saga

* Add in their respective variables in the code. Example below:
```
String username = "maxiuchiha22"; //gallery name
int folderId = 76514340;  //folder id
```
* Directory default: **C:/images**