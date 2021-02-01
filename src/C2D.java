/**
 *
 * Lab 1
 * @Author Shams ansari
 * @Purpose To learn about Classes, Inheritance and Polymorphism
 * CIS 22C.  Prof.Goel
 */

public class C2D extends USD{
    //1 USD = 1.36 C2D
    private double convFactor;

    public C2D(){
        this(0,0,"C2D", 1.36);

    }

    /**
     * Creates a C2D object
     * @param dollars
     * @param cents
     * @param name
     * @param convFactor 1 usd = convFactor
     */
    public C2D(int dollars, int cents, String name, double convFactor){
        super(dollars, cents, name);
        setConvFactor(convFactor);
    }

    /**
     * Converts a USD object to a C2D object
     * if USD x = new C2D(), just upcast because its already a c2d
     * @param usd usd object
     * @return c2d object
     */
    public  C2D convertToC2D(USD usd){
        if(isSameCurrency(usd)){
            return (C2D) usd;
        }
        int newTotal = (int)Math.round(usd.getTotalInCents()* getConvFactor());
        int dollars = newTotal / 100;
        int cents = newTotal % 100;
        return new C2D(dollars,cents, "C2D", getConvFactor());
    }

    /**
     * Converts this object to a USD object
     * @return USD Object
     */
    public USD convertToUSD(){
        int newTotal = (int)Math.round(getTotalInCents()* 1/getConvFactor());
        int dollars = newTotal / 100;
        int cents = newTotal % 100;
        return new USD(dollars,cents, "USD");
    }

    public double getConvFactor() {
        return convFactor;
    }

    public void setConvFactor(double convFactor) {
        this.convFactor = convFactor;
    }
}
