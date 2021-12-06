package com.company.cursach;

import java.io.*;

public class MyListImpl implements Serializable {
    private String universityName;
    private int currentSize = 0;
    private MyQueueImpl[] values;

    public MyListImpl(String universityName) {
        this.universityName = universityName;
        this.values = new MyQueueImpl[10];
    }

    public MyQueueImpl get(int index) {
        if (index >= currentSize || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return values[index];
    }

    public MyQueueImpl getByName(String name) {
        for (MyQueueImpl queue : values) {
            if (name.equals(queue.getDepartmentName())) {
                return queue;
            }
        }
        return null;
    }

    /**
     * Метод добавления нового элемента в коллекцию
     * @param value новый добавляемый элемент
     */
    public void add(MyQueueImpl value) {
        if (currentSize == values.length) {
            grow();
        }
        values[currentSize++] = value;
    }

    /**
     * Метод увеличения массива, вызывается когда заполнился массив элементов
     */
    private void grow() {
        int newCapacity = (int) (currentSize * 1.5);
        MyQueueImpl[] newValues = new MyQueueImpl[newCapacity];
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i];
        }
        this.values = newValues;
    }

    /**
     * Метод удаления по индексу
     * @param index индекс элемента который хотим удалить
     */
    public void removeByIndex(int index) {
        if (index >= currentSize || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        System.arraycopy(values, index + 1, values, index, values.length - index - 1);
        currentSize--;
    }

    public int getSize() {
        return currentSize;
    }

    public String getUniversityName() {
        return universityName;
    }

    @Override
    public String toString() {
        return universityName;
    }
}
