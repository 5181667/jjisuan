package calculate;
import java.util.Random;
public abstract class jisuanti {
    private double left,right,result;
    private final int MAX = 100;
    private final int MIN = 0;
    private char op;

    public double getLeft() {
        return left;
    }

    public void setLeft(double left) {
        this.left = left;
    }

    public double getRight() {
        return right;
    }

    public void setRight(double right) {
        this.right = right;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public char getOp() {
        return op;
    }

    public void setOp(char op) {
        this.op = op;
    }
    private int generateRandom(int min,int max) {//生成随机数
        Random r = new Random();
        return r.nextInt(max-min+1)+min;
    }
    private boolean isBetween(double value,int min,int max) {
        return value >=min && value <=max;
    }
    public boolean isEqual(jisuanti e) {
        boolean b = false;
        if(e.getOp() != this.getOp()) {
            b = false;
        }
        else {
            b = e.getLeft() == this.getLeft() && e.getRight() == this.getRight();
        }
        return b;
    }
    public String toString() {//将算式转换成字符串
        return ""+this.getLeft() + this.getOp() +this.getRight();
    }
    public String toString2() {
        return toString()+"=";
    }
    public String toString3() {
        return toString2()+this.getResult();
    }
    public void generateEquation(char op) {//构造算式
        do {
            left = generateRandom(MIN,MAX);
            right= generateRandom(MIN,MAX);
            result = calculate();
        }while(!isBetween(result,MIN,MAX));
        this.setOp(op);
    }

    protected abstract double calculate();
}
