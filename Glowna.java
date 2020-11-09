public class Glowna {
    static int ilosc_pociagow=10;
    static int ilosc_torow=5;
    static Stacja stacja;
    static int liczba_osob;
    public Glowna(){ }
    public static void main(String[] args) {
        stacja=new Stacja(ilosc_torow, ilosc_pociagow);
        for(int i=0;i<ilosc_pociagow;i++)
            new Pociag(i,2000,stacja,liczba_osob).start();
    }
}