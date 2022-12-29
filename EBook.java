
import java.util.Calendar;

/**
 * EBook is the child of Media class.
 * It inherits all attributes and methods of
 * its parent.
 */
public class EBook extends Media {
    //EBook has its attribute as well as chapters
    private int chapters;

    //create MovieDVD using passed values as this is constructor
    public EBook(int id, String title, int year, boolean available, int chapters) {
        //first create parent object by passing values to parent
        super(id, title, year, available);
        //then assign attributes of EBook
        this.chapters = chapters;
    }

    // to access the private instance fields
    //we need getter and setter methods like these
    //from name we can see which attributes it access or changes

    //return chapters of the Ebook object
    public int getChapters() {
        return chapters;
    }

    //change chapters of the Ebook object
    public void setChapters(int chapters) {
        this.chapters = chapters;
    }

    //return object attributes as string line
    @Override
    public String toString() {
        return String.format("EBook [ id=%d, title=%s, year=%d, chapters=%d, available=%s]",
                getId(), getTitle(), getYear(),  chapters, isAvailable());
    }

    //Rental fee of the EBook should be calculated
    //based on some rule. This rule is 10 cents per
    //chapter and $1 if it was published in this year.
    @Override
    public double calculateRentalFee() {
        //fee is calculated based on chapters
        double fee = chapters * 0.10;
        //compare release year current year, if equal
        if (this.getYear() == Calendar.getInstance().get(Calendar.YEAR)) {
            // add $1.00 to the fee
            fee += 1.00;
        }
        //return fee
        return fee;
    }
}
