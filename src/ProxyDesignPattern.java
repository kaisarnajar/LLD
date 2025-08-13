interface VideoService
{
    void watchVideo(String videoTitle);
}

class RealVideoService implements VideoService
{
    @Override
    public void watchVideo(String videoTitle)
    {
        System.out.println("Watching Video : " + videoTitle);
    }
}

class User
{
    String name;
    Boolean isAuthorized;
    User(String name,Boolean isAuthorized)
    {
        this.name = name;
        this.isAuthorized = isAuthorized;
    }

    Boolean getIsAuthorized()
    {
        return isAuthorized;
    }
}

class ProxyService implements VideoService
{
    User user;
    RealVideoService realVideoService;
    ProxyService(User user)
    {
        realVideoService = new RealVideoService();
        this.user = user;
    }

    @Override
    public void watchVideo(String videoTitle)
    {
        if(user.isAuthorized)
        {
            realVideoService.watchVideo(videoTitle);
        }
        else
        {
            System.out.println("User isn't authorized to watch the video");
        }
    }
}

public class ProxyDesignPattern
{
    public static void main(String[] args) {
        User userOne = new User("Kaisar", true);
        ProxyService proxyServiceOne = new ProxyService(userOne);
        proxyServiceOne.watchVideo("Tere Bin");

        User userTwo = new User("Tahir", false);
        ProxyService proxyServiceTwo = new ProxyService(userTwo);
        proxyServiceTwo.watchVideo("Mere Humsafar");
    }
}
