import {ProductInOrder} from "./ProductInOrder";

export class ProductInfo {
    productId: string | undefined;
    productName: string | undefined;
    productPrice: number | undefined;
    productStock: number | undefined;
    productDescription: string | undefined;
    productIcon: string | undefined;
    productStatus: number | undefined; // 0: onsale 1: offsale
    categoryType: number | undefined;
    createTime: string | undefined;
    updateTime: string | undefined;

    constructor(productInOrder?: ProductInOrder) {
        if (productInOrder) {
            this.productId = productInOrder.productId;
            this.productName = productInOrder.productName;
            this.productPrice = productInOrder.productPrice;
            this.productStock = productInOrder.productStock;
            this.productDescription = productInOrder.productDescription;
            this.productIcon = productInOrder.productIcon;
            this.categoryType = productInOrder.categoryType;
            this.productStatus = 0;
        } else {
            this.productId = '';
            this.productName = '';
            this.productPrice = 20;
            this.productStock = 100;
            this.productDescription = '';
            this.productIcon = '';
            this.categoryType = 0;
            this.productStatus = 0;
        }
    }
}