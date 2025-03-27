package linear;

import java.util.Iterator;

/**
 * @author 张晓童
 * @description 单向链表
 * 单向链表是链表中的一种，它由多个节点组成，每个节点都由一个数据域和一个指针域组成，数据域用来存储数据，指针域用来指向后继结点。
 * 单向链表的头结点的数据域不存储数据，指针域指向第一个真正存储数据的节点。
 * [null|next]->[data|next]->[data|next]->[data|next]->[data|null]
 * @date 2025/3/24 14:14
 */
public class UnidirectionLinkedList<T> implements Iterable<T> {
    // 头结点
    private Node<T> firstNode;
    // 链表长度
    private int length;

    /**
     * 内部节点类
     * @param <T>
     */
    private class Node<T>{
        // 数据域
        private T data;
        // 后继结点指针域
        private Node<T> next;

        public Node(T data,Node<T> next){
            this.data = data;
            this.next = next;
        }
    }

    public UnidirectionLinkedList(){
        // 初始化头结点
        firstNode = new Node<>(null, null);
        // 初始化长度
        length = 0;
    }

    // 清空数据
    public void clear(){
        // 头结点的next指针指向null
        firstNode.next = null;
        // 设置长度为0
        length = 0;
    }

    // 是否为空：true.空，false.不是空
    public boolean isEmpty(){
        return length == 0;
    }

    // 获取链表长度
    public int getLength(){
        return length;
    }

    // 根据下标获取数据
    public T get(int i){
        // 如果是空链表的话，直接返回null
        if (isEmpty()) {
            return null;
        }

        // 当前节点指向下标0的节点
        Node<T> currentNode = firstNode.next;
        // 找到i节点
        for (int j = 0; j < i; j++) {
            currentNode = currentNode.next;
        }
        // 返回i节点的数据
        return currentNode.data;
    }

    // 链表的尾部插入一条数据
    // [null|next]->[data|next]->[data|next]->[data|next]->[data|null]
    public void insert(T t){
        // 先找到链表的最后一个节点
        Node<T> lastNode = firstNode;
        while (lastNode.next != null){
            lastNode = lastNode.next;
        }
        // 创建新节点，并插入数据
        Node<T> newNode = new Node<>(t, null);
        // 最后一个节点的next指针指向新节点
        lastNode.next = newNode;
        // 长度++
        length++;
    }

    // 根据i下标，插入一条数据
    // 设置i下标前一个节点的next指针为新节点，新节点的next指针为i节点
    public void insert(int i, T t){
        // 找到下标为i的前一个节点
        Node<T> preNode = firstNode;
        for (int j = 0; j < i; j++) {
            preNode = preNode.next;
        }
        // 找到下标为i的节点
        Node<T> iNode = preNode.next;
        // 创建新节点，并设置新节点的next指针指向下标为i的节点
        Node<T> newNode = new Node<>(t, iNode);
        // 下标为i的前一个节点的next指针指向新节点
        preNode.next = newNode;
        // 长度++
        length++;
    }

    // 根据i下标，删除一条数据
    // 设置i下标前一个节点的next指针为i下标的后一个节点
    public T remove(int i){
        // 找到下标为i的前一个节点
        Node<T> preNode = firstNode;
        for (int j = 0; j <= i-1; j++) {
            preNode = preNode.next;
        }
        // 找到下标为i的节点
        Node<T> removeNode = preNode.next;
        // 设置i下标前一个节点的next指针为i下标的后一个节点
        preNode.next = removeNode.next;
        // 长度--
        length--;
        // 返回删除的数据
        return removeNode.data;
    }

    // 找t元素的下标
    public int indexOf(T t){
        // 设置当前节点为头结点
        Node<T> currentNode = firstNode;
        // 只要当前节点的next指针不为空，就一直往后找
        for (int i = 0; currentNode.next != null; i++) {
            // 当前节点 = 后继节点
            currentNode = currentNode.next;
            // 当前节点的数据 == t，返回i下标
            if (currentNode.data.equals(t)){
                return i;
            }
        }
        // 找到最后一个节点也没找到时，返回-1
        return -1;
    }

    // 链表反转
    public void reverse(){
        // 空链表直接return
        if (isEmpty()) {
            return;
        }

        // 从第一个节点开始反转
        reverse(firstNode.next);
    }

