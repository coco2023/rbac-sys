import {ProductInfo} from "./ProductInfo";

export class ProductInOrder {
    productId: string | undefined;
    productName: string | undefined;
    productPrice: number | undefined;
    productStock: number | undefined;
    productDescription: string | undefined;
    productIcon: string | undefined;
    categoryType: number | undefined;
    count: number | undefined;

    constructor(productInfo:ProductInfo, quantity = 1){
        this.productId = productInfo.productId;
        this.productName = productInfo.productName;
        this.productPrice = productInfo.productPrice;
        this.productStock = productInfo.productStock;
        this.productDescription = productInfo.productDescription;;
        this.productIcon = productInfo.productIcon;
        this.categoryType = productInfo.categoryType;
        this.count = quantity;
    }

}
