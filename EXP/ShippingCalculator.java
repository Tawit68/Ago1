/**
 * Shipping Cost Calculator
 * Calculates shipping cost based on weight, service type, and membership status
 */
public class ShippingCalculator {
    
    // Service type constants
    public static final String STANDARD = "standard";
    public static final String PREMIUM = "premium";
    
    /**
     * Calculate shipping cost based on given parameters
     * 
     * @param weight Package weight in kilograms
     * @param serviceType "standard" or "premium"
     * @param isExpress true if express service is selected
     * @param isVIP true if customer is VIP member
     * @return Total shipping cost in baht
     */
    public static double calculateShippingCost(double weight, String serviceType, 
                                               boolean isExpress, boolean isVIP) {
        double baseCost = 0;
        
        // Validate weight
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be greater than 0");
        }
        
        // Determine base cost based on service type and weight
        if (serviceType.equalsIgnoreCase(STANDARD)) {
            if (weight <= 1) {
                baseCost = 40;
            } else if (weight <= 3) {
                baseCost = 60;
            } else if (weight <= 5) {
                baseCost = 80;
            } else {
                baseCost = 100;
            }
        } else if (serviceType.equalsIgnoreCase(PREMIUM)) {
            if (weight <= 1) {
                baseCost = 60;
            } else if (weight <= 3) {
                baseCost = 90;
            } else if (weight <= 5) {
                baseCost = 120;
            } else {
                baseCost = 150;
            }
        } else {
            throw new IllegalArgumentException("Service type must be 'standard' or 'premium'");
        }
        
        // Add express service fee if selected
        if (isExpress) {
            baseCost += 30;
        }
        
        // Apply VIP discount if applicable (20% off)
        if (isVIP) {
            baseCost *= 0.8;
        }
        
        return baseCost;
    }
    
    /**
     * Get base cost description based on weight and service type
     */
    public static String getWeightBracket(double weight) {
        if (weight <= 1) {
            return "0-1 kg";
        } else if (weight <= 3) {
            return ">1-3 kg";
        } else if (weight <= 5) {
            return ">3-5 kg";
        } else {
            return ">5 kg";
        }
    }
}
