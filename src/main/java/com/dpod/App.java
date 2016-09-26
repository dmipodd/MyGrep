package com.dpod;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Finds all ORA errors in logs from folderPath.
 */
public class App {

    private static final String folderPath = "c:\\Temp\\33\\CEE\\unpacked";

    public static void main(String[] args) {
        File folder = new File(folderPath);

        Collection<File> files = FileUtils.listFiles(folder, null, true);
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (File file : files) {
            System.out.println("    File " + file + " started!");
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                String s = bufferedReader.readLine();
                while (s != null) {
                    int indexOf = s.indexOf("ORA-");
                    if (indexOf != -1) {
                        String substring = s.substring(indexOf, indexOf + 9);
                        if (!map.containsKey(substring)) {
                            map.put(substring, 0);
                            System.out.println(s);
                        } else {
                            map.put(substring, map.get(substring) + 1);
                        }
                    }
                    s = bufferedReader.readLine();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(bufferedReader);
            }
            System.out.println("    File " + file + " processed!");
        }
        System.out.println(map);
    }

}
