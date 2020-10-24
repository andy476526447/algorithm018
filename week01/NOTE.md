学习笔记

作业1：用 add first 或 add last 这套新的 API 改写 Deque 的代码
public void newDeque() {
        Deque<String> deque = new LinkedList<>();

        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");

        System.out.println(deque);

        String str = deque.getFirst();
        System.out.println(str);
        System.out.println(deque);

        while (deque.size() > 0) {
            System.out.println(deque.removeFirst());
        }
        System.out.println(deque);
    }

作业2：分析 Queue 和 PriorityQueue 的源码
   (1)Queue的源码
    public interface Queue<E> extends Collection<E> {
	    //向队列中添加元素，添加失败时返回false
        boolean add(E e);
        //向队列中添加元素，添加失败时返回false
        boolean offer(E e);
        //返回并删除队首元素，删除失败时抛出异常
        E remove();
        //返回并删除队首元素，删除失败返回null
        E poll();
        //检索获取队首元素，检索失败会抛出异常
        E element();
        //检索获取队首元素，检索失败返回null
        E peek();
    }
	从源码可以看出，Queue是一个接口，所以在使用Queue时需要用到它的实现类，比较LinkedList，PriorityQueue等，上述方法具体的实现都在其实现类中完成。
   (2)PriorityQueue源码
	 java中PriorityQueue通过二叉小顶堆实现，可以用一棵完全二叉树表示，其内部是的元素根据比较器的规则进行了排序。这里我们从源码的角度来分析一下其内部原理。
	 1）Queue的实现类
	    public class PriorityQueue<E> extends AbstractQueue<E> implements java.io.Serializable ，
        public abstract class AbstractQueue<E> extends AbstractCollection<E> implements Queue<E> 
        从这段继承关系可以看出，PriorityQueue间接实现了接口Queue，所以它是Queue的一个实现类。	
        private static final int DEFAULT_INITIAL_CAPACITY = 11;
	    transient Object[] queue;
	
	    public PriorityQueue() {
           this(DEFAULT_INITIAL_CAPACITY, null);
        }
		   public PriorityQueue(int initialCapacity) {
           this(initialCapacity, null);
        }
	    public PriorityQueue(Comparator<? super E> comparator) {
            this(DEFAULT_INITIAL_CAPACITY, comparator);
        }
	    public PriorityQueue(int initialCapacity,Comparator<? super E> comparator) {
            if (initialCapacity < 1)
                throw new IllegalArgumentException();
            this.queue = new Object[initialCapacity];
            this.comparator = comparator;
        }
		PriorityQueue内部维护了以个数组，我们知道二叉数可以用数组来表示，所以这个数组用来实现二叉小丁顶堆，从而来维护一个有序的二叉树。
		上述的几个构造函数可以看出，PriorityQueue的默认容量是11，也可以自己初始定义。传入的比较器用来规定排序的原则，默认情况下采用的自然排序。
	    从这几处的源码可以看出，实现排序的基础是比较器和数组。
		public boolean add(E e) {
           return offer(e);
        }
		
	 2）检索元素peek()与element()
	    public E peek() {
            return (size == 0) ? null : (E) queue[0];
        }
		由于peek是取第一个元素，所以直接根据下标0取数组第一个元素即可。由于peek不删除元素，所以不会破坏二叉树的结构，所已时间复杂度是O(1)。
	    public E element() {
            E x = peek();
            if (x != null)
                return x;
            else
                throw new NoSuchElementException();
        }
		element()方法是在PriorityQueue的父类AbstractQueue中定义的，在检索成功的情况下，功能和peek()一致，检索失败的情况下会抛异常。
	 3）添加元素offer()与add()
		public boolean offer(E e) {
           if (e == null)
               throw new NullPointerException();
           modCount++;
           int i = size;
           if (i >= queue.length)
               grow(i + 1);
           size = i + 1;
           if (i == 0)
               queue[0] = e;
           else
               siftUp(i, e);
           return true;
        }
		首先我们可以看到，PriorityQueue中是不能添加null的，会抛异常。添加元素时，会先判断当前PriorityQueue是否满了，如果满了就会通过grow()来扩容；
		如果没有满就直接往里面插入元素。插入元素包括两种情况，第一种情况是PriorityQueue为空时，就添加在数组下标为0的地方，即：queue[0] = e;
		第二中情况是非空时，就需要调用siftUp()来调整，让整个结构按照指定规则排序。
	    private void grow(int minCapacity) {
            int oldCapacity = queue.length;
            // Double size if small; else grow by 50%
            int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                                             (oldCapacity + 2) :
                                             (oldCapacity >> 1));
            // overflow-conscious code
            if (newCapacity - MAX_ARRAY_SIZE > 0)
                newCapacity = hugeCapacity(minCapacity);
            queue = Arrays.copyOf(queue, newCapacity);
        }
		private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
	    private static int hugeCapacity(int minCapacity) {
            if (minCapacity < 0) // overflow
                throw new OutOfMemoryError();
            return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
        }
		扩容时会根据队列的规模来确定扩容一倍或者50%，如果容量超过了指定的最大值，则会扩容到系统最大整数值Integer.MAX_VALUE。
		
		public boolean add(E e) {
           return offer(e);
        }
		这里可以看出，add()方法是等同于offer()方法的。
	 4)删除元素
	    public E poll() {
            if (size == 0)
                return null;
            int s = --size;
            modCount++;
            E result = (E) queue[0];
            E x = (E) queue[s];
            queue[s] = null;
            if (s != 0)
                siftDown(0, x);
            return result;
        }
	    poll是获取并删除队首元素，也就是在peek的基础上再删除队首元素。移除掉第一个元素后，破坏了原有的树结构，就调用siftDown()方法来进行调整。
	    public E remove() {
            E x = poll();
            if (x != null)
                return x;
            else
                throw new NoSuchElementException();
        }
		remove()方法是在PriorityQueue的父类AbstractQueue中定义的，可见在删除成功时功能和poll一样，失败时会报一次。
	    PriorityQueue还提供了删除指定元素的方法
        public boolean remove(Object o) {
            int i = indexOf(o);
            if (i == -1)
                return false;
            else {
                removeAt(i);
                return true;
            }
        }
		
	    E removeAt(int i) {
            // assert i >= 0 && i < size;
            modCount++;
            int s = --size;
            if (s == i) // removed last element
                queue[i] = null;
            else {
                E moved = (E) queue[s];
                queue[s] = null;
                siftDown(i, moved);
                if (queue[i] == moved) {
                    siftUp(i, moved);
                    if (queue[i] != moved)
                        return moved;
                }
            }
            return null;
        }
		这段源码中可以看到，删除指定元素其实就是先找到该元素在数组中的下标。如果指定的是末尾元素，直接删除；否则，删除该元素然后会重新调整树结构。
	
	到这里，Queue接口中定义的6个常用方法在PriorityQueue中的实现就分析完了。