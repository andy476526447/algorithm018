学习笔记
一、二叉树的先序、中序、后序遍历
  1、递归模板
    (1)先序
	   public void preorder(TreeNode root) {
	      if (root == null) {
		      return;
		  }
		  res.add(root.val);
		  preorder(root.left);
		  preorder(root.right);
	   }
	(2)中序
	   public void inorder(TreeNode root) {
	      if (root == null) {
		     return;
		  }
		  inorder(root.left);
		  res.add(root.val);
		  inorder(root.right);
	   }
	(3)后序
	    public void postorder(TreeNode root) {
		    if (root == null) {
			   return;
			}
			postorder(root.left);
			postorder(root.right);
			res.add(root.val);
		}
  2、迭代模板:显式使用栈
    (1)先序 //也可以使用后文的DFS实现
	 public void preorder(TreeNode root) {
	      Deque<TreeNode> stack = new ArrayDeque<>();
		  while(root !=null || !stack.isEmpty()) {
		     while(root != null) {
			    stack.push(root);
				res.add(root.val);//保存结果
				root = root.left;
			 }
			 root = stack.pop();
			 root = root.right;
		  }
	 }
	(2)中序
	public void inorder(TreeNode root) {
	    Deque<TreeNode> stack = new ArrayDeque<>();
		while(root != null || !stack.isEmpty()) {
		   while(root != null) {
		      stack.push(root);
			  root = root.left;
		   }
		   root = stack.pop();
		   res.add(root.val);//保存结果
		   root = root.right;
		}
	}
	(3)后序//先序是根——左——右，而后序是左-右-根，可以将先序改成根-右-左，然后将结果反转。如下代码对比先序的代码：
	public void postorder(TreeNode root) {
	     Deque<TreeNode> stack = new ArrayDeque<>();
		 while(root != null || !stack.isEmpty()) {
		     while(root != null) {
			    stack.push(root);
				res.add(root.val);//保存结果
				root = root.right;//注意这里和先序、中序的差别
			 }
			 root = stack.pop();
			 root = root.left();
		 }
		 Collections.reverse(res);//将前面的结果反转
	}
	  当然，上面这种方法比较间接，借助于先序遍历的理解。下面采用一种直接的迭代方式：
	public void postorder(TreeNode root) {
	      Deque<TreeNode> stack = new ArrayDeque<>();
		  TreeNode preNode = new TreeNode();//该节点用于保存前一个出栈的节点
		  while (root != null || !stack.isEmpty()) {
		      //将当前节点的左子树节点一次入栈
		      while (root != null) {
			     stack.push(root);
				 root = root.left;
			  }
			  root = stack.peek();
			  //当前节点没有右孩子了，或者其右孩子已经出栈了，则当前节点也出栈
			  if (root.right == null || root.right == preNode) {
			      root = stack.pop();
                  res.add(root.val);//保存结果
                  preNode = root; //记录本次刚输出的节点
                  root = null;
			  } else {
			    //当前节点还有右孩子，且其右孩子还没有出栈，则先处理其右孩子
				root = root.right;
			  }
		  }
	}

二、N叉树的先序与后序遍历
  1、递归模板
    (1)先序
    public void preorder(TreeNode root) {
	    if(root == null) {
		   return;
		}
		res.add(root.val);//保存结果
		if (root.children != null) {
	      for (int i= 0; i < root.children.size(); i++) {
		    dfs(root.children.get(i));
		  }
	   }
	}
    (2)后序
    public void postorder(TreeNode root) {
	    if (root == null) {
		    return;
		}
		for(int i = 0; i < root.children.size(); i++) {
		   postorder(root.children.get(i));
		}
		res.add(root.val);//保存结果
	 }
   2、迭代模板
	(1)先序 //即下文中DFS的实现
    public void preorder(Node root) {
        Deque<Node> stack = new ArrayDeque<>(); //BFS使用队列，这里使用栈
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            res.add(root.val);//保存结果
            int childCount = root.children == null ? 0 : root.children.size();
            //这里需要注意孩子节点入栈的顺序
			for (int i = childCount - 1; i >= 0; i--) {
                stack.push(root.children.get(i));
            }
        }
    }
    (2)后序 //先序是根——左——右，而后序是左-右-根，可以将先序改成根-右-左，然后将结果反转。如下代码对比先序的代码：
	public void postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            result.add(root.val);//保存结果
            int childCount = root.children == null ? 0 : root.children.size();
			//入栈的顺序正好和先序遍历相反,注意这里不要使用addAll来添加，否则入栈的顺序会是反的
            for (int i = 0; i < childCount; i++) {
                stack.push(root.children.get(i));
            }
        }
		//将结果反转
        Collections.reverse(result);
        for (Integer i:result) {
            System.out.print(i);
        }
    }
	目前还没有找到类似二叉树直接后序遍历的方法

