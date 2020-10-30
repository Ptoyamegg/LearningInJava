package xyz.dyk.io.xml;
//  {Require: nu.xom.Node; You must install the XOM library from http://www.xom.nu }
//  {RunFirst: Person}

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Elements;

import java.io.File;
import java.util.ArrayList;

public class People extends ArrayList<Person> {
    public People(String fileName) throws Exception {
        Document doc = new Builder().build(new File(fileName));
        Elements elements = doc.getRootElement().getChildElements();
        for (int i = 0; i < elements.size(); i++) {
            add(new Person(elements.get(i)));
        }
    }

    public static void main(String[] args) throws Exception {
        People p = new People("src/xyz.dyk.io/xml/People.xml");
        System.out.println(p);
    }
}
