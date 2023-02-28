package calculate;

import java.util.ArrayList;
import java.util.Scanner;


public class Answer {
    private ArrayList<Integer> answer;//表示答案的数据结构
    private int index;//索引

    public ArrayList<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<Integer> answer) {
        this.answer = answer;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    public Answer() {//构造方法
        answer = new ArrayList<Integer>();//初始化
        index = 0;
    }

    public void scanAnswerFromKeyboard(int count) {//从键盘接收用户练习的答案
        Scanner sc = new Scanner(System.in);//初始化输入流
        answer.clear();
        System.out.println("请按照题目序号依次输入答案后回车");
        for(int i = 1; i <= count; i++) {
            System.out.print("("+i+")");
            answer.add(sc.nextInt());//读取整数并保存在answer中
        }
        sc.close();
    }

    public void reset() {//重置答案
        index = 0;
    }

    //实现对答案数据集合的遍历访问
    public boolean add(int a) {
        return answer.add(a);
    }
    public boolean hasNext() {
        return index < answer.size();
    }
    public int next() {
        if(index<answer.size()) {
            return answer.get(index++);
        }
        else {
            return -1;
        }
    }

    public int get(int index) {//按照题目序号获取答案
        return answer.get(index);
    }
    public void set(int index, int x) {
        answer.set(index, x);
    }
}