三、树的BFS(广度优先搜索)和DFS(深度优先搜索)实现模板
  1、递归实现
    (1)BFS
	   一般来说不能使用递归来实现BFS，因为BFS使用的时队列实现，而递归使用的是栈实现。
	(2)DFS
	  普通的N叉树的DFS包括先序遍历和后序遍历，它们的递归实现和前文一致。如果是二叉树，还有中序遍历，递归实现和前文一致。
	  
  2、迭代实现
    (1)BFS //即按层次来遍历
	public void bfs(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            res.add(root.val);//保存结果
            if (root.children != null) {
                queue.addAll(root.children);//这里的addAll后，root的children会从左往右依次入队。这一点Deque实现的栈正好相反，这一点需要注意
            }
        }
    }
    (2)DFS 
	 先序遍历、中序遍历(二叉树)和后序遍历的迭代方式实现和前文一致。

四、图的BFS和DFS遍历模板
    树是图的一种特殊情况，相比于树而言图中可能出现环，所以在遍历的时候可能重复访问。
	所以树的BFS和DFS实现需要在树的基础上维护一个Set来避免访问重复的节点即可。
  1、BFS
    public void graphyBfs(Node root) {
        if (root == null) {
            return;
        }
        Set<Node> visitedSet = new HashSet<>(); //维护一个set，保存已经访问过的节点
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            visitedSet.add(root);//保存每个已经输出的节点
            res.add(root.val);//保存结果
            if (root.children == null) {
                return;
            }
            for (int i = 0; i < root.children.size(); i++) {
                Node tmpNode = root.children.get(i);
                if (!visitedSet.contains(tmpNode)) {//已经输出过的节点，则不用再加入
                    queue.offer(tmpNode);
                }
            }
        }
    }
  2、DFS
    迭代方式、递归方式，都维护一个set来保存已经访问过的节点，在输出节点时保存到set中，在添加节点时添加去重操作即可。

