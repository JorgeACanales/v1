package jac.grocerylist;

public class Item {

    /*nombres de variables exactos a los valores retorno php*/
    private int idProduct;
    private String product;
    private String branch;
    private String presentation;
   // private double presentationCount;
   // private double price;


  //  public Item(int idProduct, String product, String branch, String presentation, double presentationCount, double price) {
  public Item( String product, String branch, String presentation, int idProduct) {

        this.idProduct = idProduct;
        this.product = product;
        this.branch = branch;
        this.presentation = presentation;
     //   this.presentationCount = presentationCount;
       // this.price = price;

    }

    //public Item(String product, String branch, String presentation, double presentationCount, double price) {
 public Item(String product, String branch, String presentation) {
        this.product = product;
        this.branch = branch;
        this.presentation = presentation;
       //    this.presentationCount = presentationCount;
       //  this.price = price;

    }


    public Item(int idProduct) {
        this.idProduct = idProduct;

    }

    public Item(String product) {
        this.product = product;

    }

    public int getidProduct() {
        return idProduct;
    }

    public String getproduct() {
        return product;
    }

    public String getbranch() {
        return branch;
    }

    public String getpresentation(){
        return presentation;
    }
/*
    public double getpresentationCount() {
        return presentationCount;
    }


    public double getprice () {
        return price ;
    }
*/


}



