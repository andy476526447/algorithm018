学习笔记

一、递归模板（java,四部曲）
public void recursion(level,param){
    //1、递归终结条件，在这里处理结果
    if (level > MAX_LEVEL) {
       return;
    }
    //2、处理当前层逻辑，比如遍历时，在这里打印或者保存当前这一层的结果
    process(level, param);
    //3、下探到下一层
    recursion(level + 1, newParam);
    //4、对当前层环境做一些清理等操作
}

二、分治算法模板
public int divideConquer(Problem problem...){
   //终止条件
   if (problem == null) {
      int result = processResult()
	  result result;
   }
   subProblems = splitProblem(problem);
   result0 = divideConquer(subProblems[0]);
   result1 = divideConquer(subProblems[1]);
   //将分治后的结果合并
   result = processResult(result0,result1);
   
   return result;
   
}