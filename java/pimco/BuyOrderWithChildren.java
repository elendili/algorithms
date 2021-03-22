package pimco;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.function.Function;

public class BuyOrderWithChildren {

    static class BuyOrder {
        private final String security;
        private final int amount;
        private final List<BuyOrder> children = new ArrayList<>();

        public BuyOrder(final String security, final int amount) {
            this.security = security;
            this.amount = amount;
        }

        public String getSecurity() {
            return security;
        }

        public int getAmount() {
            return amount;
        }

        public List<BuyOrder> getChildren() {
            return children;
        }

        public void addChild(final BuyOrder child) {
            if (child != null) {
                children.add(child);
            }
        }
    }

    public class BuyOrderTree {
        private final BuyOrder root;  // order 5 in the example above

        public BuyOrderTree(final BuyOrder root) {
            this.root = root;
        }

        // implement here visiting and applying of function
        public void visitAllOrders(final Function<BuyOrder, Void> orderVisitFunc) {
            Deque<BuyOrder> q = new ArrayDeque<>();
            q.add(root);
            while (!q.isEmpty()) {
                BuyOrder bo = q.poll();
                orderVisitFunc.apply(bo);
                q.addAll(bo.getChildren());
            }
        }
    }

}
