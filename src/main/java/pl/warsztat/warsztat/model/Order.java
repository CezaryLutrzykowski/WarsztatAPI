package pl.warsztat.warsztat.model;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45)
    private String token;

    @Column(length = 45)
    private String Samochod;

    @Column(length = 45)
    private Long Przebieg;

    @Column(length = 45)
    private Long PrzebiegPrzyWymianieOleju;

    @Column(length = 45)
    private Long NastepnaWymianaOleju;

    @Column(length = 45)
    private Long RokProdukcji;

    @Column(length = 45)
    private String Silnik;

    @Column(length = 500)
    private String JakieCzesciWymieniane;

    @Column(length = 45)
    private String StanNaprawy;

    @Column(length = 45)
    private Long CenaCzesci;

    @Column(length = 45)
    private Long CenaRobocizna;

    public Order() {

    }

    public Order(String token) {
        Random random = new Random();
        this.token = random.nextInt(999999) + token;
    }


    public Order(String token, String samochod, Long przebieg, Long przebiegPrzyWymianieOleju, Long nastepnaWymianaOleju, Long rokProdukcji, String silnik,String JakieCzesciWymieniane ,String StanNaprawy ,Long CenaCzesci, Long CenaRobocizna) {
        //this.id = id;
        Random random = new Random();
        this.token = random.nextInt(999999) + token;
        this.Samochod = samochod;
        this.Przebieg = przebieg;
        this.PrzebiegPrzyWymianieOleju = przebiegPrzyWymianieOleju;
        this.NastepnaWymianaOleju = nastepnaWymianaOleju;
        this.RokProdukcji = rokProdukcji;
        this.Silnik = silnik;
        this.JakieCzesciWymieniane = JakieCzesciWymieniane;
        this.StanNaprawy = StanNaprawy;
        this.CenaCzesci=CenaCzesci;
        this.CenaRobocizna = CenaRobocizna;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSamochod() {
        return Samochod;
    }

    public void setSamochod(String samochod) {
        Samochod = samochod;
    }

    public Long getPrzebieg() {
        return Przebieg;
    }

    public void setPrzebieg(Long przebieg) {
        Przebieg = przebieg;
    }

    public Long getPrzebiegPrzyWymianieOleju() {
        return PrzebiegPrzyWymianieOleju;
    }

    public void setPrzebiegPrzyWymianieOleju(Long przebiegPrzyWymianieOleju) {
        PrzebiegPrzyWymianieOleju = przebiegPrzyWymianieOleju;
    }

    public Long getNastepnaWymianaOleju() {
        return NastepnaWymianaOleju;
    }

    public void setNastepnaWymianaOleju(Long nastepnaWymianaOleju) {
        NastepnaWymianaOleju = nastepnaWymianaOleju;
    }

    public Long getRokProdukcji() {
        return RokProdukcji;
    }

    public void setRokProdukcji(Long rokProdukcji) {
        RokProdukcji = rokProdukcji;
    }

    public String getSilnik() {
        return Silnik;
    }

    public void setSilnik(String silnik) {
        Silnik = silnik;
    }

    public String getJakieCzesciWymieniane() {
        return JakieCzesciWymieniane;
    }

    public void setJakieCzesciWymieniane(String jakieCzesciWymieniane) {
        JakieCzesciWymieniane = jakieCzesciWymieniane;
    }

    public String getStanNaprawy() {
        return StanNaprawy;
    }

    public void setStanNaprawy(String stanNaprawy) {
        StanNaprawy = stanNaprawy;
    }

    public Long getCenaCzesci() {
        return CenaCzesci;
    }

    public void setCenaCzesci(Long cenaCzesci) {
        CenaCzesci = cenaCzesci;
    }

    public Long getCenaRobocizna() {
        return CenaRobocizna;
    }

    public void setCenaRobocizna(Long cenaRobocizna) {
        CenaRobocizna = cenaRobocizna;
    }

    @Override
    public String toString() {
        return this.token;
    }
}
