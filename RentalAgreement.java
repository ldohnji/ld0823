import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentalAgreement {
    //variable declaration
    private Tool tool;
    private int duration;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private double dailyRentalCharge;
    private int chargeDays;
    private double prediscountCharge;
    private double discountPercent;
    private double discountAmt;
    private double finalCharge;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yy");
    private static final NumberFormat CURRENCY_FORMATTER = DecimalFormat.getCurrencyInstance();
    private static final NumberFormat PERCENT_FORMATTER = NumberFormat.getPercentInstance();

    //constructor
    public RentalAgreement(Tool tool, int duration, LocalDate checkoutDate, double discountPercent){
        this.tool = tool;
        this.duration = duration;
        this.checkoutDate = checkoutDate;
        this.discountPercent = discountPercent;

        calDueDate();
        calCharges();
    }

    //logic to calculate the due date
    private void calDueDate(){
        this.dueDate = this.checkoutDate.plusDays(duration);
    }

    //logic to calculate charges and discounts
    private void calCharges(){
        LocalDate currentDate = checkoutDate; //keeps track of current date as we iterate through duration of stay
        //loop to iterate through each day during the stay to determine if it is a chargeable day
        for (int i = 0; i < duration; i++){
            boolean isWeekend = currentDate.getDayOfWeek().getValue() >=6;
            boolean isHoliday = isIndependenceDay(currentDate) || isLaborDay(currentDate);

            if (isWeekend && tool.isWeekendRentalCharge()){
                chargeDays++;
            } else if (!isWeekend && !isHoliday && tool.isWeekdayRentalCharge()) {
                chargeDays++;
            } else if (isHoliday && tool.isHolidayRentalCharge()) {
                chargeDays++;
            }

            currentDate = currentDate.plusDays(1);
        }

        dailyRentalCharge = tool.getDailyRentalCharge();
        prediscountCharge = chargeDays * dailyRentalCharge;
        discountAmt = prediscountCharge * discountPercent;
        finalCharge = prediscountCharge - discountAmt;
    }

    //checks if it is independence day or an observed date if it falls on a weekend
    private boolean isIndependenceDay(LocalDate date){
        return (date.getMonthValue() == 7 && date.getDayOfMonth() == 4)
                || (date.getMonthValue() == 7 && date.getDayOfWeek().getValue() == 5 && date.getDayOfMonth() == 3)
                || (date.getMonthValue() == 7 && date.getDayOfWeek().getValue() == 1 && date.getDayOfMonth() == 5);
    }

    //checks if it is the first monday in Sept
    private boolean isLaborDay(LocalDate date){
        return (date.getMonthValue() == 9 && date.getDayOfWeek().getValue() == 1 && date.getDayOfMonth() <= 7);
    }

    //to help display the information in an organized manner
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Tool code: ").append(tool.getToolCode()).append("\n");
        sb.append("Tool type: ").append(tool.getToolType()).append("\n");
        sb.append("Tool brand: ").append(tool.getBrand()).append("\n");
        sb.append("Duration: ").append(duration).append("\n");
        sb.append("Checkout date: ").append(checkoutDate.format(DATE_FORMATTER)).append("\n");
        sb.append("Due date: ").append(dueDate.format(DATE_FORMATTER)).append("\n");
        sb.append("Daily rental charge: ").append(CURRENCY_FORMATTER.format(dailyRentalCharge)).append("\n");
        sb.append("Charge days: ").append(chargeDays).append("\n");
        sb.append("Pre-discount charge: ").append(CURRENCY_FORMATTER.format(prediscountCharge)).append("\n");
        sb.append("Discount percent: ").append(PERCENT_FORMATTER.format(discountPercent)).append("\n");
        sb.append("Discount amount: ").append(CURRENCY_FORMATTER.format(discountAmt)).append("\n");
        sb.append("Final charge: ").append(CURRENCY_FORMATTER.format(finalCharge)).append("\n");
        return sb.toString();
    }
}
