import java.util.*;

class RecentlyPlayedSongsStore {

    private int capacity;
    private Map<String, List<String>> songsByUser;

    public RecentlyPlayedSongsStore(int capacity) {
        this.capacity = capacity;
        this.songsByUser = new HashMap<>();
    }

    public void addSong(String songName, String userID) {
        if (!songsByUser.containsKey(userID)) {
            songsByUser.put(userID, new ArrayList<>());
        }

        songsByUser.get(userID).add(songName);

        if (songsByUser.get(userID).size() > capacity) {
            songsByUser.get(userID).remove(0);
        }
    }

    public List<String> getRecentlyPlayedSongs(String userID) {
        return songsByUser.get(userID);
    }
}

class Main {

    public static void main(String[] args) {
        RecentlyPlayedSongsStore store = new RecentlyPlayedSongsStore(3);

        store.addSong("S1", "user1");
        store.addSong("S2", "user1");
        store.addSong("S3", "user1");
        store.addSong("S4", "user1");

        System.out.println(store.getRecentlyPlayedSongs("user1"));
        // Output: ["S2", "S3", "S4"]

        store.addSong("S1", "user1");

        System.out.println(store.getRecentlyPlayedSongs("user1"));
        // Output: ["S4", "S2", "S1"]
    }
}