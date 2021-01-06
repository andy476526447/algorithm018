学习笔记

一、总览
  1、数据结构与算法总览，见附图。
     为了便于排版和后续查阅，我将数据结构的基础知识总结写到了个人博客中：https://www.cnblogs.com/andy-songwei/p/14240141.html

二、常见的数据结构
  2、数组：数组的底层硬件实现是，有一个叫内存控制器的结构，为数组分配一个段连续的内存空间，这些空间中存储着数组中对应的值（值为基本数据类型）或者地址（值为引用类型）。当根据index访问数组中的某个元素时，内存控制器直接定位到该index所在的地址，无论是第一个元素、中间元素还是最后一个元素，都能一次性定位到，时间复杂度为O(1)。
     Java中ArrayList是对数组的一个典型使用，其内部维护着一个数组，ArrayList的增、删、查等，都是对其中数组进行操作。所以根据index进行查找时比较快，时间复杂度为O(1)；但增加和删除元素时需要扩容或者移动数组元素的位置等操作，其中扩容时还会开辟更大容量的数组，将原数组的值复制到新数组中，并将新数组复制给原数组，所以此时时间复杂度和空间复杂度为O(n)。对于频繁查找数据时，使用ArrayList效率比较高。
     ArrayList源码：http://developer.classpath.org/doc/java/util/ArrayList-source.html

  3、链表：可以通过使用双向链表或者设置头尾指针的方式，让操作链表更加方便。
    Java中LinkedList是对链表的一个典型使用，其内部维护着一个双向链表，对数据的增，删、查、改操作实际上都是对链表的操作。增、删、改非首位节点本身操作时间复杂度为O(1)，但是要查找到对应操作的位置，实际上也要经过遍历查找，而链表的时间复杂度为O(n)。
    LinkedList源码：http://developer.classpath.org/doc/java/util/LinkedList-source.html
 
  4、跳表：跳表是在一个有序链表的基础上升维，添加多级索引，以空间换时间，其空间复杂度为O(n)，用于存储索引节点。其有序性对标的是平衡二叉树，二叉搜索树等数据结构。

  5、数组、链表、跳表对增、删、查时间复杂度比较：

           	         数组	链表	  跳表
          preppend   O(n)	O(1)	O(logn)
          append	 O(1)	O(1)	O(logn)
          lookup	 O(1)	O(n)	O(logn)
          insert	 O(n)	O(1)	O(logn)
          delete	 O(n)	O(1)	O(logn)
		  

三、刷Leetcode等算法题时一些很实用的辅助方法锦集
  1、使用Arrays.sort(int[] a)进行排序
       底层采用的是快速排序算法实现的：时间复杂度为O(nlogn)，空间复杂度O(logn)，不稳定。默认是从小到大排序。
	int[] arr = new int[]{2, 9, 6, 8, 4, 3};
	Arrays.sort(arr);
	System.out.println(Arrays.toString(arr));
	打印结果：
	[2, 3, 4, 6, 8, 9]
	参数可以是其它基本数据类型，也可以是自定义类型，可以通过使用Comparator比较器来自定义排序顺序。
	例如，降序排列：
	Integer[] arr = new Integer[]{2, 9, 6, 8, 4, 3};
	Arrays.sort(arr, new Comparator<Integer>() {
		@Override
		public int compare(Integer t0, Integer t1) {
			return t1 - t0;
		}
	});
	System.out.println(Arrays.toString(arr));
	打印结果：
	[9, 8, 6, 4, 3, 2]
 
  2、使用Arrays.toString(int[] arr)将数组组合成字符串
     内部是通过StringBuilder + for 循环来实现的，该方法可以方便调试查看数组的值，而无需自己手动来组装字符串。
	int[] arr = new int[]{2, 9, 6, 8, 4, 3};
	System.out.println(Arrays.toString(arr));
	打印结果：
	[2, 9, 6, 8, 4, 3]
	同样，该方法的参数数组类型可以是其它数据类型。

  3、使用构造函数或者addAll将一个集合中的内容添加到新的ArrayList中
     //构造函数
     public ArrayList(Collection<? extends E> c) 
     //addAll方法
     public boolean addAll(Collection<? extends E> c) 
     内部实现原理：ArrayList内部维护了一个数组，无论是使用构造函数还是addAll方法，都是将给定集合中的元素复制到该数组中。
    
     List<Integer> list = new ArrayList<>();
     list.add(1);
     list.add(2);
     list.add(3);
     List<Integer> newList1 = new ArrayList<>(list);
     System.out.println(newList1);
     List<Integer> newList2 = new ArrayList<Integer>();
     newList2.addAll(list);
     System.out.println(newList2);
     打印结果：
     [1, 2, 3]
     [1, 2, 3]

  4、ArrayList与数组互相转化
    (1)使用list.toArray()将ArrayList转为数组
         List<Integer> list = new ArrayList<>();
         list.add(1);
         list.add(2);
         list.add(3);
         Object[] a = list.toArray();
         System.out.println(Arrays.toString(a));
      打印结果：
       [1, 2, 3]
    (2)使用Arrays.asList(数组)将数组转为ArrayList
         Integer[] arr = new Integer[]{2, 9, 6, 8, 4, 3};
         List<Integer> arrList = Arrays.asList(arr);
         System.out.println("size=" + arrList.size() + ";arrList=" + arrList);
      打印结果：
         size=6;arrList=[2, 9, 6, 8, 4, 3]
 
  5、使用Collections.reverse(list) 倒转ArrayList中的元素
      List<Integer> list = new ArrayList<>();
      list.add(1);
      list.add(2);
      list.add(3);
      Collections.reverse(list);
      System.out.println(list);
      打印结果：
      [3, 2, 1]
 

  6、按照多个空格，对字符串进行分割：split(“\\s+”)或者split(" +")
    例如：
     String s = "leetcode    is     very     good!";
     String[] arr = s.split("\\s+"); //或者s.split(" +");
     System.out.println(Arrays.toString(arr));
     打印结果：
     [leetcode, is, very, good!]

  7、去掉字符串首尾空字符串：trim()
     String s = "  ab cd   ";
     Sytem.out.println(s.trim());
     打印结果：
     ab cd
	  
  8、拼接字符串：join()
     String[] strArray = {"ab","cd","ef"};
     System.out.println(String.join(" ",strArray));
    打印结果：
    ab cd ef
    这里将数组组合成字符串，用“ ”连接。join的第一个参数可以替换其它的连接符号，第二个参数除了使用数组外，还可以使用LIst，Deque等集合对象

 