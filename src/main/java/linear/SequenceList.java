package linear;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author 张晓童
 * @description 顺序表
 * 顺序表是在计算机内存中以数组形式保存的线性表，线性表的顺序存储是指用一组地址连续的存储单元，依次存储线性表中的各个元素。
 * @date 2025/3/24 13:07
 */
public class SequenceList<T> implements Iterable<T> {
    // 一组存储单元
    private T[] items;
    // 长度
    private int length;

    public SequenceList(int capacity){
        items = (T[]) new Object[capacity];
        length = 0;
    }

    public void clear(){
        items = (T[]) new Object[items.length];
        length = 0;
    }

    public boolean isEmpty(){
        return length == 0;
    }

    public int size(){
        return length;
    }

    public T get(int i){
        return items[i];
    }

    public int indexOf(T t){
        for (int i = 0; i < items.length; i++) {
            if (t.equals(items[i])){
                return i;
            }
        }
        return -1;
    }

    public void insert(T t){
        if (items.length == length){
            resize(items.length * 2);
        }
        items[length++] = t;
    }

    public void insert(int i, T t){
        if (items.length == length){
            resize(items.length * 2);
        }

        for (int j = length++;  j > i; j--) {
            items[j] = items[j-1];
        }
        items[i] = t;
    }

    public T remove(int i){
        T temp = items[i];
        for (int j = i; j < items.length-1; j++) {
            items[j] = items[j+1];
        }
        length--;

        // 30 <= 100/4=25 不缩容
        // 20 <= 100/4=25 缩容
        if (length <= items.length / 4){
            resize(items.length / 2);
        }
        return temp;
    }

    public void resize(int scale){
        T[] temp = this.items;
        items = (T[]) new Object[scale];
        for (int i = 0; i < length; i++) {
            items[i] = temp[i];
        }
    }

    @Override
    public String toString() {
        return "SequenceList{" +
                "list=" + Arrays.toString(items) +
                ", length=" + length +
                '}';
    }

    class SequenceIterator implements Iterator {
        private int current;

        public SequenceIterator(){
            this.current = 0;
        }

        @Override
        public boolean hasNext() {
            return current < length;
        }

        @Override
        public Object next() {
            return items[current++];
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new SequenceIterator();
    }
}
