/**
 *
 * Lab 1
 * @Author Shams ansari
 * @Purpose To learn about Classes, Inheritance and Polymorphism
 * CIS 22C.  Prof.Goel
 */


import java.text.NumberFormat;

/**
 * Represents U.S Dollar
 */
public class USD implements Comparable<USD> {

    private int dollars;
    private int cents;
    private String name;

    /**
     * Initializes a 0.00 dollar with name "USD"
     */
    public USD() {
        this(0, 0, "USD");
    }

    /**
     *  Constructs new USD.
     * @param dollars whole dollar amount (1,5,10 etc)
     * @param cents cents in the dollar (25, 75, 110 etc)
     * @param name name of currency
     */
    public USD(int dollars, int cents, String name) {

        setDollars(dollars);
        setCents(cents);
        setName(name);
    }

    /**
     * Copy Constructor
     * @param copy USD to copy
     */
    public USD(USD copy) {
        setDollars(copy.getDollars());
        setCents(copy.getCents());
        setName(copy.getName());
    }

    /**
     * Adds  parameter USD object to this USD object.
     * does nothing if the currency(name) is not the same or if curr value is negative.
     * @param curr USD to add
     */
    public void add(USD curr) {
        if (!isSameCurrency(curr) || curr.getTotalInCents() < 0) {
            return;
        }
        addCents(curr.getCents());
        addDollars(curr.getDollars());
    }

    /**
     * Subtracts given object from this object.
     * Does nothing if the currency is not the same.
     * Does nothing if curr object is less that this object or curr value is negative
     * @param curr USD to subtract
     */
    public void sub(USD curr) {
        if (!isSameCurrency(curr)) {
            return;
        }
        if (compareTo(curr) < 0 || curr.getTotalInCents() < 0) {
            return;
        }
        int myTotal = getTotalInCents();
        int currTotal = curr.getTotalInCents();
        int newVal = myTotal - currTotal;
        setCents(newVal % 100);
        setDollars(newVal / 100);

    }

    /**
     * Tests if two currencies are the same vaue.
     * @param curr USD to test
     * @return True if two Currencies have the same value and are the same currency
     */
    public boolean equals(USD curr) {
        return isSameCurrency(curr) && getTotalInCents() == curr.getTotalInCents();
    }


    public int getDollars() {
        return dollars;
    }

    public void setDollars(int dollar) {
        this.dollars = dollar;
    }

    public int getCents() {
        return cents;
    }

    public void setCents(int cents) {
        this.cents = cents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Prints amount in this object.
     * Ex: "USD: $5.49"
     */
    public void printInfo() {

        System.out.println("\t"+ getName() + ":  " + this);
    }

    /**
     * Helper method.
     * @return int of total in cents
     */
    protected int getTotalInCents() {
        return getCents() + getDollars() * 100;
    }

    /**
     * Helper method
     * Ex: 1.50
     * @return gets total in dollars
     */
    protected double getTotalInDollars(){
        return getDollars() + getCents()/100.0;
    }

    /**
     * Helper method. tests if two objects have the same name
     * @param curr
     * @return true if curr has same name as this
     */
    protected boolean isSameCurrency(USD curr) {
        return getName().equals(curr.getName());
    }

    /**
     *  Adds dollars to this object
     * @param dollars
     */
    protected void addDollars(int dollars){
        setDollars(getDollars() + dollars);
    }

    /**
     * adds cents to this object. If cents > 100, adds dollars too
     * @param cents
     */
    protected void addCents(int cents){
        int myTotal = getTotalInCents()  + cents;
        setCents(myTotal % 100);
        setDollars(myTotal / 100);

    }

    /**
     * Compare two of the same currency.
     *
     * @param curr
     * @return -2 if they are not the same currency
     *          -1 if this is less than curr
     *          0 if this is equal to curr
     *          1 if this is greater than curr
     */
    @Override
    public int compareTo(USD curr)  {
        if(!isSameCurrency(curr)){
            return -2;
        }
        if (getTotalInCents() < curr.getTotalInCents()) {
            return -1;
        }
        if (getTotalInCents() > curr.getTotalInCents()) {
            return 1;
        }
        return 0;
    }

    /**
     *
     * @return String in money format ( Ex "$1,369.35" ) for dollars = 1369 and cents =35
     */
    @Override
    public String toString() {

        return NumberFormat.getCurrencyInstance().format(getTotalInDollars());

    }
}
