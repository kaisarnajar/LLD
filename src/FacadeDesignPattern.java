class MediaPlayer
{
    public void loadMusicFile(String fileName)
    {
        System.out.println("Loading music file : " + fileName);
    }

    public void play()
    {
        System.out.println("Playing music.....");
    }
}

class VideoPlayer
{
    public void loadFile(String fileName)
    {
        System.out.println("Loading video file : " + fileName);
    }

    public void play()
    {
        System.out.println("Playing video.....");
    }
}

class ImageViewer
{
    public void loadImageFile(String fileName)
    {
        System.out.println("loading image file..... : " + fileName);
    }

    public void play()
    {
        System.out.println("Displaying image....");
    }
}

class MediaFacade
{
    private final MediaPlayer mediaPlayer;
    private final VideoPlayer videoPlayer;
    private final ImageViewer imageViewer;

    MediaFacade()
    {
        this.imageViewer = new ImageViewer();
        this.mediaPlayer = new MediaPlayer();
        this.videoPlayer = new VideoPlayer();
    }

    public void playMusic(String fileName)
    {
        mediaPlayer.loadMusicFile(fileName);
        mediaPlayer.play();
    }

    public void watchVideo(String fileName)
    {
        videoPlayer.loadFile(fileName);
        videoPlayer.play();
    }

    public void viewImage(String fileName)
    {
        imageViewer.loadImageFile(fileName);
        imageViewer.play();
    }
}

public class FacadeDesignPattern
{
    public static void main(String[] args)
    {
        MediaFacade facade = new MediaFacade();

        facade.playMusic("Kaisar.mp4");
        facade.watchVideo("Teri Meri Prem Kahani");
        facade.viewImage("Tahir.jpg");
    }
}
