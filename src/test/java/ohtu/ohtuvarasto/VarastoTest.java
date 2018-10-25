package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto, maariteltyVarasto, negatiivisetParametrit, suurempiSaldoKuinTilavuus, varastoSaldolla;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        maariteltyVarasto = new Varasto(10, 5);
        negatiivisetParametrit = new Varasto(-1, -5);
        suurempiSaldoKuinTilavuus = new Varasto(10, 20);
        varastoSaldolla = new Varasto(10, 5.9);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoVarastonOikeallaSaldolla() {
        assertEquals(5, maariteltyVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivisetParametritNollaksi() {
        assertEquals(0, negatiivisetParametrit.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, negatiivisetParametrit.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void suurempiSaldoKuinTilavuus() {
        assertEquals(10, suurempiSaldoKuinTilavuus.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisaysEiMuutaSaldoa() {
        double varastonSaldo = varasto.getSaldo();
        varasto.lisaaVarastoon(-5);
        assertEquals(varastonSaldo, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void tilavuudenYlittavaHukkaan() {
        double varastonTilavuus = varasto.getTilavuus();
        varasto.lisaaVarastoon(varastonTilavuus+10);
        assertEquals(varastonTilavuus, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttoPaluttaaNollan() {
        assertEquals(0, varasto.otaVarastosta(-666), vertailuTarkkuus);
    }
    
    @Test
    public void saldonYlittavanMaaranOttaminen() {
        double odotettuMaara = varastoSaldolla.getSaldo();
        assertEquals(odotettuMaara, varastoSaldolla.otaVarastosta(6.1), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonToString() {
        String odotettu = "saldo = " + varastoSaldolla.getSaldo() + ", vielä tilaa " + varastoSaldolla.paljonkoMahtuu();
        assertEquals(odotettu, varastoSaldolla.toString());
    }
    
    // heh heh
    
}