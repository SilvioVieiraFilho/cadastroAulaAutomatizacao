package br.com.juliodelima.taskit.signup;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class SignUpTest {
    private WebDriver navegador;

    @Before
    public void setUp() {
        // 🛠 Caminho personalizado do Chrome renomeado como google_1232.exe
        String caminhoChrome = "C:/Program Files/Google/Chrome/Application/chrome_123.exe";
        File chromeFile = new File(caminhoChrome);
        if (!chromeFile.exists()) {
            throw new RuntimeException("Chrome personalizado não encontrado no caminho: " + caminhoChrome);
        }

        // 📦 Configurando o WebDriverManager com o executável personalizado
        WebDriverManager.chromedriver()
                .browserBinary(caminhoChrome)
                .setup();

        // ⚙️ Configura opções para o Chrome
        ChromeOptions options = new ChromeOptions();
        options.setBinary(caminhoChrome);
        options.addArguments("--start-maximized");

        navegador = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        if (navegador != null) {
            navegador.quit();
        }
    }

    @Dado("que o navegador esteja aberto")
    public void queONavegadorEstaAberto() {
        // Navegador já está aberto no Before
    }

    @E("o cliente acessa a URL {string}")
    public void oClienteAcessaAURL(String url) {
        navegador.get(url);
    }

    @E("preencha o campo digite seu e-mail incorreto {string}")
    public void preenchaocampodigiteseuemailincorreto(String id) {
        navegador.findElement(By.id("email")).sendKeys(id);
    }

    @E("preencha o campo senha {string}")
    public void preencheOCampoSenha(String id) {

        navegador.findElement(By.id("password")).sendKeys(id);
    }

    @Quando("clicar no botão entrar {string}")
    public void clicarNoBotãoEntrar(String botaoClasse) {
        navegador.findElement(By.className(botaoClasse)).click();
    }

    @Então("tera que apresentar uma mensagem comunicando que precisa ser um e-mail")
    public void teraqueapresentarumamensagemcomunicandoqueprecisaserumemail() {
        WebElement emailInput = navegador.findElement(By.id("email"));

        String validationMessage = (String) ((JavascriptExecutor) navegador)
                .executeScript("return arguments[0].validationMessage;", emailInput);

        System.out.println("Mensagem: " + validationMessage);

        // Dica: para teste mais robusto, verifique que a mensagem contém algo como "@"
        Assert.assertTrue(validationMessage.toLowerCase().contains("@"));
    }


}
