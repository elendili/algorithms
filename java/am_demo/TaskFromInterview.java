package am_demo;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskFromInterview {
    // customers
    // wish list: item can be a string
    // customer hs friend bidirectional
    // find the most popular item across all whish lists

    Collection<Customer> getAllFriends(Customer c) {
        return getAllFriends(c, new HashSet<>());
    }

    Collection<Customer> getAllFriends(Customer c, Set<Customer> customerSet) {
        Set<Customer> filteredFriends = c.getFriends().stream().filter(f -> !customerSet.contains(f)).collect(Collectors.toSet());
        customerSet.addAll(filteredFriends);
        filteredFriends.forEach(f -> getAllFriends(f, customerSet));
        return customerSet;
    }

    String getMostPopularWishAmongFriends(Customer c) {
        Collection<Customer> friends = getAllFriends(c);

        Map<String, Integer> frequencies = friends.stream().flatMap(f -> f.getWishList().stream())
                .collect(Collectors.toMap(wish -> wish, w -> 1, (v1, v2) -> v1 + 1, HashMap::new));

        String maxWish = frequencies.entrySet().stream()
                .max(comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey).get();
        return maxWish;
    }

    @Test
    public void test() {
        Customer a = new Customer("a");
        Customer b = new Customer("b").addFriends(a);
        Customer c = new Customer("c").addFriends(a, b);
        Customer d = new Customer("d").addFriends(c, a);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        assertEquals("z", getMostPopularWishAmongFriends(c));
    }
}

class Customer {
    private final String name;
    private final Random r = new Random();
    private Set<Customer> friends = new HashSet<>();

    public Customer(String name) {
        this.name = name;
    }

    public Customer addFriends(Customer... customers) {
        asList(customers).stream()
                .filter(c -> !friends.contains(c))
                .forEach(c -> {
                    friends.add(c);
                    c.addFriends(this);
                });
        return this;
    }

    boolean hasFriend(Customer c) {
        return friends.contains(c);
    }

    Set<Customer> getFriends() {
        return friends;
    }

    Collection<String> getWishList() {
        char[] letters = "asdfghjklqwertyuiopzxcvbnm".toCharArray();
        List<String> wishes = IntStream.range(0, 3)
                .mapToObj(i -> "" + letters[r.nextInt(letters.length)]).collect(toList());
        wishes.add("z");
        return wishes;
    }

    @Override
    public String toString() {
        String fs = friends.stream().map(f -> f.name).collect(Collectors.joining(","));
        return name + (friends.isEmpty() ? "" : " => [" + fs + "]");
    }

}
