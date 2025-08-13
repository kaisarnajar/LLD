import java.util.ArrayList;
import java.util.List;

interface Iterator {
    boolean hasNext();

    void next();
}

interface IPlaylist {
    List<String> getSongs();
}

class PlayList implements IPlaylist {
    List<String> songs;


    PlayList() {
        songs = new ArrayList<>();
    }

    public void addSong(String song) {
        songs.add(song);
    }

    public void removeSong(String song) {
        songs.remove(song);
    }

    @Override
    public List<String> getSongs() {
        return songs;
    }
}

class SimpleIterator implements Iterator {

    IPlaylist playlist;
    int position;

    SimpleIterator(IPlaylist playlist) {
        this.playlist = playlist;
        position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < playlist.getSongs().size();
    }

    @Override
    public void next() {
        if (hasNext()) {
            System.out.println(playlist.getSongs().get(position++));
        } else {
            System.out.println("We are at the end of playlist");
        }
    }
}


public class IteratorDesignPattern {

    public static void main(String[] args) {
        PlayList myPlaylist = new PlayList();
        myPlaylist.addSong("Song 1");
        myPlaylist.addSong("Song 2");
        myPlaylist.addSong("Song 3");
        myPlaylist.addSong("Song 4");

        SimpleIterator simpleIterator = new SimpleIterator(myPlaylist);

        while (simpleIterator.hasNext()) {
            simpleIterator.next();
        }
    }
}
