package linear;

import linear.UnidirectionLinkedList;

/**
 * @author 张晓童
 * @description 单向链表测试类
 * @date 2025/3/24 15:03
 */
public class UnidirectionLinkedListTest {
    public static void main(String[] args) {
        UnidirectionLinkedList<String> list = new UnidirectionLinkedList<>();
        // 初始化数据：张三、李四、王五、赵六、小明、小红
        list.insert("张三");
        list.insert("李四");
        list.insert("王五");
        list.insert("赵六");
        list.insert("小明");
        list.insert("小红");
        list.insert("小蓝");
        System.out.print("初始化数据：");
        for (String s : list) {
            System.out.print(s+" ");
        }
        System.out.println();
        // 往张三后面插入一个小白
        list.insert(1,"小白");
        System.out.print("插入后数据：");
        for (String s : list) {
            System.out.print(s+" ");
        }
        System.out.println();
        String index2 = list.get(2);
        System.out.println("索引下标2的数据为："+index2);
        list.remove(2);
        System.out.print("删除索引下标2后的数据：");
        for (String s : list) {
            System.out.print(s+" ");
        }
        System.out.println();
        System.out.println("小黑的索引：" + list.indexOf("小黑"));
        System.out.println("小白的索引：" + list.indexOf("小白"));
        System.out.println("列表长度："+list.getLength());
        System.out.println("列表是否为空："+list.isEmpty());
        System.out.println("------------------------------------");
        System.out.print("链表反转前：");
        for (String s : list) {
            System.out.print(s+" ");
        }
        System.out.println();
        list.reverse();
        System.out.print("链表反转后：");
        for (String s : list) {
            System.out.print(s+" ");
        }
        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("链表中间值："+list.getMid());
        System.out.println("链表是否有环："+list.isCircle());
        // 设置环，环的入口是下标1
        list.setCircle(1);
        System.out.println("链表是否有环："+list.isCircle());
        System.out.println("链表环的入口："+list.getEntrance());
        System.out.println("------------------------------------");
        list.clear();
        System.out.println("列表是否为空："+list.isEmpty());
    }
}
