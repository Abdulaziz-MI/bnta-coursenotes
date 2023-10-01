public class Computer {

    private int storage;
    private String printer;

    public Computer(int storage) {
        this.storage = storage;
        this.printer = "";
    }

    public int getStorage() {
        return this.storage;
    }

    public void addStorage( int extraStorage){
        this.storage += extraStorage;
    }


    public String getPrinter() {
        return this.printer;
    }

    public void setPrinter(String printer) {
        this.printer = printer;
    }

    public void printMessage(String message){
        if (!this.printer.equals("")){
            System.out.println(message);
        }
    }

}
