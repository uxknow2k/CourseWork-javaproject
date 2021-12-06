package com.company.cursach;

import java.io.*;

public class Utils {
    public static String print(MyListImpl myList) {
        StringBuilder sb = new StringBuilder();
        int teacherIndex;
        int departmentIndex = 0;
        sb.append(myList.getUniversityName()).append("\n");
        for (int i = 0; i < myList.getSize(); i++) {
            MyQueueImpl queue = myList.get(i);
            sb.append("-------------------------------------------\n");
            sb.append("\t" + departmentIndex++ + " department: " + queue.getDepartmentName() + "\n");
            teacherIndex = 0;
            Teacher[] teachers = new Teacher[queue.getSize()];
            int counter = 0;
            while (queue.getSize() > 0) {
                Teacher tmp = queue.pop();
                sb.append("\t\t" + teacherIndex++ + " teacher: " + tmp.toString() + "\n");
                teachers[counter++] = tmp;
            }
            for (Teacher teacher : teachers) {
                queue.add(teacher);
            }
        }
        return sb.toString();
    }

    /**
     * Метод для загрузки данных из файла
     * @param filePath файл откуда нужно загрузить
     * @return данные загруженные из файла
     */
    public static MyListImpl load(String filePath) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(
                    new FileInputStream(filePath));
            MyListImpl myList = (MyListImpl) objectInputStream.readObject();
            objectInputStream.close();
            return myList;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String save(String filePath, MyListImpl myList) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(filePath));
            objectOutputStream.writeObject(myList);
            objectOutputStream.close();
            return "Save success!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Something went wrong in saving file!";
        }
    }
}
