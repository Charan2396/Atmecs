import java.util.*;
import java.math.BigDecimal;

public class Orders {
    
    public static void possibleOrders_helper(
                        String[] menuItemName, 
                        BigDecimal[] menuItemPrice, 
                        int menuStartIndex,
                        BigDecimal totalPrice, 
                        ArrayList<String> currentResultEntry, 
                        ArrayList<ArrayList<String>> result, 
                        HashMap<BigDecimal, ArrayList<ArrayList<String>>> mem) {
        /*
        if(mem.containsKey(totalPrice)) {
            //return mem.get(totalPrice);
        }
        */
        for(int i=menuStartIndex; i<menuItemName.length; i++) {
            BigDecimal updatedTotalPrice = totalPrice.subtract(menuItemPrice[i]);
            if( updatedTotalPrice.compareTo(BigDecimal.valueOf(0)) > 0 ) {
                currentResultEntry.add(menuItemName[i]);
                possibleOrders_helper(menuItemName, menuItemPrice, i, updatedTotalPrice, currentResultEntry, result, mem);
                currentResultEntry.remove(currentResultEntry.size() - 1);
            } else if ( updatedTotalPrice.compareTo(BigDecimal.valueOf(0)) == 0 ) {
                currentResultEntry.add(menuItemName[i]);
                result.add(new ArrayList<String>(currentResultEntry));
                currentResultEntry.remove(currentResultEntry.size() - 1);
            }
        }
    }
    
    public static ArrayList possibleOrders(HashMap<String, Double> menuTemp, double totalPriceTemp) {
        String[] menuItemName = new String[menuTemp.size()];
        BigDecimal[] menuItemPrice = new BigDecimal[menuTemp.size()];
        int i = 0;
        for (Map.Entry<String, Double> menuEntry: menuTemp.entrySet()) {
            String menuEntryKey = (String)menuEntry.getKey();
            double menuEntryValue = (double)menuEntry.getValue();
            menuItemName[i] = menuEntryKey;
            menuItemPrice[i] = BigDecimal.valueOf(menuEntryValue);
            i+=1;
        }
        BigDecimal totalPrice = BigDecimal.valueOf(totalPriceTemp);
        
        ArrayList<String> currentResultEntry = new ArrayList<String>();
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        HashMap<BigDecimal, ArrayList<ArrayList<String>>> mem = new HashMap<BigDecimal, ArrayList<ArrayList<String>>>();
        
        possibleOrders_helper(menuItemName, menuItemPrice, 0, totalPrice, currentResultEntry, result, mem);
        
        return result;
    }
    
    public static void printPossibleOrdersResult(ArrayList<ArrayList<String>> result) {
        System.out.println(result);
    }
    
    public static void main(String args[])
    {
        HashMap<String, Double> menu = new HashMap<String, Double>();
        menu.put("Fruit", 2.15);
        menu.put("Fries", 2.75);
        menu.put("Salad", 3.35);
        menu.put("Wings", 3.55);
        menu.put("Mozzarella", 4.20);
        menu.put("Plate", 5.80);
        printPossibleOrdersResult(possibleOrders(menu, 15.05));
    }
}