import java.util.ArrayList;

/**
 * Case Study 2: Shopping Cart
 * 
 * An online shopping application maintains products selected by a customer.
 * The customer can:
 * 1. Add products.
 * 2. Remove products.
 * 3. Check whether a product is in the cart.
 * 4. Display all products.
 */
public class CaseStudy2 {

    private ArrayList<String> cart;

    public CaseStudy2() {
        cart = new ArrayList<>();
    }

    // 1. Add product to cart
    public void addProduct(String product) {
        if (product != null && !product.trim().isEmpty()) {
            cart.add(product.trim());
            System.out.println("Added to cart: \"" + product.trim() + "\"");
        } else {
            System.out.println("Invalid product name.");
        }
    }

    // 2. Remove product from cart
    public boolean removeProduct(String product) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).equalsIgnoreCase(product.trim())) {
                String removed = cart.remove(i);
                System.out.println("Removed from cart: \"" + removed + "\"");
                return true;
            }
        }
        System.out.println("Cannot remove: \"" + product + "\" was not found in the cart.");
        return false;
    }

    // 3. Check whether a product is in the cart
    public boolean isInCart(String product) {
        for (String item : cart) {
            if (item.equalsIgnoreCase(product.trim())) {
                System.out.println("Check: \"" + product + "\" is present in the cart.");
                return true;
            }
        }
        System.out.println("Check: \"" + product + "\" is NOT in the cart.");
        return false;
    }

    // 4. Display all products
    public void displayCart() {
        System.out.println("\n--- Current Shopping Cart Items ---");
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        for (int i = 0; i < cart.size(); i++) {
            System.out.println((i + 1) + ". " + cart.get(i));
        }
        System.out.println("Total items in cart: " + cart.size());
    }

    public static void main(String[] args) {
        CaseStudy2 shoppingCart = new CaseStudy2();

        System.out.println("===== CASE STUDY 2: SHOPPING CART APPLICATION =====");

        // 1. Add products
        System.out.println("\n[Action] Adding products to cart...");
        shoppingCart.addProduct("Laptop");
        shoppingCart.addProduct("Wireless Mouse");
        shoppingCart.addProduct("Noise-Cancelling Headphones");
        shoppingCart.addProduct("USB-C Hub");

        // 4. Display all products
        shoppingCart.displayCart();

        // 3. Check if products are in the cart
        System.out.println("\n[Action] Checking product existence in cart...");
        shoppingCart.isInCart("Wireless Mouse");
        shoppingCart.isInCart("Smartwatch");

        // 2. Remove products
        System.out.println("\n[Action] Removing products from cart...");
        shoppingCart.removeProduct("Wireless Mouse");
        shoppingCart.removeProduct("Mechanical Keyboard"); // Not present

        // Display updated cart
        shoppingCart.displayCart();
    }
}
