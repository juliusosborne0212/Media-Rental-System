

/**
 * This is Media class.
 * It represents one Media object.
 * It has id, title and year.
 * This is abstract template for
 * other Media objects for CD, DVD and Ebook
 * which we will create later.
 */
public abstract class Media {
    //Media object has these attributes.
    //these are private meaning we cannot access them
    //from outside the class
    private int id;
    private String title;
    private int year;
    //for renting, we need to know if it is available
    private boolean available;


    //this method is special method called constructor
    //basically it creates a Media object using passed values
    //but this it is abstract class, we need to call this from child
    //classes, we cannot directly create object of Media objects.
    public Media(int id, String title, int year, boolean available) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.available = available;
    }

    // to access the private instance fields
    //we need getter and setter methods like these
    //from name we can see which attributes it access or changes

    //return id of the media object
    public int getId() {
        return id;
    }

    //return title of the media object
    public String getTitle() {
        return title;
    }

    //return year of the media object
    public int getYear() {
        return year;
    }

    //change title of the media object
    public void setTitle(String title) {
        this.title = title;
    }

    //change year of the media object
    public void setYear(int year) {
        this.year = year;
    }

    //return availability of the media object
    public boolean isAvailable() {
        return available;
    }

    //change availability of the media object
    public void setAvailable(boolean available) {
        this.available = available;
    }

    //return rent fee of the media objects
    //since this is abstract method, the child
    //class will implement this based on their value
    public abstract double calculateRentalFee();
}
