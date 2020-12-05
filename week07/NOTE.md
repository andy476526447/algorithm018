学习笔记

单词搜索II(https://leetcode-cn.com/problems/word-search-ii/) trie实现方式时间复杂度问题；
代码实现为：https://github.com/andy476526447/algorithm018/blob/master/week07/Test_212%E5%8D%95%E8%AF%8D%E6%90%9C%E7%B4%A2II.java
从实现方式可以看到，整个过程分成了两个大的步骤：
一、创建trie
    给定String[] words，将其创建为Trie的过程是遍历每个word中的字符，并针对该字符创建一个节点然后插入到Trie中。
	这样最坏的情况是，words中的所有word均没有相同的前缀，此时words中的每个字符都需要插入一次。
	这样将消耗的时间为 O(wl)，其中w为words的长度，l为所有word的平均长度。

二、在board中查找words中存在的单词
    首先需要遍历board中的每个字符，判断该字符是否存在于对那个的trie中，其时间复杂度为O(mn)，m为board的行数，n为board的列数。
	最坏的情况下是board中的每个字符都满足条件，需要进一步查看相邻的字符，这里我们仍然以最坏的情况考虑。
	第一个字符确定后，会有4个方向前进，然后后面每个字符都只能有3个方向可以选择，最后一个字符不用再选择，所以时间复杂度为O(4*3^(L-2))
	那么这个步骤中，最坏的时间复杂度为O(4mn*3^(L-2))，其中L为word字符串的长度

综合第一步和第二步的结果，总时间复杂度为：O(wl+4mn*3^(L-2))