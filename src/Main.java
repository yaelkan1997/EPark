import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static ArrayList<Object> systemObjects = new ArrayList<Object>(); // Data structure with all the System Objects
    public static HashMap<Child, Integer> childrenAndAmountToPay = new HashMap<>(); // Map of children and amount to pay
    public static HashMap<Integer, Integer> childIdAndPassword = new HashMap<>(); // Map of children ID's and passwords
    public static CreditCardCompany creditCardCompany = new CreditCardCompany();
    public static Park park;

    private static int childId = 0;
    private static int childPassword = 123;
    private static Guardian guardian = new Guardian();


    // Function that gets a String input
    public static String CheckInputChildName()
    {
        String in;
        while(true)
        {
            Scanner myObj = new Scanner(System.in);
            in = myObj.next();  // Read user input

                if (! in.matches("^\\d+(\\.\\d+)?"))
                {
                    return in;
                }
                System.out.println("Wrong input! You need to insert a string");

        }
    }


    // Function that gets an int input
    public static int CheckInputIntForAge()
    {
        int in;
        while(true)
        {
            Scanner myObj = new Scanner(System.in);
            try {
                in = myObj.nextInt();  // Read user input
                if (in > 35){
                    System.out.println("child can't be over 35 years old,\npleas try again:");
                    continue;
                }

                return Integer.parseInt(String.valueOf(in));
            }
            catch (InputMismatchException e){
                System.out.println("Wrong input! You need to insert an Integer,\npleas try again:");

            }

        }

    }


    // Function that gets a valid date input
    public static String CheckDateInput()
    {
        while(true)
        {
            Scanner myObj = new Scanner(System.in);
            String in = myObj.next();  // Read user input

                if (in.length() == 7)
                {
                    try {

                        int month = Integer.parseInt(in.substring(0,2));
                        String separator = in.substring(2,3);
                        int year = Integer.parseInt(in.substring(3,7));

                        if(month > 12 || month < 1 || year < 2023 || !separator.equals("/"))
                        {
                            System.out.println("Wrong input! You need to insert a valid date input");
                            continue;
                        }

                        return in;
                    }
                    catch(NumberFormatException e)
                    {
                        System.out.println("Wrong input! You need to insert a valid date input");
                        continue;
                    }
                }
                System.out.println("Wrong input! You need to insert a valid date input");
                continue;
        }
    }

    public static int CheckIntInput(){
        int in;
        while(true)
        {
            Scanner myObj = new Scanner(System.in);
            try {
                in = myObj.nextInt();  // Read user input

                return Integer.parseInt(String.valueOf(in));
            }
            catch (InputMismatchException e){
                System.out.println("Wrong input! You need to insert an Integer,\nPlease try again");

            }
        }

    }




    public static void main(String[] args)
    {
        park = new Park();

        ExtremeDevices MambaRide = new ExtremeDevices("MambaRide", 12, 1.4);
        Devices GiantWheel = new Devices("GiantWheel", 0, 0);
        Devices Carrousel = new Devices("Carrousel", 8, 0);
        park.add_device(MambaRide, 10);
        park.add_device(GiantWheel, 7);
        park.add_device(Carrousel, 5);


        systemObjects.add(park);
        systemObjects.add(guardian);
        systemObjects.add(MambaRide);
        systemObjects.add(GiantWheel);
        systemObjects.add(Carrousel);
        systemObjects.add(creditCardCompany);


        boolean runSystem = true;

        //Main loop that runs the system
        while(runSystem)
        {
            System.out.println("Welcome to ePark !");
            System.out.println("1.   Register child");
            System.out.println("2.   Manage ticket");
            System.out.println("3.   Add ride");
            System.out.println("4.   Remove ride");
            System.out.println("5.   Exit park");
            System.out.println("6.   Exit");

            //Scanner myObj = new Scanner(System.in);
            int in = CheckIntInput(); // Read user input

            switch (in)  // SwitchCase on the user input
            {
                case 1: // Register child
                    RegisterChild();
                    break;
                    //AddRide(false, null);
                case 2: // Manage ticket
                    ManageTicket();
                    break;

                case 3: // Add ride
                    AddRide(false, null);
                    break;
                case 4: // Remove ride
                    RemoveRide(false, null);
                    break;
                case 5: // Exit park
                    ExitPark();


                case 6: // Exit
                    runSystem = false;
            }
        }
        System.out.println("Thank you for visiting E-park");
    }



    //Register Child function - gets all the relevant data from user, creates a new child (with all its components) and gives the user the generated child id and password
    private static void RegisterChild()
    {
        int child_age;
        String child_name;
        int child_height;
        int child_weight;
        int credit_card_num;
        String expiration_date;
        int max_amount;

        //Gets child name
        System.out.println("Please enter the child's name");
        child_name = CheckInputChildName();

        //Gets child age
        System.out.println("Please enter the child's age");
        child_age = CheckInputIntForAge();

        //Creates a new child - assign him the guardian
        Child new_child = new Child(guardian, child_name, child_age);

        //Gets credit card number
        System.out.println("Please enter credit card number");
        credit_card_num = CheckIntInput();
        guardian.setCreditCard(credit_card_num);

        //Gets expiration date
        System.out.println("Please enter card expiration date (MM/YYYY)");
        expiration_date = CheckDateInput();
        guardian.setExpirationDate(expiration_date);

        //Gets credit card company approval
        System.out.println("Waiting for credit card company approval...");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String approval = creditCardCompany.get_approval(guardian.getCreditCard(), guardian.getExpirationDate());
        System.out.println(approval);

        //Gets an amount restriction for the child
        System.out.println("Please enter a maximum amount to charge");
        max_amount = CheckIntInput();

        //Sets the amount restriction for the child
        new_child.setMax_amount(max_amount);

        //Sets the generated id for the child.
        new_child.setId(childId);

        //Updates the DBs with the new child and his amount to pay + his password
        childrenAndAmountToPay.put(new_child, 0);
        childIdAndPassword.put(childId, childPassword);

        //Creates a new E-Ticket for the child
        ETicket eTicket = new ETicket(new_child, new_child.getId(), new_child.getAge(), childPassword);

        new_child.seteTicket(eTicket);
        guardian.add_child(new_child, eTicket);

        //Gets the child's height
        System.out.println("Please enter the child's height (in cm)");
        child_height = CheckIntInput();

        //Gets the child's weight
        System.out.println("Please enter the child's weight (in kg)");
        child_weight = CheckIntInput();

        //Updates the E-ticket with the corresponding value of the child
        eTicket.setHeight(child_height);
        eTicket.setWeight(child_weight);

        System.out.println("Registration for the child is complete -\nYour login details are:\n- Child ID: " + childId +"\n- Password: " + childPassword + "\nPlease keep these credentials until your child exits the park for following actions !");

        //Increments the ID and password generators
        childId++;
        childPassword++;


    }




    //Function that shows the child's devices entries and allows to edit them
    //Require a password login to view the child's entries
    private static void ManageTicket() {

        //Gets the child's ID
        System.out.println("Please enter the child's ID:");
        int child_Id = CheckIntInput();

        //Gets the password
        System.out.println("Please enter your password:");
        int received_password = CheckIntInput();
        int expected_password;//change here

        //Verifies the password
        System.out.println("Verifying the child password, this may take a couple of seconds");
        try
        {
            TimeUnit.SECONDS.sleep(2);
            expected_password = childIdAndPassword.get(child_Id);
            if (expected_password != received_password)
            {
                System.out.println("You have entered an invalid username or password");
                return;
            }
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }catch (NullPointerException e)
        {
            System.out.println("You have entered an invalid username or password");
            return;
        }


        //Gets the desired child
        Child currChild = guardian.getChild(child_Id);
        if (currChild == null)
        {
            System.out.println("There is no child with id: " + child_Id + " in the System");
            return;
        }

        //Prints the child's entries list
        currChild.showAllInfo();
        System.out.println("------------------------------");


        //Opens sub-menu for editing the entries
        boolean menuFlag = true;
        while(menuFlag)
        {
            System.out.println("Please choose an option:");
            System.out.println("1. add rides");
            System.out.println("2. remove ride");
            System.out.println("3. back to main menu");

            int choice = CheckIntInput();
            switch (choice)
            {
                case 1:
                    AddRide(true, currChild);
                    break;
                case 2:
                    RemoveRide(true, currChild);
                    break;
                case 3:
                    menuFlag = false;
                    break;


                default:
                {
                    System.out.println("You inserted a wrong number, please try again");
                }
            }
        }
    }

    //Function to add an entry for a child
    private static void AddRide(boolean enteredFromManageTicket, Child child)
    {
        //Checks if the function is called from the main menu or from the 'Manage Ticket' option
        //If the function is called from main menu - need to enter child's ID
        if (!enteredFromManageTicket)
        {
            System.out.println("Please enter the child's id.");
            int childId = CheckIntInput();
            child = guardian.getChild(childId);
            if (child == null) {
                System.out.println("There is no child with id: " + childId + " in the System");
                return;
            }
        }

        //Extracts from the E-Ticket all the allowed devices for the child
        ETicket childETicket = child.getETicket();
        ArrayList<Devices> allowedDevices = park.getAllowedDevices(child.getAge(), childETicket.getHeight());
        if (allowedDevices.size() == 0) {
            System.out.println("The child does not allowed to enter any Device");
            return;
        }


        //Selection of the device for buying entrances
        boolean menuFlag = true;
        int numOfAllowedDevices = allowedDevices.size();
        while (menuFlag)
        {
            System.out.println("Please choose the device you want to buy entries for:");
            for (int i = 0; i < numOfAllowedDevices; i++) {
                int indexToShow = i + 1;
                System.out.println(indexToShow + ". " + allowedDevices.get(i));
            }
            System.out.println(allowedDevices.size()+1 + ". back to main menu");

            int choice = CheckIntInput();
            if (choice > allowedDevices.size() +1 || choice < 1)
            {
                System.out.println("You inserted a wrong number");
            }
            else if (choice == allowedDevices.size() + 1)
            {
                return;
            }

            //Device selected
            else
                {
                Devices deviceToAdd = allowedDevices.get(choice - 1);
                System.out.println("How many entries you want to buy? Please insert a number.");
                int entries = CheckIntInput();

                // Checks if buying this number of entries won't exceed the credit limit set at registration
                double addedMoney = deviceToAdd.getPrice() * choice;
                if (child.getETicket().getTotalPay() + addedMoney > child.getMax_amount()) {
                    System.out.println("We are sorry, but you can't add this number of entries for the specific device becuase credit limit exceeded");
                    return;
                }

                else
                    {
                    // Extreme devices need extra approval from the guardian
                    if (deviceToAdd instanceof ExtremeDevices)
                    {
                        while (menuFlag)
                        {
                            System.out.println("We need your approval for the Extreme Device - " + deviceToAdd );
                            System.out.println("1. approve");
                            System.out.println("2. don't approve");
                            choice = CheckIntInput();
                            if(choice == 2)
                            {
                                return;
                            }
                            menuFlag = false;
                        }
                    }

                    //Updates the E-Ticket with the entries and the payment
                    child.getETicket().addPayment(addedMoney);
                    child.getETicket().updateDevicesEntries(deviceToAdd, entries);
                    menuFlag = false;
                    System.out.println(String.valueOf(entries) + " entries for the ride " + deviceToAdd + " added to the child.");
                    }
            }
        }
    }

    //Function to remove an entry for a child
    private static void RemoveRide(boolean enteredFromManageTicket, Child child)
    {
        //Asks the user to enter the child's ID (if he wants to add entries from main menu and not after clicking "Manage ticket")
        if (!enteredFromManageTicket) {
            System.out.println("Please enter the child's id");
            int child_Id = CheckIntInput();
            child = guardian.getChild(child_Id);
            if (child == null) {
                System.out.println("There is no child with id: " + child_Id + " in the System");
                return;
            }
        }

        //Gets the E-Ticket of that child
        ETicket childETicket = child.getETicket();

        //Checks if the ticket has entries
        if(!childETicket.haveEntries())
        {
            System.out.println("The child doesn't have any entries");
            return;
        }

        boolean menuFlag = true;
        while (menuFlag)
        {
            //Shows the devices and entries that are available on the ticket
            System.out.println("Please choose the Device you would like to remove entries for:");
            HashMap<Devices, Integer> devices_and_entries_on_ticket = childETicket.getDevicesEntry();
            ArrayList<Devices> devices_on_ticket = new ArrayList<>(devices_and_entries_on_ticket.keySet());


            for (int i = 0; i < devices_on_ticket.size(); i++) {
                int indexToShow = i + 1;
                System.out.println(indexToShow+ ". " + devices_on_ticket.get(i));

            }

            System.out.println(devices_on_ticket.size() + 1  + ". back to main menu");

            //Get input from the user of which device to remove
            int choice = CheckIntInput();
            if (choice > devices_on_ticket.size() + 1 || choice < 1) {
                System.out.println("You inserted a wrong number, Please choose again.");
            } else if (choice == devices_on_ticket.size() + 1) {
                return;
            }

            else
            {
                //Gets input from the user of how many entries to remove
                Devices device_to_remove = devices_on_ticket.get(choice - 1);
                System.out.println("Please insert the number of entries you want to remove:");
                int entries = CheckIntInput();

                if (entries > devices_and_entries_on_ticket.get(device_to_remove))
                {
                    System.out.println("You don`t have enough entries on ticket from that device.");
                    return;
                }

                double removedMoney = device_to_remove.getPrice() * choice * -1;

                //Updates the E-Ticket with the entries and the payment
                child.getETicket().updateDevicesEntries(device_to_remove, entries*-1);
                child.getETicket().addPayment(removedMoney);
                menuFlag = false;
                System.out.println(String.valueOf(entries) + " entries for the ride " + device_to_remove + " have been removed to the child.");
            }
        }
    }

    //Function that removes a child from the park
    private static void ExitPark()
    {
        //Gets input of the child's ID that we want to remove
        //Scanner myObj = new Scanner(System.in);
        System.out.println("Please enter the child`s ID who want to Exit");
        //String in = myObj.nextLine();  // Read user input

        int id = CheckIntInput();

        //Find the child to remove
        for(Child child : childrenAndAmountToPay.keySet())
        {
            if(child.idIsEqual(id)) // check if the specific child is in the system
            {
                if(child.hasEticket())
                {
                    System.out.println("Please return your eTicket:\n1. ok\n2. I don't want to return!");
                    int num  = CheckIntInput();

                    //Waits for a correct answer from the user
                    while (true)
                    {
                        if(num==1)
                        {
                            ETicket eTicket = child.getGuardian().returnETicket(child);
                            child.setRegisted(false);
                            child.setOwnETicket(false);
                            double totalPay = eTicket.getTotalPay();
                            int creditCardNumber = eTicket.getCreditCardNumber();
                            creditCardCompany.chargeCard(creditCardNumber, totalPay);
                            System.out.println("Your card has been charged with total amout of: " + totalPay + ". Hope to see you again..");
                            return;
                        }
                        else if (num==2)
                        {
                            return;
                        }
                        else
                        {
                            System.out.println("You inserted a wrong number");
                            num = CheckIntInput();  // Read user input
                        }
                    }
                }
                System.out.println("The child doesn't have an eTicket..");
                return;
            }
        }
        System.out.println("Can`t find the child, you inserted a wrong id number, please try again.");
    }
}
