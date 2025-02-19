package repo;

import data.Product;
import lombok.Data;

@Data

public class Repository {

    Product[] products = new Product[0];


    public Product[] findAll() {
        return getProducts();
    }


}

