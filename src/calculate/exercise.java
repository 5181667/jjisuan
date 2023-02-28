package calculate;

import java.util.ArrayList;
import java.util.Random;

public class exercise {


    public class Exercise {
        private int count = 50;
        private int index = 0;//当前访问的算式序号
        private ArrayList<jisuanti> exercise = null;//习题数据结构：采用封装集合类的方法

        public ArrayList<jisuanti> getExercise() {
            return exercise;
        }

        public void setExercise(ArrayList<jisuanti> exercise) {
            this.exercise = exercise;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }


        public Exercise () {//习题类构造方法
            index = 0;
            exercise = new ArrayList<jisuanti>();
        }

        public Exercise(int count) {//习题类构造方法
            this.setCount(count);//可构造任意数量的算术习题
            index = 0;
            exercise = new ArrayList<jisuanti>();
        }

        public boolean occursIn(jisuanti e) {
            boolean b = false;
            for(jisuanti equation: exercise) {
                if(equation.isEqual(e)) {
                    b = true;
                    break;
                }
            }
            return b;
        }

        public void generateExercise() {//生成随机算式
            int i = 0;
            Random r = new Random();
            while(i<count) {
                jisuanti e = null;
                int ff=r.nextInt(4);
                if(ff==0) {//随机对象生成随机数
                    e = new add();//1代表加法
                }
                else if (ff==1){
                    e = new jian();//0代表减法
                }else if(ff==2){
                    e=new cheng();
                }
                else {
                    e=new chu();
                }
                if(!occursIn(e)) {
                    exercise.add(e);
                    i++;
                }
            }
        }

        public void generateAddExercise() {
            int i = 0;
            while(i<count) {
                jisuanti e = new add();
                if(!occursIn(e)) {
                    exercise.add(e);
                    i++;
                }
            }
        }

        public void generateSubExercise() {
            int i = 0;
            while(i<count) {
                jisuanti e = new jian();
                if(!occursIn(e)) {
                    exercise.add(e);
                    i++;
                }
            }
        }

        public void printExercise() {//输出算式
            int i = 0;
            for(jisuanti e:exercise) {
                i++;
                System.out.println("("+i+")"+e.toString3());
                if(i%5 == 0) {
                    System.out.println("\t");
                }
            }
        }

        public boolean hasNext() {//遍历算式
            return index < exercise.size();
        }
        public jisuanti next() {//获取下一个算式
            if(index < exercise.size()) {
                return exercise.get(index++);
            }
            else {
                return null;
            }
        }

        public boolean add(jisuanti e) {//在习题集中加入算式
            if(index < count) {
                exercise.add(e);
                index++;
                return true;
            }
            else {
                return false;
            }
        }

        public int size() {//获取习题数量
            return exercise.size();
        }

        public jisuanti get(int index) {
            if(index < size()) {
                return exercise.get(index);
            }
            else {
                return null;
            }
        }

    }

}
