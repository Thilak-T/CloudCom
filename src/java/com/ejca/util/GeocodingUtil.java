/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ejca.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Rucha Kulkarni
 */
public class GeocodingUtil {
    
   private static final String GEOCODER_REQUEST_PREFIX_FOR_XML = "http://maps.google.com/maps/api/geocode/xml";

   private long latitude = 0;
   
   private long longitude = 0;

    public long getLatitude() {
        return latitude;
    }

    public long getLongitude() {
        return longitude;
    }
   
   
   public final String getCoordinates(String address)  throws IOException, XPathExpressionException, ParserConfigurationException, SAXException {

   
    // prepare a URL to the geocoder
    URL url = new URL(GEOCODER_REQUEST_PREFIX_FOR_XML + "?address=" + URLEncoder.encode(address, "UTF-8") + "&sensor=false");

    // prepare an HTTP connection to the geocoder
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    Document geocoderResultDocument = null;
    try {
      // open the connection and get results as InputSource.
      conn.connect();
      InputSource geocoderResultInputSource = new InputSource(conn.getInputStream());

      // read result and parse into XML Document
      geocoderResultDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(geocoderResultInputSource);
    } finally {
      conn.disconnect();
    }

    // prepare XPath
    XPath xpath = XPathFactory.newInstance().newXPath();

    // extract the result
    NodeList resultNodeList = null;

    // a) obtain the formatted_address field for every result
    resultNodeList = (NodeList) xpath.evaluate("/GeocodeResponse/result/formatted_address", geocoderResultDocument, XPathConstants.NODESET);
    for(int i=0; i<resultNodeList.getLength(); ++i) {
      System.out.println(resultNodeList.item(i).getTextContent());
    }

    // b) extract the locality for the first result
    resultNodeList = (NodeList) xpath.evaluate("/GeocodeResponse/result[1]/address_component[type/text()='locality']/long_name", geocoderResultDocument, XPathConstants.NODESET);
    for(int i=0; i<resultNodeList.getLength(); ++i) {
      System.out.println(resultNodeList.item(i).getTextContent());
    }

    // c) extract the coordinates of the first result
    resultNodeList = (NodeList) xpath.evaluate("/GeocodeResponse/result[1]/geometry/location/*", geocoderResultDocument, XPathConstants.NODESET);
    float lat = Float.NaN;
    float lng = Float.NaN;
    for(int i=0; i<resultNodeList.getLength(); ++i) {
      Node node = resultNodeList.item(i);
      if("lat".equals(node.getNodeName())) {
          lat = Float.parseFloat(node.getTextContent());
          latitude = (long)lat;
          System.out.println("Latitude------>" + latitude);
      }
      if("lng".equals(node.getNodeName())) {
          lng = Float.parseFloat(node.getTextContent());
          longitude = (long)lng;
          System.out.println("Longitude----->" + longitude);          
      }
    }
    System.out.println("lat/lng=" + lat + "," + lng);
//    
//    // c) extract the coordinates of the first result
//    resultNodeList = (NodeList) xpath.evaluate("/GeocodeResponse/result[1]/address_component[type/text() = 'administrative_area_level_1']/country[short_name/text() = 'US']/*", geocoderResultDocument, XPathConstants.NODESET);
//    float lat1 = Float.NaN;
//    float lng1 = Float.NaN;
//    for(int i=0; i<resultNodeList.getLength(); ++i) {
//      Node node = resultNodeList.item(i);
//      if("lat".equals(node.getNodeName())) lat1 = Float.parseFloat(node.getTextContent());
//      if("lng".equals(node.getNodeName())) lng1 = Float.parseFloat(node.getTextContent());
//    }
//    System.out.println("lat/lng=" + lat1 + "," + lng1);
            return lat + "," + lng;
  }
}
