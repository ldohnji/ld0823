import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
public class RentalAppTests {
    @Test
    void testRentalAgreement(){
        RentalApp app = new RentalApp(); //create new app
        app.addTool(new Tool("CHNS", "Chainsaw", "Stihl", 1.49, true, false, true)); //add new tool

        LocalDate checkoutDate = LocalDate.of(2020, 7, 2);
        RentalAgreement agreement = app.checkoutTool("CHNS", 8, checkoutDate, 0.15); //checks the generated string matches the output

        //compare the expected and actual results
        assertEquals("Tool code: CHNS\n" +
                "Tool type: Chainsaw\n" +
                "Tool brand: Stihl\n" +
                "Duration: 8\n" +
                "Checkout date: 07/02/20\n" +
                "Due date: 07/10/20\n" +
                "Daily rental charge: $1.49\n" +
                "Charge days: 7\n" +
                "Pre-discount charge: $10.43\n" +
                "Discount percent: 15%\n" +
                "Discount amount: $1.56\n" +
                "Final charge: $8.87\n", agreement.toString());
    }

    @Test
    void testRentalAgreement1(){
        RentalApp app = new RentalApp();
        app.addTool(new Tool("LADW", "Ladder", "Werner", 1.99, true, true, false));

        LocalDate checkoutDate = LocalDate.of(2021, 9, 3);
        RentalAgreement agreement = app.checkoutTool("LADW", 5, checkoutDate, 0.01);

        assertEquals("Tool code: LADW\n" +
                "Tool type: Ladder\n" +
                "Tool brand: Werner\n" +
                "Duration: 5\n" +
                "Checkout date: 09/03/21\n" +
                "Due date: 09/08/21\n" +
                "Daily rental charge: $1.99\n" +
                "Charge days: 4\n" +
                "Pre-discount charge: $7.96\n" +
                "Discount percent: 1%\n" +
                "Discount amount: $0.08\n" +
                "Final charge: $7.88\n", agreement.toString());
    }

    @Test
    void testRentalAgreement2() {
        RentalApp app = new RentalApp();
        app.addTool(new Tool("JAKD", "Jackhammer", "DeWalt", 2.99, true, false, false));

        LocalDate checkoutDate = LocalDate.of(2010, 9, 3);
        RentalAgreement agreement = app.checkoutTool("JAKD", 9, checkoutDate, 0);

        assertEquals("Tool code: JAKD\n" +
                "Tool type: Jackhammer\n" +
                "Tool brand: DeWalt\n" +
                "Duration: 9\n" +
                "Checkout date: 09/03/10\n" +
                "Due date: 09/12/10\n" +
                "Daily rental charge: $2.99\n" +
                "Charge days: 5\n" +
                "Pre-discount charge: $14.95\n" +
                "Discount percent: 0%\n" +
                "Discount amount: $0.00\n" +
                "Final charge: $14.95\n", agreement.toString());
    }

    @Test
    void testRentalAgreement3() {
        RentalApp app = new RentalApp();
        app.addTool(new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, true, false, false));

        LocalDate checkoutDate = LocalDate.of(2010, 9, 3);
        RentalAgreement agreement = app.checkoutTool("JAKR", 5, checkoutDate, 0.25);

        assertEquals("Tool code: JAKR\n" +
                "Tool type: Jackhammer\n" +
                "Tool brand: Ridgid\n" +
                "Duration: 5\n" +
                "Checkout date: 09/03/10\n" +
                "Due date: 09/08/10\n" +
                "Daily rental charge: $2.99\n" +
                "Charge days: 2\n" +
                "Pre-discount charge: $5.98\n" +
                "Discount percent: 25%\n" +
                "Discount amount: $1.50\n" +
                "Final charge: $4.49\n", agreement.toString());
    }

    @Test
    void testInvalidDiscountPercent(){
        RentalApp app = new RentalApp();
        app.addTool(new Tool("LADW", "Ladder", "Werner", 1.99, true, true, false));

        LocalDate checkoutDate = LocalDate.of(2023, 7, 2);

        assertThrows(IllegalArgumentException.class, () -> app.checkoutTool("LADW", 7, checkoutDate, 120));
        assertThrows(IllegalArgumentException.class, () -> app.checkoutTool("LADW", 4, checkoutDate, -30));
    }

    @Test
    void testInvalidDuration(){
        RentalApp app = new RentalApp();
        app.addTool(new Tool("JAKD", "Jackhammer", "DeWalt", 2.99, true, false, false));

        LocalDate checkoutDate = LocalDate.of(2022, 9, 3);

        assertThrows(IllegalArgumentException.class, () -> app.checkoutTool("JAKD", -1, checkoutDate, 1));
    }
}
