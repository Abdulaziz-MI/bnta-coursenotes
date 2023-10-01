public class Rectangle {

    private int length;
    private int width;

    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public int calculateArea(){
        return this.length * this.width;
    }

    public boolean checkIfSquare(){
        return this.length == this.width;
    }
}
