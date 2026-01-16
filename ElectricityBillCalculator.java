import java.util.Scanner;

public class ElectricityBillCalculator {
    
    public static double calculateElectricityBill(int units) {
        double totalCost = 0.0;
        
        if (units <= 0) {
            return 0.0;
        }
        
        // Calculate cost based on tiers
        if (units <= 150) {
            // First tier: 0-150 units at 3.50 THB/unit
            totalCost = units * 3.50;
        } else if (units <= 400) {
            // First 150 units at 3.50 THB/unit
            totalCost = 150 * 3.50;
            // Remaining units (151-400) at 4.20 THB/unit
            totalCost += (units - 150) * 4.20;
        } else {
            // First 150 units at 3.50 THB/unit
            totalCost = 150 * 3.50;
            // Next 250 units (151-400) at 4.20 THB/unit
            totalCost += 250 * 4.20;
            // Remaining units (401+) at 5.00 THB/unit
            totalCost += (units - 400) * 5.00;
        }
        
        // Add fixed service fee
        totalCost += 50.0;
        
        return totalCost;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("กรุณาป้อนจำนวนหน่วยไฟฟ้าที่ใช้ (kWh): ");
        int units = scanner.nextInt();
        
        double billAmount = calculateElectricityBill(units);
        
        System.out.println("\n--- สรุปรายละเอียดค่าไฟฟ้า ---");
        System.out.println("จำนวนหน่วยไฟฟ้าที่ใช้: " + units + " kWh");
        System.out.printf("ค่าไฟฟ้ารวม: %.2f บาท%n", billAmount);
        
        // Show breakdown of charges
        System.out.println("\n--- รายละเอียดการคิดเงิน ---");
        if (units <= 0) {
            System.out.println("ไม่มีการใช้งานไฟฟ้า");
        } else if (units <= 150) {
            System.out.printf("หน่วยที่ 1-150: %d x 3.50 = %.2f บาท%n", units, units * 3.50);
        } else if (units <= 400) {
            System.out.printf("หน่วยที่ 1-150: 150 x 3.50 = %.2f บาท%n", 150 * 3.50);
            System.out.printf("หน่วยที่ 151-%d: %d x 4.20 = %.2f บาท%n", units, units - 150, (units - 150) * 4.20);
        } else {
            System.out.printf("หน่วยที่ 1-150: 150 x 3.50 = %.2f บาท%n", 150 * 3.50);
            System.out.printf("หน่วยที่ 151-400: 250 x 4.20 = %.2f บาท%n", 250 * 4.20);
            System.out.printf("หน่วยที่ 401-%d: %d x 5.00 = %.2f บาท%n", units, units - 400, (units - 400) * 5.00);
        }
        
        System.out.printf("ค่าบริการ (คงที่): 50.00 บาท%n");
        System.out.printf("รวมทั้งสิ้น: %.2f บาท%n", billAmount);
        
        scanner.close();
    }
}