五、HashMap总结
  1、底层数据结构
    HashMap在jdk1.7及之前的版本中，由数组+链表的结构实现，从jdk1.8开始，由数组+链表+红黑树的结构实现，这里在jdk1.8的基础上探讨HashMap。
	源码中维护了一个数组：
	transient Node<K,V>[] table;
	static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;
	}
	这个数组存储的Node，就包含了我们put时的K与V，K的hash值，以及指向下一个节点的指针next。数组中查询节点的时间复杂度是O(1)，但是插入、删除的时间
	复杂度是O(n)，所以执行插入和删除操作比较耗时。HashMap中加入链表结构来解决这个问题。我们知道，解决hash冲突的一般方法有：开发地址法、二次hash法、
	拉链法等，这里采用的就是拉链法，也就是这里的数组+链表结构了。查找元素时，最好的情况是就在数组中，时间复杂度为O(1)，最坏的情况是在链表的末尾，
	时间复杂度是O(n)(当然，由于HashMap的扩容机制和良好的hash算法，hash冲突发生得比较少)；插入和删除的时间复杂度就变成了O(1)了。
	jdk1.8加入了红黑树，当链表的长度达到8的时候就会由链表升维为红黑树，当红黑树减少到6时又由红黑树降到链表。这里需要补充一点的是，红黑树的节点占用
	的空间比链表要大，维护红黑树的空间成本比较大，但操作方便；而链表正好相反，所以这里的8和6是一个平衡的值。在链表转为红黑树时，还会判断当前的Entry
	的数量是否小于64，小于64时会扩容，减少hash冲突，生成红黑树的可能性就小了很多。可见，只有当数量比较多时，维护红黑树的效率才比较明显。
	红黑树的节点如下，实际上也Node的子类：
	static final class TreeNode<K,V> extends LinkedHashMap.LinkedHashMapEntry<K,V> {
        TreeNode<K,V> parent;  // red-black tree links
        TreeNode<K,V> left;
        TreeNode<K,V> right;
        TreeNode<K,V> prev;    // needed to unlink next upon deletion
        boolean red;
	}
	
  2、构造函数的选择
    HashMap提供了4个构造函数，实际工作中可能会用到下面3个：
    public HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
    }
	public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }
	public HashMap(Map<? extends K, ? extends V> m) {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        putMapEntries(m, false);
    }
	这三个构造函数都使用了默认的扩容因子，
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
	其值为0.75，当HashMap当前使用率达到整个容量(capacity)的75%时就会扩容。第一个构造函数使用得最频繁，会分配默认大小的容量：
	static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
	第二个构造函数会指定初始容量，指定容量后通过计算，会分配比该初始值大的最近的2的n次方大小的容量，比如传入的initialCapacity为12，
	实际上会分配16的容量，最大能分配的容量为；
	static final int MAXIMUM_CAPACITY = 1 << 30;
	第三个可以用于复制指定的HashMap。
	由于扩容需要执行不少操作，所以肯定是会占用一些资源的，如果平时开发比较明确需要使用多少容量，最好使用第二个，可以避免频繁扩容影响性能。
	
  3、元素的插入
     插入元素的方法是put(K,V),其基本步骤是：
	（1）根据Key算出hash值，(n-1)&hash来确定其在数组中的index(这里的n表示数组的长度)
	（2）如果数组的这个index位置为空，则直接插入，时间复杂度是O(1)，如果达到扩容条件还会扩容。
	（3）如果数组的这个index已经有值了，那就依次遍历，比价Key来判断是否已经存在，存在就修改该节点的Value，不存在就新建节点并插在链尾。
	     如果链表长度达到了8，此时会升维形成红黑树。如果还在链表阶段，时间复杂度是O(1)+O(k)，这里O(1)是插入，O(k)是遍历，由于不会超过8，
		 所以也可以认为是O(1)。在形成红黑树时，还会判断容量是否小于64，如果是，会扩容。
    （4）在第3步中，可能插入前已经是红黑树了，那就在红黑树中先查找是否存在，存在则修改，不存在则新建并插入。这样，时间复杂度是O(l)+O(logK)。
	 所以综合来看，可以理解为插入一个元素时时间复杂度最好是O(1),最坏是O(logn)
  
  4、获取元素
     获取元素的方法是get(K),基本步骤是：
	（1）根据Key的hash值确定其在数组中的index。
	（2）先判断数组的这个地方是否有节点，没有则返回null。
	（3）如果有，则根据hash和Key判断第一个节点是否为目标节点，是则返回其Value。否则继续判断，根据第一个节点是TreeNode实例来判断当前是链表还是红黑树。
	     同样根据hash值和Key来确定是否存在，存在则返回Value，否则返回null。
     所以时间复杂度也和插入时类似，最好时是O(1),最坏时是O(logn)。
	
  5、删除元素
     删除元素的方法是remove(K),先和获取元素一样查找该节点，删除，然后调整结构。

  6、Key为null时的处理
     HashMap的K和V均可以为null，当Key为null时有，其hash值定为0；
	 public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
     }
	 static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
     }
  
  7、做算法题时常用的方法
    Map<Object, Object> map = new HashMap<>();
    map.put(K,V); //存取KV对
	map.get(K); //如果不存在，则返回null
	map.getOrDefault(K,defaultValue); //相比get方法，会得到设定的默认值defaultValue。该方法很有用
	map.entrySet(); //获取所有KV对的实体Set，其元素类型为Map.Entry<K, V>。HashMap中的Node，TreeNode都是其子类。
	map.keySet(); //获取Key的集合Set
	map.values(); //获取value的集合Collection,区别于Set
	map.containsKey(K); //判断是否包含指定Key的Entry
    map.containsValue(V); //判断是否包含指定Value的Entry
	map.remove(K); //删除指定Key的Entry
	map.putAll(otherMap); //复制给定的map
	map.size(); //Entry的数量
    map.clear(); //清除所有Entry
    map.isEmpty(); //判断是否为空
	