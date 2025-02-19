package manager;

import data.Product;
import exception.NotFoundException;
import lombok.Data;
import repo.Repository;

@Data

public class Manager {

    private Repository repo = new Repository();
    public Product[] products = new Product[0];

    public void addNew(Product product) {
        //создаем новый массив на один элемант больше
        Product[] add = new Product[products.length + 1];
        //Копируем все элеманты из старого массива в новый
        for (int i = 0; i < products.length; i++) {
            add[i] = products[i];
        }
        //добавляем новый продект в конец массива
        add[add.length - 1] = product;
        //Кладем значение add в массив products
        products = add;
    }

    public Product[] searchProduct() {
        return products;
    }

    public boolean matches(Product product, String search) {
//        if (product.getName().contains(search)) {
//            return true;
//        } else {
//            return false;
//        }
        return product.getName().contains(search);
    }


    public Product[] searchByMatches(String text) {
        //Здесь храним подошедшие запросу продукты
        Product[] result = new Product[0];
        for (Product product : products) {
            if (matches(product, text)) {
                Product[] tmp = new Product[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                //добавляем новый продект в конец массива
                tmp[tmp.length - 1] = product;
                //Кладем значение tmp в массив result
                result = tmp;

            }
        }
        return result;
    }

    public void delete(int removeId) throws NotFoundException {

        if (findById(removeId)==null){
            throw new NotFoundException("Вы не можете удалить, так как " + removeId + " нет в списке");
        }

        // Создаём новый массив на один элемент меньше
        Product[] tmp = new Product[products.length - 1];
        // Индекс для нового массива
        int index = 0;
        // Копируем все элементы, кроме удаляемого
        for (int i = 0; i < products.length; i++) {
            if (products[i].getId() != removeId) {
                tmp[index] = products[i];
                index++;
            }
        }
        // Обновляем массив products
        products = tmp;
    }

    public Product findById(int id) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].getId()==id )
                return products[i];
        }
        return null;
    }

}
