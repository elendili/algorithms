package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class DesignAnATMMachine {

    class ATM {

        private final int[] banknoteValues = new int[]{20, 50, 100, 200, 500};
        private final int[] banknotesCount = new int[5];

        public ATM() {

        }

        //  Deposits new banknotes in the order $20, $50, $100, $200, and $500.
        public void deposit(int[] banknotesCount) {
            for (int i = 0; i < banknotesCount.length; i++) {
                this.banknotesCount[i] += banknotesCount[i];
            }
        }

        // Returns an array of length 5 of the number of banknotes
        // that will be handed to the user in the order $20, $50, $100, $200, and $500,
        // and update the number of banknotes in the ATM after withdrawing.
        // Returns [-1] if it is not possible (do not withdraw any banknotes in this case).
        public int[] withdraw(final int amount) {
            int remainder = amount;
            int[] out = new int[5];
            for (int i = banknoteValues.length - 1; i > -1; i--) {
                int depositedBanknotesCount = banknotesCount[i];
                if (depositedBanknotesCount > 0) {
                    int banknoteValue = banknoteValues[i];
                    if (remainder >= banknoteValue) {
                        int banknoteCountToWithdraw = Math.min(remainder / banknoteValue, depositedBanknotesCount);
                        int banknoteNotionalToWithdraw = banknoteCountToWithdraw * banknoteValue;
                        remainder = remainder - banknoteNotionalToWithdraw;
                        out[i] = banknoteCountToWithdraw;
                    }
                }
            }
            assert remainder >= 0 : "remainder can't be negative";
            if (remainder != 0) {
                return new int[]{-1};
            } else {
                for (int i = 0; i < banknotesCount.length; i++) {
                    this.banknotesCount[i] -= out[i];
                }
            }

            return out;
        }
    }


    @Test
    public void test() {
        ATM atm = new ATM();
        atm.deposit(new int[]{0, 0, 1, 2, 1}); // 500+2*200+100=1000
        Assertions.assertEquals("[0, 0, 1, 0, 1]", Arrays.toString(atm.withdraw(600)));
        atm.deposit(new int[]{0, 1, 0, 1, 1}); // 500+200+50=7500
        Assertions.assertEquals("[-1]", Arrays.toString(atm.withdraw(600)));
        Assertions.assertEquals("[0, 1, 0, 0, 1]", Arrays.toString(atm.withdraw(550)));
    }

}
