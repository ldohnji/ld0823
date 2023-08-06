public class Tool {
    private String toolCode;
    private String toolType;
    private String brand;
    private double dailyRentalCharge;
    private boolean weekdayRentalCharge;
    private boolean weekendRentalCharge;
    private boolean holidayRentalCharge;

    public Tool(String toolCode, String toolType, String brand, double dailyRentalCharge,
                boolean weekdayRentalCharge, boolean weekendRentalCharge, boolean holidayRentalCharge){
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
        this.dailyRentalCharge = dailyRentalCharge;
        this.weekdayRentalCharge = weekdayRentalCharge;
        this.weekendRentalCharge = weekendRentalCharge;
        this.holidayRentalCharge = holidayRentalCharge;
    }

    public String getToolCode() {
        return toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public String getBrand() {
        return brand;
    }

    public double getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public boolean isWeekdayRentalCharge() {
        return weekdayRentalCharge;
    }

    public boolean isWeekendRentalCharge() {
        return weekendRentalCharge;
    }

    public boolean isHolidayRentalCharge() {
        return holidayRentalCharge;
    }
}
