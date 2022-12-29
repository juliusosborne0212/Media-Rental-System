
/**
 * MovieDVD is the child of Media class.
 * It inherits all attributes and methods of
 * its parent.
 */
public class MovieDVD extends Media {
    //MovieDVD has its attribute as well as size in megabytes
    private double size;

    //create MovieDVD using passed values as this is constructor
    public MovieDVD(int id, String title, int year, boolean available, double megabytes) {
        //first create parent object by passing values to parent
        super(id, title, year, available);
        //then assign attributes of MovieDVD
        this.size = megabytes;
    }

    // to access the private instance fields
    //we need getter and setter methods like these
    //from name we can see which attributes it access or changes

    //return size of the movie dvd object
    public double getSize() {
        return size;
    }

    //change size of the movie dvd object
    public void setSize(double size) {
        this.size = size;
    }

    //return object attributes as string line
    @Override
    public String toString() {
        return String.format("MovieDVD [ id=%d, title=%s, year=%d, size=%.1fMB, available=%s]",
                getId(), getTitle(), getYear(), size, isAvailable());
    }


    //calculate fee for movie dvd.
    //it is fixed rate for all movies.
    @Override
    public double calculateRentalFee() {
        //let's make rental fee of the movie fixed as
        //$4.25
        return 4.25;
    }

}