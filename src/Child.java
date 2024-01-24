public class Child
{

    private int id;
    private String name;
    private int age;
    private Guardian guardian;
    private int max_amount;
    private ETicket eTicket;
    private boolean owneETicket = false;
    private boolean isRegisted;



    public Child(Guardian guardian, String name, int age)
    {
        this.name = name;
        this.age = age;
        this.guardian = guardian;
        this.isRegisted = false;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean idIsEqual(int id)
    {
        if(this.id == id)
            return true;
        return false;
    }

    public boolean hasEticket()
    {
        return this.owneETicket;
    }

    public Guardian getGuardian()
    {
        return this.guardian;
    }

    public int getMax_amount() {
        return max_amount;
    }

    public void setMax_amount(int max_amount) {
        this.max_amount = max_amount;
    }


    public void seteTicket(ETicket eTicket) {
        this.eTicket = eTicket;
        this.owneETicket = true;

    }

    public void setRegisted(boolean val)
    {
        this.isRegisted = val;
    }

    public void setOwnETicket(boolean val)
    {
        this.owneETicket = val;
    }

    public void showAllInfo()
    {
        this.eTicket.showAllInfo();
    }

    public ETicket getETicket()
    {
        return this.eTicket;
    }
}
