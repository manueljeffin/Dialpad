import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Lottery {

    private static final int NEEDED_SLOT_COUNT = 7;

    private static final int MAX_DIGIT_COUNT_IN_SLOT = 2;

    private static final int MAX_DIGIT_VALUE = 59;


    /**
     * Method which parses the string in a DFS manner thereby scanning all combination of a number(1 & 2 digits), and forming with those which matches the criteria ( in this case, [unique, inorder, using all nos])
     * @param lotteryNumber given Lottery number
     * @param formattedLotteryNumber lottery number obtained till now
     * @param formattedLotteryNumberList final list which will have the formatted lotteryNumber
     * @param set To take care of duplicate case at each level
     * @param slotCount Total slots formed till now
     * @param slotPointer Pointer to the current slot's index
     */
    private static void getFormattedLotteryNumber(String lotteryNumber, String formattedLotteryNumber,
                                                  List<String> formattedLotteryNumberList, HashSet<String> set,
                                                  int slotCount, int slotPointer) {
        if (slotCount > NEEDED_SLOT_COUNT) {
            return;
        }
        if (NEEDED_SLOT_COUNT == slotCount && formattedLotteryNumber.length() == (lotteryNumber.length() + 6)) {
            formattedLotteryNumberList.add(formattedLotteryNumber);
        }

        for (int digitCount = 1; digitCount <= MAX_DIGIT_COUNT_IN_SLOT; digitCount++) {
            if (slotPointer + digitCount > lotteryNumber.length()) {
                break;
            }
            String currentNumber = lotteryNumber.substring(slotPointer, slotPointer + digitCount);
            if (set.contains(currentNumber) || Integer.parseInt(currentNumber) > MAX_DIGIT_VALUE) {
                continue;
            } else {
                set.add(currentNumber);
                String formedLotteryNumber = formattedLotteryNumber + currentNumber + ((slotCount == NEEDED_SLOT_COUNT - 1)? "" : " ");
                getFormattedLotteryNumber(lotteryNumber, formedLotteryNumber, formattedLotteryNumberList, set, slotCount + 1, slotPointer + digitCount);
                set.remove(currentNumber);
            }
        }
    }

    private static List<String> getLotteryNumbersDriver(String lotteryNumber) {
        List<String> formattedLotteryNumberList = new ArrayList<>();
        if (lotteryNumber.isEmpty()) {
            return formattedLotteryNumberList;
        }
        getFormattedLotteryNumber(lotteryNumber, "", formattedLotteryNumberList,
                new HashSet<String>(), 0, 0);
        return formattedLotteryNumberList;
    }

    public static void main(String[] args) {
        String lotteryNumber;
        lotteryNumber = "4938532894754";
        System.out.println(getLotteryNumbersDriver(lotteryNumber));

        lotteryNumber = "1234567";
        System.out.println(getLotteryNumbersDriver(lotteryNumber));

    }


}
