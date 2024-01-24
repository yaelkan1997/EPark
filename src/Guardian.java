import java.util.HashMap;

public class Guardian
{
    private int creditCard;
    private String expirationDate;
    private HashMap<Child, ETicket> listOfChildren;



    public Guardian()
    {
        listOfChildren = new HashMap<>();
    }

    public void setCreditCard(int creditCard) {
        this.creditCard = creditCard;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCreditCard() {
        return creditCard;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public ETicket returnETicket(Child child)
    {
        ETicket temp = listOfChildren.get(child);
        this.listOfChildren.remove(child);
        return temp;
    }

    public void add_child(Child child, ETicket eTicket)
    {
        this.listOfChildren.put(child, eTicket);
    }

    public Child getChild(int id)
    {
        if(listOfChildren.isEmpty())
            return null;
        for(Child child : this.listOfChildren.keySet())
        {
            if(child.idIsEqual(id))
                return child;
        }
        return null;
    }
}
