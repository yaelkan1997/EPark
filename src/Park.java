import java.util.ArrayList;

public class Park
{
    private ArrayList<Devices> devices;

    public Park()
    {
        devices = new ArrayList<Devices>();
    }

    //Function that adds a device to the park's list of device
    public boolean add_device(Devices device, double price)
    {
        if (this.devices.contains(device))
        {
            return false;
        }

        else
        {
            device.setPrice(price);
            this.devices.add(device);
            return true;
        }
    }

    //Function that returns a list of the allowed devices for given age and height
    public ArrayList<Devices> getAllowedDevices(int age, double height)
    {
        ArrayList<Devices> devices = new ArrayList<>();
        for (Devices device : this.devices)
        {
            if(device.isAllowed(age, height))
                devices.add(device);
        }
        return devices;
    }

}
