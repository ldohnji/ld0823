import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalApp {
    //instance variable
    private List<Tool> tools;

    //constructor initializes the tools list
    public RentalApp(){
        tools = new ArrayList<>();
    }

    //allows you to populate the list of tools
    public void addTool(Tool tool){
        tools.add(tool);
    }

    //this method looks up corresponding tool object based on the tool code
    public RentalAgreement checkoutTool(String toolCode, int duration, LocalDate checkoutDate, double discountPercent){
        Tool tool = findTool(toolCode);
        if (tool == null){
            throw new IllegalArgumentException("The tool code entered is invalid.");
        }

        if (duration < 1){
            throw new IllegalArgumentException("Duration must be greater than 0.");
        }

        if (discountPercent < 0 || discountPercent > 100){
            throw new IllegalArgumentException("The discount percentage MUST be between 0 and 100.");
        }

        return new RentalAgreement(tool, duration, checkoutDate, discountPercent); //creates a new object based on the info provided
    }

    //loops though tools to find the corresponding tool object
    private Tool findTool(String toolCode){
        for (Tool tool : tools){
            if (tool.getToolCode().equals(toolCode)){
                return tool;
            }
        }
        return null;
    }

    public static void main(String[] args){
        RentalApp app = new RentalApp(); //creates new instance of app

        //Add tools to the addTool method
        app.addTool(new Tool("CHNS", "Chainsaw", "Stihl", 1.49, true, false, true));
        app.addTool(new Tool("LADW", "Ladder", "Werner", 1.99, true, true, false));
        app.addTool(new Tool("JAKD", "Jackhammer", "DeWalt", 2.99, true, false, false));
        app.addTool(new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, true, false, false));

        LocalDate checkoutDate = LocalDate.of(2022, 9, 3);
        RentalAgreement agreement = app.checkoutTool("CHNS", 5, checkoutDate, 1);
        System.out.println(agreement);

        LocalDate checkoutDate1 = LocalDate.of(2020, 7, 2);
        RentalAgreement agreement1 = app.checkoutTool("LADW", 4, checkoutDate1, .15);
        System.out.println(agreement1);

        LocalDate checkoutDate2 = LocalDate.of(2015, 7, 2);
        RentalAgreement agreement2 = app.checkoutTool("CHNS", 3, checkoutDate2, .25);
        System.out.println(agreement2);

        LocalDate checkoutDate3 = LocalDate.of(2019, 9, 3);
        RentalAgreement agreement3 = app.checkoutTool("JAKD", 5, checkoutDate3, 0);
        System.out.println(agreement3);

        LocalDate checkoutDate4 = LocalDate.of(2023, 7, 2);
        RentalAgreement agreement4 = app.checkoutTool("JAKR", 11, checkoutDate4, .05);
        System.out.println(agreement4);

        LocalDate checkoutDate5 = LocalDate.of(2020, 7, 2);
        RentalAgreement agreement5 = app.checkoutTool("JAKR", 4, checkoutDate5, 0.5);
        System.out.println(agreement5);
    }
}