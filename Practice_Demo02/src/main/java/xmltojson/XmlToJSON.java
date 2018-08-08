package xmltojson;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.List;

/**
 * Created by dx on 2018/8/8.
 *
 * @author dx
 */
public class XmlToJSON {
    public static void main(String[] args) {
        //创建解析器
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(new File("Practice_Demo02\\src\\main\\resources\\example.xml"));
            Element root = document.getRootElement();
            getElements(root);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    private static void getElements(Element e) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjectOne = new JSONObject();
        JSONObject jsonObjectTwo = new JSONObject();
        List<Element> list = e.elements();
        try {
            FileWriter fl = new FileWriter(new File("Practice_Demo02\\src\\main\\resources\\result.json"));
            BufferedWriter bw = new BufferedWriter(fl);

            for (Element element : list) {
                String type = element.attributeValue("Type");
                jsonObjectOne.put("Type", type);
                String isbn = element.attributeValue("ISBN");
                jsonObjectOne.put("ISBN", isbn);
                //将jsonObjectTwo的json放入jsonObjectOne中
                jsonObjectOne.put("IFO", jsonObjectTwo);
                String title = element.element("title").getText();
                jsonObjectTwo.put("title", title);
                String author = element.element("author").getText();
                jsonObjectTwo.put("author", author);
                String price = element.element("price").getText();
                jsonObjectTwo.put("price", price);
                //将jsonObjectOne的json放入jsonArray数组中
                jsonArray.put(jsonObjectOne);
            }

            String str = jsonArray.toString();
            bw.write(str);


            bw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }


    }
}
