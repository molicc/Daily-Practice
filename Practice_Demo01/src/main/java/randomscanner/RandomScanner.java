package randomscanner;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by dx on 2018/8/6.
 *
 * @author
 * 通过poi实现对excel文件的读取，遍历符合条件的数据并输出
 * 随机点名2位同学
 */
public class RandomScanner {
    /**
     * fileToBeRead为文件名，arg为需要查询的列号
     */
    public static String fileToBeRead = "F:\\Test\\培训班成员分组-Java&HAP.xlsx";

    public static void main(String[] args) {
        Set<Integer> numSet = new HashSet<Integer>();
        numSet = RandomNum.randomSet(1, 49, 2, (HashSet<Integer>) numSet);
        int[] index = new int[2];
        int k = 0;
        Iterator<Integer> iterator = numSet.iterator();
        while (iterator.hasNext()) {
            index[k] = iterator.next();
            k++;
        }
        try {
            // 创建对Excel工作簿文件的引用
            Excel_reader test = new Excel_reader();
            //后面的参数代表需要输出哪些列，参数个数可以任意
            ArrayList<ArrayList<String>> arr = test.xlsx_reader(fileToBeRead, 0, 1, 2, 3, 4, 5);
            System.out.println("序号 部门 姓名 性别 工号 组号");
            for (Integer flag : index) {
                for (int i = 0; i < arr.size(); i++) {
                    ArrayList<String> row = arr.get(i);
                    if (row.get(0).equals(flag.toString())) {
                        for (int j = 0; j < row.size(); j++) {
                            System.out.print(row.get(j) + " ");
                        }
                    }
                }
                System.out.println("");
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

}


class Excel_reader {


    /**
     * @param excel_url 需要解析的文件路径
     * @param args      需要返回的列
     * @return 返回二维字符串数组集合
     * @throws IOException
     */
    @SuppressWarnings({"resource", "unused"})
    public ArrayList<ArrayList<String>> xlsx_reader(String excel_url, int... args) throws IOException {

        //读取xlsx文件
        XSSFWorkbook xssfWorkbook = null;
        //寻找目录读取文件
        File excelFile = new File(excel_url);
        InputStream is = new FileInputStream(excelFile);
        xssfWorkbook = new XSSFWorkbook(is);

        if (xssfWorkbook == null) {
            System.out.println("未读取到内容,请检查路径！");
            return null;
        }

        ArrayList<ArrayList<String>> ans = new ArrayList<ArrayList<String>>();
        //遍历xlsx中的sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // 对于每个sheet，读取其中的每一行
            for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow == null) {
                    continue;
                }
                ArrayList<String> curarr = new ArrayList<String>();
                for (int columnNum = 0; columnNum < args.length; columnNum++) {
                    XSSFCell cell = xssfRow.getCell(args[columnNum]);
                    curarr.add(Trim_str(getValue(cell)));
                }
                ans.add(curarr);
            }
        }
        return ans;
    }

    //判断后缀为xlsx的excel文件的数据类
    @SuppressWarnings("deprecation")
    private static String getValue(XSSFCell xssfRow) {
        if (xssfRow == null) {
            return "---";
        }
        if (xssfRow.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
            double cur = xssfRow.getNumericCellValue();
            long longVal = Math.round(cur);
            Object inputValue = null;
            if (Double.parseDouble(longVal + ".0") == cur) {
                inputValue = longVal;
            } else {
                inputValue = cur;
            }
            return String.valueOf(inputValue);
        } else if (xssfRow.getCellType() == XSSFCell.CELL_TYPE_BLANK || xssfRow.getCellType() == XSSFCell.CELL_TYPE_ERROR) {
            return "---";
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }


    /**
     * 字符串修剪  去除所有空白符号 ， 问号 ， 中文空格
     */
    static private String Trim_str(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("[\\s\\?]", "").replace("　", "");
    }
}
