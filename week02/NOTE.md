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

