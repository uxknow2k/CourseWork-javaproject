package com.company.cursach;

import static com.company.cursach.Utils.print;

public class Main {
    public static void main(String[] args) {
        MyListImpl myList = new MyListImpl("MGU");

        MyQueueImpl itDepartment = new MyQueueImpl("IT");
        itDepartment.add(new Teacher("Durov", Position.PROFESSOR));
        itDepartment.add(new Teacher("Gates", Position.ASSISTANT));
        itDepartment.add(new Teacher("Bezos", Position.SENIOR_LECTURER));

        MyQueueImpl lawyerDepartment = new MyQueueImpl("Lawyer");
        lawyerDepartment.add(new Teacher("Makarov", Position.PROFESSOR));
        lawyerDepartment.add(new Teacher("Helidze", Position.SENIOR_ASSISTANT));

        MyQueueImpl medicineDepartment = new MyQueueImpl("Medicine");
        medicineDepartment.add(new Teacher("Urgi", Position.ASSISTANT_PROFESSOR));

        MyQueueImpl aviationDepartment = new MyQueueImpl("Aviation");
        aviationDepartment.add(new Teacher("Gagarin", Position.ASSISTANT_PROFESSOR));
        aviationDepartment.add(new Teacher("Titova", Position.TEACHER));

        myList.add(itDepartment);
        myList.add(lawyerDepartment);
        myList.add(medicineDepartment);
        myList.add(aviationDepartment);
        System.out.println("Выводим всю коллекцию");
        System.out.println(print(myList));

        System.out.println("Удаляем кафедру по индексу 1");
        myList.removeByIndex(1);
        System.out.println(print(myList));

        System.out.println("Добавляем нового преподавателя в IT");
        myList.getByName("IT").add(new Teacher("Zukerberg", Position.ASSISTANT_PROFESSOR));
        System.out.println(print(myList));

        System.out.println("Удаляем первого преподавателя с кафедры Медицины");
        myList.getByName("Medicine").pop();
        System.out.println(print(myList));

        System.out.println("Создаем новую кафедру");
        myList.add(new MyQueueImpl("Education"));
        System.out.println(print(myList));

        System.out.println("Удаляем учителя из кафедры IT по индексу 1");
        myList.getByName("IT").removeById(1);
        System.out.println(print(myList));
    }
}
