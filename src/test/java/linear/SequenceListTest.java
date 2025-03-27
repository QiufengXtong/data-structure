package linear;

/**
 * @author 张晓童
 * @description 顺序表测试类
 * 顺序表的查询时间复杂度是O(1)，但是插入和删除操作的时间复杂度都是O(n)
 * @date 2025/3/24 13:08
 */
public class SequenceListTest {
    public static void main(String[] args) {
        SequenceList<String> list = new SequenceList<>(5);
        // 初始化数据：张三、李四、王五
        list.insert("张三");
        list.insert("李四");
        list.insert("王五");
        list.insert("王五");
        list.insert("王五");
        System.out.println("初始化数据：" + list);
        // 往张三后面插入一个小白
        list.insert(1,"小白");
        System.out.println("插入后的数据：" + list);
        String index2 = list.get(2);
        System.out.println("索引下标2的数据为："+index2);
        list.remove(2);
        System.out.println("删除索引下标2后的数据：" + list);
        System.out.println("小黑的索引：" + list.indexOf("小黑"));
        System.out.println("小白的索引：" + list.indexOf("小白"));
        System.out.println("列表长度："+list.size());
        System.out.println("列表是否为空："+list.isEmpty());
        list.clear();
        System.out.println("列表是否为空："+list.isEmpty());


        System.out.println("----------------------------------");
        list = new SequenceList<>(5);
        list.insert("张三");
        list.insert("李四");
        list.insert("王五");
        list.insert("赵六");
        System.out.println("扩容前：" + list);
        list.insert("小白");
        list.insert("小黑");
        list.insert("小蓝");
        list.insert("小绿");
        list.insert("小紫");
        list.insert("小黄");
        list.insert("小红");
        System.out.println("扩容后：" + list);
        list.remove(5);
        list.remove(4);
        list.remove(3);
        list.remove(2);
        list.remove(1);
        list.remove(0);
        System.out.println("缩容后：" + list);
        System.out.println("----------------------------------");
        for (String s : list) {
            System.out.println(s);
        }
    }
}