    // [null|next]->[data|next]->[data|next]->[data|null]
    // [null|next]->[1|next2]->[2|next3]->[3|null]
    // 递归反转每一个节点
    public Node<T> reverse(Node<T> currentNode){
        // 如果当前节点是最后一个节点的话，头节点的next指针指向最后一个节点并且返回最后一个节点
        if (currentNode.next == null){
            firstNode.next = currentNode;
            return currentNode;
        }
        // 如果当前节点还有next节点，那么我先递归的去反转下一个节点
        Node<T> reverseNode = reverse(currentNode.next);
        // 反转后的节点next指针指向当前节点
        reverseNode.next = currentNode;
        // 当前节点的next指针设置为空
        currentNode.next = null;
        // 返回当前节点
        return currentNode;
    }

    // 获取链表的中间值
    public T getMid(){
        // 快指针
        Node<T> fastNode = firstNode.next;
        // 慢指针
        Node<T> slowNode = firstNode.next;
        // 快指针和快指针的下一个节点都不为空时，进入循环
        while (fastNode != null && fastNode.next != null){
            // 快指针移动两位
            fastNode = fastNode.next.next;
            // 慢指针移动一位
            slowNode = slowNode.next;
        }
        // 返回慢指针的数据
        return slowNode.data;
    }

    /**
     * 链表是否有环
     * @return true.有环，false.没有环
     */
    public boolean isCircle(){
        // 快指针
        Node<T> fastNode = firstNode.next;
        // 慢指针
        Node<T> slowNode = firstNode.next;

        // 快指针和快指针的下一个节点都不为空时，进入循环
        while (fastNode != null && fastNode.next != null){
            // 快指针移动两位
            fastNode = fastNode.next.next;
            // 慢指针移动一位
            slowNode = slowNode.next;

            // 如果有环的话，快指针和慢指针总会相遇的
            if (slowNode.equals(fastNode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 给链表设置一个环
     * @param i 环的入口下标
     * @return true.设置成功，false.设置失败
     */
    public boolean setCircle(int i){
        if (length < 1){
            return false;
        }

        // 找到i节点
        Node<T> iNode = firstNode.next;
        for (int j = 0; j < i; j++) {
            iNode = iNode.next;
        }
        // 找到最后一个节点
        Node<T> lastNode = firstNode;
        while(lastNode.next != null){
            lastNode = lastNode.next;
        }
        // 设置最后一个节点的next指针为i节点
        lastNode.next = iNode;
        return true;
    }

    /**
     * 找到有环链表的入口节点
     * @return 有环链表的入口节点
     */
    public T getEntrance(){
        // 快指针
        Node<T> fastNode = firstNode.next;
        // 慢指针
        Node<T> slowNode = firstNode.next;
        // 临时指针
        Node<T> tempNode = null;

        // 快指针和快指针的下一个节点都不为空时，进入循环
        while (fastNode != null && fastNode.next != null){
            // 快指针移动两位
            fastNode = fastNode.next.next;
            // 慢指针移动一位
            slowNode = slowNode.next;

            // 如果有环的话，快指针和慢指针总会相遇的
            if (slowNode.equals(fastNode)) {
                if (tempNode == null){
                    // 临时指针初始化为第一个节点
                    tempNode = firstNode.next;
                    continue;
                }
            }

            if (tempNode != null){
                tempNode = tempNode.next;
            }

            // 临时指针和慢指针相遇时，就是环的入口
            if (slowNode.equals(tempNode)){
                break;
            }
        }
        return tempNode.data;
    }

    @Override
    public Iterator<T> iterator() {
        return new UnidirectionLinkedIterator();
    }

    // 单向链表迭代器
    class UnidirectionLinkedIterator implements Iterator{
        private Node currentNode;

        public UnidirectionLinkedIterator(){
            // 初始化当前节点为头结点
            currentNode = firstNode;
        }

        @Override
        public boolean hasNext() {
            // 当前节点的next不等于空，就还有下一个节点
            return currentNode.next != null;
        }

        @Override
        public Object next() {
            // 当前节点 = 下一个节点
            currentNode = currentNode.next;
            // 返回下一个节点的数据
            return currentNode.data;
        }
    }
}
