package au.com.corexin.oanda.v2.bo.type;

/**
 * Created by steven on 2017/6/17.
 */
public enum GranularityType {
    S5(5),
    S10(10),
    S15(15),
    S30(30),
    M1(1*60),
    M2(2*60),
    M3(3*60),
    M5(5*60),
    M10(10*60),
    M15(15*60),
    M30(30*60),
    H1(60*60),
    H2(2*60*60),
    H3(3*60*60),
    H4(4*60*60),
    H6(6*60*60),
    H8(8*60*60),
    H12(12*60*60),
    D(24*60*60),
    W(7*24*60*60);
//        M(30*24*60*60); // This is not accurate

    //
    public int seconds;
    private GranularityType(int sec)
    {
        this.seconds = sec;
    }
}
