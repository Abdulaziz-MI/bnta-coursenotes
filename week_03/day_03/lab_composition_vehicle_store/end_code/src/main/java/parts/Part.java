package parts;

import products.IProduct;

public final class Part implements IPart {
    private String manufacturer;
    private long partNumber;
    private IProduct baseProduct;

    public Part (String manufacturer, long partNumber, IProduct baseProduct) {
        this.manufacturer = manufacturer;
        this.partNumber = partNumber;
        this.baseProduct = baseProduct;
    }


    public String getManufacturer() {
        return this.manufacturer;
    }

    public long getPartNumber() {
        return this.partNumber;
    }

    public void setManufacturer(String man) {
        this.manufacturer = man;
    }

    public void setPartNumber(long num) {
        this.partNumber = num;
    }

    public float getPrice() {
        return this.baseProduct.getPrice();
    }

    public long getInventory() {
        return this.baseProduct.getInventory();
    }

    public void setPrice(float price) {
        this.baseProduct.setPrice(price);
    }

    public void setInventory(long amount) {
        this.baseProduct.setInventory(amount);
    }

    public void setTitle(String title) {
        this.baseProduct.setTitle(title);
    }

    public String getTitle() {
        return this.baseProduct.getTitle();
    }

    public void addInventory(long amount) {
        this.baseProduct.addInventory(amount);
    }

    public void removeInventory(long amount) throws Exception {
        this.baseProduct.removeInventory(amount);
    }
}
