package SplitwiseSystemDesign.SplitPackage;

import SplitwiseSystemDesign.User;

import java.util.*;

// Interface defining the behavior for all types of splits
public interface Split {
    /**
     - Calculates the split for the given amount among participants based on specific split details.
     -
     - @param amount        The total amount to split.
     - @param participants  The list of users participating in the split.
     - @param splitDetails  Additional details required for the specific split type.
     - @return A map where the key is the User and the value is the amount they owe.
     */
    Map<User, Double> calculateSplit(double amount, List<User> participants, Map<String, Object> splitDetails);
}
