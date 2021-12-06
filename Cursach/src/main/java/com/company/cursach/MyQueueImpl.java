package com.company.cursach;

import java.io.Serializable;

public class MyQueueImpl implements Serializable {
    private String departmentName;
    private Item head;
    private Item tail;
    private int currentSize;

    public MyQueueImpl(String departmentName) {
        this.departmentName = departmentName;

        head = new Item();
        tail = head;
        currentSize = 0;
    }

    public MyQueueImpl() {
        head = new Item();
        tail = head;
    }

    /**
     * Метод извлечения элемента из начала очереди
     * @return элемент в начале очереди
     */
    public Teacher pop() {
        if (currentSize == 0) {
            throw new IndexOutOfBoundsException();
        }
        Teacher teacher = head.getTeacher();
        if (head == tail) {
            tail = null;
        }
        currentSize--;
        head = head.nextItem;
        return teacher;
    }

    /**
     * Метод добавления элемента в очередь
     * @param teacher добавляемый элемент
     */
    public void add(Teacher teacher) {
        Item item = new Item(teacher);
        if (head == null || head.getTeacher() == null) {
            head = item;
        } else {
            tail.nextItem = item;
        }
        tail = item;
        currentSize++;
    }

    public void removeById(int id) {
        if (id < 0 || id >= currentSize) {
            throw new IndexOutOfBoundsException();
        }
        if (id == 0) {
            this.pop();
            return;
        }
        Item p = this.head;
        int counter = 0;
        while(p.nextItem != null) {
            if (id == counter + 1) {
                p.nextItem = p.nextItem.nextItem;
                break;
            }
            p = p.nextItem;
            counter++;
        }
        if (head.nextItem == null) {
            tail = head;
        }
        currentSize--;
    }

    public int getSize() {
        return currentSize;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    private class Item implements Serializable {
        private Teacher teacher;
        private Item nextItem = null;

        public Item() {
        }

        public Item(Teacher teacher) {
            this.teacher = teacher;
        }

        public Teacher getTeacher() {
            return teacher;
        }

        public void setTeacher(Teacher teacher) {
            this.teacher = teacher;
        }

        public Item getNextItem() {
            return nextItem;
        }

        public void setNextItem(Item nextItem) {
            this.nextItem = nextItem;
        }
    }

    @Override
    public String toString() {
        return departmentName;
    }
}
