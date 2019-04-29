package com.watson.uitest.struct.element.locator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/263:05 PM
 */
public class Locator {

    private Map<String, String> locators = new HashMap<String, String>();


    public void addIdLocator(String id) {
        locators.put("id", id);
    }

    public void addNameLocator(String name) {
        locators.put("name", name);
    }

    public void addXpathLocator(String xpath) {
        locators.put("xpath", xpath);
    }

    public void addCSSLocator(String css) {
        locators.put("css", css);
    }

    public void addLinkTextLocator(String linkText) {
        locators.put("linktext", linkText);
    }

    public void addPartialLinkTextLocator(String partialLinkText) {
        locators.put("partiallinktext", partialLinkText);
    }

    public void addTagNameLocator(String tagName) {
        locators.put("tagname", tagName);
    }

    public void addClassLocator(String className) {
        locators.put("class", className);
    }


    public String getLocatorById() {
        return locators.get("id");
    }

    public String getLocatorByName() {
        return locators.get("name");
    }

    public String getLocatorByXpath() {
        return locators.get("xpath");
    }

    public String getLocatorByCSS() {
        return locators.get("css");
    }

    public String getLocatorByLinkText() {
        return locators.get("linktext");
    }

    public String getLocatorByPartialLinkText() {
        return locators.get("partiallinktext");
    }

    public String getLocatorByTagName() {
        return locators.get("tagname");
    }

    public String getLocatorByClass() {
        return locators.get("class");
    }


}
