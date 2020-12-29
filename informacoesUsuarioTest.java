package Testes1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class informacoesUsuarioTest {

    private WebDriver navegador;

    @Before
    public void setUp(){
        //Abrindo o navegador;
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\lucaa\\Downloads\\testando\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Navegando para pagina do Taskit;
        navegador.get("http://www.juliodelima.com.br/taskit");

        //clicar no link que possui o texto "Sign in"
        navegador.findElement(By.linkText("Sign in")).click();

        //identificando o formulario de login;
        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));

        //Digitar no campo de name login que está dentro do formulario de id "signinbox" o texto "julio0001"
        formularioSignInBox.findElement(By.name("login")).sendKeys("julio0001");

        //Digitar no campo de name "password" que está dentro do formulario de id "signinbox" a senha "123456"
        formularioSignInBox.findElement(By.name("password")).sendKeys("123456");

        //Clicar no link com o texto SING IN
        navegador.findElement(By.linkText("SIGN IN")).click();
    }

    //@Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(){

        //Clicar no link que possui a class "me"
        navegador.findElement(By.className("me")).click();

        //Clicar no link que tem o texto "More data about you"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();

        //Clicar no button atraves do seu xpath //button[@data-target="addmoredata"]
        navegador.findElement(By.xpath("//button[@data-target='addmoredata']")).click();

        //Identificar a popup onde está o formulario de id "addmoredata"
        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

        //Na combo de name "type" escolher a opção "Phone"
        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText("Phone");

        //Adicionar o numero no input q tem o name de "contact"
        popupAddMoreData.findElement(By.name("contact")).sendKeys("5583991206898");

        //Clicar no link de text "save" que está na popup
        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        //Na mensagem  com o id "toast-container" validar que o texto é: "Your contact has been added!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals("Your contact has been added!",mensagem);

    }

    @Test
    public void testMudarNomeDoUsuario(){
        //Clica no link da class "me"
        navegador.findElement(By.className("me")).click();

        //Clica no link que tem o texto "ABOUT YOU"
        navegador.findElement(By.linkText("ABOUT YOU")).click();

        //No input de name "name" que tem o id na div pai "aboutyou", digita "Lucas"
        WebElement formularioAboutYou = navegador.findElement(By.id("aboutyou"));

        //Limpando o valor do input
        formularioAboutYou.findElement(By.name("name")).clear();

        //Alterando o valor do input de "Julio" para "Lucas"
        formularioAboutYou.findElement(By.name("name")).sendKeys("Lucas");

        //Clica no button de id "changeAboutYou"
        navegador.findElement(By.xpath("//button[@id='changeAboutYou']")).click();

        // Validar que foi concretizado a auteração do nome do usuario com a frase "now you will be called lucas!"
        //Dentro do id "toast-container"
        WebElement mensagemOk = navegador.findElement(By.id("toast-container"));
        String mensagemVerificada = mensagemOk.getText();
        assertEquals("Now you will be called Lucas!", mensagemVerificada);

    }

    @After
    public void tearDown(){
        //Fechar o navegador
        navegador.close();
    }
}
