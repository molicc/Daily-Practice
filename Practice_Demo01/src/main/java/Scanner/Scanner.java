package Scanner;

import java.io.File;

/**
 * Created by dx on 2018/8/6.
 *
 * @author dx
 *         遍历文件层级目录
 */
public class Scanner {
    public static void main(String[] args) {
        //更改地址为需要遍历的文件目录
        printFiles(new File("F:\\Test"));

    }

    public static void printFiles(File dir) {
        if (dir.isDirectory()) {
            System.out.println(dir.getName());
            File next[] = dir.listFiles();
            int length = next.length;
            for (int i = 0; i < length; i++) {
                System.out.print("-------");
                if (next[i].isFile()) {
                    System.out.print("-------");
                    System.out.println(next[i].getName());
                } else if (next[i].isDirectory()) {
                    printFiles(next[i]);
                }

            }
        } else if (dir.isFile()) {
            System.out.println(dir.getName());
        }
    }
}
