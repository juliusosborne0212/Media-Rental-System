
import java.io.*;
import java.util.*;

/**
 * This class manages Media object,
 * like load, add, search and show.
 */
public class Manager {
    //We store all media object in this list
    //since Media is parent of all child classes
    //we can declare list to store Media type
    //and we can assign any child objects to it
    private List<Media> mediaList = new ArrayList<>();
    //store child class names in constants to make the reference
    // to them easy
    private final String EBOOK = "EBook";
    private final String MOVIEDVD = "MovieDVD";
    private final String MUSICCD = "MusicCD";
    private final String EXTENSION = "txt";
    private final String DELIM = ";";


    //we store media objects in text files
    //load all text files from given folder
    public void loadMedia(String folderName) {
        //folder treated as file in java
        File folder = new File(folderName);
        //get list of files all files from folder
        File[] fileList = folder.listFiles();
        //take each file
        for (int i = 0; i < fileList.length; i++) {
            //if this is not a folder and has given extension
            if (fileList[i].isFile() && fileList[i].getName().endsWith(EXTENSION)) {
                //overloaded load media from file object
                loadMedia(fileList[i]);
            }
        }
    }

    //load media object from file
    private void loadMedia(File file) {
        String s[];
        //read contents of file
        try {
            Scanner in = new Scanner(file);
            //read line, split by deliminator
            s = in.nextLine().trim().split(DELIM);
            //get each field
            String className = s[0];
            int id = Integer.parseInt(s[1]);
            String title = s[2];
            int year = Integer.parseInt(s[3]);
            boolean available = Boolean.parseBoolean(s[5]);
            //based on child type, create child media and add to list
            if(className.equalsIgnoreCase(EBOOK)) {
                addMedia(new EBook(id, title, year, available, Integer.parseInt(s[4])));
            } else if (className.equalsIgnoreCase(MOVIEDVD)) {
                addMedia(new MovieDVD(id, title, year, available, Double.parseDouble(s[4])));
            } else {
                addMedia(new MusicCD(id, title, year, available,Integer.parseInt(s[4])));
            }
            in.close();
        } catch (Exception ex) {
            //throw exception with custom message
            throw new RuntimeException("File cannot be opened: Could not load, no such directory");
        }

    }


    //add new Media object to its Media list
    public void addMedia(Media media) {
        mediaList.add(media);
    }

    //search media objects by title and return them as a list
    public List<Media> find(String title) {
        //empty list
        List<Media> media =  new ArrayList<>();
        //take each child from the list
        for(Media child: mediaList) {
            //add to result media list of match
            if( child.getTitle().toLowerCase().contains(title.toLowerCase()) ) {
                media.add(child);
            }
        }
        //throw exception with custom message
        if(media.isEmpty()) throw new RuntimeException("There is no media with title: "+title);
        return media;
    }

    //rent media by id, may throw exception if not found
    public double rent(int id) {
        //media for renting
        Media toBeRented = null;
        //take each child from the list
        for(Media child: mediaList) {
            //check if found
            if(child.getId()==id) {
                //save it
                toBeRented = child;
            }
        }
        //if found
        if(toBeRented!=null) {
            if(toBeRented.isAvailable()) {
                //rent
                toBeRented.setAvailable(false);
                //return fee
                return toBeRented.calculateRentalFee();
            } else {
                //throw exception with custom message
                throw new RuntimeException("Media is not available");
            }

        } else {
            //throw exception with custom message
            throw new RuntimeException(String.format("Media by id %d not found!",id));
        }

    }

    //return all media objects to show
    @Override
    public String toString() {
        String str = "";
        for(Media media:mediaList) {
            str+=media+"\r\n";
        }
        return str;
    }
}

