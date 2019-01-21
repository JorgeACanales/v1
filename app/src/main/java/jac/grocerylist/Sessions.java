package jac.grocerylist;

import android.app.Application;

public class Sessions  extends Application {

    public String product;

    public String getSesProduct() {
        return product;
    }

    public void setSesProduct(String product) {
        this.product = product;
    }


    private int idProduct;

    public int getSesidProduct() {
        return idProduct;
    }

    public void setSesidProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    private String sesBranch;

    public String getsesBranch() {
        return sesBranch;
    }

    public void setsesBranch(String sesBranch) {
        this.sesBranch = sesBranch;
    }


    private String sesPresentation;

    public String getsesPresentation() {
        return sesPresentation;
    }

    public void setsesPresentation(String sesPresentation) {
        this.sesPresentation = sesPresentation;
    }

    private Double sespresentationCount;

    public Double getsespresentationCount() {
        return sespresentationCount;
    }

    public void setsespresentationCount(Double sespresentationCount) {
        this.sespresentationCount = sespresentationCount;
    }

    private Double sesprice;

    public Double getsesprice() {
        return sesprice;
    }

    public void setsesprice(Double sesprice) {
        this.sesprice = sesprice;
    }

    private String sesButton;
    public String getSesButton() {
        return sesButton;
    }
    public void setSesButton(String sesButton) {
        this.sesButton = sesButton;
    }




}

