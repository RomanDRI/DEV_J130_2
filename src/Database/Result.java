package Database;

public class Result {
    public static void main(String[] args) {
        Repository repository = new Repository();
        System.out.println("Список продуктов доступных для заказа:");
        repository.findAllProducts().forEach(System.out::println);
        System.out.println("Список продуктов в заказе по выбранному id:");
        repository.findProductsById(3).forEach(System.out::println);
        System.out.println("Регистрация нового заказа");
        OrderItem orderItem = new OrderItem(new String[]{"3251615"},new Integer[]{2});
        orderItem.getPrice();
        Order order = new Order("Валентин Валентинович", "(981)324-45-23", "er@ma.ru","ул. Есенина 56-76", new String[]{"3251617", "3251615"},new Integer[]{4,5});
        repository.saveNewOrder(order);
        System.out.println("Заказ успешно зарегистрирован.");
    }
}
