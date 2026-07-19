package model.AcqusitionModule;

public class AcquisitionDetail {

    private int detailId;
    private int acquisitionId;
    private int bookId;
    private int quantity;
    private double price;

    public AcquisitionDetail() {
    }

    public AcquisitionDetail(int acquisitionId, int bookId, int quantity, double price) {
        this.acquisitionId = acquisitionId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getDetailId() { return detailId; }
    public void setDetailId(int detailId) { this.detailId = detailId; }

    public int getAcquisitionId() { return acquisitionId; }
    public void setAcquisitionId(int acquisitionId) { this.acquisitionId = acquisitionId; }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}