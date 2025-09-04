package br.com.juliodelima.taskit.signup;

import com.github.javafaker.Faker;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class SignUpTest {
    protected static WebDriver driver;


    private WebDriver navegador = BaseTest.getDriver();
    private Faker faker = new Faker();

    @Before
    public void setUp() {
        String caminhoChrome = "C:/Program Files/Google/Chrome/Application/chrome_123.exe";
        File chromeFile = new File(caminhoChrome);
        if (!chromeFile.exists()) {
            throw new RuntimeException("Chrome personalizado não encontrado no caminho: " + caminhoChrome);
        }

        WebDriverManager.chromedriver().browserBinary(caminhoChrome).setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary(caminhoChrome);
        options.addArguments("--start-maximized");

        navegador = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        if (navegador != null) {
            navegador.quit();
            navegador = null; // Libera para o próximo teste
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

    @E("preencha o campo com seu  e-mail invalido {string}")
    public void preenchaocampocomseuemailinvalido(String id) {
        navegador.findElement(By.id("email")).sendKeys(id);
    }

    @E("preencha o campo com seu valido e-mail {string}")
    public void preenchaocampocomseuvalidoemail(String id) {
        navegador.findElement(By.id("email")).sendKeys(id);
    }

    @E("preencha o campo de email com um email válido")
    public void preenchaOCampoEmailComEmailValido() {
        String emailFaker = faker.internet().emailAddress();
        System.out.println("Email gerado pelo Faker: " + emailFaker);
        navegador.findElement(By.id("email")).sendKeys(emailFaker);
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

        Assert.assertTrue(validationMessage.toLowerCase().contains("@"));
    }

    @Então("deve ser redirecionado para a página principal")
    public void verificarRedirecionamento() {
        String urlAtual = navegador.getCurrentUrl();
        assert urlAtual.contains("https://front.serverest.dev/home");
        WebDriverWait wait = new WebDriverWait(navegador, Duration.ofSeconds(20));
    }

    @Então("deve exibir a mensagem de email e ou senha invalidos")
    public void deveExibirMensagemEmailOuSenhaInvalidos() {
        WebDriverWait wait = new WebDriverWait(navegador, Duration.ofSeconds(10));

        WebElement mensagem = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.alert.alert-secondary.alert-dismissible > span")
        ));

        String texto = mensagem.getText();
        Assertions.assertEquals("Email e/ou senha inválidos", texto);
    }
}