import java.util.Random;
public class Pociag extends Thread {
    //definicja stanu pociagu
    static int STACJA=1;
    static int START=2;
    static int PRZEJAZD=3;
    static int KONIEC_PRZEJAZDU=4;
    static int KOLIZJA=5;
    static int DODAJ_WEGIEL=1000;
    static int REZERWA_WEGLA=500;

    //zmienne pomocnicze
    int numer;
    int wegiel;
    int stan;
    int liczba_podrozujaych;
    Stacja l;
    Random rand;
    public Pociag(int numer, int wegiel, Stacja l, int liczba_podrozujaych){
        this.numer=numer;
        this.wegiel=wegiel;
        this.stan=PRZEJAZD;
        this.liczba_podrozujaych = liczba_podrozujaych;
        this.l=l;
        rand=new Random();
    }
    public void run(){
        while(true){
            if(stan==STACJA){
                if(rand.nextInt(2)==1){
                    liczba_podrozujaych = rand.nextInt(100);
                    stan=START;
                    wegiel=DODAJ_WEGIEL;
                    System.out.println("Proszę o pozwolenie wyjazdu ze stacji, pociag nr.  "+numer);
                            stan=l.start(numer);
                }
                else{
                    System.out.println("Nie wyjeżdzam ze stacji");
                }
            }
            else if(stan==START){
                liczba_podrozujaych = rand.nextInt(100);
                System.out.println("Wyjechałem ze stacji, pociąg nr. "+numer + ". Liczba osób w pociągu: " + liczba_podrozujaych);
                stan=PRZEJAZD;

            }
            else if(stan==PRZEJAZD){
                wegiel-=rand.nextInt(500);
                liczba_podrozujaych = rand.nextInt(100);
                System.out.println("Pociąg nr. "+numer+" w drodze" + ". Liczba osób w pociągu: " + liczba_podrozujaych);
                if(wegiel<=REZERWA_WEGLA){
                    stan=KONIEC_PRZEJAZDU;
                }
                else try{
                    sleep(rand.nextInt(1000));
                }
                catch (Exception e){}
            }
            else if(stan==KONIEC_PRZEJAZDU){
                System.out.println("Prosze o pozowolenie na wjazd na stację  "+numer+" ilosc węgla do napędu "+wegiel + ", liczba wysiadających pasażerów: " + liczba_podrozujaych);
                stan=l.laduj();
                if(stan==KONIEC_PRZEJAZDU){
                    wegiel-=rand.nextInt(500);

                    System.out.println("REZERWA "+wegiel);
                    if(wegiel<=0) stan=KOLIZJA;
                }
            }
            else if(stan==KOLIZJA){
                System.out.println("Awaria pociągu nr. "+numer);
                l.zmniejsz();
            }
        }} }