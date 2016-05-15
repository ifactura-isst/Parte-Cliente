package es.upm.dit.isst;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SistemaTestCase {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://ifactura-conjunto.appspot.com/";
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
  }

  @Test
  public void testPruebaFinalSistema() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("Email")).clear();
    driver.findElement(By.id("Email")).sendKeys("alvaro.glez.mej@gmail.com");
    driver.findElement(By.id("next")).click();
    driver.findElement(By.id("Passwd")).clear();
    driver.findElement(By.id("Passwd")).sendKeys("alvarogon12194");
    driver.findElement(By.id("signIn")).click();
    assertEquals("iFactura-Consumidor", driver.getTitle());
    driver.findElement(By.linkText("Añadir factura")).click();
    driver.findElement(By.linkText("Manualmente")).click();
    assertEquals("Introduce los datos de tu factura", driver.findElement(By.cssSelector("legend")).getText());
    driver.findElement(By.id("municipio")).clear();
    driver.findElement(By.id("municipio")).sendKeys("Seseña");
    new Select(driver.findElement(By.id("provincia"))).selectByVisibleText("Toledo");
    driver.findElement(By.id("empresa")).clear();
    driver.findElement(By.id("empresa")).sendKeys("Orange");
    driver.findElement(By.id("start_date")).clear();
    driver.findElement(By.id("start_date")).sendKeys("12/01/2016");
    driver.findElement(By.id("end_date")).clear();
    driver.findElement(By.id("end_date")).sendKeys("12/02/2016");
    driver.findElement(By.id("cuotas")).clear();
    driver.findElement(By.id("cuotas")).sendKeys("21.95");
    driver.findElement(By.id("consumos")).clear();
    driver.findElement(By.id("consumos")).sendKeys("0.5");
    driver.findElement(By.id("sinImpuestos")).clear();
    driver.findElement(By.id("sinImpuestos")).sendKeys("18.56");
    driver.findElement(By.id("total")).clear();
    driver.findElement(By.id("total")).sendKeys("22.87");
    driver.findElement(By.id("datos")).clear();
    driver.findElement(By.id("datos")).sendKeys("1.5");
    driver.findElement(By.id("minutos")).clear();
    driver.findElement(By.id("minutos")).sendKeys("150");
    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
    driver.findElement(By.linkText("Añadir factura")).click();
    driver.findElement(By.linkText("Escanear PDF")).click();
    driver.findElement(By.id("archivoPDF")).clear();
    driver.findElement(By.id("archivoPDF")).sendKeys("/Users/alvaro/Downloads/factura.pdf");
    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
    driver.findElement(By.cssSelector("button.btn.btn-info")).click();
    driver.findElement(By.cssSelector("button.btn.btn-info")).click();
    driver.findElement(By.linkText("Apuntarse a compra colectiva")).click();
    driver.findElement(By.xpath("(//button[@type='submit'])[4]")).click();
    driver.findElement(By.xpath("(//button[@type='submit'])[3]")).click();
    driver.findElement(By.linkText("Inicio")).click();
    driver.findElement(By.linkText("Logout")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

}
