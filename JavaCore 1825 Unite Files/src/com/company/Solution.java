package com.company;

/*
1825 Собираем файл

Собираем файл из кусочков.
Считывать с консоли имена файлов.
Каждый файл имеет имя: [someName].partN.
Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.
Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end".
В папке, где находятся все прочтенные файлы, создать файл без суффикса [.partN].
Например, Lion.avi.
В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть потоки.

Требования:
1. Программа должна считывать имена файлов с консоли, пока не будет введено слово "end".
2. Создай поток для записи в файл без суффикса [.partN] в папке, где находятся все считанные файлы.
3. В новый файл перепиши все байты из файлов-частей *.partN.
4. Чтение и запись должны происходить с использованием буфера.
5. Созданные для файлов потоки должны быть закрыты.

 */

import java.io.*;
import java.util.*;
import java.io.File;

public class Solution {
public static void main(String[] args) throws IOException {
    BufferedReader conReader = new BufferedReader(new InputStreamReader(System.in));
    TreeSet<String> fileNames = new TreeSet<>();
    String currentPath = null;
    String lastFileName = null;
    while (true) {
        String readedFileName = conReader.readLine();
        if (readedFileName.equals("end"))
            break;
        File tmp = new File (readedFileName);
        fileNames.add(tmp.getName());
        currentPath = tmp.getParent();
        lastFileName = tmp.getName();
    }
conReader.close();
    String outputFileName = currentPath+"\\"+lastFileName.substring(0, lastFileName.indexOf(".part"));
    FileOutputStream fileWrite = new FileOutputStream(outputFileName);
    for (String fileName : fileNames) {
        FileInputStream fileRead = new FileInputStream(currentPath+"\\"+fileName);
        while(fileRead.available()>0) {
            byte[] buf = new byte[fileRead.available()];
            fileRead.read(buf);
            fileWrite.write(buf);
        }
        fileRead.close();
    }
    fileWrite.close();

}
}




