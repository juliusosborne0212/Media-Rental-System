
import java.util.Calendar;

/**
 * MusicCD is the child of Media class.
 * It inherits all attributes and methods of
 * its parent.
 */
public class MusicCD extends Media {
    //MusicCD has its attribute as well as length in minutes
    private int length;

    //create MusicCD using passed values as this is constructor
    public MusicCD(int id, String title, int year, boolean available, int minutes) {
        //first create parent object by passing values to parent
        super(id, title, year, available);
        //then assign attributes of MusicCD
        this.length = minutes;
    }

    // to access the private instance fields
    //we need getter and setter methods like these
    //from name we can see which attributes it access or changes

    //return length of the Music CD object
    public int getLength() {
        return length;
    }

    //change length of the Music CD object
    public void setLength(int length) {
        this.length = length;
    }

    //return object attributes as string line
    @Override
    public String toString() {
        return String.format("MusicCD [ id=%d, title=%s, year=%d, length=%dmin, available=%s]",
                getId(), getTitle(), getYear(), length, isAvailable());
    }

    //Rental fee of the MusicCD should be calculated
    //based on some rule. This rule is 88 cents per
    //minute and $1 if it was released in this year.
    @Override
    public double calculateRentalFee() {
        //fee is calculated based on length
        double fee = length * 0.88;
        //compare release year current year, if equal
        if (super.getYear() == Calendar.getInstance().get(Calendar.YEAR)) {
            // add $1.00 to the fee
            fee += 1.00;
        }
        //return fee
        return fee;
    }
}
