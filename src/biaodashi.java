import java.util.Random;

public class biaodashi {
    private int left,right;
    private double result;
    private char op;

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = cal(scb());
    }

    public char getOp() {
        return op;
    }

    public void setOp(char op) {
        this.op = op;
    }
    public String scb(){
        Random r=new Random();
        setLeft(r.nextInt(99));
        setRight(r.nextInt(99));
        int f=0;
        f=r.nextInt(4);
        switch (f) {
            case 0 -> op = '+';
            case 1 -> op = '-';
            case 2 -> op = '*';
            case 3 -> op = '/';
        }
        setOp(op);
        return String.valueOf(left)+' '+op+' '+String.valueOf(right)+' '+"= ";
    }


    public double cal(String bd){
        System.out.print(bd);
        return switch (getOp()) {
            case '+' -> getLeft() + getRight();
            case '-' ->getLeft() - getRight();
            case '*' -> getLeft() * getRight();
            case '/' -> getLeft() / getRight();
            default -> getLeft() + getRight();
        };
    }

    public static void main(String[] args) {
        int n=50;
        biaodashi d=new biaodashi();

        while(n>0){
            System.out.println(d.cal(d.scb())+"\n");
            n=n-1;
        }
    }
}
