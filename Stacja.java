public class Stacja {
    static int STACJA=1;
    static int START=2;
    static int PRZEJAZD=3;
    static int KONIEC_PRZEJAZDU=4;
    static int KOLIZJA=5;
    int ilosc_torow;
    int ilosc_zajetych;
    int ilosc_pociagow;
    Stacja(int ilosc_torow,int ilosc_pociagow){
        this.ilosc_torow=ilosc_torow;
        this.ilosc_pociagow=ilosc_pociagow;
        this.ilosc_zajetych=0;
    }
    synchronized int start(int numer){
        ilosc_zajetych--;
        System.out.println("Pozwolenie na wyjazd pociągu nr. "+numer);
        return START;
    }
    synchronized int laduj(){
        try{
            Thread.currentThread().sleep(1000);
        }
        catch(Exception ie){
        }
        if(ilosc_zajetych<ilosc_torow){
            ilosc_zajetych++;
            System.out.println("Pozwolenie wjazdu na tor  "+ilosc_zajetych);
            return STACJA;
        }
        else
        {return KONIEC_PRZEJAZDU;}
    }
    synchronized void zmniejsz(){
        ilosc_pociagow--;
        System.out.println("DEAD");
        if(ilosc_pociagow==ilosc_torow) System.out.println("Ilosc pociagow taka sama jak torów");
    }
}