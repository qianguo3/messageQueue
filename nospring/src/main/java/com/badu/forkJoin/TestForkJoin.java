package com.badu.forkJoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class TestForkJoin {

    static  Integer sum=0;
    static  class  MyForkJoinTast extends RecursiveTask<Integer> {

        private Integer start;
        private Integer end;
        private Integer MAX;
        private List<Integer> list;

        public MyForkJoinTast(Integer MAX,Integer start, Integer end,  List<Integer> list) {
            this.start = start;
            this.end = end;
            this.MAX = MAX;
            this.list = list;
        }

        @Override
        protected Integer compute() {
            if ((end-start)<MAX){
                System.out.println("开始计算：start:="+start+",end:"+end);
                for (Integer i = start; i <= end; i++) {
                    Integer integer = list.get(i);
                    sum = sum+integer;
                }
                return 0;
            }
            else {
                MyForkJoinTast tast1= new MyForkJoinTast(MAX,start, (start + end) / 2,list);
                tast1.fork();
                MyForkJoinTast tast2 = new MyForkJoinTast(MAX,(start + end) / 2+1, end,list);
                tast2.fork();
                return tast1.join()+tast2.join();

            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println(i);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors()+1);
        ForkJoinTask<Integer> submit = forkJoinPool.submit(new MyForkJoinTast(2,0,list.size()-1, list));
        Integer integer = submit.get();

        System.out.println("sum:"+sum+"");
        System.out.println("result:"+integer);

    }

}
